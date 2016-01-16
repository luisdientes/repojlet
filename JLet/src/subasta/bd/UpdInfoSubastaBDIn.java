package subasta.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdInfoSubastaBDIn extends ObjectBD {

    public UpdInfoSubastaBDIn()
    {
        super();
    }

    public UpdInfoSubastaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idcodsub");
    	addVariable("tpevento");
    	addVariable("cduserxx");
    	addVariable("txusuari");
    	addVariable("txmailxx");
    	addVariable("telefono");
    	addVariable("isopaisx");
    	addVariable("cantidad");
    	addVariable("cddivisa");
    	addVariable("direnvio");
    	addVariable("txcoment");
    	addVariable("nunidade");
    	addVariable("pretrans");
    	addVariable("fhventax");

    }
}
