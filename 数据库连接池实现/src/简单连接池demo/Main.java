package �����ӳ�demo;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {
		ConnectionInvoke in = new ConnectionInvoke(20);
		in.prepare("���ݿ���","�û���","����");
		Connection conn = (Connection)in.getConnection();
        //����дҵ�����
		System.out.println("CURD");     
        conn.close();
        
	}

}
