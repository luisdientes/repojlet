package common.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPaisesBDIn extends ObjectBD {

    public ListPaisesBDIn()
    {
        super();
    }

    public ListPaisesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("isocodex");
    	addVariable("txnombre");
    	addVariable("txmoneda");
    	addVariable("simbolmo");
    	addVariable("txcontin");
    	addVariable("mcactivo");

    }
}
