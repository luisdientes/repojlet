package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListStockClienteBDIn extends ObjectBD {

    public ListStockClienteBDIn()
    {
        super();
    }

    public ListStockClienteBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("cdestado");
    }
}
