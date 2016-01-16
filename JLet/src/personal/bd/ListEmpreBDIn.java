package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListEmpreBDIn extends ObjectBD {

    public ListEmpreBDIn()
    {
        super();
    }

    public ListEmpreBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("txrazons");
    	addVariable("txdirecc");
    	addVariable("nifcifxx");
    	addVariable("txwebxxx");
    	addVariable("txmailxx");
    	addVariable("txcontac");
    	addVariable("idempres");
    	addVariable("idemisor");

    }
}