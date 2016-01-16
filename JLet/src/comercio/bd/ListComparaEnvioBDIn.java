package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListComparaEnvioBDIn extends ObjectBD {

    public ListComparaEnvioBDIn()
    {
        super();
    }

    public ListComparaEnvioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("codeenvi");
    }
}
