package article.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import article.service.ArticleService;
import auth.model.User;
import mvc.controller.CommandHandler;

public class ArticleWriterHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/article/writeForm.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		if(req.getMethod().equalsIgnoreCase("get")){
			return FORM_VIEW;
		}else if(req.getMethod().equalsIgnoreCase("post")){
			
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			User user = (User)req.getSession().getAttribute("auth");
			
			
			ArticleService service = ArticleService.getInstance();
			int result = service.insertArticle(user.getId(), user.getName(), title, content);
			if(result<0){
				req.setAttribute("error", "게시판 저장에 실패하였습니다.");
				return FORM_VIEW;
			}
			
			return "/WEB-INF/view/article/writeSuccess.jsp";
		}
		
		return null;
	}

}
