package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasTmpBDIn extends ObjectBD {

    public ListLineasTmpBDIn()
    {
        super();
    }

    public ListLineasTmpBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("cdestado");
    	addVariable("idtmpfra");
    	addVariable("marcados");
    	
    }
}
