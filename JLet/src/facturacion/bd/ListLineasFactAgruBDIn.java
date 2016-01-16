package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasFactAgruBDIn extends ObjectBD {

    public ListLineasFactAgruBDIn()
    {
        super();
    }

    public ListLineasFactAgruBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("cdestado");
    	addVariable("imeisxxx");
    	
    }
}
