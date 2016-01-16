package arquitectura.webchannel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import arquitectura.controller.ServiceRequestHandler;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import arquitectura.session.SessionHelper;

public abstract class HttpHandler extends ServiceRequestHandler {
	
	public static final String SERVICES_DELIMITER = ".";

	protected Locale locale 		 = Locale.getDefault();
	protected String datePattern 	 = "dd/MM/yyyy";
	protected char dateSeparator 	 = '/';
	protected char decimalSeparator  = ',';
	protected char thousandSeparator = '.';
	
	String userLog = "";
	String cdrolxx = "";
	
	public HttpHandler(){
	
	}
	
	public HttpServletRequest getRequest(){
		return (HttpServletRequest)request;
	}
	
	public HttpServletResponse getResponse(){
		return (HttpServletResponse)response;
	}
	
	public void buildInput(ObjectIO input) throws Exception{
		try{
			HttpServletRequest rq = getRequest();
			Grid grid;
			
			Enumeration e;
			String var;
			String[] values;
			e = rq.getParameterNames();
						
			while (e.hasMoreElements()){
				var = (String)e.nextElement();
				values = rq.getParameterValues(var);
				if (values.length > 1){
					grid = new Grid();
					grid.addColumn(var);
					for (int i=0; i < values.length; i++){
						ArrayList row = new ArrayList();
						row.add(values[i]);
						grid.addRow(row);
					}
					//input.addGrid(var, grid);
				} else {
					if (values.length > 0){
						input.addVariable(var, values[0]);
					} else {
						input.addVariable(var, null);
					}
				}				
			}
			
			HttpSession session = rq.getSession();
			sessionHelper = (SessionHelper)session.getAttribute("sessionHelper");
			if (sessionHelper == null){
				sessionHelper = instanceOfSessionHelper();
				session.setAttribute("sessionHelper", sessionHelper);
			}
			input.addVariable("sessionHelper", sessionHelper);
			setView(getRequest().getParameter("view"));
			
		} catch (Exception e){
			throw e;
		}
	}
	
	public ObjectIO buildOutput(ObjectIO input, ObjectIO output) throws Exception{
		ObjectIO outputReturn = _buildOutput(input, output, "services");
		return outputReturn;
	}
	
	public ObjectIO _buildOutput(ObjectIO input, ObjectIO output, String servicesVar) throws Exception{
		
		ObjectIO srvOut = output;
		
		try{
			
			
			String services = input.getStringValue(servicesVar);
			StringTokenizer st = new StringTokenizer(services,SERVICES_DELIMITER);
			
			while (st.hasMoreTokens()){
				String serviceName = st.nextToken();
				
				if (srvOut != null){
					srvOut = throwService(serviceName, input);
					output = srvOut;
				}
				
			}
			
			
			
			HttpSession session = getRequest().getSession();
			SessionHelper sessionHelper = (SessionHelper)input.getValue("sessionHelper");
			session.setAttribute("sessionHelper", sessionHelper);
			
		} catch (Exception e){
			
		}
		return srvOut;
	}
	
	private void prepareFormat(){
		
		try{
			Locale newLocale = (Locale)sessionHelper.getValue("localeObject");
			if (newLocale != null){
				this.locale = newLocale;
			}
			
			ResourceBundle formatBundle = getFormatProperties();
			
			String newDatePattern = formatBundle.getString("datePattern");
			if (newDatePattern != null){
				datePattern = newDatePattern;
			}

			Character newDateSeparator = new Character(formatBundle.getString("dateSeparator").charAt(0));
			if (newDateSeparator != null){
				dateSeparator = newDateSeparator.charValue();
			}
		
			Character newDecimalSeparator = new Character(formatBundle.getString("decimalSeparator").charAt(0));
			if (newDecimalSeparator != null){
				decimalSeparator = newDecimalSeparator.charValue();
			}
			
			Character newThousandSeparator = new Character(formatBundle.getString("thousandSeparator").charAt(0));
			if (newThousandSeparator != null){
				thousandSeparator = newThousandSeparator.charValue();
			}
			
		} catch (Exception e){
			
		}
	}
	
	public void buildInterface(ObjectIO output) throws Exception{
		
		String view = getView();
		if (view == null){
			System.out.println("-:[WARNING]:- Parametros de la vista no recibidos.");
		} else {
			String aux = view.toLowerCase();
			if (aux.endsWith(".jsp") || aux.endsWith(".htm") || aux.endsWith(".html") || aux.endsWith(".php")){
				forward(view,output);
			} else {
				super.buildInterface(output);
			}
		}
				
	}
	
	public void buildFormat(ObjectIO output, ObjectIO format) throws Exception{
		
		try{
			prepareFormat();			
		} catch (Exception e){
			throw e;
		}
	}
	
	private void forward(String view, Object io) throws Exception{
		
		HttpServletRequest req  = getRequest();
		HttpServletResponse res = getResponse();
		
		req.setAttribute("io",io);
	
		HttpSession session = req.getSession();	
		sessionHelper = (SessionHelper)session.getAttribute("sessionHelper");
		if (sessionHelper == null){
			sessionHelper = instanceOfSessionHelper();
			session.setAttribute("sessionHelper", sessionHelper);
		}
		
		try {
			userLog = sessionHelper.getStringValue("usuario");
			cdrolxx = sessionHelper.getStringValue("rol");
		} catch (Exception e){
			
		}
		
		try {
			RequestDispatcher rd = req.getRequestDispatcher(view);
			if (rd != null){
				rd.forward(req,res);
			}
		} catch (Exception e){
			throw e;
		}
	}
	
	public Object formatValue(Object obj){
		
		Object objFormatted;
		String value = "";
		objFormatted = (Object)value;
		if (obj != null){
			if ((obj instanceof java.lang.Integer) || (obj instanceof java.lang.Long) || (obj instanceof java.math.BigInteger)){
				value = obj.toString();
				objFormatted = value;
			} else if ((obj instanceof java.lang.Double) || (obj instanceof java.lang.Float)){
				DecimalFormat nf = (DecimalFormat)NumberFormat.getNumberInstance(locale);
				nf.getDecimalFormatSymbols().setDecimalSeparator(decimalSeparator);
				nf.getDecimalFormatSymbols().setDecimalSeparator(thousandSeparator);
				value = nf.format(obj);
				objFormatted = value;
			} else if (obj instanceof java.math.BigDecimal){
				BigDecimal bd = (BigDecimal)obj;
				DecimalFormat nf = (DecimalFormat)NumberFormat.getNumberInstance(locale);
				DecimalFormatSymbols dfs = nf.getDecimalFormatSymbols();
				dfs.setDecimalSeparator(decimalSeparator);
				dfs.setGroupingSeparator(thousandSeparator);
				nf.setDecimalFormatSymbols(dfs);
				nf.setMinimumFractionDigits(bd.scale());
				nf.setMaximumFractionDigits(bd.scale());
				value = nf.format(bd);
				objFormatted = value;				
			} else if (obj instanceof java.util.Date){
				SimpleDateFormat df = new SimpleDateFormat(datePattern, locale);
				value = df.format((Date)obj);
				objFormatted = value;
			} else if (obj instanceof Grid){
				//Incluir el SCANROWS
				//((Grid)obj).scanRows(this,SCAN_FOR_FORMAT,null);				
			} else {
				value = obj.toString();
				objFormatted = value;
			}
		}
		return objFormatted;
	}
	
	public void scan(Grid grid, int id, int rowIndex, int colIndex, String colName, Object value, Object user){
		
		try{
			Object obj = formatValue(value);
			grid.setCell(rowIndex,colIndex,obj);
		} catch (Exception e){
						
		}
	}
	
}
