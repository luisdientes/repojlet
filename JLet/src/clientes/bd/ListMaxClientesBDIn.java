package clientes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListMaxClientesBDIn extends ObjectBD {

    public ListMaxClientesBDIn()
    {
        super();
    }

    public ListMaxClientesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("tpclient");
    	addVariable("rzsocial");

    }
}
