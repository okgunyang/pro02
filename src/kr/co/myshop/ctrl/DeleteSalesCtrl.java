package kr.co.myshop.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.myshop.vo.Notice;

@WebServlet("/DeleteSalesCtrl")
public class DeleteSalesCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	int parselNo = 0;
	int salePayNo = 0;
	int amount = 0;
	int proNo = 0; 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ResultSet rs = null;
		int saleNo = Integer.parseInt(request.getParameter("saleNo"));
		System.out.println("지워질 구매내역 : "+saleNo);
		String sql = "";
		int cnt = 0;
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			
			con.setAutoCommit(false);
			sql = "select * from sales where saleno=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, saleNo);
			rs = pstmt.executeQuery();

			if(rs.next()){
				parselNo = rs.getInt("parselno");
				salePayNo = rs.getInt("salepayno");
				amount = rs.getInt("amount");
				proNo = rs.getInt("prono");
				
				System.out.println("parselNo : "+parselNo);
				System.out.println("salePayNo : "+salePayNo);
				System.out.println("amount : "+amount);
				System.out.println("proNo : "+proNo);
				
				sql = "delete from parsel where parselno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, parselNo);
				pstmt.executeUpdate();
				
				sql = "delete from payment where salepayno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, salePayNo);
				pstmt.executeUpdate();
				
				sql = "update wearing set amount=amount+? where prono=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, amount);
				pstmt.setInt(2, proNo);
				pstmt.executeUpdate();
				
				sql = "delete from sales where saleno=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, saleNo);
				cnt = pstmt.executeUpdate();
				
				con.commit();
			}

			con.setAutoCommit(true);
			if(cnt>=1){
				response.sendRedirect("GetMemberSalesInfoCtrl");
			} else {
				response.sendRedirect("GetSalesDetailCtrl?saleNo="+saleNo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
}
