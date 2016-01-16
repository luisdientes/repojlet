package clientes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class LineasTmpClientesBDIn extends ObjectBD {

    public LineasTmpClientesBDIn()
    {
        super();
    }

    public LineasTmpClientesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("tpclient");
    	addVariable("idclient");
    	addVariable("clientea");
    }
}
