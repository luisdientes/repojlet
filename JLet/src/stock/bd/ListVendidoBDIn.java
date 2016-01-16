package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListVendidoBDIn extends ObjectBD {

    public ListVendidoBDIn()
    {
        super();
    }

    public ListVendidoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("cdestado");
    	addVariable("idclient");
    }
}
