package article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mvc.util.jdbcUtil;

public class ArticleContentDao {
	private static final ArticleContentDao dao = new ArticleContentDao();

	public static ArticleContentDao getInstance() {
		return dao;
	}
	
	private ArticleContentDao(){
		
	}
	
	public int insert(Connection conn, ArticleContent content) throws SQLException{
		PreparedStatement pstmt =null;
		
		try{
			String sql = "insert into article_content (article_no, content) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int result = pstmt.executeUpdate();
			if(result > 0){
				return content.getNumber();
			}				
		}finally{
			jdbcUtil.close(pstmt);
		}
		return -1;
	}
	
	
	
	public ArticleContent selectById(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from article_content where article_no=?";
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent article =null;
						
			while(rs.next()){
				article = new ArticleContent(
								rs.getInt("article_no"),
								rs.getString("content")
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
		String sql = "delete from article_content where article_no=?";
		pstmt =conn.prepareStatement(sql);
		pstmt.setLong(1, no);		
		int result = pstmt.executeUpdate();
		
		return result;
		
	} finally {
		// TODO: handle finally clause
		jdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, ArticleContent content){
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update article_content set content=? where article_no?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content.getContent());
			pstmt.setInt(2, content.getNumber());
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
	
	
	
}
