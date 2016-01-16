package subasta.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListSubastasBDIn extends ObjectBD {

    public ListSubastasBDIn()
    {
        super();
    }

    public ListSubastasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

    	addVariable("idcodsub");
    	addVariable("txwebxxx");
    	addVariable("txnombre");
    	addVariable("idpaisxx");
    	addVariable("tipovent");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    	addVariable("mcactivo");
    	addVariable("idprodwe");
    	
    }
}
