package gestion.administracion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListEmpleadosBDIn extends ObjectBD {

    public ListEmpleadosBDIn()
    {
        super();
    }

    public ListEmpleadosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    	addVariable("cdrolxxx");
    }
}
