package DTO;

public class WifiDTO {
	private String distance;
	private String x_swifi_mgr_no; // 관리번호
	private String x_swifi_wrdofc; // 자치구
	private String x_wifi_main_nm; // 와이파이명
	private String x_wifi_addr1; // 도로명 주소
	private String x_wifi_addr2; // 상세주소
	private String x_wifi_instl_floor; // 설치위치(층)
	private String x_wifi_instl_ty; // 설치유형
	private String x_wifi_instl_mby; // 설치기관
	private String x_wifi_svc_se; // 서비스 구분
	private String x_wifi_cmcwr; // 망 종류
	private String x_wifi_cnstc_year; // 설치년도
	private String x_wifi_inout_door;// 실내외구분 
	private String x_wifi_remars3; //wifi 접속환경
	private String lat; // x좌표
	private String lnt; // y좌표
	private String work_dttm; // 작업일자
	
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getX_swifi_mgr_no() {
		return x_swifi_mgr_no;
	}
	public void setX_swifi_mgr_no(String x_swifi_mgr_no) {
		this.x_swifi_mgr_no = x_swifi_mgr_no;
	}
	public String getX_swifi_wrdofc() {
		return x_swifi_wrdofc;
	}
	public void setX_swifi_wrdofc(String x_swifi_wrdofc) {
		this.x_swifi_wrdofc = x_swifi_wrdofc;
	}
	public String getX_wifi_main_nm() {
		return x_wifi_main_nm;
	}
	public void setX_wifi_main_nm(String x_wifi_main_nm) {
		this.x_wifi_main_nm = x_wifi_main_nm;
	}
	public String getX_wifi_addr1() {
		return x_wifi_addr1;
	}
	public void setX_wifi_addr1(String x_wifi_addr1) {
		this.x_wifi_addr1 = x_wifi_addr1;
	}
	public String getX_wifi_addr2() {
		return x_wifi_addr2;
	}
	public void setX_wifi_addr2(String x_wifi_addr2) {
		this.x_wifi_addr2 = x_wifi_addr2;
	}
	public String getX_wifi_instl_floor() {
		return x_wifi_instl_floor;
	}
	public void setX_wifi_instl_floor(String x_wifi_instl_floor) {
		this.x_wifi_instl_floor = x_wifi_instl_floor;
	}
	public String getX_wifi_instl_ty() {
		return x_wifi_instl_ty;
	}
	public void setX_wifi_instl_ty(String x_wifi_instl_ty) {
		this.x_wifi_instl_ty = x_wifi_instl_ty;
	}
	public String getX_wifi_instl_mby() {
		return x_wifi_instl_mby;
	}
	public void setX_wifi_instl_mby(String x_wifi_instl_mby) {
		this.x_wifi_instl_mby = x_wifi_instl_mby;
	}
	public String getX_wifi_svc_se() {
		return x_wifi_svc_se;
	}
	public void setX_wifi_svc_se(String x_wifi_svc_se) {
		this.x_wifi_svc_se = x_wifi_svc_se;
	}
	public String getX_wifi_cmcwr() {
		return x_wifi_cmcwr;
	}
	public void setX_wifi_cmcwr(String x_wifi_cmcwr) {
		this.x_wifi_cmcwr = x_wifi_cmcwr;
	}
	public String getX_wifi_cnstc_year() {
		return x_wifi_cnstc_year;
	}
	public void setX_wifi_cnstc_year(String x_wifi_cnstc_year) {
		this.x_wifi_cnstc_year = x_wifi_cnstc_year;
	}
	public String getX_wifi_inout_door() {
		return x_wifi_inout_door;
	}
	public void setX_wifi_inout_door(String x_wifi_inout_door) {
		this.x_wifi_inout_door = x_wifi_inout_door;
	}
	public String getX_wifi_remars3() {
		return x_wifi_remars3;
	}
	public void setX_wifi_remars3(String x_wifi_remars3) {
		this.x_wifi_remars3 = x_wifi_remars3;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLnt() {
		return lnt;
	}
	public void setLnt(String lnt) {
		this.lnt = lnt;
	}
	public String getWork_dttm() {
		return work_dttm;
	}
	public void setWork_dttm(String work_dttm) {
		this.work_dttm = work_dttm;
	}
	public WifiDTO(String distance, String x_swifi_mgr_no, String x_swifi_wrdofc, String x_wifi_main_nm,
			String x_wifi_addr1, String x_wifi_addr2, String x_wifi_instl_floor, String x_wifi_instl_ty,
			String x_wifi_instl_mby, String x_wifi_svc_se, String x_wifi_cmcwr, String x_wifi_cnstc_year,
			String x_wifi_inout_door, String x_wifi_remars3, String lat, String lnt, String work_dttm) {
		super();
		this.distance = distance;
		this.x_swifi_mgr_no = x_swifi_mgr_no;
		this.x_swifi_wrdofc = x_swifi_wrdofc;
		this.x_wifi_main_nm = x_wifi_main_nm;
		this.x_wifi_addr1 = x_wifi_addr1;
		this.x_wifi_addr2 = x_wifi_addr2;
		this.x_wifi_instl_floor = x_wifi_instl_floor;
		this.x_wifi_instl_ty = x_wifi_instl_ty;
		this.x_wifi_instl_mby = x_wifi_instl_mby;
		this.x_wifi_svc_se = x_wifi_svc_se;
		this.x_wifi_cmcwr = x_wifi_cmcwr;
		this.x_wifi_cnstc_year = x_wifi_cnstc_year;
		this.x_wifi_inout_door = x_wifi_inout_door;
		this.x_wifi_remars3 = x_wifi_remars3;
		this.lat = lat;
		this.lnt = lnt;
		this.work_dttm = work_dttm;
	}
	
	
}
