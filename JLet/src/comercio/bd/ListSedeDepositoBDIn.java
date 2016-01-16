package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListSedeDepositoBDIn extends ObjectBD {

    public ListSedeDepositoBDIn()
    {
        super();
    }

    public ListSedeDepositoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("mcintern");
    }
}
