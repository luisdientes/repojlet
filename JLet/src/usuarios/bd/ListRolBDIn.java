package usuarios.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListRolBDIn extends ObjectBD {

    public ListRolBDIn()
    {
        super();
    }

    public ListRolBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idusuari");
    	addVariable("cdrolxxx");
    }
}
