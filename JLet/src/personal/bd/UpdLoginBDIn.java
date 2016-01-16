package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdLoginBDIn extends ObjectBD {

    public UpdLoginBDIn()
    {
        super();
    }

    public UpdLoginBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idtrabaj");
       	addVariable("username");
       	addVariable("password");
       	addVariable("txmailxx");
       	addVariable("tfnomovi");
    }
}
