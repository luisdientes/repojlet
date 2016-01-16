package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdFacturaBDIn extends ObjectBD {

    public UpdFacturaBDIn()
    {
        super();
    }

    public UpdFacturaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idfactur");
    	addVariable("idemisor");
    	addVariable("cdfactur");
    	addVariable("tipofact");
    	addVariable("aniofact");
    	addVariable("cddivisa");
    	addVariable("baseimpo");
    	addVariable("imptaxes");
    	addVariable("imptotal");
    	addVariable("filecrea");
    	addVariable("cdestado");
    	addVariable("formpago");
    	addVariable("condpago");
    	addVariable("idclient");

    }
}
