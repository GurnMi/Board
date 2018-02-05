package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import member.model.Member;
import member.model.MemberDao;
import mvc.util.ConnectionProvider;
import mvc.util.jdbcUtil;

public class AuthService {
	private static final AuthService instance = new AuthService();

	public static AuthService getInstance() {
		return instance;
	}

	public AuthService() {
		// TODO Auto-generated constructor stub
	}
	
	//-2 : notJoin 회원없음
	//-3 : 비밀번호 불일치
	//1 : 정상로그인
	public HashMap<String, Object> checkLoginMember(String id, String password){
		Connection conn = null;
		MemberDao dao = MemberDao.getInstance();
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Member member = dao.selectById(conn, id);
			if(member == null){
				map.put("error", -2);
				return map;	//회원없음
			}
			
			if(member.getPassword().equals(password) ==false){
				map.put("error", -3);
				return map;//비밀번호 불일치
			}
			map.put("error", 0);
			map.put("member", member);
			return map;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("error", -1);
		}finally{
			jdbcUtil.close(conn);
		}
		//catch에서 오류남
		return map;
	}
	
	
}
