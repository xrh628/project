package common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;



import entity.Book;
import entity.Page;
import entity.User;
import service.BookService;
import service.UserService;

@Component
public class Test {
public static void main(String[]args) {
	
	BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	BookService bookService = (BookService) factory.getBean("bookService");


	 



}

}
