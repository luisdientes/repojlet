package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdDiasTraBDIn extends ObjectBD {

    public UpdDiasTraBDIn()
    {
        super();
    }

    public UpdDiasTraBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("fechasel");
    	addVariable("horaselx");
    	addVariable("proyecto");
    	addVariable("cduserid");
    
    	

    }
}
