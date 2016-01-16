package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListIMEIDesgloseBDIn extends ObjectBD {

    public ListIMEIDesgloseBDIn()
    {
        super();
    }

    public ListIMEIDesgloseBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("desvalue");
    	addVariable("substrin");
 
    }
}
