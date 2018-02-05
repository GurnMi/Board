package article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.util.jdbcUtil;

public class ArticleDao {
	private static final ArticleDao dao = new ArticleDao();

	public static ArticleDao getInstance() {
		return dao;
	}
	
	private ArticleDao(){
	}
	
	public int insert(Connection conn, Article article) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "insert into article" +
						"(writer_id, writer_name, title, regdate, moddate, read_cut)"+
						"values (?,?,?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setTimestamp(4,toTimeStamp(article.getRegDate()));
			pstmt.setTimestamp(5, toTimeStamp(article.getModifiedDate()));
			int result = pstmt.executeUpdate();
			
			if(result>0){
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article");
				if(rs.next()){
					int newNo = rs.getInt(1);
					return newNo;
				}
			}
			
		}finally{
			jdbcUtil.close(rs);
			jdbcUtil.close(stmt);
			jdbcUtil.close(pstmt);			
		}
		return -1;
	}
	
	public List<Article> listArticle(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from article order by article_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Article> list = new ArrayList<Article>();
			while(rs.next()){
				Article article = new Article(
										rs.getInt("article_no"),
										rs.getString("writer_id"),
										rs.getString("writer_name"),
										rs.getString("title"),
										rs.getTimestamp("regdate"),
										rs.getTimestamp("moddate"),
										rs.getInt("read_cut")
										);
				list.add(article);
				
			}
			return list;
			
		} finally {
			// TODO: handle finally clause
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		
	}
	
	public Article selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from article where article_no=?";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			Article article =null;
						
			while(rs.next()){
				article = new Article(
								rs.getInt("article_no"),
								rs.getString("writer_id"),
								rs.getString("writer_name"),
								rs.getString("title"),
								rs.getTimestamp("regdate"),
								rs.getTimestamp("moddate"),
								rs.getInt("read_cut")
								);
			}
			return article;
			
		} finally {
			// TODO: handle finally clause
			jdbcUtil.close(rs);
			jdbcUtil.close(pstmt);
		}
		
	}
	
	public int delete(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt =null;
	try {
		
		String sql = "delete from article where article_no=?";
		pstmt =conn.prepareStatement(sql);
		pstmt.setLong(1, no);		
		int result = pstmt.executeUpdate();
		
		return result;
		
	} finally {
		// TODO: handle finally clause
		jdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, Article article){
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update article set title=? where article_no?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setInt(2, article.getNumber());
			int result = pstmt.executeUpdate();
			if(result<0){
				return -2;
			}
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return -1;
	}
	
	
	private Timestamp toTimeStamp(Date date){
		return new Timestamp(date.getTime());
	}
}	
