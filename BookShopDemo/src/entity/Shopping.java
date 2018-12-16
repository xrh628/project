package entity;

public class Shopping {
	Integer shoppingId;
	Integer userId;
	Integer bookNum;
	Book book;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public  Integer getBookNum() {
		return bookNum;
	}
	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}
	
}
