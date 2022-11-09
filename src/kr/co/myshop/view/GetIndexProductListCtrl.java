package kr.co.myshop.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import kr.co.myshop.vo.Product;

public class GetIndexProductListCtrl {
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";

	public ArrayList<Product> bestProduct(){
		//결과를 데이터베이스로 부터 받아서 리스트로 저장
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			//많이 팔린 상품(팔린 상품의 총개수가 많은 순으로 4위까지인 상품) 
			sql = "select * from product where prono in (select * from (select prono from sales group by prono order by sum(amount) desc limit 4) as tot)";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
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
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proList;
	}
	public ArrayList<Product> newProduct(){
		//결과를 데이터베이스로 부터 받아서 리스트로 저장
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			//새로운 상품(등록된 날짜의 역순으로 정렬했을 때 4위까지인 상품) 
			sql = "select * from product order by regdate desc limit 4";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
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
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proList;
	}
	public ArrayList<Product> hotProduct(){
		//결과를 데이터베이스로 부터 받아서 리스트로 저장
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			//데이터베이스 연결
			Class.forName(DRIVER);
			//핫한 상품(열어본 횟수의 역순으로 정렬했을 때 4위까지인 상품) 
			sql = "select * from product order by scnt desc limit 4";
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
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
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proList;
	}
}
