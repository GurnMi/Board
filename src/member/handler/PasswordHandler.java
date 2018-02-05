package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import member.model.Member;
import member.service.MemberService;
import mvc.controller.CommandHandler;

public class PasswordHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")){
			
			System.out.println();
			return "/WEB-INF/view/passwordForm.jsp";
			
					
		}else if(req.getMethod().equalsIgnoreCase("post")){
			
			User user = (User) req.getSession().getAttribute("auth");
			String id = user.getId();
			
			String password = req.getParameter("password");
			String newpw = req.getParameter("confirmPassword");
			MemberService service = MemberService.getInstance();
			int error = service.ChangePasswordService(id, password, newpw);
			if(error ==-3){//비밀번호 불일치
				req.setAttribute("passNotMatch", "비밀번호가 일치하지 않습니다.");
				return "/WEB-INF/view/passwordForm.jsp";
			}else if(error == 1){
				req.setAttribute("result", "비밀번호가변경되었습니다");
				res.sendRedirect("index.jsp");
				return null;
			}
			
		}
		return null;
	}
	
}
