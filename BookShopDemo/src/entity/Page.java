package entity;

import java.util.List;

public class Page {
Integer pageNum; //当前页码
Integer count; //满足条件的总记录数
Integer totalPage; //总页数
String flag;
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
List list; //展示的集合
public Integer getPageNum() {
	return pageNum;
}
public void setPageNum(Integer pageNum) {
	this.pageNum = pageNum;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
public Integer getTotalPage() {
	return totalPage;
}
public void setTotalPage(Integer totalPage) {
	this.totalPage = totalPage;
}
public List getList() {
	return list;
}
public void setList(List list) {
	this.list = list;
}

}
