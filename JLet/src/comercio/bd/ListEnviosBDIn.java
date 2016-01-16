package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListEnviosBDIn extends ObjectBD {

    public ListEnviosBDIn()
    {
        super();
    }

    public ListEnviosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("codeenvi");
    	addVariable("idemisor");
    	addVariable("fhdesdex");
    	addVariable("fhhastax");
    }
}
