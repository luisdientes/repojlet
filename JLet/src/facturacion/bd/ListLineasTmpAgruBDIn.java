package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasTmpAgruBDIn extends ObjectBD {

    public ListLineasTmpAgruBDIn()
    {
        super();
    }

    public ListLineasTmpAgruBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("cdestado");
    	addVariable("idtmpfra");
    	addVariable("idfactur");
    	
    }
}
