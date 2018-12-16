package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Shopping;

public interface ShoppingMapper {
	List<Shopping> findByUserId(Integer userId);
	void insertGwc(Shopping sp);
	void deletejl(Integer id);
	void insertGwc2(Shopping sp);
	void updateGwc(Shopping sp);
	Integer sfcz(@Param("userId")Integer userId,@Param("bookId")Integer bookId);
	void updateGwc2(@Param("userId")Integer userId,@Param("bookId")Integer bookId);
	double findPriceSum2(Integer userId) ;
	void deletejl2(Integer id);
}
