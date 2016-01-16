package common.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListDivEmisorBDIn extends ObjectBD {

    public ListDivEmisorBDIn()
    {
        super();
    }

    public ListDivEmisorBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");

    }
}
