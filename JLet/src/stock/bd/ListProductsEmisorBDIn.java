package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListProductsEmisorBDIn extends ObjectBD {

    public ListProductsEmisorBDIn()
    {
        super();
    }

    public ListProductsEmisorBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("tpproduc");
    	addVariable("idemisor");
    
    }
}
