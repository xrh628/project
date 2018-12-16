package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import entity.Book;
import entity.Detail;
import entity.Order;
import entity.Page;
import entity.Shopping;
import entity.User;
import mapper.DetailMapper;
import mapper.OrderMapper;

@Service
public class OrderService {
	OrderMapper orderMapper;
	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}
    @Resource
    DetailService detailService;
   @Resource
   ShoppingService shoppingService;
	@Resource
	BookService bookService;
public List<Order> selectAllOrder(Integer id){
	List<Order> orders= orderMapper.selectAllOrder(id);
	return orders;
}

public  void addOrder(Integer id) {
	Order order=new Order();
	User user=new User();
	user.setUserId(id);
	order.setUser(user);
	order.setSumPrice(shoppingService.findPriceSum(id));
	orderMapper.addOrder(order);
	List<Shopping> shoppings= shoppingService.findByUserId(id);
	for(Shopping shopping:shoppings) {
		Book book=shopping.getBook();
		Integer kucun=book.getBookSum()-shopping.getBookNum();
		book.setBookSum(kucun);
		bookService.updateSum(book);
		Detail detail=new Detail();
		detail.setOrderId(order.getOrderId());
		detail.setBook(book);
		detail.setBookSum(shopping.getBookNum());
		detailService.addDetail(detail);
		shoppingService.deletejl(id);
	}
	
}

public List<Order> findAllOrder(Integer id){
	List<Order> orders =orderMapper.findAllOrder(id);
	return orders;
}

 public void updateState(Integer id) {
	orderMapper.updateState(id);
}
 
public Page findByPage(Integer id,Integer pageNum,String flag ,String bookName) {
	
	Map map=new HashMap();
	map.put("userId", id);
	if(flag=="find3" && (! bookName .equals(""))) {
		map.put("bookName", bookName);
		List<Integer> orderId=bookService.selectOrderId(map);
		String orderIds = orderId.toString();
		orderIds = orderIds.substring(1,orderIds.length()-1);
		orderIds=orderIds.replaceAll(" ", "");
		map.put("orderIds", orderIds);
	}
	map.put("flag", flag);
	
	map.put("from", (pageNum-1)*3);
	List<Order> orders=orderMapper.findByPage(map);
	Integer count=null;
	 count=orderMapper.findCount(map);
	 if(count != null) {
	int totalPage=count%3==0?count/3:count/3+1;
	Page page=new Page();
	page.setCount(count);
	page.setList(orders);
	page.setPageNum(pageNum);
	page.setTotalPage(totalPage);
	page.setFlag(flag);
	return page;}else {
		Page page=new Page();
		return page;
	}
}






}
