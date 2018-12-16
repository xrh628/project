package mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import entity.Book;

@Repository
public interface BookMapper {
	List<Book> findByPage(Map map);

	Integer findCount(Book book);
   
	List<Book> findByUserId(Integer id);

	double findPriceSum2(Integer id);

	Book selectById(Integer id);

	void updateSum(Book book);
	
	List<Integer> selectOrderId(Map map);
}
