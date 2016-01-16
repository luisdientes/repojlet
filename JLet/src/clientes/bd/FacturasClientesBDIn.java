package clientes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class FacturasClientesBDIn extends ObjectBD {

    public FacturasClientesBDIn()
    {
        super();
    }

    public FacturasClientesBDIn(ObjectIO bdata)
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
