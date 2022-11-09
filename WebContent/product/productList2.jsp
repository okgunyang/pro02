<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, kr.co.myshop.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>제품 목록</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="common.css">
<style>
.title { padding-top:36px; padding-bottom:20px; }
</style>
<style>
.in_container { clear:both; width:1400px; margin:0 auto; }
.in_container:after { content:""; display:block; clear:both; }
.card-title { overflow:hidden; text-overflow:ellipsis; white-space:nowrap; }
.card-text {   overflow: hidden;  text-overflow: ellipsis;  display: -webkit-box;
  -webkit-line-clamp: 2;  -webkit-box-orient: vertical; }
#best li { margin-bottom:24px; }  
.breadcrumb-item+.breadcrumb-item::before { float:left; }
#cateNo { display:block; width:120px; float:left; }
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<%
	List<Product> proList = (ArrayList<Product>) request.getAttribute("proList");
	int cateNo = (int) request.getAttribute("cateNo");
	String cateName = "";
	System.out.println("cateNo : "+cateNo);
	if(String.valueOf(cateNo)!=null) {
		if(cateNo==1){
			cateName = "SUIT";
		} else if(cateNo==2){
			cateName = "OUTER";
		} else if(cateNo==3){
			cateName = "TOP";
		} else if(cateNo==4){
			cateName = "PANTS";
		} else if(cateNo==5){
			cateName = "SHOES";
		} else if(cateNo==6){
			cateName = "BAG";
		} else {
			cateName = "제품목록";
		}
	} else {
		cateName = "제품목록";
	}
	if(sid!=null) {
		sid = sid;
	} else {
		sid = "guest";
	}
%>
<nav aria-label="breadcrumb bg-light" style="clear:both;height:64px;width:100vw;border-bottom:3px solid #999;">
  <ol class="breadcrumb bg-transparent" style="float:right;">
    <li class="breadcrumb-item"><a href="<%=request.getContextPath() %>/index.jsp">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page"><%=cateName %></li>
  </ol>
</nav>
<div class="container-fluid" id="content">
	<div class="row" id="content_row">
		<% if(sid!=null && sid.equals("admin")) { %>
		<%@ include file="../admin/admin_sidebar.jsp" %>
		<% } %>
		<% if(sid!=null && sid.equals("admin")) { %>
		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
		<% } else { %>
		<main class="content container">
		<% } %>
			<div class="btn-group" id="btn_grp1" style="margin-top:40px;">
				<a href="<%=request.getContextPath() %>/GetProductListCtrl" class="btn btn-primary">전체</a>
				<a href="<%=request.getContextPath() %>/GetProductItemListCtrl?cateNo=1" class="btn btn-outline-primary">SUIT</a>
				<a href="<%=request.getContextPath() %>/GetProductItemListCtrl?cateNo=2" class="btn btn-outline-primary">OUTER</a>
				<a href="<%=request.getContextPath() %>/GetProductItemListCtrl?cateNo=3" class="btn btn-outline-primary">TOP</a>
				<a href="<%=request.getContextPath() %>/GetProductItemListCtrl?cateNo=4" class="btn btn-outline-primary">PANTS</a>
				<a href="<%=request.getContextPath() %>/GetProductItemListCtrl?cateNo=5" class="btn btn-outline-primary">SHOES</a>
				<a href="<%=request.getContextPath() %>/GetProductItemListCtrl?cateNo=6" class="btn btn-outline-primary">BAG</a>
			</div>
			<script>
			$(document).ready(function(){
				var url = location.href;
				var loc = url.indexOf("=");
				var url2 = parseInt(url.substr(loc+1, url.length-1));
				console.log(url2);
				if(isNaN(url2)){
					$("#btn_grp1 a").removeClass("btn-primary").addClass("btn-outline-primary");
					$("#btn_grp1 a").eq(0).removeClass("btn-outline-primary").addClass("btn-primary");
				} else {
					$("#btn_grp1 a").removeClass("btn-primary").addClass("btn-outline-primary");
					$("#btn_grp1 a").eq(url2).removeClass("btn-outline-primary").addClass("btn-primary");
				}
			});	
			</script>
			<h2 class="title"><%=cateName %></h2>
			<ul class="row" id="best">
			<% for(int i=0;i<proList.size();i++) {
				Product pro = proList.get(i);
			%>
				<li class="col-xl-3 col-lg-4 col-md-6 col-sm-12">
					<div class="card" style="width: 18rem;">
					  <img src="<%=request.getContextPath() %>/upload/<%=pro.getProPic() %>" class="card-img-top" alt="<%=pro.getProName() %>">
					  <div class="card-body">
					    <h5 class="card-title"><%=pro.getProName() %></h5>
					    <p class="card-text"><%=pro.getProSpec() %></p>
					    <p class="card-text"><strong style="color:red;"><%=pro.getProPrice() %>원</strong> (<del><%=pro.getOriPrice() %>)</del></p>
					    <a href="<%=request.getContextPath() %>/GetProductDetailCtrl?proNo=<%=pro.getProNo() %>" class="btn btn-primary">제품 상세보기</a>
					  </div>
					</div>
				</li>
			<% } %>
			<% if(proList.size()==0){ %>
				<li style="width:1000px;">
					<hr>
					<p>해당 카테고리의 상품이 존재하지 않습니다.</p>
					<hr>
				</li>
			<% } %>
			</ul>
			<% if(sid.equals("admin")) { %>
			<div class="btn-group">
				<a href="<%=request.getContextPath() %>/InsertProductCategoryCtrl" class="btn btn-danger">상품 등록</a>
			</div>
			<% } %>
		</main>
	</div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>