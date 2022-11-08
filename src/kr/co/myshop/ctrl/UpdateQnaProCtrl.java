package kr.co.myshop.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.myshop.vo.Qna;

@WebServlet("/UpdateQnaProCtrl.do")
public class UpdateQnaProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		String sec = request.getParameter("sec");
		int lev = Integer.parseInt(request.getParameter("lev"));
		String sql = "";
		int cnt = 0;
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "update qnaa set title=?, content=?, author=?, sec=?, lev=? where no=?";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title); 
			pstmt.setString(2, content);
			pstmt.setString(3, author);
			pstmt.setString(4, sec);
			pstmt.setInt(5, lev);
			pstmt.setInt(6, no);
			cnt = pstmt.executeUpdate();
			
			if(cnt>=1){
				response.sendRedirect("GetQnaListCtrl.do");
			} else {
				response.sendRedirect("GetQnaDetailCtrl.do?no="+no);
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
