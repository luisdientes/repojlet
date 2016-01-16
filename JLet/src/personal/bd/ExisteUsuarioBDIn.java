package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ExisteUsuarioBDIn extends ObjectBD {

    public ExisteUsuarioBDIn()
    {
        super();
    }

    public ExisteUsuarioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("username");
    	addVariable("password");
    }
}
