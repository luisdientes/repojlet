package reparaciones.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListReparaBDIn extends ObjectBD {

    public ListReparaBDIn()
    {
        super();
    }

    public ListReparaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

    	addVariable("idemisor");
    	addVariable("idrecibo");
    	addVariable("cdrecibo");
    	addVariable("tpclient");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    }
}
