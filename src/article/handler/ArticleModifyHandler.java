package article.handler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContent;
import article.service.ArticleService;
import auth.model.User;
import mvc.controller.CommandHandler;

public class ArticleModifyHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){

			String no = req.getParameter("no");
			int articleNum =Integer.parseInt(no); 
			
			ArticleService service = ArticleService.getInstance();
			HashMap<String, Object> map =service.readArticle(articleNum);
			
			Article article = (Article)map.get("article");
			/*System.out.println(article.toString());
			System.out.println(article.getName());*/
			
			ArticleContent content = (ArticleContent)map.get("content");
			
			req.setAttribute("article", article);
			req.setAttribute("content", content);
			
			return "/WEB-INF/view/article/modifyForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")){
			//    Article article = req.getParameter("article");
			String no = req.getParameter("no");
			int articleNum =Integer.parseInt(no); 
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			User user = (User)req.getSession().getAttribute("auth");
			
			ArticleService service = ArticleService.getInstance();
			int result = service.updateArticle(articleNum, title, content);
			
			if(result<0){
				req.setAttribute("error", "수정실패");
				return "/WEB-INF/view/article/modifyForm.jsp";
			}
			return "/WEB-INF/view/article/modify.jsp";
		}
		return null;
	}

}
