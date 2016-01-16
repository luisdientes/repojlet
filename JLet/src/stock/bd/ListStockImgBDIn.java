package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListStockImgBDIn extends ObjectBD {

    public ListStockImgBDIn()
    {
        super();
    }

    public ListStockImgBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("cdestado");
    	addVariable("imeicode");
    	addVariable("idclient");
    	addVariable("txmarcax");
    	addVariable("tpproduc");
    }
}
