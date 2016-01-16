package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.FactAlbaraBD;
import factura.bd.FactAlbaraBDIn;
import factura.bd.FactDevolucionBD;
import factura.bd.FactDevolucionBDIn;
import factura.bd.ListAreasneBD;
import factura.bd.ListAreasneBDIn;
import factura.bd.ListClientesBD;
import factura.bd.ListClientesBDIn;
import factura.bd.ListFormaPagoBD;
import factura.bd.ListFormaPagoBDIn;
import factura.bd.ListTipoFacBD;
import factura.bd.ListTipoFacBDIn;
import factura.bd.MaxFechaFacturaBD;
import factura.bd.MaxFechaFacturaBDIn;
import facturacion.bd.ListTipoFacturaBD;
import facturacion.bd.ListTipoFacturaBDIn;


public class VistaPreviaDevFacturaSrv extends Service {

    public VistaPreviaDevFacturaSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			input.addVariable("idclient");
			input.addVariable("tpclient");
			input.addVariable("fechafac");
			input.addVariable("tipoPorc");
			input.addVariable("tipoFact");
			input.addVariable("idemisor");
			input.addVariable("tipovist");
			input.addVariable("idalbara");
			input.addVariable("idtmpfra");
			input.addVariable("tipologo");
			input.addVariable("formpago");
			input.addVariable("condpago");
			input.addVariable("catefact");
			input.addVariable("aniofact");
			input.addVariable("factasoc");
			input.addVariable("idfactur");
			
			
				
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
			output.addVariable("gridLine");
			output.addVariable("idclient");
			output.addVariable("tpclient");
			output.addVariable("gridClie");
			output.addVariable("fechafac");
			output.addVariable("tipoPorc");
			output.addVariable("tipoFact");
			output.addVariable("idemisor");
			output.addVariable("tipovist");
			output.addVariable("gridTpFa");
			output.addVariable("fecmaxfa");
			output.addVariable("cdfactur");
			output.addVariable("tipologo");
			output.addVariable("formpago");
			output.addVariable("condpago");
			output.addVariable("catefact");
			output.addVariable("idtmpfra");
			output.addVariable("idalbara");
			output.addVariable("factasoc");
			output.addVariable("idfactur");
			output.addVariable("gridArne");
			output.addVariable("gridFrPg");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
		String idclient = "";
		String tpclient = "";
		String fechafac = "";
		String tipologo = "";
		String formpago = "";
		String condpago = "";
		String catefact = "";
        
        //Varibales de salida
        String txmensaj = "";
        Grid gridResu = null;
        Grid gridClie = null;
        Grid gridTpFa = null;
        Grid gridFech = null;
        Grid gridArne = null;
        Grid gridFrPg = null;
        String tipoPorc = "";
        String tipofact = "";
        String idemisor = "";
        String tipovist = "";
        String idalbara = "";
        String factasoc = "";
        String idfactur = "";
        String imptaxes = "";
        //Otras Variables
        
		String idtmpfra = "";
		String aniofact = "";
		
        try {

			   idclient = input.getStringValue("idclient");
			   fechafac = input.getStringValue("fechafac");
			   tipoPorc = input.getStringValue("tipoPorc");
			   tipofact = input.getStringValue("tipoFact");
			   idemisor = input.getStringValue("idemisor");
			   tipovist = input.getStringValue("tipovist");
			   idalbara = input.getStringValue("idalbara");
			   tipologo = input.getStringValue("tipologo");
			   formpago = input.getStringValue("formpago");
			   condpago = input.getStringValue("condpago");
			   catefact = input.getStringValue("catefact");
			   aniofact = input.getStringValue("aniofact");
			   factasoc = input.getStringValue("factasoc"); 
			   idfactur = input.getStringValue("idfactur");
			   
   
					/* lineas albaran*/
				   
			   		FactDevolucionBDIn FactAlbaBDIn = new FactDevolucionBDIn();
				    FactAlbaBDIn.setValue("idfactur",idfactur);
				    FactAlbaBDIn.setValue("idemisor",idemisor);
				    FactDevolucionBD lineasAlBD = new FactDevolucionBD(FactAlbaBDIn);
				    lineasAlBD.setConnection(con);
				    gridResu = lineasAlBD.execSelect();
				    
				   // factasoc = gridResu.getStringCell(0, "idclient"); 
				    
				    //para sacar luego los datos del cliente en la Vista previa
				    String idclidet = gridResu.getStringCell(0, "idclient"); 
				    tpclient = gridResu.getStringCell(0, "tpclient"); 
						// saco el idtmpfra del idalbaran que halla introducido
					idtmpfra = gridResu.getStringCell(0, "idtmpfra");
					fechafac = gridResu.getStringCell(0, "fechafac");
					imptaxes = gridResu.getStringCell(0, "imptaxes");
					
					
					
					/*TIPO DEVOLUCION IMPUESTO O SIN IMPUESTO*/
					
					/**/
					
					if(imptaxes.equals("0.0")){
						   ListTipoFacturaBDIn ListTipoBDIn  = new ListTipoFacturaBDIn();  
						   ListTipoBDIn.setValue("idemisor", idemisor);
						   ListTipoBDIn.setValue("tipofact", tipofact);
						   ListTipoBDIn.setValue("codespec", "DS");
						   ListTipoFacturaBD ListTipoBD = new ListTipoFacturaBD(ListTipoBDIn);
						   ListTipoBD.setConnection(con);
						   gridTpFa = ListTipoBD.execSelect();
						
						tipofact = gridTpFa.getStringCell(0, "tipofact");
					}else{
						
						ListTipoFacturaBDIn ListTipoBDIn  = new ListTipoFacturaBDIn();  
						   ListTipoBDIn.setValue("idemisor", idemisor);
						   ListTipoBDIn.setValue("tipofact", tipofact);
						   ListTipoBDIn.setValue("codespec", "DI");
						   ListTipoFacturaBD ListTipoBD = new ListTipoFacturaBD(ListTipoBDIn);
						   ListTipoBD.setConnection(con);
						   gridTpFa = ListTipoBD.execSelect();
						tipofact = gridTpFa.getStringCell(0, "tipofact");
					}
		
					
					/*MAX FACTURA PARA ACTUALIZAR EL IDTMPFRA DE LAS facturas*/
						
				   
				   /*Detalle cliente*/
				   ListClientesBDIn DetCliBDIn= new ListClientesBDIn();
				   DetCliBDIn.setValue("idclient",idclidet);
				   DetCliBDIn.setValue("tpclient",tpclient);
				   ListClientesBD DetCliBD = new ListClientesBD(DetCliBDIn);
				   DetCliBD.setConnection(con);
				   gridClie = DetCliBD.execSelect();
				   tipoPorc= "0";
				   
				   // tipo facturas
				   ListTipoFacBDIn ListTipoBDIn  = new ListTipoFacBDIn();  
				   ListTipoBDIn.setValue("cdemisor", idemisor);
				   ListTipoBDIn.setValue("tipofact", tipofact);
				   ListTipoFacBD ListTipoBD = new ListTipoFacBD(ListTipoBDIn);
				   ListTipoBD.setConnection(con);
				   gridTpFa = ListTipoBD.execSelect();
		        	
		        	
				   /* Fecha ultima factura*/
				   MaxFechaFacturaBDIn FechaFaBDIn = new MaxFechaFacturaBDIn();
				   FechaFaBDIn.setValue("idemisor", idemisor);
				   MaxFechaFacturaBD FechaFacBD = new MaxFechaFacturaBD(FechaFaBDIn);
				   FechaFacBD.setConnection(con);
				   gridFech = FechaFacBD.execSelect();
		        	
				   /*Areas Negocio*/
				   ListAreasneBDIn areasneBDIn = new ListAreasneBDIn();
				   areasneBDIn.setValue("idemisor",idemisor);
				   ListAreasneBD areasneBD = new ListAreasneBD(areasneBDIn);
				   areasneBD.setConnection(con);
				   gridArne = areasneBD.execSelect();
		        	
		        	
				   /*Forma pago*/
				   ListFormaPagoBDIn formPagoBDIn = new ListFormaPagoBDIn();
				   formPagoBDIn.setValue("idemisor",idemisor);
				   ListFormaPagoBD formPagoBD = new ListFormaPagoBD(formPagoBDIn);
				   formPagoBD.setConnection(con);
				   gridFrPg = formPagoBD.execSelect();
	        		 
		
			   
			   output.setValue("txmensaj", txmensaj);
	           output.setValue("idclient", idclidet);
	           output.setValue("tpclient", tpclient);
	           output.setValue("fechafac", fechafac);
	           output.setValue("tipoPorc", tipoPorc);
	          output.setValue("tipoFact", tipofact);
	           output.setValue("idemisor", idemisor);
	           output.setValue("tipovist", tipovist);
	           output.setValue("cdfactur", idalbara);
	           output.setValue("tipologo", tipologo);
	           output.setValue("formpago", formpago);
	           output.setValue("condpago", condpago);
	           output.setValue("catefact", catefact);
	           output.setValue("idtmpfra", idtmpfra);
	           output.setValue("factasoc", factasoc);
	           output.setValue("idfactur", idfactur);
	           
	           output.setGrid("gridLine", gridResu);
	           output.setGrid("gridClie", gridClie);
	           output.setGrid("gridTpFa", gridTpFa);
	           output.setGrid("gridArne", gridArne);
	           output.setGrid("gridFrPg", gridFrPg);
	           
	           output.setGrid("fecmaxfa",gridFech);
	         
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
       
}
	