package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxAltaTmpStockBDIn extends ObjectBD {

    public MaxAltaTmpStockBDIn()
    {
        super();
    }

    public MaxAltaTmpStockBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("mcestado");
    	addVariable("codprodu");
    	addVariable("idemisor");
    
    }
}
