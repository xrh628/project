package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.Book;
import entity.Page;
import mapper.BookMapper;
@Service
public class BookServiceImpl implements BookService {

BookMapper bookMapper;
public void setBookMapper(BookMapper bookMapper) {
	this.bookMapper = bookMapper;
}
/* (non-Javadoc)
 * @see service.BookService#findByPage(entity.Book, java.lang.Integer)
 */
@Override
public Page findByPage(Book book,Integer pageNum) {
	Map map=new HashMap();
	map.put("book", book);
	map.put("from", (pageNum-1)*3);
	List<Book> books=bookMapper.findByPage(map);
	Integer count=null;
	 count=bookMapper.findCount(book);
	 if(count != null) {
	int totalPage=count%3==0?count/3:count/3+1;
	Page page=new Page();
	page.setCount(count);
	page.setList(books);
	page.setPageNum(pageNum);
	page.setTotalPage(totalPage);
	return page;}else {
		Page page=new Page();
		return page;
	}
}

 
 public void updateSum(Book book) {
	 bookMapper.updateSum(book);
 }
 
  public Book selectById(Integer id) {
	 Book book= bookMapper.selectById(id);
	  return book;
  }
  
@Override
public List<Integer> selectOrderId(Map map) {
	List<Integer> orderIds=bookMapper.selectOrderId(map);
	return orderIds;
}

}
