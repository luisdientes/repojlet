package contabilidad.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListCodApuntesBDIn extends ObjectBD {

    public ListCodApuntesBDIn()
    {
        super();
    }

    public ListCodApuntesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
   	
	     addVariable("idemisor");
	     addVariable("txnombre");
    }
}
