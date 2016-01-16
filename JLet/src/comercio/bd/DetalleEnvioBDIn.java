package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class DetalleEnvioBDIn extends ObjectBD {

    public DetalleEnvioBDIn()
    {
        super();
    }

    public DetalleEnvioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idtradet");
    	addVariable("codeenvi");
    	addVariable("idenviox");
    	addVariable("idempres");
    	addVariable("medenvio");
    	addVariable("txidenti");
    	addVariable("perconta");
    	addVariable("tfnocont");
    	addVariable("txmailxx");
    	addVariable("precioen");
    	addVariable("impuesto");
    	addVariable("txdivisa");
    	addVariable("pagenvio");
    	addVariable("totalenv");
    	addVariable("fhrecogi");
    	addVariable("horareco");
    	addVariable("fhentreg");
    	addVariable("horaentr");
    	addVariable("cdestado");
    }
}
