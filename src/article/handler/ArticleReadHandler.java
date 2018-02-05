package article.handler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContent;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleReadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String no = req.getParameter("no");
		int articleNum = Integer.parseInt(no);
		
		ArticleService service = ArticleService.getInstance();
		HashMap<String, Object> map =service.readArticle(articleNum);
		
		Article article = (Article)map.get("article");
		/*System.out.println(article.toString());
		System.out.println(article.getName());*/
		
		ArticleContent content = (ArticleContent)map.get("content");
		
		req.setAttribute("article", article);
		req.setAttribute("content", content);
		
		
		
		return "/WEB-INF/view/article/read.jsp";
	}

}
