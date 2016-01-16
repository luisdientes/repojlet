package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasEnvioTasasBDIn extends ObjectBD {

    public ListLineasEnvioTasasBDIn()
    {
        super();
    }

    public ListLineasEnvioTasasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("codeenvi");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    }
}
