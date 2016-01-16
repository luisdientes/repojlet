package stock;


import stock.bd.ListMarcasBD;
import stock.bd.ListMarcasBDIn;
import stock.bd.ListPhonesPiezasBD;
import stock.bd.ListPhonesPiezasBDIn;
import stock.bd.ListPiezasImgBD;
import stock.bd.ListPiezasImgBDIn;
import utils.PropiedadesJLet;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;



public class ListPiezasImgSrv extends Service {

    public ListPiezasImgSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();	
			input.addVariable("idemisor");
			input.addVariable("cdestado");
			input.addVariable("tipocons");
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("logoemis");
			input.addVariable("idmarcax");
			input.addVariable("tpproduc");
			input.addVariable("idgrupox");
			input.addVariable("listpiec");
			

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
			output.addVariable("cdestado");
			output.addVariable("idclient");
			output.addVariable("tpclient");
			output.addVariable("logoemis");
			output.addVariable("txmarcax");
			output.addVariable("tpproduc");
			output.addVariable("idgrupox");
			output.addVariable("listpiec");
			output.addVariable("gridProd");
			output.addVariable("grPrAgru");
			output.addVariable("gridMarc");
			output.addVariable("gridClie");
			output.addVariable("gridPhon");
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String idemisor = null;
    	String cdestado = null;
    	String tipocons = null;
    	String idclient = null;
    	String tpclient = null;
    	String logoemis = null;
    	String idmarcax = null;
    	String tpproduc = null;
    	String idgrupox = null;
        String listpiec = null;
        //Varibales de salida
    	Grid gridProd = null;
    	Grid grPrAgru = null; 
    	Grid gridMarc = null;
    	Grid gridClie = null;
    	Grid gridPhon = null;
    	
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	cdestado = input.getStringValue("cdestado");
        	tipocons = input.getStringValue("tipocons");
        	idclient = input.getStringValue("idclient");
        	tpclient = input.getStringValue("tpclient");
        	logoemis = input.getStringValue("logoemis"); 
        	idmarcax = input.getStringValue("idmarcax"); 
        	tpproduc = input.getStringValue("tpproduc"); 
        	idgrupox = input.getStringValue("idgrupox"); 
        	listpiec = input.getStringValue("listpiec"); 
	        try {
	        	 
	        	ListPiezasImgBDIn lineasBDIn= new ListPiezasImgBDIn();
	        	lineasBDIn.setValue("idgrupox", idgrupox);
	        	ListPiezasImgBD lineasBD = new ListPiezasImgBD(lineasBDIn);
				lineasBD.setConnection(con);
				gridProd = lineasBD.execSelect();
	 
	        	
	        	ListMarcasBDIn listMarcaBDIn  = new ListMarcasBDIn();  
	        	listMarcaBDIn.setValue("idemisor", idemisor);
	        	ListMarcasBD listMarcasBD = new ListMarcasBD(listMarcaBDIn);
	        	listMarcasBD.setConnection(con);
	        	gridMarc= listMarcasBD.execSelect();
	        	
	        	
	        	String piezasph = PropiedadesJLet.getInstance().getProperty("stock.phones.pieces"); 
	        	
	        	ListPhonesPiezasBDIn piezasPhoneBDIn = new ListPhonesPiezasBDIn();
	        	piezasPhoneBDIn.setValue("piezasph", piezasph);
	        	ListPhonesPiezasBD piezasPhoneBD = new ListPhonesPiezasBD(piezasPhoneBDIn);
	        	piezasPhoneBD.setConnection(con);
	        	gridPhon = piezasPhoneBD.execSelect();
	        	

	        } catch (Exception e) {
				System.err.println(this.getClass().getName() +" ERROR recogiendo productos. "+ e.getMessage());
			}
    
	        output.setValue("cdestado", cdestado);
        	output.setValue("idemisor", idemisor);
        	output.setValue("idclient", idclient);
        	output.setValue("tpclient", tpclient);
        	output.setValue("logoemis", logoemis);
        	output.setValue("txmarcax", idmarcax);
        	output.setValue("idgrupox", idgrupox);
        	output.setValue("tpproduc", tpproduc);
        	output.setValue("listpiec", listpiec);
		    output.setValue("gridProd", gridProd);
		    output.setValue("gridClie", gridClie);
		    output.setValue("gridMarc", gridMarc);
		    output.setValue("gridPhon", gridPhon);
		    
		    
		    
		    output.setValue("txmensaj", "OK");

        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
}
	