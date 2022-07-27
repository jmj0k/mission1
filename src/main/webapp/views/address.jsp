<%-- 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>와이파이 위치</title>
    
</head>
<body>
<div id="map" style="width:100%;height:350px;"></div>
<% 
	String lat = request.getAttribute("lat").toString();
	String lnt = request.getAttribute("lnt").toString();
%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f23279e59d7b3fb100fa266494233825&libraries=services"></script>
<script>
var mapContainer = document.getElementById('map'),
mapOption = { 
    center: new kakao.maps.LatLng(<%=lat%>, <%=lnt%>),
    level: 1
};

var map = new kakao.maps.Map(mapContainer, mapOption);
var markerPosition  = new kakao.maps.LatLng(<%=lat%>, <%=lnt%>); 
var marker = new kakao.maps.Marker({
position: markerPosition
});

marker.setMap(map);

</script>
</body>
</html>
--%>