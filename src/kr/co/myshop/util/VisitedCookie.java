package kr.co.myshop.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VisitedCookie")	
public class VisitedCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		// 방문횟수 저장
		out.println("<html>");
		out.println("<head>");
		out.println("<title>제목</title>");
		out.println("</head>");
		out.println("<body>");
		// out.println("");
		out.println("<p> 방문 횟수 </p>");
		Cookie cookies[] = req.getCookies();
		Cookie visitedCookie = null;			// Key Value로 관리하기 위한 쿠키를 만들기
		if(cookies != null) {
			//해당 쿠키 검색
				for (int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("visited")) {
						visitedCookie = cookies[i];			
						break; 										// 해당 쿠키 찾으면 루프 정지 
					}
				}
			// 해당 쿠키를 찾으면, 기존의 쿠키를 카운트하여 방문횟수 반환
			if(visitedCookie != null) {
				int count = Integer.parseInt( visitedCookie.getValue() )+1; 	// count에 방문 횟수가 계속 추가
				out.println("<p>" + count + "번째 방문입니다 </p>");
				// 그리고, cookie 값을 갱신
				visitedCookie.setValue(count + "");
				// 기간 설정
				visitedCookie.setMaxAge(60); 			// 365 * 24 * 60 * 60 -> 1년. 쿠키는 보통 기간을 길게 준다. 
				resp.addCookie(visitedCookie); 			// 갱신된 값을 다시 출력
			}
			// 못 찾았을 때, 하나도 없다는 뜻이므로 여기서 쿠키를 생성해주어야 한다
			// 참고로, 쿠키는 프로그램 끄더라도 계속 저장되어 있다.
			else {
				out.println("<p>첫 번째 방문입니다</p>");
				Cookie newCookie = new Cookie("visited", "1");		// 쿠키 명칭 : visited
				resp.addCookie(newCookie);
			}
		} else {
				
		}		
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}