package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdLoteBDIn extends ObjectBD {

    public UpdLoteBDIn()
    {
        super();
    }

    public UpdLoteBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("codeenvi");
		 addVariable("fhcreaci");

    }
}
