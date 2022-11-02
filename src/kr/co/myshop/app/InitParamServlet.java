package kr.co.myshop.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String DRIVER, URL, USER, PASS;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    @Override
    public void init() {
    	DRIVER = "com.mysql.cj.jdbc.Driver";
    	URL = "jdbc:mysql://localhost:3306/myshop1?serverTimezone=Asia/Seoul";
    	USER = "root";
    	PASS = "a1234";
    }
 
    public void toString(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<h2>MySQL 추출 변수</h2>");
        out.print("<h3>DRIVER : "+DRIVER+"</h3>");
        out.println("<h3>URL :  "+URL+"</h3>");
        out.print("<h3>USER : "+USER+"</h3>");
        out.print("<h3>PASS : "+PASS+"</h3>");
    }
    
    public InitParamServlet dbParam(){
    	InitParamServlet serv = new InitParamServlet();
    	serv.init();
    	return serv;
    }
}
