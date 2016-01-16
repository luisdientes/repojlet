package usuarios.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListUsuariosBDIn extends ObjectBD {

    public ListUsuariosBDIn()
    {
        super();
    }

    public ListUsuariosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idusuari");
    	addVariable("cdrolxxx");
    }
}
