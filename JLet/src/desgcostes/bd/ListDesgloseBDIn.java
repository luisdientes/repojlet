package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListDesgloseBDIn extends ObjectBD {
 
    public ListDesgloseBDIn()
    {
        super();
    }

    public ListDesgloseBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
        
    	addVariable("idemisor");
    	addVariable("txpaisxx");
    	addVariable("canalven");
    	addVariable("fhdesdex"); 
    	addVariable("fhhastax");

    }
}
