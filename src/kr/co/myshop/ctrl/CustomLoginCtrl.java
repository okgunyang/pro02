package kr.co.myshop.ctrl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crypto.util.SHA256;

@WebServlet("/CustomLoginCtrl")
public class CustomLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String cusId = request.getParameter("cusId");
		String cus = request.getParameter("cusPw");
		String cusPw = "";
		try {
			cusPw = SHA256.encrypt(cus);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "select * from custom where cusid=? and cuspw=?";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = null;
			pstmt.setString(1, cusId);
			pstmt.setString(2, cusPw);
			rs = pstmt.executeQuery();
			HttpSession session = request.getSession();
			
			if(rs.next()){
				//세션을 발생시키고, 인덱스로 이동
				session.setAttribute("sid", cusId);
				session.setAttribute("sname", rs.getString("cusname"));
				response.sendRedirect("index.jsp");
			} else {
				//로그인 페이지로 이동
				response.sendRedirect("./custom/login.jsp");
			}

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
