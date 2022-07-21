<%@page import="okhttp3.Request"%>
<%@page import="DTO.WifiDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.WifiDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기 </title>
<link rel="icon" href="../resources/imgs/favicon.ico" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet" /> 
<link rel="stylesheet" href="../resources/css/main.css" />
</head>
<body>
	<jsp:include page = "../components/header.html"/>
	<div class="container page search">
		<nav class="sub-nav">
			<ul class="sub-nav-list">
				<li>
					<span>LAT: </span> 
					<input id="lat-input" type="text" placeholder="0.0" required>
				</li>
				<li>
					<span>LNT: </span> 
					<input id="lnt-input" type="text" placeholder="0.0" required>
				</li>
				<li>
					<a id="get-my-location-btn">
						<span> 내 위치 가져오기 </span> 
					</a>
				</li>
				<li>
					<a id="get-Wifi-info-btn">
						<span> 근처 WIFI 정보 보기 </span> 
					</a>
				</li>
			</ul>
		</nav>
		<table class="wifi-info-table">
			<thead>
			  <tr>
			    <th>거리 <br> (Km) </th>
			    <th>관리번호</th>
			    <th>자치구</th>
			    <th>와이파이명</th>
			    <th>도로명주소</th>
			    
			   	<th>상세주소</th>
			    <th>설치위치</th>
			    <th>설치유형</th>
			    <th>설치기관</th>
			    <th>서비스구분</th>
			    
			    <th>망종류</th>
			    <th>설치년도</th>
			    <th>실내외구분</th>
			    <th>WIFI 접속환경</th>
			    <th>X좌표</th>
			    
			   	<th>Y좌표</th>
			    <th>작업일자</th>
			  </tr>
			</thead>
			<tbody>
				<% 
					WifiDAO wifiDAO = new WifiDAO();
					List<WifiDTO> results = (List<WifiDTO>)request.getAttribute("result");
					if (results != null) {
						for (WifiDTO result: results) {
							out.write("<tr>");
							out.write("<td>" + result.getDistance() +"</td>");
							out.write("<td>" + result.getX_swifi_mgr_no() + "</td>");
							out.write("<td>" + result.getX_swifi_wrdofc() + "</td>");
							out.write("<td>" + result.getX_wifi_main_nm() + "</td>");
							out.write("<td>" + result.getX_wifi_addr1() + "</td>");
							out.write("<td>" + result.getX_wifi_addr2() + "</td>");
							out.write("<td>" + result.getX_wifi_instl_floor() + "</td>");
							out.write("<td>" + result.getX_wifi_instl_ty() + "</td>");
							out.write("<td>" + result.getX_wifi_instl_mby() + "</td>");
							out.write("<td>" + result.getX_wifi_svc_se() + "</td>");
							out.write("<td>" + result.getX_wifi_cmcwr() + "</td>");
							out.write("<td>" + result.getX_wifi_cnstc_year() + "</td>");
							out.write("<td>" + result.getX_wifi_inout_door() + "</td>");
							out.write("<td>" + result.getX_wifi_remars3() + "</td>");
							out.write("<td>" + result.getLat() + "</td>");
							out.write("<td>" + result.getLnt() + "</td>");
							out.write("<td>" + result.getWork_dttm() + "</td>");
							out.write("</tr>");
						}
					} else {
						out.write("<td class='before' colspan='17'> 위치 정보를 입력하신 후에 조회해 주세요. </td>");
					}
				%>
			</tbody>
		</table>
	</div>
	<script type="module" src="../resources/js/search.js"></script>
</body>
</html>
