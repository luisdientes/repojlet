package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdEmpleBDIn extends ObjectBD {

    public UpdEmpleBDIn()
    {
        super();
    }

    public UpdEmpleBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
       	addVariable("txnombre");
       	addVariable("txapelli");
       	addVariable("txempres");
       	addVariable("cdnifxxx");
       	addVariable("txmailxx");
       	addVariable("tfnomovi");
       	addVariable("txcoment");
       	addVariable("idusuari");
       	addVariable("idproyec");
       	addVariable("idacceso");
       	addVariable("idemisor");
       	
    }
}
