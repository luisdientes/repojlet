package reparaciones.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdReparaBDIn extends ObjectBD {

    public UpdReparaBDIn()
    {
        super();
    }

    public UpdReparaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idrecibo");
    	addVariable("tpclient");
    	addVariable("cdrecibo");
    	addVariable("idemisor");
    	addVariable("txnombre");
    	addVariable("txmodelo");
    	addVariable("txcolorx");
    	addVariable("txmarcax");
    	addVariable("txdescri");
    	addVariable("tximeixx");
    	addVariable("fhentrad");
    	addVariable("costcheq");
    	addVariable("costordx");
    	addVariable("perconta");
    	addVariable("telefono");
    	addVariable("txmailxx");
    	addVariable("tiempent");
    	addVariable("garantia");
    	addVariable("entregad");
    	addVariable("recibido");
    	addVariable("filereci");

    }
}
