package myapp;

import java.sql.Connection;
import java.sql.SQLException;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
public class ConnectionPool {
	private static Object obj = new Object();
    private static ConnectionPool instance = null;
   
    private ConnectionPool(){}
    
    public static ConnectionPool getInstance()
    {
        if (instance == null)
        	synchronized (obj)
        	{
        		if (instance == null)
        			instance = new ConnectionPool();
        	}
        return instance;
    }
   
    public Connection getConnection()
    {
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/PoolName");
             c = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}