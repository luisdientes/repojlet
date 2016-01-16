package factura;


import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import factura.bd.FactAlbaraBD;
import factura.bd.FactAlbaraBDIn;
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


public class VistaPreviaAlbaSrv extends Service {

    public VistaPreviaAlbaSrv() {
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
        String tipoFact = "";
        String idemisor = "";
        String tipovist = "";
        String idalbara = "";
        String factasoc = "";
        //Otras Variables
        
		String idtmpfra = "";
		String aniofact = "";
		
        try {

			   idclient = input.getStringValue("idclient");
			   fechafac = input.getStringValue("fechafac");
			   tipoPorc = input.getStringValue("tipoPorc");
			   tipoFact = input.getStringValue("tipoFact");
			   idemisor = input.getStringValue("idemisor");
			   tipovist = input.getStringValue("tipovist");
			   idalbara = input.getStringValue("idalbara");
			   tipologo = input.getStringValue("tipologo");
			   formpago = input.getStringValue("formpago");
			   condpago = input.getStringValue("condpago");
			   catefact = input.getStringValue("catefact");
			   aniofact = input.getStringValue("aniofact");
			   factasoc = input.getStringValue("factasoc"); 
			 
			   if (tipovist!=null && tipovist.equals("AL")){// ES ALBARAN
				   
					/* lineas albaran*/
				   
				    FactAlbaraBDIn FactAlbaBDIn= new FactAlbaraBDIn();
				    FactAlbaBDIn.setValue("idalbara",idalbara);
				    FactAlbaBDIn.setValue("idemisor", idemisor);
				    FactAlbaBDIn.setValue("aniofact", aniofact);
				    FactAlbaBDIn.setValue("tipofact", "0");
				    FactAlbaBDIn.setValue("cdestado", "F");
				    FactAlbaBDIn.setValue("estaalba", "P");
				    FactAlbaraBD lineasAlBD = new FactAlbaraBD(FactAlbaBDIn);
				    lineasAlBD.setConnection(con);
				    gridResu = lineasAlBD.execSelect();
				    
				    //para sacar luego los datos del cliente en la Vista previa
				    String idclidet = gridResu.getStringCell(0, "idclient"); 
				    tpclient = gridResu.getStringCell(0, "tpclient"); 
						// saco el idtmpfra del idalbaran que halla introducido
					idtmpfra = gridResu.getStringCell(0, "idtmpfra");
					fechafac = gridResu.getStringCell(0, "fechafac");
		
					
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
	        		 
			   }
			   
			   output.setValue("txmensaj", txmensaj);
	           output.setValue("idclient", idclient);
	           output.setValue("tpclient", tpclient);
	           output.setValue("fechafac", fechafac);
	           output.setValue("tipoPorc", tipoPorc);
	           output.setValue("tipoFact", tipoFact);
	           output.setValue("idemisor", idemisor);
	           output.setValue("tipovist", tipovist);
	           output.setValue("cdfactur", idalbara);
	           output.setValue("tipologo", tipologo);
	           output.setValue("formpago", formpago);
	           output.setValue("condpago", condpago);
	           output.setValue("catefact", catefact);
	           output.setValue("idtmpfra", idtmpfra);
	           output.setValue("factasoc", factasoc);
	           
	           
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
	