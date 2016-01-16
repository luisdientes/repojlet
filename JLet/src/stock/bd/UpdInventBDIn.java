package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdInventBDIn extends ObjectBD {

    public UpdInventBDIn()
    {
        super();
    }

    public UpdInventBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("idinvent");
		 addVariable("idemisor");
		 addVariable("tpclient");
		 addVariable("idunicox");
    	 addVariable("cdestado");
    	 addVariable("nameinve");
    	 addVariable("tpproduc");
    	 
    	 
    }
}
