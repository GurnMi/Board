package article.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class AriticleListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		ArticleService service = ArticleService.getInstance();
		List<Article> list = service.listArticle();
		req.setAttribute("list", list);
		
		return "/WEB-INF/view/article/list.jsp";
	}
	
}
