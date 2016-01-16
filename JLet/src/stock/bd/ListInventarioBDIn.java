package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListInventarioBDIn extends ObjectBD {

    public ListInventarioBDIn()
    {
        super();
    }

    public ListInventarioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("nameinve");
    	addVariable("tpproduc");
    	addVariable("tipocheq");
    	
    	
    }
}
