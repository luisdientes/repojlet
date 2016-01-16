/*
 * Command.java
 *
 * Created on 4 de abril de 2002, 14:10
 */

package arquitectura.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import arquitectura.database.DBPoolManager;
import arquitectura.objects.ObjectIO;
import arquitectura.session.SessionHelper;
import arquitectura.utils.Trace;


public abstract class Service {

    protected String moduleName    = "";
    protected String serviceName = "";
    
    protected Connection con = null;
    private String poolName = null;
    private boolean connectionOwner = true;
    
    private SessionHelper sessionHelper; 
    public HttpSession sesion;
    
    //NUEVAS VAR JEJ
    public String username = "NOUSERNAME";
    
    private static final boolean TRACE_ACTIVE = true;
    
    /** Creates new Command */
    public Service() {
      
    	String nombre = this.getClass().getName();
    	if (nombre.lastIndexOf('.') != -1) {
    		moduleName = nombre.substring(nombre.lastIndexOf('.')+1);
    	} else {
    		moduleName = nombre;
    	}
    	
    }

    // package
    public void _process(ObjectIO input, ObjectIO output) throws Exception {    	
    	try
        {
            process(input, output);
        } 
        catch(Exception se)
        {
            se.printStackTrace();
            throw se;
        } finally
        {
            try
            {
                freeConnection();
            } 
            catch(Exception e)
            {
                throw new Exception(e);
            }
        }
    }
    
    public abstract void process(ObjectIO input, ObjectIO output) throws Exception;
    
    public ObjectIO instanceOfInput(){
        // Por defecto se devuelve un ObjectIO genérico
        //  el servicio deberá implementar este método para devolver una instancia de
        //  la clase interfaz de entrada derivada de ObjectIO
        return new ObjectIO();
    }
    
    public ObjectIO instanceOfOutput(){
        // Por defecto se devuelve un ObjectIO genérico
        //  el servicio deberá implementar este método para devolver una instancia de
        //  la clase interfaz de salida derivada de ObjectIO
        return new ObjectIO();
    }
    
    public void setSesion(HttpSession reqsesion) throws Exception {
        
    	try  {
    		sesion = reqsesion;
    		username = (String)sesion.getAttribute("username");
    	} catch (Exception e) {
    		
    	}
    }
    
    protected Object getSessionParameter(ObjectIO input, String name) throws Exception {
        SessionHelper sessionHelper = (SessionHelper)input.getValue("sessionHelper");
        if(sessionHelper!=null)
            return sessionHelper.getValue(name);        
        else
            return null;
    }
    
    protected String getStringSessionParameter(ObjectIO input, String name) throws Exception {
        SessionHelper sessionHelper = (SessionHelper)input.getValue("sessionHelper");
        if(sessionHelper!=null)
            return (String)sessionHelper.getStringValue(name);        
        else
            return null;
    }
    
    protected String getStringSessionParameter(ObjectIO input, String name, String def) throws Exception {
        SessionHelper sessionHelper = (SessionHelper)input.getValue("sessionHelper");
        if(sessionHelper!=null)
            return sessionHelper.getStringValue(name, def);        
        else
            return def;
    }
    
    protected void setSessionParameter(ObjectIO input, String name, Object value) throws Exception {
        SessionHelper sessionHelper = (SessionHelper)input.getValue("sessionHelper");
        if(sessionHelper!=null)
            sessionHelper.setValue(name, value);        
    }

    protected Object getSessionParameter(String name) throws Exception {
        if(sessionHelper!=null)
            return sessionHelper.getValue(name);
        else
            return null;
    }
    
    protected String getStringSessionParameter(String name) throws Exception {
        if(sessionHelper!=null)
            return (String)sessionHelper.getStringValue(name);        
        else
            return null;
    }
    
    protected String getStringSessionParameter(String name, String def) throws Exception {
        if(sessionHelper!=null)
            return sessionHelper.getStringValue(name, def);        
        else
            return def;
    }
    
    protected void setSessionParameter(String name, Object value) throws Exception {
        if(sessionHelper!=null)
            sessionHelper.setValue(name, value);        
    }
        
    // Establecer la conexión con la base de datos
    public void setConnection(Connection con) throws SQLException{

        // Si no se ha pedido/establecido ya, pedirla
        if(this.con==null){
            connectionOwner = false;
            this.con = con;
        } else{
            throw new SQLException("El servicio ya tinene una conexión");
        }
        
    }
    
    // Obtener la conexión con la base de datos
    protected Connection getConnection() throws SQLException{

        // Si no se ha pedido ya, pedirla
        if(this.con==null){
            try{
                this.con = DBPoolManager.getInstance().getDefaultPool().getConnection();
                connectionOwner = true;
                con.setAutoCommit(false);
            } catch(SQLException e){
                throw(e);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return this.con;
        
    }

    // Obtener la conexión con la base de datos
    protected Connection getConnection(String poolName) throws SQLException{

        // Si no se ha pedido ya, pedirla
        if(this.con==null){
            try{
                this.con = DBPoolManager.getInstance().getPool(poolName).getConnection();
                connectionOwner = true;
                this.poolName = poolName;
                con.setAutoCommit(false);
            } catch(SQLException e){
                throw(e);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return this.con;
        
    }
    
    protected Locale getLocale(){
        Locale locale = null;
        try{
            locale = (Locale)getSessionParameter("localeObject");
            
        } catch(Exception e){
        } finally{
            if(locale==null){
                locale = Locale.getDefault();            
            }
        }
        return locale;
    }
    
    public void setSessionHelper(SessionHelper sessionHelper){
        this.sessionHelper = sessionHelper;
    }

    public SessionHelper getSessionHelper(){
        return sessionHelper;
    }    
    
    // Liberar la conexión con la base de datos
    protected void freeConnection() throws SQLException {
        if(this.con!=null && connectionOwner){            
            if(poolName!=null){
                DBPoolManager.getInstance().getPool(this.poolName).freeConnection(this.con);
            } else{
                DBPoolManager.getInstance().getDefaultPool().freeConnection(this.con);
            }
        }                   
        this.poolName = null;
        this.con = null;
    }
    
    protected void trace(String traceStr){
        if(TRACE_ACTIVE){
            Trace.trace(Trace.DEBUG, traceStr);
        }
    }
    

    public void commitConnection() throws SQLException
    {
      
      if (connectionOwner)
      {
        if (con != null)
        {
          try
          {
            con.commit();
          }
          catch(Exception excep)
          {
            throw new SQLException("No se puede realizar el commit de la trasaccion");
          }
        }
      }
    }

    public void rollbackConnection() throws SQLException
    {
      if (connectionOwner)
      {
        if (con != null)
        {
          try
          {
            con.rollback();
          }
          catch(Exception excep)
          {
            throw new SQLException("No se puede realizar el rollback de la trasaccion");
          }
        }
      }
    }    
    
    public void setModuleName(String moduleName)
    {
      this.moduleName = moduleName;
    }
    
    public String getModuleName()
    {
      return moduleName;
    }
    
}
