package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import mvc.util.jdbcUtil;

public class MemberDao {
	private static final MemberDao dao = new MemberDao();

	public static MemberDao getInstance() {
		return dao;
	}
	
	private MemberDao(){
	}
	
	public int insert(Connection conn, Member mem) throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "insert into member (memberid, name, password, regdate) values (?,?,?,?)";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			return pstmt.executeUpdate();
		}finally{
			jdbcUtil.close(pstmt);
		}
	}
	
	public Member selectById(Connection conn, String id) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			String sql = "select * from member where memberid =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Date date = new Date(rs.getTimestamp("regdate").getTime());
				Member member = new Member(
								rs.getString("memberid"),
								rs.getString("name"),
								rs.getString("password"),
								date);
				return member;
			}
			return null;
		}finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
			
		}
	}
	
	public int updatePw(Connection conn, Member member) throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "update member set password=? where memberid=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, member.getPassword());
		pstmt.setString(2, member.getId());
		return pstmt.executeUpdate();
		
	}
	
}
