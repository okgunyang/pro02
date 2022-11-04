package kr.co.myshop.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/SearchMovieServlet")
public class SearchMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 url
		String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		//key값에 들어갈 발급받은 키값
		String key = "2d1960a325f72e312330c46c40440bb2";
		//targetDt 들어갈 날짜 포멧 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//어제날짜로
		String yesterday = sdf.format(System.currentTimeMillis()-86400000);
		
		String result = Jsoup.connect(url)		//매개변수 url 요청
						.data("key",key)
						.data("targetDt",yesterday)
						.userAgent("Mozilla")
						.ignoreContentType(true)
						.execute().body();
		System.out.println(result);
		//String 타입 데이터를 변환하기위한 변환용 객체 생성
		JsonParser parser = new JsonParser();
		//result를 변환하여 JsonObject객체에 저장  -> 원래 변환하기 전 타입은 Object
		JsonObject resultInfo = (JsonObject)parser.parse(result); 
		//키값이 boxOfficeResult 인 값을 저장
		JsonObject detail1 = resultInfo.getAsJsonObject("boxOfficeResult");
		
		//JsonArray 로 박스오피스 순위를 저장
		JsonArray rank = detail1.getAsJsonArray("dailyBoxOfficeList");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(rank, response.getWriter());		
		//여기서 넘기면 apiTest 의 무비에 있는 함수의 (data)가 jSon
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
