package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class AsignaProyBDIn extends ObjectBD {

    public AsignaProyBDIn()
    {
        super();
    }

    public AsignaProyBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    	addVariable("idproyec");
    }
}
