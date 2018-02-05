package auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class LogOutHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		//getSession(true) : 세션이 이미 있는지 확인을 하며, 이미있다면 그 세션을 반환시키고, 없으면 새로운 세션을 생성한다.
		//getSession(false) : 세션이 있다면 그 세션을 리턴하지만, 세션이 존재하지 않는 다면 null을 리턴한다.(즉 새로 생성하지는 않음).
		HttpSession session = req.getSession(false);
		if(session != null){
			session.invalidate();//세션 닫는 함수
		}
		res.sendRedirect("index.jsp");//리턴에서 주소 반환하면 포워드로 되서 주소가 계속 logout.do로 나오게됨.
									//새로고침하면 계속 재요청되니까 리다이렉트로 처리
		return null;
	}

}
