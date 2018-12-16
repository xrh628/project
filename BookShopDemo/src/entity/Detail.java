package entity;

public class Detail {
Integer detailId;
Integer orderId;
Book book;
Integer bookSum;
public Integer getDetailId() {
	return detailId;
}
public void setDetailId(Integer detailId) {
	this.detailId = detailId;
}
public Integer getOrderId() {
	return orderId;
}
public void setOrderId(Integer orderId) {
	this.orderId = orderId;
}


public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
public Integer getBookSum() {
	return bookSum;
}
public void setBookSum(Integer bookSum) {
	this.bookSum = bookSum;
}

}
