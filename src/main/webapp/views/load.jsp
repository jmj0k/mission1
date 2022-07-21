<%@page import="DAO.WifiDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="../resources/imgs/favicon.ico" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet" /> 
<link rel="stylesheet" href="../resources/css/main.css" />
<title>와이파이 정보 구하기</title>
</head>
<body class="load">
	<%	
		WifiDAO wifiDAO = new WifiDAO();
	 	int result = wifiDAO.getPublicWifiData(); 
	 %>
	<jsp:include page = "../components/header.html"/>
	<div class="container">
		<div class="pop-up">
			<div class="Text">
				<% if (result > 0) { %>
				<%=result %>개의 데이터 저장을 완료했습니다! 😁 </br>
				클릭하면 Home으로 돌아갑니다! </br>
				<%} else if (result == -1) {%>
				현재 API 서버가 불안정합니다. 😰 </br>
				나중에 다시 시도해주세요.
				<%} else {%>
				데이터를 저장하는데 문제가 생겼습니다. 😰 </br>
				다시 시도해주세요.
				<%} %>
			</div>
		</div>
		
	</div>
	<script type="module" src="../resources/js/load.js"></script>
</body>
</html>