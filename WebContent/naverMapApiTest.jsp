<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  7geaqrnt5l, jmn2agwecn -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=jmn2agwecn&submodules=geocoder"></script>
<title>네이버 지도 API 테스트</title>
</head>
<body>
	<section>
		<div class = "container" style ="color : black;">
			<h2>1. 네이버 지도 API</h2>
			<div id = "map" style="width:100%; height:500px;"></div>
		</div>
	</section>	
	<script>
	window.onload=function(){
		var map = new naver.maps.Map('map',{
			center : new naver.maps.LatLng(37.65971649095605,126.77097712250817),
			zoom : 10,
			zoomControl : true,
			zoomControlOptions:{
				position : naver.maps.Position.TOP_RIGHT,
				style : naver.maps.ZoomControlStyle.SMALL
			}
		});
		var marker = new naver.maps.Marker({
			position : new naver.maps.LatLng(37.533807,126.896772),
			map:map
		});
		naver.maps.Event.addListener(map,'click',function(e){ //맵에 클릭 이벤트 발생시!
			marker.setPosition(e.coord);
			if(infoWindow != null){					//  
				if(infoWindow.getMap()){			//열러있는지 안열려있는지 판단
					infoWindow.close();
				}
			}
			//위, 경도를 이용하여 주소를 얻어오기 위해서는 reverseGeocode 를 사용해야함
			//클라이언트 키 뒤에 &submodules=geocoder 를 추가 (추가해야 사용가능)
			
			naver.maps.Service.reverseGeocode({
				location : new naver.maps.LatLng(e.coord.lat(),e.coord.lng())
			},function(status,response){
				if(status !== naver.maps.Service.Status.OK){
					return alert("실패");
				}
				var result = response.result, 
				items = result.items;	// 배열 타입으로 응답을 받아서 변수에 저장
			address = items[2].address; // 0-> 지번주소, 1-> 도로명	 2-> 	
			contentString = [
				'<div class="iw_inner">',
				'  <h3>일산더조은컴퓨터학원</h3>',
				'  <p>'+address+'</p>',
				'</div>'
			].join('');
		});
			
	});
		var contentString = [
			'<div class="iw_inner">',
			'  <h3>일산더조은컴퓨터학원</h3>',
			'  <p>경기 고양시 일산동구 중앙로1275번길 38-10 우림로데오스위트 2층 201호</p>',
			'</div>'
		].join('');
		var infoWindow = new naver.maps.InfoWindow();
		naver.maps.Event.addListener(marker,'click',function(e){
			if(infoWindow.getMap()){				//윈도우창 열고 다른곳 찍었을때 닫히는것
				infoWindow.close();
			}else{
				infoWindow = new naver.maps.InfoWindow({
					content : contentString
				});
				infoWindow.open(map,marker);
			}			
		});
	}
	</script>
</body>
</html>