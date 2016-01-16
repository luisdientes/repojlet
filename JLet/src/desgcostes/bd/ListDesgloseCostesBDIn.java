package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListDesgloseCostesBDIn extends ObjectBD {
 
    public ListDesgloseCostesBDIn()
    {
        super();
    }

    public ListDesgloseCostesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idunicox");
    	addVariable("idemisor");
    	addVariable("mostriva");
    	addVariable("cdgrupox");
    	addVariable("tipooper"); 
    	addVariable("mcactivo");

    }
}
