package personal.bd;

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
    	addVariable("idproyec");
    	addVariable("txnombre");
    	addVariable("idempres");
    	addVariable("tpproyec");
    	addVariable("numperso");
    	addVariable("txduraci");
    	addVariable("txdescri");
    	addVariable("fhinicio");
    	addVariable("fhfinxxx");
    	addVariable("idemisor");

    }
}