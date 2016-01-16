package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasCodBarrasBDIn extends ObjectBD {

    public ListLineasCodBarrasBDIn()
    {
        super();
    }

    public ListLineasCodBarrasBDIn(ObjectIO bdata)
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
