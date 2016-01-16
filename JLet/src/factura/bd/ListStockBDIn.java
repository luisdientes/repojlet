package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListStockBDIn extends ObjectBD {

    public ListStockBDIn()
    {
        super();
    }

    public ListStockBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    	addVariable("idemisor");
    	addVariable("idunicox");
    	addVariable("idclient");
    	addVariable("cdestado");
    	addVariable("stockyde");
    	
    }
}
