<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%
	String sid = (String) session.getAttribute("sid");
	String sname = (String) session.getAttribute("sname");
%>
<nav class="navbar navbar-expand-lg navbar-light bg-warning">
	<div class="in_container"> 
	  <a class="navbar-brand" id="logo" href="index.jsp">MONSELFIT</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">MonSelFIT<span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Service</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="false">
	          Product
	        </a>
	        <div class="dropdown-menu">
	          <a class="dropdown-item" href="GetProductItemListCtrl?cateNo=1">SUIT</a>
	          <a class="dropdown-item" href="GetProductItemListCtrl?cateNo=2">OUTER</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="GetProductItemListCtrl?cateNo=3">TOP</a>
	          <a class="dropdown-item" href="GetProductItemListCtrl?cateNo=4">PANTS</a>
	          <div class="dropdown-divider"></div>
	          <a class="dropdown-item" href="GetProductItemListCtrl?cateNo=5">SHOES</a>
	          <a class="dropdown-item" href="GetProductItemListCtrl?cateNo=6">BAG</a>
	        </div>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-expanded="true">
	          Community
	        </a>
	        <div class="dropdown-menu">
	          <a class="dropdown-item" href="<%=request.getContextPath() %>/GetBoardListCtrl">Notice</a>
	          <a class="dropdown-item" href="#">Q&A</a>
	          <a class="dropdown-item" href="#">Use After</a>
	        </div>
	      </li>
	    </ul>
		<ul class="nav justify-content-end">
		  <% if(sid!=null) { %>
			  <li class="nav-item">
			    <span class="nav-link"><%=sname %>님</span>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link active" href="<%=request.getContextPath() %>/LogOutCtrl">로그아웃</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/GetCustomInfoCtrl">회원정보</a>
			  </li>
			  <% if(sid.equals("admin")) { %>
				  <li class="nav-item">
				    <a class="nav-link" href="<%=request.getContextPath() %>/admin/index.jsp">관리자 페이지로</a>
				  </li>
			  <% } %>
		  <% } else { %>
			  <li class="nav-item">
			    <a class="nav-link active" href="<%=request.getContextPath() %>/custom/login.jsp">로그인</a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="<%=request.getContextPath() %>/custom/membership.jsp">회원가입</a>
			  </li>
		  <% } %>
		  <li class="nav-item">
		    <a class="nav-link" href="#">Contact</a>
		  </li>
		</ul>	    
	  </div>
	</div>
</nav>