package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasBDIn extends ObjectBD {

    public UpdLineasBDIn()
    {
        super();
    }

    public UpdLineasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("cdfactur");
    	addVariable("cdoldest");
    	addVariable("cdnewest");
    	addVariable("imeicode");
    	addVariable("idtmpfra");
    	addVariable("estaalba");
    	addVariable("marcados");
    	addVariable("namefile");

    }
    
}
