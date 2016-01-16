package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListTmpStockBDIn extends ObjectBD {

    public ListTmpStockBDIn()
    {
        super();
    }

    public ListTmpStockBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("cdimeixx");
    	addVariable("pricecmp");
    	addVariable("divisaxx");
    	addVariable("divisvnt");
    	addVariable("pricevnt");
    	
    	addVariable("proveedo");
    	addVariable("clasexxx");
    	addVariable("cargador");
    	addVariable("enchufex");
    	
    	addVariable("usbxxxxx");
    	addVariable("cajaxxxx");
    	addVariable("cascosxx");
    	addVariable("codprodu");
    	addVariable("tpproduc");
    	addVariable("cdestado");
    	addVariable("fechacmp");
    	addVariable("idgralta");
    	addVariable("idcolorx");
    	
    	
    	
    	
    	
    	
    	
    	
    }
}
