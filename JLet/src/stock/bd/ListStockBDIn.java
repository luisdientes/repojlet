package stock.bd;

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
    	addVariable("idemisor");
    	addVariable("cdestado");
    	addVariable("imeicode");
    	addVariable("idclient");
    	addVariable("codprodu");
    	addVariable("txmarcax");
    	addVariable("tpproduc");
    	addVariable("tipocheq");
    	addVariable("tpclient");
    	
    	
    }
}
