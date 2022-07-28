package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DTO.HistoryDTO;
import Util.DBConnection;

public class HistoryDAO {
	
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt = null;

	// 히스토리 삭제 메소드
	public int deleteUserHistory (String idx) throws SQLException {
		String SQL = "DELETE FROM History WHERE idx = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, idx);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pstmt.executeUpdate();
	}
	// 히스토리 삽입 메소드
	public int insertUserHistory(String lat, String lnt) throws SQLException {
		// lat & lnt는 복합 유니크키로 설정
		String SQL = "INSERT INTO History (lat, lnt) VALUES(?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
		    pstmt.setString(1, lat);
		    pstmt.setString(2, lnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pstmt.executeUpdate();
	}
	// 유저 히스토리 선택
	public List<HistoryDTO> selectUserHistory() throws SQLException {
		List<HistoryDTO> result = new LinkedList<>();
		
		String SQL = "SELECT * FROM History";
		pstmt = conn.prepareStatement(SQL);
		ResultSet resultSet = pstmt.executeQuery();
		
		while (resultSet.next()) {
			HistoryDTO historyDTO = new HistoryDTO(
					resultSet.getString("idx")
					, resultSet.getString("lat")
					, resultSet.getString("lnt")
					, resultSet.getString("time")
			);
			result.add(historyDTO);
		}
		
		return result;
	}
	
}
