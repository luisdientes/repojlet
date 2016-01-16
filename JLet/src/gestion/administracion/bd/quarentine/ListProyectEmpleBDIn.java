package gestion.administracion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListProyectEmpleBDIn extends ObjectBD {

    public ListProyectEmpleBDIn()
    {
        super();
    }

    public ListProyectEmpleBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cduserid");
    }
}