package productos.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdProducBDIn extends ObjectBD {

    public UpdProducBDIn()
    {
        super();
    }

    public UpdProducBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idmarcax");
    	addVariable("txmodelo");
    	addVariable("txcatego");
    	addVariable("impdefco");
    	addVariable("impdefve");
    	addVariable("imagedef");
    	addVariable("imagefil");
    	addVariable("idgrupox");
    	
    	
    }
}
