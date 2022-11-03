package kr.co.myshop.app;

public class D {
	public final static String NOTICE_SELECT_ALL = "select * from notice order by notino";
	public final static String NOTICE_SELECT_BY_NOTINO = "select * from notice where notino=?";
	public final static String NOTICE_INSERT = "insert into notice(title, content, author) values (?,?,?)";
	public final static String NOTICE_UPDATE = "update notice set title=?, content=? where notino=?";
	public final static String NOTICE_DELETE = "delete from notice where notino=?";	
}