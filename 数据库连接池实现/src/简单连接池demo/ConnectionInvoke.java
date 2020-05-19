package �����ӳ�demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
public class ConnectionInvoke implements InvocationHandler{
	private Connection c;
	private Integer maxconn;	
	final LinkedList<Connection> list = new LinkedList<>();
	
	public ConnectionInvoke(Integer maxconn) {
		this.maxconn = maxconn;
	}
	//���������ż���Ԥ��ʼ������
	public void prepare(String database,String user,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for(int i=0;i<maxconn;i++) {
				list.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, user, password));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("��ʼ�����ӳأ���ǰ��������"+list.size());
	}
	public synchronized Object getConnection() {
		this.c = list.removeFirst();	
		System.out.println("��ȡ���ӳɹ������ӳص�ǰ����������"+list.size());
		Class<? extends Connection> cl = c.getClass();
		return Proxy.newProxyInstance(cl.getClassLoader(), cl.getInterfaces(),this);
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (!method.getName().equals("close")) {
			method.invoke(c, args);
		} else {
			list.add(c);
			System.out.println("���ӷŻ����ӳأ����ӳص�ǰ����������"+list.size());
		}
		return null;
	}

}
