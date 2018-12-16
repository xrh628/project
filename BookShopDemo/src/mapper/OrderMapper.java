package mapper;

import java.util.List;
import java.util.Map;

import entity.Order;

public interface OrderMapper {
	List<Order> selectAllOrder(Integer id);

	void addOrder(Order order);

	List<Order> findAllOrder(Integer id);

	List<Order> findByPage(Map map);

	Integer findCount(Map map);

	void updateState(Integer id);

	List<Order> findByPage3(Map map);

	Integer findCount3(Integer id);

	List<Order> findByPage1(Map map);

	Integer findCount1(Integer id);

	Integer selectOrderId();


}
