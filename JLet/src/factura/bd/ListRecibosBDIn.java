package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListRecibosBDIn extends ObjectBD {

    public ListRecibosBDIn()
    {
        super();
    }

    public ListRecibosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idrecibo");
    	addVariable("idemisor");
    	addVariable("idfactur");
    	addVariable("idclient");
    	addVariable("tpclient");
    	addVariable("fhrecibo");
    	addVariable("canrecib");
    	addVariable("concepto");
    	addVariable("cdfactur");
    	addVariable("formpago");
    	addVariable("txbancox");
    	addVariable("numrecib");
    	addVariable("namefile");
    	addVariable("fecharec");
    	addVariable("tpfacrec");
    	addVariable("idformpa");	
    }
}
