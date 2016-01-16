package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdCostesBDIn extends ObjectBD {

    public UpdCostesBDIn()
    {
        super();
    }

    public UpdCostesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idunicox");
    	addVariable("idemisor");
    	addVariable("codedesg");
    	addVariable("desvalue");
    	addVariable("cddivisa");
 
    }
}
