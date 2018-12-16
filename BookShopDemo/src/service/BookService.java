package service;

import java.util.List;
import java.util.Map;

import entity.Book;
import entity.Page;

public interface BookService {

	Page findByPage(Book book, Integer pageNum);
	
	 public void updateSum(Book book);
	 
	 public Book selectById(Integer id);
	 
	 public List<Integer> selectOrderId(Map map);
}