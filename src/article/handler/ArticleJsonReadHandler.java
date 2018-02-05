package article.handler;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import article.model.Article;
import article.model.ArticleContent;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleJsonReadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		String no = req.getParameter("no");
		int articleNum = Integer.parseInt(no);
		
		ArticleService service = ArticleService.getInstance();
		HashMap<String, Object> map =service.readArticle(articleNum);
		
		
		int error = (int)map.get("error");
		
		ObjectMapper om = new ObjectMapper();
		HashMap<String, Object> sendData = new HashMap<>();
		
		if(error<0){
			sendData.put("result", "fail");
		}else{
			sendData.put("result", "success");
			Article article = (Article)map.get("article");
			ArticleContent content = (ArticleContent)map.get("content");
			sendData.put("article", article);
			sendData.put("content", content);
		}
		//json으로 변환해줌
		String json = om.writeValueAsString(sendData);
		
		res.setContentType("application/json;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.print(json);
		pw.flush();
		
		return null;
	}

}
