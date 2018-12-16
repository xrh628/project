package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Book;

import entity.Order;
import entity.Page;
import entity.Shopping;
import entity.User;
import service.BookService;
import service.BookServiceImpl;
import service.OrderService;
import service.ShoppingService;
import service.UserService;

@Controller
public class BookController {
	@Resource
	BookService bookService;
	@Resource
	UserService userService;
	@Resource
	ShoppingService shoppingService;
	@Resource
	OrderService orderService; 

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public String index(Model model) {
		Page page = bookService.findByPage(new Book(), 1);
		model.addAttribute("page", page);
		model.addAttribute("flag",1);
		return "index";
	}

	@RequestMapping(value = "index.do",method = RequestMethod.POST)
	public String index(Model model, Book book, @RequestParam("pageNum") int pageNum) {
		Page page = bookService.findByPage(book, pageNum);
		model.addAttribute("page", page);
		model.addAttribute("book", book);
		model.addAttribute("flag",1);
		return "index";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	 @ResponseBody
	public String login(@RequestParam String un, @RequestParam String pw, HttpSession session
		) {
		User user = userService.findUser(un, pw);
		if (user != null) {
			session.setAttribute("loginUser", user);
			return "ok";
		} else {
		return "error";
		}
	}

	@RequestMapping(value = "zhuxiao.do", method = RequestMethod.GET)
	public String zhuxiao(HttpSession session) {
		session.invalidate();
		return "redirect:index.do";
	}
	
	@RequestMapping(value = "insert.do", method = RequestMethod.POST)
	public String insert(User user) {
		userService.addUser(user);
		return "register_success";
	}

	@RequestMapping(value = "fgwc.do", method = RequestMethod.POST)
	public String fgwc(HttpSession session, HttpServletRequest request,Model model,Book book,
			@RequestParam("pageNum") int pageNum) {
		User user = (User) session.getAttribute("loginUser");
		int userId = user.getUserId();
		  List<String> bookIds=(List<String>)session.getAttribute("bookIds");
		  if(bookIds !=null && bookIds.size() !=0) {
			  for(String bookId1:bookIds) {
				  int bookId=Integer.parseInt(bookId1);
				  if(shoppingService.sfcz(userId, bookId)==null) {
				  Shopping sp=new Shopping();
				  Book book2=new Book();
				  book2.setBookId(bookId);
				  sp.setBook(book2);
				  sp.setUserId(userId);
				  shoppingService.insertGwc(sp);
			  }else {
				  shoppingService.updateGwc2(userId, bookId);
			  }
			  }
			  session.removeAttribute("bookIds");
		  }
		
		String[] list = request.getParameterValues("bookId");
		if(list==null) {
			System.out.println("list==null");
		}
		if (list != null) {
			for (String m : list) {
				if(bookIds==null || (bookIds !=null && bookIds.contains(m)==false)) {
				Shopping sp = new Shopping();
				int bookId= Integer.parseInt(m);
				if(shoppingService.sfcz(userId, bookId)==null) {
					Book book2=new Book();
					book2.setBookId(bookId);
				sp.setBook(book2);
				sp.setUserId(userId);
				shoppingService.insertGwc(sp);
				}else {
					shoppingService.updateGwc2(userId, bookId);
				}
			}
			}
		}
		
		Page page = bookService.findByPage(book, pageNum);
		model.addAttribute("page", page);
		return "index";
	}
   
       @RequestMapping(value="fgwc2.do",method=RequestMethod.GET)
       public String fgwc2(HttpSession session,Model model) {
    	   User user= (User)session.getAttribute("loginUser");
    	   if(user==null) {
    		   model.addAttribute("url2","fgwc2.do");
    		   return "login";
    	   }else {
    	   int id=user.getUserId();
    	  List<Shopping>  list=shoppingService.findByUserId(id);
    	   if(list != null && list.size() !=0) {
    		double pS=shoppingService.findPriceSum(id);
    	    model.addAttribute("pS",pS);
    	   session.setAttribute("list", list);
    	   model.addAttribute("flag",3);
    	   return "shopping";
    	    }else {
    	    	session.removeAttribute("list");
    	    	 model.addAttribute("flag",3);
    	    	   return "shopping";
    	    }
    	   }
    	  
       }
           
       @RequestMapping(value="fgwc3.do",method=RequestMethod.POST)
       public String fgwc3(HttpSession session,Model model ,HttpServletRequest request) {
    	   List <Shopping> list=(List<Shopping>) session.getAttribute("list");
    	   String[] num = request.getParameterValues("bookNum");
    	   User user= (User)session.getAttribute("loginUser");
    	   int id=user.getUserId();
    	   int j=0;
    		for (Shopping m : list) {
				if(num !=null) {
				for(int i=0;i<num.length;i++) {
					if(i==j) {
						int bookNum=Integer.parseInt(num[i]);
						if(bookNum>m.getBook().getBookSum()){
					m.setBookNum(m.getBook().getBookSum());}else {
						m.setBookNum(bookNum);
					}
						shoppingService.updateGwc(m);	
				}
				}
				}
				j++;
			}
    		 shoppingService.deletejl2(id);
    		   List<Shopping> list2=shoppingService.findByUserId(id);
    		   if(list2 != null && list2.size() != 0) {
        	   double pS=shoppingService.findPriceSum(id);
        	   model.addAttribute("list",list2);
        	   model.addAttribute("pS",pS);
        	   model.addAttribute("flag",3);}
    		   session.removeAttribute("list");
        	   return "shopping";
       }
       

       @RequestMapping(value="scdd.do",method=RequestMethod.POST)
       public String scdd(HttpSession session) throws Exception {
    	   User user=(User)session.getAttribute("loginUser");
    	   int id=user.getUserId();
    	   List<Shopping>list=null;
    	list=shoppingService.findByUserId(id);
    	if(list !=null && list.size() !=0) {
 	   orderService.addOrder(id);
 	   return "shopping-success";
    	}
    	session.removeAttribute("list");
    	return "shopping";
       }
       
       @RequestMapping(value="xsdd.do",method=RequestMethod.GET)
       public String xsdd(HttpSession session,Model model,@RequestParam("flag")String flag) {
    	   User user=(User)session.getAttribute("loginUser");
    	   if(user==null) {
    		   model.addAttribute("url2", "xsdd.do?flag=find");
    		   return "login";
    	   }
    	   int id=user.getUserId();
    	   Page page=orderService.findByPage(id, 1,flag,null);
    	  model.addAttribute("page",page);
    	  model.addAttribute("flag", 2);
    	   return "orderlist";
       }
       
       @RequestMapping(value="xsdd.do",method=RequestMethod.POST)
       public String xsdd(HttpSession session,Model model,@RequestParam("pageNum") int pageNum,@RequestParam("bookName") String bookName, 
    		   @RequestParam("flag")String flag) {
    	   User user=(User)session.getAttribute("loginUser");
    	   int id=user.getUserId();
    	   Page page;
    	   if(bookName.equals("")) {
    		   if(flag.equals("find1")){
    			   page=orderService.findByPage(id, pageNum, "find1", null);
    		   }
    		   else if(flag.equals("find2")) {
    			   page=orderService.findByPage(id, pageNum, "find2", null);
    		   }else {
    	    page=orderService.findByPage(id, pageNum,"find",null);}}else {
        	    page=orderService.findByPage(id, pageNum,"find3",bookName);
        	    model.addAttribute("bookName", bookName);}
    	   
		model.addAttribute("page", page);
    	   model.addAttribute("flag",2);
    	   return "orderlist";
       }
       @RequestMapping(value="zhuce.do",method=RequestMethod.GET)
       public String zhce(Model model) {
    	   model.addAttribute("flag", 4);
    	   return "register";
       }
       @RequestMapping(value="qsdd.do",method=RequestMethod.GET)
       public String qsdd(Order order,Model model,@RequestParam("pageNum") int pageNum,HttpSession session) {
    	   User user=(User)session.getAttribute("loginUser");
    	   int userId=user.getUserId();
    	   order.setUser(user);
    	   int id=order.getOrderId();
    	   orderService.updateState(id);
    	   Page page=orderService.findByPage(userId, pageNum,"find",null);
    	   model.addAttribute("page", page);
    	   model.addAttribute("flag", 2);
    	   return "orderlist";
       }
       
       @RequestMapping(value="IM.do",method=RequestMethod.GET)
       public String im(Model model,HttpSession session,int pageNum) {
    	   User user=(User)session.getAttribute("loginUser");
    	   int id=user.getUserId();
    	   Page page=orderService.findByPage(id, pageNum,"find2",null);
    	   model.addAttribute("page",page);
    	   model.addAttribute("flag", 2);
    	   return "orderlist";
       }
       
       @RequestMapping(value="BM.do",method=RequestMethod.GET)
       public String bm(Model model,HttpSession session,int pageNum) {
    	   User user=(User)session.getAttribute("loginUser");
    	   int id=user.getUserId();
    	   Page page=orderService.findByPage(id, pageNum,"find1",null);
    	   model.addAttribute("page",page);
    	   model.addAttribute("flag", 2);
    	   return "orderlist";
       }

       @RequestMapping(value="bmBook.do",method=RequestMethod.POST)
       @ResponseBody
       public void bmBook(@RequestParam("bookId")String bookId,HttpSession session) {
    	   List<String> bookIds=(List<String>)session.getAttribute("bookIds");
    	   if(bookIds != null && bookIds.size() != 0) {
    		   for(String id:bookIds) {
    			   if(id.equals(bookId)) {
    				   bookIds.remove(id);
    				   session.setAttribute("bookIds", bookIds);
    			   }
    		   }
    	   }
       }
       
       @RequestMapping(value="addBook2.do",method=RequestMethod.GET)
       @ResponseBody
       public void addBook2(@RequestParam("bookId")String bookId,HttpSession session) {
    	   List<String> bookIds=(List<String>)session.getAttribute("bookIds");
    	   if(bookIds==null) {
    		   bookIds=new ArrayList<String>();
    	   }
    	   bookIds.add(bookId);
    	   session.setAttribute("bookIds", bookIds);
       }
}
