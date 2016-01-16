package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListStockAgruBDIn extends ObjectBD {

    public ListStockAgruBDIn()
    {
        super();
    }

    public ListStockAgruBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("cdestado");
    }
}
