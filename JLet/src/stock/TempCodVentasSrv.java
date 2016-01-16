package stock;


import stock.bd.UpdCodVentasBD;
import stock.bd.UpdCodVentasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;


public class TempCodVentasSrv extends Service {

    public TempCodVentasSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("codprodu");
			input.addVariable("txmarcax");
			input.addVariable("txdescri");
			input.addVariable("impdefve");
			input.addVariable("cantidad");
			input.addVariable("tpproduc");
			input.addVariable("cddivisa");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("txmensaj");
			output.addVariable("idemisor");
			output.addVariable("gridCodi");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String codprodu = null;
    	String txmarcax = null;
    	String txdescri = null;
    	String impdefve = null;
    	String cantidad = null;
    	String cddivisa = null;
    	String tpproduc = "";
    	Grid gridCodi = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	codprodu = input.getStringValue("codprodu");
        	txmarcax = input.getStringValue("txmarcax");
        	txdescri = input.getStringValue("txdescri");
        	impdefve = input.getStringValue("impdefve");
        	cantidad = input.getStringValue("cantidad");
        	tpproduc = input.getStringValue("tpproduc");
        	cddivisa = input.getStringValue("cddivisa");
        	
        	if(cddivisa != null && cddivisa.equals("EUR")){
        		cddivisa ="&euro;";
        	}
	        try {
	        	
	        	UpdCodVentasBDIn lineasBDIn= new UpdCodVentasBDIn();
	        	lineasBDIn.setValue("idemisor", idemisor);
	        	lineasBDIn.setValue("codprodu", codprodu);
	        	lineasBDIn.setValue("txmarcax", txmarcax);
	        	lineasBDIn.setValue("txdescri", txdescri);
	        	lineasBDIn.setValue("impdefve", impdefve);
	        	lineasBDIn.setValue("cantidad", cantidad);
	        	lineasBDIn.setValue("divisaxx", cddivisa);
	        	lineasBDIn.setValue("tpproduc", tpproduc);
	        	UpdCodVentasBD lineasBD = new UpdCodVentasBD(lineasBDIn);
				lineasBD.setConnection(con);
				int insert = lineasBD.execInsert();
				
				
				Service srv = null;
	        	
	        	ObjectIO srvIn = null;
	        	ObjectIO srvOut = null;
	        	
	        	srv = new ListCodVentasSrv();
	        	srvIn  = srv.instanceOfInput();
				srvOut = srv.instanceOfOutput();
				srvIn.setValue("idemisor", idemisor);
				srv.setConnection(con);
				srv.process(srvIn, srvOut);

				gridCodi = srvOut.getGrid("gridCodi");

	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
        	output.setValue("idemisor", idemisor);
		    output.setValue("gridCodi", gridCodi);
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
		