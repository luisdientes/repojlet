package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListFormaPagoBDIn extends ObjectBD {

    public ListFormaPagoBDIn()
    {
        super();
    }

    public ListFormaPagoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idfrmpag");
    }
}
