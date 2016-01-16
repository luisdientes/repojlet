package arquitectura.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import arquitectura.session.SessionHelper;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class RequestHandler implements Controller  {
	
	private static final String SERVICES_DELIMITER = "."; 
	
	protected HttpServletRequest request;
	protected Object response;	
	protected SessionHelper sessionHelper;	
	protected String view;
	
	
	public abstract ObjectIO  throwService(String name, ObjectIO input) throws Exception;
	public abstract void buildFormat (ObjectIO output, ObjectIO format) throws Exception;
	public abstract void buildInterface (ObjectIO output, ObjectIO format) throws Exception;	
	protected abstract String getViewPackage();
	
	protected HttpSession sesion = null;
	
	public RequestHandler(){
		
	}
	
	public void buildInterface(ObjectIO input) throws Exception{
	 try{
	            View v = getViewInstance(getView());//  (View)Class.forName(getView()).newInstance();
	            v.setRequest(request);
	            v.setResponse(response);            
	            v.process(input);
	        } catch(Exception e){
	            throw new Exception(e);
	        }
	    }
	
	public void process (Object request, Object response) throws Exception{
		this.request = (HttpServletRequest)request;
		this.response = response;
		
		try{
			
			sesion = this.request.getSession();
			ObjectIO input = instanceOfInput();
			buildInput(input);
			
			try {
				String username = sesion.getAttribute("username").toString();
				String services = input.getStringValue("services");
				String view		= input.getStringValue("view");
				pintaTraza(username,services,view);
			} catch (Exception e) {
				System.err.println(" ERROR - Pintando traza [RequestHandler]");
			}
			
			ObjectIO output = instanceOfOutput();
			output = buildOutput(input,output);
			
			
			
			ObjectIO format = instanceOfFormat();			
			buildFormat(output, format);
			
			buildInterface(output);			
			
		} catch (Exception e){
			throw new Exception();
		}
	}
	
	public void buildInput(ObjectIO input) throws Exception {
        try{
        	System.out.println(" PARAMETROS "+request.getParameterNames());
        	System.out.println(" PARAMETROS "+request.getParameterMap());
        	System.out.println(" Controlador ---> "+request.getParameter("controller"));
        	System.out.println(" Servicio ---> "+request.getParameter("services"));
        	System.out.println(" Vista ---> "+request.getParameter("view"));
        	        	
             try{
                    HttpServletRequest rq = request;
                    Grid grid;

                    //Recorrer todas las variables de la llamada http
                    Enumeration e;
                    String var;
                    String[] values; 
                    e=rq.getParameterNames();
                    while (e.hasMoreElements()){
                        var=(String)e.nextElement();
                        values=rq.getParameterValues(var);
                        if (values.length >1){
                            //Creamos un grid
                            grid = new Grid();
                            grid.addColumn(var);
                            for (int i=0; i<values.length; i++){
                                //@fjpr / 2002.05.20 / ArrayList no está sincronizada        
                                //Vector row = new Vector();
                                ArrayList row = new ArrayList();
                                row.add(values[i]);
                                grid.addRow(row);
                            }
                            input.addGrid(var, grid);
                        }
                        else{
                            // Lo introducimos en la tabla hash.
                            if (values.length > 0)
                                input.addVariable(var, values[0]);
                            else
                                input.addVariable(var, null);
                        }
                    }

                    //Introducimos la sesión como parámetro,
                    // *** solo válido para canal http.
                    // para pruebas
                    input.addVariable("session", rq.getSession());
                    // *** solo válido para canal http.

                    // Recuperar el sessionHelper de la sesión HTTP e incorporarlo a la entrada
                    //  (creándolo si no existe)
                    HttpSession session = rq.getSession();
                    sessionHelper = (SessionHelper)session.getAttribute("sessionHelper");
                    if(sessionHelper==null){
                        sessionHelper = instanceOfSessionHelper();
                        session.setAttribute("sessionHelper", sessionHelper);
                    }
                    input.addVariable("sessionHelper", sessionHelper);
                    
                    // Recuperar la vista
                    setView(request.getParameter("view"));
                    //this.asyncview = getRequest().getParameter("asyncview");
                    
                } catch(Exception e){
                    throw new Exception(e);
                }            
        	
        }
        catch(Exception e){
            throw new Exception(e);
        }
        
    }
	
	public ObjectIO buildOutput(ObjectIO input, ObjectIO output) throws Exception {
        ObjectIO outputReturn = _buildOutput(input, output, "services");
		return outputReturn;
    }
	
	public ObjectIO instanceOfInput(){
		return new ObjectIO();
	}
	
	public ObjectIO instanceOfOutput(){
		return new ObjectIO();
	}

	public ObjectIO instanceOfFormat(){
		return new ObjectIO();
	}
	
	protected SessionHelper instanceOfSessionHelper(){
		return new SessionHelper();
	}
	
	protected void setView(String view){
		this.view = view;
	}
	
	protected String getView(){
		return this.view;
	}
	
	private ObjectIO _buildOutput(ObjectIO input, ObjectIO output, String servicesVar) throws Exception {

        // Parámetros de la petición http en input
        //  controller: el nombre del controlador invocado (derivado de esta clase)
        //  services: cadena de servicios a invocar
        //  view: vista (página jsp de respuesta)
		
		ObjectIO srvOut = null;
        try{
            // La entrada también forma parte de la salida
            //input.scanVars(output, IOobject.SCAN_FOR_COPY, null);

            // Lanzamiento de servicios

            // Salida del último servicio lanzado            

            // Descomponer la cadena de servicios y lanzarlos        
            String services = input.getStringValue(servicesVar);
            StringTokenizer st = new StringTokenizer(services, SERVICES_DELIMITER);
            while (st.hasMoreTokens()) {
                String serviceName = st.nextToken();        
                
                
                long t0 = System.currentTimeMillis(); 

                // La salida del servicio anterior puede estar esperándose como entrada del servicio
                //srvOut.scanVars(input, IOobject.SCAN_FOR_COPY, null);

                // Invocar al servicio
                srvOut = throwService(serviceName, input);

                // Traza de la salida del servicio

                // Traza de la salida del servicio
                //Trace.trace(Trace.DEBUG, "Service output");
                //output.trace();

            }
            
        } catch(Exception e){
            throw new Exception(e);
        }
		return srvOut;
    }
	
	
	
	
	private View getViewInstance(String viewName) throws Exception {
        
		Exception lastException = null;
        View view = null;
        Iterator i = getViewPackages().iterator();
        String message = "";
        
        while (i.hasNext() && view==null) {
            String pack = (String) i.next();            
            try {
                Class cls = Class.forName(pack + "." + viewName);
                view = (View)cls.newInstance();
            } catch (Exception e) {

                view = null;
                lastException = e;
                message = message + e.getMessage() + "\n";
            }
        }
        
        if (view==null) {
            if (lastException==null){
                message = "Paquete no encontrado para la vista";
            }
            throw new Exception(message);
        }
        
        return view;
    }
    
	protected Collection getViewPackages(){        
        Vector packages = new Vector();
        packages.add(getViewPackage());
        return packages;
    }
	
	public HttpSession getSession(){
		return sesion;
	}
    
	public Connection getConnnection(String databaseType, String cduserxx, String password, String db_schema, String nameServ, int port){
	    
		Connection con = null;
		
    	try {
    		
    		if (databaseType.equals("ORACLE")){
    			
    			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
    		    con = DriverManager.getConnection("jdbc:oracle:thin:@"+nameServ+":"+port+":"+db_schema,cduserxx,password);

    		} 
    		
    		if (databaseType.equals("MYSQL")){
    			
			    MysqlDataSource dataSource = new MysqlDataSource();
				dataSource.setUser(cduserxx);
				dataSource.setPassword(password);
				dataSource.setDatabaseName(db_schema);				
				dataSource.setServerName(nameServ);
				dataSource.setPort(port);
				
				con = dataSource.getConnection();
    		}
    		
			return con;    
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
    }
	
	public void pintaTraza(String username, String services, String view){

		DecimalFormat formatFecha = new DecimalFormat("00");
		
		try {
			Calendar c = new GregorianCalendar();
		
			String dia = formatFecha.format(c.get(Calendar.DATE));
			String mes = formatFecha.format(c.get(Calendar.MONTH));
			String ano = Integer.toString(c.get(Calendar.YEAR));
			String seg = formatFecha.format(c.get(Calendar.SECOND));
			String min = formatFecha.format(c.get(Calendar.MINUTE));
			String hor = formatFecha.format(c.get(Calendar.HOUR_OF_DAY));
			
			String fechahor = dia + "/" + mes + "/" + ano + " " + hor + ":" + min + ":" + seg; 
			
			System.out.println("-:[TR@Z@]:- "+ fechahor +" - :[USER]:"+ username +";"+" - :[SERV]:"+ services +";"+" - :[VIEW]:"+ view +";");
		} catch (Exception e) {
			System.err.println("EROR PINTANDO TRAZA [U]"+ username +"[S]"+ services +"[V]"+ view);
		}
		
	}

	
	
	
	
	
	
}
