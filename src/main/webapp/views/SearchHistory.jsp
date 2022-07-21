<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>와이파이 정보 구하기 </title>
<link rel="icon" href="../resources/imgs/favicon.ico" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet" /> 
<link rel="stylesheet" href="../resources/css/main.css" />
<meta charset="UTF-8">
</head>
<body>
	<jsp:include page = "../components/header.html"/>
	<div class="container page search-history">
		<table class="history-info-table">
			<thead>
			  <tr>
			    <th>IDX</th>
			    <th>X좌표</th>
			    <th>Y좌표</th>
			    <th>조회일자</th>
			    <th>비고</th>
			  </tr>
			</thead>
			<tbody>
				<td class="before" colspan="5">
					위치 정보를 입력한 후에 조회해 주세요.
				</td>
			</tbody>
		</table>
	</div>
	<script type="module" src="../resources/js/history.js"></script>
</body>
</html>