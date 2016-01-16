package gestion.administracion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListProyectBDIn extends ObjectBD {

    public ListProyectBDIn()
    {
        super();
    }

    public ListProyectBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    	addVariable("idemisor");
    	addVariable("idproyec");
    }
}