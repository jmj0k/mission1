package DTO;

public class HistoryDTO {
	private String idx; // idx
	private String lat; // lat
	private String lnt; // lnt
	private String time; // timestamp
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public HistoryDTO(String idx, String lat, String lnt, String time) {
		super();
		this.idx = idx;
		this.lat = lat;
		this.lnt = lnt;
		this.time = time;
	}
}
