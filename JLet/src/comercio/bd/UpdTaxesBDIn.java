package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdTaxesBDIn extends ObjectBD {

    public UpdTaxesBDIn()
    {
        super();
    }

    public UpdTaxesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("idlineax");
    	 addVariable("custotax");
    	 addVariable("consutax");
    	 addVariable("fletecst");
    	 addVariable("itbisimp");
    	 addVariable("tramadua");
    	 addVariable("almacena");
    	 addVariable("movconte");
    	 addVariable("cargnavi");


    }
}
