package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdDetalleBDIn extends ObjectBD {

    public UpdDetalleBDIn()
    {
        super();
    }

    public UpdDetalleBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idunicox");
    	addVariable("idemisor");
    	addVariable("codedeta");
    	addVariable("desvalue");

    }
}
 