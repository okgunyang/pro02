package kr.co.myshop.view;

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

import kr.co.myshop.vo.Qna;

@WebServlet("/GetQnaDetailCtrl.do")
public class GetQnaDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "select * from qnaa where no=?";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			
			con.setAutoCommit(false);	//트랜잭션 처리시에는 같이 처리될 수 있도록 오토커밋을 꺼야함
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			
			//결과를 데이터베이스로 부터 받아서 VO에 저장
			Qna vo = new Qna();
			if(rs.next()){
				//해당 글이 있는 경우에만 읽은 횟수를 1씩 증가 시킴
				sql = "update qnaa set visited=visited+1 where no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
				con.commit();	//지금까지 진행했던 내용들이 모두 같이 성공수행될수 있도록 수동커밋을 함
				con.setAutoCommit(true);	//다음 sql 실행 구문을 위해 다시 오토커밋을 켜놓음
				vo.setNo(rs.getInt("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAuthor(rs.getString("author"));
				vo.setResDate(rs.getDate("resdate"));
				vo.setLev(rs.getInt("lev"));
				vo.setParno(rs.getInt("parno"));
				vo.setSec(rs.getString("sec"));
				vo.setVisited(rs.getInt("visited"));
			}
			request.setAttribute("qna", vo);
			
			//qna/qnaDetail.jsp 에 포워딩
			RequestDispatcher view = request.getRequestDispatcher("./qna/qnaDetail.jsp");
			view.forward(request, response);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
