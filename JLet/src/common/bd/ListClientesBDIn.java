package common.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListClientesBDIn extends ObjectBD {

    public ListClientesBDIn()
    {
        super();
    }

    public ListClientesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("rzsocial");
    	addVariable("cdintern");

    }
}
