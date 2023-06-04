package guestbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	// DB 연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"jwbook","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<Post> getAll() throws Exception {
		Connection conn = open();
		List<Post> postList = new ArrayList<>();
		
		String sql = "select aid, username, email, title, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, content from post";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				Post p = new Post();
				p.setAid(rs.getInt("aid"));
				p.setUsername(rs.getString("username"));
				p.setEmail(rs.getString("email"));
				p.setDate(rs.getString("cdate"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				
				postList.add(p);
			}
			return postList;			
		}
	}
	
	public Post getPost(int aid) throws SQLException {
		Connection conn = open();
		
		Post p = new Post();
		String sql = "select aid, username, email, title, PARSEDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, content from post where aid=?";
	
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn; pstmt; rs) {
			p.setAid(rs.getInt("aid"));
			p.setUsername(rs.getString("username"));
			p.setEmail(rs.getString("email"));
			p.setTitle(rs.getString("title"));
			p.setDate(rs.getString("cdate"));
			p.setContent(rs.getString("content"));
			pstmt.executeQuery();
			return p;
		}
	}
	
	public void addPost(Post p) throws Exception {
		Connection conn = open();
		
		String sql = "insert into post(username, email, title, date, content) values(?,?,?,CURRENT_TIMESTAMP(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, p.getUsername());
			pstmt.setString(2, p.getEmail());
			pstmt.setString(3, p.getTitle());
			pstmt.setString(4, p.getContent());
			
			pstmt.executeUpdate();
		}
	}
	
	public void delPost(int aid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from post where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, aid);
			// 삭제된 뉴스 기사가 없을 경우
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	
	public void updatePost(Post p, int aid) throws SQLException {
		Connection conn = open();
		
		String sql = "update post set username=?, email=?, date=CURRENT_TIMESTAMP(), title=?, content=? where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, p.getUsername());
			pstmt.setString(2, p.getEmail());
			pstmt.setString(3, p.getTitle());
			pstmt.setString(4, p.getContent());
			pstmt.setInt(5, aid);
			
			pstmt.executeUpdate();
		}
	}
}
