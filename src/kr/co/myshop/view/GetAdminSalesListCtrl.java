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
import kr.co.myshop.vo.Sales;

@WebServlet("/GetAdminSalesListCtrl")
public class GetAdminSalesListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			sql = "select a.saleno, a.cusid, a.prono, a.amount, a.saledate, a.parselno, a.salepayno, b.parselstate from sales a inner join parsel b on a.parselno=b.parselno order by a.saleno desc";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			//결과를 데이터베이스로 부터 받아서 리스트로 저장
			List<Sales> saleList = new ArrayList<Sales>();
			while(rs.next()){
				Sales vo = new Sales();
				vo.setSaleNo(rs.getInt("saleno"));
				vo.setCusId(rs.getString("cusId"));
				vo.setProNo(rs.getString("prono"));
				vo.setAmount(rs.getInt("amount"));
				vo.setSaleDate(rs.getString("saledate"));
				vo.setParselNo(rs.getInt("parselno"));
				vo.setSalePayNo(rs.getInt("salepayno"));
				vo.setParselState(rs.getInt("parselstate"));
				saleList.add(vo);
			}
			request.setAttribute("saleList", saleList);
			
			//notice/boardList.jsp 에 포워딩
			RequestDispatcher view = request.getRequestDispatcher("./admin/saleList.jsp");
			view.forward(request, response);
			
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
