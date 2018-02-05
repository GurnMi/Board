package article.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleService;
import auth.model.User;
import mvc.controller.CommandHandler;

public class AriticleDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		User user = (User)req.getSession().getAttribute("auth");
		String id = user.getId();
		
		String no1 = req.getParameter("no");
		int no =Integer.parseInt(no1); 
				
		ArticleService service = ArticleService.getInstance();
		service.deleteArticle(no);
		
		return "/WEB-INF/view/article/delete.jsp";
	}

}
