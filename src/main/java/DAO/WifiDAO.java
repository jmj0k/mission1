package DAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import DTO.HistoryDTO;
import DTO.WifiDTO;
import Util.DBConnection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WifiDAO {
	private static String AUTH_KEY = "7a4e64575a6d696e38336f6d465a71";
	private static String API_URL = "http://openapi.seoul.go.kr:8088/" + AUTH_KEY + "/json/TbPublicWifiInfo";
	private static OkHttpClient client = new OkHttpClient();
	
	public WifiDAO() {}
	
	/*---------------------------------- DB 테이블 명령 메소드 -------------------------------------*/
	
	// 공공 와이파이 정보 (api에서 받아온 값) 전체 삽입 메소드
	public int insertPublicWifi(JsonArray jsonArray, Connection conn) throws SQLException {
		/* 
		 * 속도 향상을 위해 autocommit을 꺼두고 반복문이 끝나면 한 번에 커밋합니다. 
		 * autocommit을 켜둘 경우 1분이 훨씬 넘게 소요됩니다. 
		 * 
		 */
		conn.setAutoCommit(false);
		PreparedStatement pstmt = null;
		int count = 0;
		
		if (jsonArray == null) return -1;
		
		String wifi_mgr_no, wifi_wrdofc, wifi_name, wifi_addr1, wifi_addr2
		, wifi_floor, wifi_instl_ty, wifi_instl_mby, wifi_svc_se, wifi_cmcwr
		, wifi_cnstc_year, wifi_inout_door, wifi_remars3, wifi_lat, wifi_lnt, work_dttm;

		for (int i = 0; i < jsonArray.size(); i++) {
			
		    wifi_mgr_no = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
		    wifi_wrdofc = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
		    wifi_name = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString();
		    wifi_addr1 = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
		    wifi_addr2 = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
		    
		    wifi_floor = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
		    wifi_instl_ty = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
		    wifi_instl_mby = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
		    wifi_svc_se = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
		    wifi_cmcwr = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
		   
		    wifi_cnstc_year = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
		    wifi_inout_door = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
		    wifi_remars3 = jsonArray.get(i).getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
		    wifi_lat = jsonArray.get(i).getAsJsonObject().get("LAT").getAsString();
		    wifi_lnt = jsonArray.get(i).getAsJsonObject().get("LNT").getAsString();
		    work_dttm = jsonArray.get(i).getAsJsonObject().get("WORK_DTTM").getAsString();
    
			String SQL = 
					"INSERT OR REPLACE INTO PublicWifiInfoTable (" 
					+ "x_swifi_mgr_no, "
					+ "x_swifi_wrdofc, "
					+ "x_wifi_main_nm, "
					+ "x_wifi_addr1, "
					+ "x_wifi_addr2, "
					+ "x_wifi_instl_floor, "
					+ "x_wifi_instl_ty, "
					+ "x_wifi_instl_mby, "
					+ "x_wifi_svc_se, "
					+ "x_wifi_cmcwr, "
					+ "x_wifi_cnstc_year, "
					+ "x_wifi_inout_door, "
					+ "x_wifi_remars3, "
					+ "lat, "
					+ "lnt, "
					+ "work_dttm)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		    pstmt = conn.prepareStatement(SQL);
		
		    pstmt.setString(1, wifi_mgr_no);
		    pstmt.setString(2, wifi_wrdofc);
		    pstmt.setString(3, wifi_name);
		    pstmt.setString(4, wifi_addr1);
		    pstmt.setString(5, wifi_addr2);
		    pstmt.setString(6, wifi_floor);
		    pstmt.setString(7, wifi_instl_ty);
		    pstmt.setString(8, wifi_instl_mby);
		    pstmt.setString(9, wifi_svc_se);
		    pstmt.setString(10, wifi_cmcwr);
		    pstmt.setString(11, wifi_cnstc_year);
		    pstmt.setString(12, wifi_inout_door);
		    pstmt.setString(13, wifi_remars3);
		    pstmt.setString(14, wifi_lnt);
		    pstmt.setString(15, wifi_lat);
		    pstmt.setString(16, work_dttm);
		    
		    count += pstmt.executeUpdate();
		}
	    conn.commit();
		return count;
	}
	
	
	// 근처 공공 와이파이 조회 메소드
	public List<WifiDTO> selectNearbyWifi(double lat, double lnt) throws SQLException {
		List<WifiDTO> result = new LinkedList<>();
		PreparedStatement pstmt = null;
		String SQL = 
				"SELECT * FROM PublicWifiInfoTable"
				+ " ORDER BY ABS(lat - " + lat + ") * ABS(lat - " + lat + ")"
				+ " + ABS(lnt - " + lnt + ") * ABS(lnt -" +  lnt + ")"
				+ " LIMIT 20";

		Connection conn = DBConnection.getConnection();
		pstmt = conn.prepareStatement(SQL);
		ResultSet resultSet = pstmt.executeQuery();
		
		while (resultSet.next()) {
			String distanceData = distance(lat, lnt, Double.parseDouble(resultSet.getString("lat")), Double.parseDouble(resultSet.getString("lnt"))); 
			WifiDTO wifiDTO = new WifiDTO(
					distanceData
					, resultSet.getString("x_swifi_mgr_no")
					, resultSet.getString("x_swifi_wrdofc")
					, resultSet.getString("x_wifi_main_nm")
					, resultSet.getString("x_wifi_addr1")
					, resultSet.getString("x_wifi_addr2")
					, resultSet.getString("x_wifi_instl_floor")
					, resultSet.getString("x_wifi_instl_ty")
					, resultSet.getString("x_wifi_instl_mby")
					, resultSet.getString("x_wifi_svc_se")
					, resultSet.getString("x_wifi_cmcwr")
					, resultSet.getString("x_wifi_cnstc_year")
					, resultSet.getString("x_wifi_inout_door")
					, resultSet.getString("x_wifi_remars3")
					, resultSet.getString("lat")
					, resultSet.getString("lnt")
					, resultSet.getString("work_dttm")
				);
			result.add(wifiDTO);
		}
		
		return result;
	}
	
	/*----------------------------------- 계산 메소드들 ----------------------------------------*/
	
	// api에서 넘어온 값을 확인하는 메소드
	public boolean APIdataCheck(JsonElement element) {
		boolean result = false;
		/*
		 * 서울시 내부 서버 오류일 때는 tbpublicwifiinfo 오브젝트가 없음..
		 * <?xml version="1.0" encoding="UTF-8"?>
			<RESULT>
			<CODE>ERROR-500</CODE>
			<MESSAGE>서버 오류입니다.
			지속적으로 발생시 열린 데이터 광장으로 문의(Q&amp;A) 바랍니다.</MESSAGE>
			</RESULT>
		 * */
		if (element != null && element.getAsJsonObject().get("TbPublicWifiInfo") != null) {
			result = element
					.getAsJsonObject()
					.get("TbPublicWifiInfo")
					.getAsJsonObject()
					.get("RESULT")
					.getAsJsonObject()
					.get("CODE")
					.getAsString()
					.equals("INFO-000");
		}

		return result;
	}

	// public wifi api 호출 메소드 
	public String getPublicWifiData(int start, int end, URL url) throws IOException {
		Request.Builder builder = new Request.Builder().url(url).get();
		Response response = client.newCall(builder.build()).execute();
		
		if (response.isSuccessful()) {
			Gson json = new Gson();
			ResponseBody body = response.body();
			if (body != null) {
				return response.body().string();
			}
		} else {
			System.out.print("error code: " + response.code());
		}
		return null;
	}
	
	// public wifi api에서 데이터 획득 및 db 삽입 메소드 호출
	public int getPublicWifiData() throws IOException {
		int start = 1, end = 1000, result = 0;
		URL url = new URL(API_URL + "/" + start + "/" + end);
		String initData = getPublicWifiData(start, end, url);
		JsonElement element = JsonParser.parseString(initData);
		JsonArray jsonArray = null;
		Connection conn = DBConnection.getConnection();
		
		if(APIdataCheck(element)) {
			int count = element
					.getAsJsonObject()
					.get("TbPublicWifiInfo")
					.getAsJsonObject()
					.get("list_total_count")
					.getAsInt();
			
			count = (count / 1000) + (count % 1000 != 0 ? 1 : 0); 
			for (int i = 0; i < count; i++) {
				url = new URL(API_URL + "/" + start + "/" + end);
				start += 1000;
				end += 1000;
				element = JsonParser.parseString(getPublicWifiData(start, end, url));
				if (APIdataCheck(element)) {
					jsonArray = (JsonArray) element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("row");
				} else {
					String errCode = element.getAsJsonObject().get("RESULT").getAsJsonObject().get("CODE").getAsString();
					if (errCode.equals("ERROR-500")) {
						System.out.println("API 서버 오류");
						System.out.println(element);
						return -1;
					}
				}
				try {
					result += insertPublicWifi(jsonArray, conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	private static String distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
	    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	         
	    dist = Math.acos(dist);
	    dist = rad2deg(dist);
	    dist = dist * 60 * 1.1515;
	    dist = dist * 1.609344;
	    dist = Math.round(dist * 10000) / 10000.0;

	    return String.valueOf(dist);
	}
	private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
	private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
