package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListDesgloseDetalleBDIn extends ObjectBD {
 
    public ListDesgloseDetalleBDIn()
    {
        super();
    }

    public ListDesgloseDetalleBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idunicox");
    	addVariable("idemisor");
    	addVariable("cdgrupox");
    	addVariable("mcactivo");

    }
}
