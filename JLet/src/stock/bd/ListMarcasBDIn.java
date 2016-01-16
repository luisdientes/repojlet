package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListMarcasBDIn extends ObjectBD {

    public ListMarcasBDIn()
    {
        super();
    }

    public ListMarcasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    
    }
}
