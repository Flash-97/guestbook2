package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVo;

public class GuestbookDao {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String iD = "webdb";
	private String passWD = "webdb";
	
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			
			conn = DriverManager.getConnection(url, iD, passWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
		    System.out.println("error:" + e);
		}  
	}
	
	private void close() {
		 // 5. 자원정리
	    try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }
	}
	
	public int bookInsert(GuestbookVo guest) {
		getConnection();
		int count = 0;

		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into guestbook "
					+ 			" values (seq_person_no.nextval, "
					+ 			" ?, "
					+ 			" ?, "
					+			" ?, "
					+ 			" to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh24:mi:ss'))";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, guest.getName());
			pstmt.setString(2, guest.getPw());
			pstmt.setString(3, guest.getContent());
			
			count = pstmt.executeUpdate();   
		    // 4.결과처리

		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		return count;
	}
	
	public List<GuestbookVo> bookList() {
		getConnection();
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();

		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT no, "
					+ 			"name, "
					+ 			"content, "
					+ 			"re_date "
					+ 		"FROM "
					+ 			"guestbook";
			
			pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();    
		    // 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				String date = rs.getString("re_date");
				
				GuestbookVo guest = new GuestbookVo(no, name, content, date);
				
				list.add(guest);
			}
		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		
		return list;
	}
	
	public int delectBook(int id, String pw) {
		getConnection();
		int count = 0;
		
		try {
		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from guestbook "
					+ 			"where no = ?"
					+ 			"and  password = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, pw);
			
			count = pstmt.executeUpdate();      
		    // 4.결과처리

		}  catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		close();
		return count;
	}
}