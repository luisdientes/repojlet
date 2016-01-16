package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasEnvioBDIn extends ObjectBD {

    public ListLineasEnvioBDIn()
    {
        super();
    }

    public ListLineasEnvioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("codeenvi");
    	addVariable("tipoenvi");
    	addVariable("idemisor");
    }
}
