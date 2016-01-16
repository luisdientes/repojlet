package clientes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdClientesBDIn extends ObjectBD {

    public UpdClientesBDIn()
    {
        super();
    }

    public UpdClientesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("rzsocial");
    	addVariable("idemisor");
    	addVariable("cdintern");
    	addVariable("idfiscal");
    	addVariable("txdirecc");
    	addVariable("txciudad");
    	addVariable("cdpostal");
    	addVariable("txmailxx");
    	addVariable("tfnofijo");
    	addVariable("tfnomovi");
    	addVariable("tfnofaxx");
    	addVariable("txwebxxx");
    	addVariable("txpaisxx");
    	addVariable("txprovin");
    	

    }
}
