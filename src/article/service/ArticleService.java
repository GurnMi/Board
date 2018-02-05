package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import article.model.Article;
import article.model.ArticleContent;
import article.model.ArticleContentDao;
import article.model.ArticleDao;
import mvc.util.ConnectionProvider;
import mvc.util.jdbcUtil;

public class ArticleService {
	private static final ArticleService service = new ArticleService();
	private static final String log = "[ArticleService]";

	public ArticleService() {
		// TODO Auto-generated constructor stub
	}

	public static ArticleService getInstance() {
		return service;
	}

	public int insertArticle(String id, String name, String title, String content) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getInstance();

			Date date = new Date();
			Article article = new Article(0, id, name, title, date, date, 0);

			int newArticleNo = articleDao.insert(conn, article);
			if (newArticleNo < 0) {
				System.out.println(log + "insert article fail");
				return -2; // insert article fail
			}

			ArticleContent articlecontent = new ArticleContent(newArticleNo, content);
			int newContentNo = contentDao.insert(conn, articlecontent);
			if (newContentNo < 0) {
				System.out.println(log + "insert article content fail");
				return -3; // insert article content fail
			}

			conn.commit();
			return 0;

		} catch (Exception e) {
			jdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			jdbcUtil.close(conn);
		}

		return -1;
	}
	
	public int updateArticle(int no, String title, String content){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getInstance();

			Date date = new Date();
			Article article = new Article();
			article.setNumber(no);
			article.setTitle(title);
			
			int newArticleNo = articleDao.update(conn, article);
			if (newArticleNo < 0) {
				System.out.println(log + "update article fail");
				return -2; // insert article fail
			}
			
			ArticleContent artcontent = new ArticleContent(no, content);
			int newArticleNo1 = contentDao.update(conn, artcontent);
			if (newArticleNo1 < 0) {
				System.out.println(log + "update articleContent fail");
				return -3; // insert article fail
			}
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			jdbcUtil.close(conn);
		}
		return -1;
	}

	public int deleteArticle(int no) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();

			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getInstance();

			int result = articleDao.delete(conn, no);
			if (result <0) {
				System.out.println(log + "delete article fail");
				return -3;
			}
			int result1 = contentDao.delete(conn, no);
			if (result1 < 0) {
				System.out.println(log + "delete article content fail");
				return -2;
			}
			conn.commit();
			return 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			jdbcUtil.close(conn);
		}
		return -1;
	}

	public List<Article> listArticle() {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			return dao.listArticle(conn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtil.close(conn);
		}
		return null;
	}

	public HashMap<String, Object> readArticle(int articleNum) {
		Connection conn = null;
		HashMap<String, Object> map = new HashMap<>();
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getInstance();

			Article article = articleDao.selectById(conn, articleNum);
			if(article ==null){
				map.put("error", -2);
				return map;
			}
			
			ArticleContent content = contentDao.selectById(conn, articleNum);
			if(content==null){
				map.put("error", -3);
				return map;
			}
			
			map.put("error", 0);
			map.put("article", article);
			map.put("content", content);

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", -1);
		} finally {
			jdbcUtil.close(conn);
		}
		return map;
	}

}
