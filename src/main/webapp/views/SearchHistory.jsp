<%@page import="DTO.HistoryDTO"%>
<%@page import="java.util.List"%>
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
				<% 
					List<HistoryDTO> results = (List<HistoryDTO>)request.getAttribute("result");
					
					if (results != null && results.size() > 0) {
						for (int i = results.size() - 1; i >= 0; i--) {
							out.write("<tr>");
							out.write("<td class='search-idx-btn' data-lat='" 
								+ results.get(i).getLat() 
								+ "' data-lnt='"
								+ results.get(i).getLnt()
								+ "'>" 
								+ (i + 1) 
								+ "</td>");
							out.write("<td>" + results.get(i).getLat() + "</td>");
							out.write("<td>" + results.get(i).getLnt() + "</td>");
							out.write("<td>" + results.get(i).getTime() + "</td>");
							out.write("<td class='delete-btn' data-idx='"+ results.get(i).getIdx() +"'> 삭제 </td>");
							out.write("</tr>");
							results.get(i);
						}
					} else {
						out.write("<td class='before' colspan='5'> 현재 저장된 값이 존재하지 않습니다. </td>");
					}
				%>
			</tbody>
		</table>
	</div>
	<script type="module" src="../resources/js/history.js"></script>
</body>
</html>