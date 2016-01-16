package recibos.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFacturaBDIn extends ObjectBD {

    public ListFacturaBDIn()
    {
        super();
    }

    public ListFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("tpclient");
 
    }
}
