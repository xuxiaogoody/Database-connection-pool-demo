package 简单连接池demo;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		ConnectionInvoke in = new ConnectionInvoke(20);
		in.prepare("数据库名","用户名","密码");
		Connection conn = (Connection)in.getConnection();
        //这里写业务代码
		System.out.println("CURD");     
        conn.close();
        
	}

}
