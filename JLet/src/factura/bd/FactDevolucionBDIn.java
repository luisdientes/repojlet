package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class FactDevolucionBDIn extends ObjectBD {

    public FactDevolucionBDIn()
    {
        super();
    }

    public FactDevolucionBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idalbara");
    	addVariable("idemisor");
    	addVariable("tpclient");
    	addVariable("estaalba");
    	addVariable("aniofact");
    	addVariable("tipofact");
    	addVariable("cdestado");
    	addVariable("idfactur");

    }
}
