package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import member.model.Member;
import member.model.MemberDao;
import mvc.util.ConnectionProvider;
import mvc.util.jdbcUtil;

public class MemberService {
	private static final MemberService instance = new MemberService();
	
	public static MemberService getInstance() {
		return instance;
	}
	
	public MemberService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// -2 : duplicateId
	public int insertMember(Member member){
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao =  MemberDao.getInstance();
			
			//id중복체크
			Member existMember= dao.selectById(conn, member.getId());
			if(existMember != null){
				return -2;
			}
			
			return dao.insert(conn, member);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(conn);
		}		
		return -1;
	}
	
	public int ChangePasswordService(String id, String password, String newpw){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			MemberDao dao =  MemberDao.getInstance();
			
			Member member = dao.selectById(conn, id);
			
			if(member.getPassword().equals(password) ==false){
				return -3;//비밀번호 불일치
			}
			member.changePassword(newpw);
			dao.updatePw(conn, member);
			return 1;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			jdbcUtil.close(conn);
		}
		return -1;
	}
}
