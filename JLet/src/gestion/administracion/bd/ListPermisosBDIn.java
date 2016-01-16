package gestion.administracion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPermisosBDIn extends ObjectBD {

    public ListPermisosBDIn()
    {
        super();
    }

    public ListPermisosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cdrolxxx");
    	addVariable("tipoperm");
    	addVariable("nivelper");
    }
}
