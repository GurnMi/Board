package mvc.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet{
	
	public void init() throws ServletException{
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex){
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
	
	private void initConnectionPool(){
		try{
			String jdbcDriver = "jdbc:mysql://localhost:3306/board?useUnicode=true&characterEncoding=utf8";
			String dbUser = "jspexam";
			String dbpass = "jsppw";
			
			DriverManagerConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcDriver,dbUser,dbpass);
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory((ConnectionFactory) connFactory, null);
			//커넥션을 검사할때 사용할 쿼리를 설정한다. "select 1" 스페이스바 필수.
			//select 1은 Microsoft SQL server에서 권장하는 방법이다.
			poolableConnFactory.setValidationQuery("select 1");
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			//풀에 있는 유휴커넥션 검사 주기를 설정.
			//주기적으로 유휴 커넥션을 풀에서 제거하는 것이 좋다.
			//일반적으로 10~30분주기로 유휴 커넥션 검사를 하도록 시정한다.
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*50L);
			//true이면  PoolableConnectionFactory 의 커넥션 검사 기능을 사용한다.
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);//커넥션 풀이 유지할 최소 유휴 커넥션 개수
			poolConfig.setMaxTotal(50);//풀이 관리하는 커넥션으 최대 개수를 설정
			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)
					DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			//커넥션 풀을 등록시, "chap14"로 설정. db와 같은 이름으로 설정했지만 다름이름도 무관.
			driver.registerPool("board", connectionPool);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
