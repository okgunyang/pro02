package kr.co.myshop.ctrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UpdateProductProCtrl")
public class UpdateProductProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
	private final static String USER = "root";
	private final static String PASS = "a1234";
	String sql = "";
	int cnt = 0;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String uploadPath = "D:\\kim6\\pro01\\pro02\\WebContent\\upload";
	   String uploadPath = request.getRealPath("/upload");
	   
	   System.out.println(uploadPath);
	   int size = 10*1024*1024;
	   int proNo = 0;
	   int cateNo = 0;
	   String proName = "";
	   String proSpec = "";
	   int oriPrice = 0;
	   double discountRate = 0.1;
	   String proPic = "";
	   String proPic2 = "";
	   String updateProPic = "";
	   String updateProPic2 = "";
	   
	   try{
	      MultipartRequest multi=new MultipartRequest(request,
	                     uploadPath,
	                     size, 
	                     "UTF-8",
	            new DefaultFileRenamePolicy());
	      proNo=Integer.parseInt(multi.getParameter("proNo"));
	      cateNo=Integer.parseInt(multi.getParameter("cateNo"));
	      proName=multi.getParameter("proName");
	      proSpec=multi.getParameter("proSpec");
	      oriPrice=Integer.parseInt(multi.getParameter("oriPrice"));
	      discountRate = Double.parseDouble(multi.getParameter("discountRate"));
	      proPic=multi.getParameter("proPic");
	      proPic2=multi.getParameter("proPic2");
	      
	      Enumeration files=multi.getFileNames();

	      String file1 =(String)files.nextElement();
	      updateProPic=multi.getFilesystemName(file1);

	      if(updateProPic!=null){
	    	  proPic=updateProPic;
	      }
	      
	      String file2 =(String)files.nextElement();
	      updateProPic2=multi.getFilesystemName(file2);

	      if(proPic2!=null){
	    	  proPic2=updateProPic2;
	      }
	      
			try {
				//데이터베이스 연결
				Class.forName(DRIVER);
				sql = "update product set cateno=?, proname=?, prospec=?, oriprice=?, discountrate=?, propic=?, propic2=? where prono=?";
				Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cateNo); 
				pstmt.setString(2, proName);
				pstmt.setString(3, proSpec);
				pstmt.setInt(4, oriPrice);
				pstmt.setDouble(5, discountRate);
				pstmt.setString(6, proPic);
				pstmt.setString(7, proPic2);
				pstmt.setInt(8, proNo);
				cnt = pstmt.executeUpdate();
				if(cnt>=1){
					response.sendRedirect("GetProductListCtrl");
				} else {
					response.sendRedirect("UpdateProductCtrl?proNo="+proNo);
				}
				pstmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	   } catch(Exception e) {
	      e.printStackTrace();
	   }  
	}
}
