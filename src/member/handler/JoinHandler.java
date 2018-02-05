package member.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.MemberService;
import mvc.controller.CommandHandler;

//join.co
public class JoinHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/JoinForm.jsp";
					
			
		}else if(req.getMethod().equalsIgnoreCase("post")){
			Member member = new Member(req.getParameter("id"),
									req.getParameter("name"), 
									req.getParameter("password"), 
									new Date());
			MemberService service = MemberService.getInstance();
			int result = service.insertMember(member);
			if(result == -2){
				req.setAttribute("duplicatedId", "이미 사용중인 아이디 입니다.");
				return "/WEB-INF/view/JoinForm.jsp";
			}
			if(result<=0){
				req.setAttribute("notInsert", "회원가입에 실패하였습니다.");
				return "/WEB-INF/view/JoinForm.jsp";
			}
			return "/WEB-INF/view/JoinSuccess.jsp";
		}
		return null;
	}

}
