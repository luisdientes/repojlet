package usuarios.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPermisosRolBDIn extends ObjectBD {

    public ListPermisosRolBDIn()
    {
        super();
    }

    public ListPermisosRolBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idusuari");
    	addVariable("cdrolxxx");
    	addVariable("valorper");
    	addVariable("tipoperm");
    	
    }
}
