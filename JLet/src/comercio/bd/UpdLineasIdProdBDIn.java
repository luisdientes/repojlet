package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasIdProdBDIn extends ObjectBD {

    public UpdLineasIdProdBDIn()
    {
        super();
    }

    public UpdLineasIdProdBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("codeenvi");
    	 addVariable("idproduc");
    }
}
