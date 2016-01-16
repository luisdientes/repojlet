package usuarios.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPantallasBDIn extends ObjectBD {

    public ListPantallasBDIn()
    {
        super();
    }

    public ListPantallasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cdrolxxx");
    	addVariable("cdpadrex");
    	addVariable("cdpantal");
    }
}
