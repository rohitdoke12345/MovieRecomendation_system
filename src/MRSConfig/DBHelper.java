package MRSConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
public class DBHelper {
	protected DBconfig db = DBconfig.getDBInstance();
    protected Connection conn=DBconfig.getconnection();
    protected PreparedStatement stmt=DBconfig.getStatement();
    protected ResultSet rs=DBconfig.getResultSet();
    protected Properties properties =DBconfig.getproperties() ;
}
