package kr.co.myshop.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.junit.Test;


public class MySqlBoardTest {
	private final static Logger log = Logger.getGlobal();
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";
	
	@Test
	public void ysqlConnectionTest() throws ClassNotFoundException {
		Class.forName(DRIVER);
		sql = "select * from notice order by notino desc";
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		){
		
			assertNotNull(con);
			log.info("con : "+con);
			log.info("pstmt : "+pstmt);
			
			while(rs.next()) {
				log.info("번호 : "+rs.getInt("notino"));
				log.info("제목 : "+rs.getString("title"));
				log.info("내용 : "+rs.getString("content"));
				log.info("작성자 : "+rs.getString("author"));
				log.info("작성일 : "+rs.getString("resdate"));
			}
			rs.close();
			pstmt.close();
			con.close();
			
		} catch(Exception e) {
				e.printStackTrace();
		}
		//fail("Not yet implemented");
	}
}
