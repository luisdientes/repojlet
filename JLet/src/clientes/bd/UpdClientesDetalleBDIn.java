package clientes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdClientesDetalleBDIn extends ObjectBD {

    public UpdClientesDetalleBDIn()
    {
        super();
    }

    public UpdClientesDetalleBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("rzsocial");
    	addVariable("idfiscal");
    	addVariable("txdirecc");
    	addVariable("txciudad");
    	addVariable("cdpostal");
    	addVariable("txmailxx");
    	addVariable("tfnofijo");
    	addVariable("tfnomovi");
    	addVariable("tfnofaxx");
    	addVariable("txwebxxx");
    	addVariable("idclient");
    	addVariable("idemisor");

    }
}
