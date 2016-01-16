package cloud.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListArchivosBDIn extends ObjectBD {

    public ListArchivosBDIn()
    {
        super();
    }

    public ListArchivosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("tpclient");
    	addVariable("filepath");
    	addVariable("tipofich");
    	addVariable("cduserid");
    	addVariable("txnombre");
    	addVariable("idinodox");

    }
}
