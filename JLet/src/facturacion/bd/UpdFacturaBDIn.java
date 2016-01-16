package facturacion.bd;

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
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("cdfactur");
    	addVariable("fhfactur");
    	addVariable("tipofact");
    	addVariable("aniofact");
    	addVariable("cddivisa");
    	addVariable("baseimpo");
    	addVariable("imptaxes");
    	addVariable("impreten");
    	addVariable("imptotal");
    	addVariable("filecrea");
    	addVariable("cdestado");
    	addVariable("formpago");
    	addVariable("condpago");
    	addVariable("catefact");
    	addVariable("idtmpfra");
    	addVariable("informda");
    	addVariable("factasoc");
    	addVariable("codvende");
    	addVariable("mcpagado");
    	addVariable("codcrono");
    	addVariable("porcrete");
    	addVariable("totalpag");
    	addVariable("mcagrupa");
  
    	
    }
}
