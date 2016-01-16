package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasAlbaBDIn extends ObjectBD {

    public UpdLineasAlbaBDIn()
    {
        super();
    }

    public UpdLineasAlbaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idlineax");
    	addVariable("codprodu");
    	addVariable("idfactur");
    	addVariable("idemisor");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("fechafac");
    	addVariable("nlineaxx");
    	addVariable("cantidad");
    	addVariable("concepto");
    	addVariable("precioun");
    	addVariable("descuent");
    	addVariable("precioto");
    	addVariable("cdestado");
    	addVariable("idunicox");

    }
}
