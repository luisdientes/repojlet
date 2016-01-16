package arquitectura.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleConnectionCacheImpl;
import arquitectura.utils.Trace;

public class DBPool {

    public static final String PROPERTY_PREFIX           = "BD.";
    public static final String PROPERTY_DRIVER           = PROPERTY_PREFIX + "driver";
    public static final String PROPERTY_URL            	 = PROPERTY_PREFIX + "url";
    public static final String PROPERTY_USER           	 = PROPERTY_PREFIX + "user";
    public static final String PROPERTY_PASSWORD       	 = PROPERTY_PREFIX + "password";
    public static final String PROPERTY_NAME             = PROPERTY_PREFIX + "name";       
    public static final String PROPERTY_INITIAL_CAPACITY = PROPERTY_PREFIX + "initCapacity"; 
    public static final String PROPERTY_MAX_CAPACITY     = PROPERTY_PREFIX + "maxCapacity"; 
            
    OracleConnectionCacheImpl ocache = null;
    
    /** Creates new DBPool */
    public DBPool(Properties properties) {
        try{
            
            Trace.trace(Trace.DEBUG, "DBPool creating DBPool... ");
            
            // Load driver
            String driver = properties.getProperty(PROPERTY_DRIVER);
            Class.forName(driver);

            Trace.trace(Trace.DEBUG, "Creating Oracle connection cache");
            
            // Create Connection Cache
            ocache = new OracleConnectionCacheImpl();

            // Parametrize Connection Cache w/ Connection Pool parameters & set max connections
            String url 			= properties.getProperty(PROPERTY_URL);
            String user 		= properties.getProperty(PROPERTY_USER);
            String password		= properties.getProperty(PROPERTY_PASSWORD);
            int maxLimit 		= Integer.parseInt(properties.getProperty(PROPERTY_MAX_CAPACITY));
            
            ocache.setURL(url);
            ocache.setUser(user);
            ocache.setPassword(password);
            ocache.setMaxLimit(maxLimit);
            ocache.setCacheScheme(OracleConnectionCacheImpl.DYNAMIC_SCHEME);
            
        } catch(Exception e){
            Trace.trace(Trace.DEBUG, "Fatal Error in RootBD initialization, creating connection cache.\n" + e);
            e.printStackTrace();
        }
    }
    
    // Obtener la conexión con la base de datos
    public Connection getConnection() throws SQLException{
        
        Connection con = null;        
        try{
            
            con = ocache.getConnection();                       
        
        } catch(Exception e){
            e.printStackTrace();
        }

        Trace.trace(Trace.DEBUG, "DBPool.getConnection() - Connections use: " + ocache.getActiveSize() + "/" + ocache.getCacheSize());
                            
        
        return con;
        
    }

    // Liberar la conexión con la base de datos
    public void freeConnection(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
        Trace.trace(Trace.DEBUG, "DBPool.freeConnection() - Connections use: " + ocache.getActiveSize() + "/" + ocache.getCacheSize());
    }
    
    
}
