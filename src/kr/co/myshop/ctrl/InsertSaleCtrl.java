package kr.co.myshop.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertSaleCtrl")
public class InsertSaleCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";
	int cnt = 0;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		String cusId = request.getParameter("cusId");
		String proName = request.getParameter("proName");
		int proPrice = Integer.parseInt(request.getParameter("proPrice"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String parselAddr = request.getParameter("address1")+request.getParameter("address2");
		String cusTel = request.getParameter("parselTel");
		
		String payMethod = request.getParameter("payMethod");
		String payCom = request.getParameter("payCom");
		String cardNum = request.getParameter("cardNum");
		int payAmount =	Integer.parseInt(request.getParameter("payAmount"));
		ResultSet rs = null;
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			
			sql = "insert into payment(paymethod, paycom, cardnum, payamount) values (?,?,?,?)";
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, payMethod);
			pstmt.setString(2, payCom);
			pstmt.setString(3, cardNum);
			pstmt.setInt(4, payAmount);
			pstmt.executeUpdate();
			
			sql = "insert into parsel(parseladdr, cusTel) values (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, parselAddr);
			pstmt.setString(2, cusTel);
			pstmt.executeUpdate();
			con.commit();
			
			sql = "select salepayno from payment order by salepayno desc limit 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			int salePayNo = 0;
			if(rs.next()){
				salePayNo = rs.getInt("salepayno");
			}
			con.commit();
			con.setAutoCommit(true);
			rs.close();
			pstmt.close();
			
			sql = "select parselno from parsel order by parselno desc limit 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int parselNo = 0;
			if(rs.next()){
				parselNo = rs.getInt("parselno");
			}
			rs.close();
			pstmt.close();
			
			sql = "insert into sales(cusid, prono, amount, parselno, salepayno) values(?,?,?,?,?)";
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cusId);
			pstmt.setInt(2, proNo);
			pstmt.setInt(3, amount);
			pstmt.setInt(4, parselNo);
			pstmt.setInt(5, salePayNo);
			cnt = pstmt.executeUpdate();
			
			sql = "update wearing set amount=amount-? where prono=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, proNo);
			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			
			if(cnt>=1){
				response.sendRedirect("GetProductListCtrl");
			} else {
				response.sendRedirect("GetProductDetailCtrl?proNo="+proNo);
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}