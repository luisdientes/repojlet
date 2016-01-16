package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdEstadoProBDIn extends ObjectBD {

    public UpdEstadoProBDIn()
    {
        super();
    }

    public UpdEstadoProBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

		 addVariable("idemisor");
		 addVariable("idclient");
		 addVariable("tpclient");
		 addVariable("idunicox");
		 addVariable("iduserxx");
    	 addVariable("newstate");
    	 
    }
}
