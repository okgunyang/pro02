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

import kr.co.myshop.vo.Notice;
import kr.co.myshop.vo.Product;

@WebServlet("/GetProductItemListCtrl")
public class GetProductItemListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateNo = Integer.parseInt(request.getParameter("cateNo"));
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "select * from product where cateno=? order by prono";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cateNo);
			ResultSet rs = pstmt.executeQuery();
			
			//결과를 데이터베이스로 부터 받아서 리스트로 저장
			List<Product> proList = new ArrayList<Product>();
			while(rs.next()){
				Product vo = new Product();
				vo.setProNo(rs.getInt("prono"));
				vo.setCateNo(rs.getInt("cateno"));
				vo.setProName(rs.getString("proname"));
				vo.setProSpec(rs.getString("prospec"));
				vo.setOriPrice(rs.getInt("oriprice"));
				vo.setDiscountRate(rs.getDouble("discountrate"));
				vo.setProPic(rs.getString("propic"));
				vo.setProPic(rs.getString("propic2"));
				proList.add(vo);
			}
			request.setAttribute("proList", proList);
			
			//notice/boardList.jsp 에 포워딩
			RequestDispatcher view = request.getRequestDispatcher("./product/productList.jsp");
			view.forward(request, response);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
