package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListAlbaranesWebBDIn extends ObjectBD {

    public ListAlbaranesWebBDIn()
    {
        super();
    }

    public ListAlbaranesWebBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("aniofact");
    	addVariable("fhfechax");
    	addVariable("tipofact");
    	addVariable("cdestado");
    	addVariable("isalbara");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    	addVariable("predesde");
    	addVariable("prehasta");
    	addVariable("tpclient");
    }
}
