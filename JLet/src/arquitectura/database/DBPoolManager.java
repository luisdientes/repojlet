package arquitectura.database;

import java.util.Hashtable;
import java.util.Properties;

import arquitectura.utils.Trace;

public class DBPoolManager {

    // Patrón Singleton
    protected static DBPoolManager me = new DBPoolManager();    

    private Hashtable pools;
    private DBPool defaultPool;
    
    /** Creates new DBPoolManager */
    private DBPoolManager() {
        Trace.trace(Trace.DEBUG, "Creating DBPoolManager...");
        
        pools = new Hashtable();
        
   }

    // Patrón singleton
    public static DBPoolManager getInstance(){
        return me;
    }
    
    public void addPool(String name, Properties properties, boolean isDefaultPool){
        DBPool pool = new DBPool(properties);
        this.pools.put(name, pool);
        if(isDefaultPool){
            this.defaultPool = pool;
        }
    }
    
    public DBPool getPool(String poolName){
        return (DBPool)this.pools.get(poolName);
    }
    
    public DBPool getDefaultPool(){    
        return this.defaultPool;
    }

}
