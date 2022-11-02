package kr.co.myshop.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.myshop.app.InitParamServlet;

@WebServlet("/ParameterTest")
public class ParameterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ParameterTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitParamServlet serv = new InitParamServlet();
		serv.init();
		serv.dbParam();
		serv.toString(request, response);
	}
}