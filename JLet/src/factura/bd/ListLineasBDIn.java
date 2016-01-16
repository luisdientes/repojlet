package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasBDIn extends ObjectBD {

    public ListLineasBDIn()
    {
        super();
    }

    public ListLineasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("cdestado");
    	addVariable("idtmpreg");
    	addVariable("imeicode");
    	addVariable("idtmpfra");
    	addVariable("idemisor");
    	addVariable("marcados");
    	addVariable("actualiz");
    	
    }
}
