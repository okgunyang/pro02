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

@WebServlet("/UpdateBoardProCtrl")
public class UpdateBoardProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int notiNo = Integer.parseInt(request.getParameter("notiNo"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String sql = "";
		int cnt = 0;
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "update notice set title=?, content=? where notino=?";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, notiNo);
			cnt = pstmt.executeUpdate();
			
			if(cnt>=1){
				response.sendRedirect("GetBoardListCtrl");
			} else {
				response.sendRedirect("GetBoardDetailCtrl?notiNo="+notiNo);
			}

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
}
