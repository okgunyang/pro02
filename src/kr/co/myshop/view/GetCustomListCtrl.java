package kr.co.myshop.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.myshop.vo.Custom;
import kr.co.myshop.vo.Notice;

@WebServlet("/GetCustomListCtrl")
public class GetCustomListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "select * from custom order by regdate desc";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			//결과를 데이터베이스로 부터 받아서 리스트로 저장
			List<Custom> cusList = new ArrayList<Custom>();
			while(rs.next()){
				Custom vo = new Custom();
				vo.setCusId(rs.getString("cusid"));
				vo.setCusPw(rs.getString("cuspw"));
				vo.setCusName(rs.getString("cusname"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setRegDate(rs.getString("regdate"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisited(rs.getInt("visited"));
				cusList.add(vo);
			}
			request.setAttribute("cusList", cusList);
			
			//notice/boardList.jsp 에 포워딩
			RequestDispatcher view = request.getRequestDispatcher("./admin/customList.jsp");
			view.forward(request, response);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
