package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import entity.Shopping;
import mapper.ShoppingMapper;
@Service
public class ShoppingService {
	ShoppingMapper shoppingMapper;
	public void setShoppingMapper(ShoppingMapper shoppingMapper) {
		this.shoppingMapper = shoppingMapper;
	}
	public Integer sfcz(Integer userId,Integer bookId) {
		Integer id=shoppingMapper.sfcz(userId, bookId);
		return id;
	}
	public void insertGwc(Shopping sp) {
		shoppingMapper.insertGwc(sp);
	}
	public void deletejl(Integer id) {
		shoppingMapper.deletejl(id);
	}
	public void deletejl2(Integer id) {
		shoppingMapper.deletejl2(id);
	}
	public void insertGwc2(Shopping sp) {
		shoppingMapper.insertGwc2(sp);
	}
	public void updateGwc(Shopping sp) {
		shoppingMapper.updateGwc(sp);
	}
	
	public void updateGwc2(Integer userId,Integer bookId) {
		shoppingMapper.updateGwc2(userId, bookId);
	}
	
	public List<Shopping> findByUserId(int userId){
		
		 List<Shopping> list=shoppingMapper.findByUserId(userId);
		 return list;
		
	}
	
	public double findPriceSum(int userId) {
		double sumPrice=shoppingMapper.findPriceSum2(userId);
		return sumPrice;
	}
	

}
