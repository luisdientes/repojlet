package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class AsignaProyDefaultBDIn extends ObjectBD {

    public AsignaProyDefaultBDIn()
    {
        super();
    }

    public AsignaProyDefaultBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    	addVariable("idproyec");
    }
}
