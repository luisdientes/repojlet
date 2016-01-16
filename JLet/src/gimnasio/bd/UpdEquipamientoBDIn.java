package gimnasio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdEquipamientoBDIn extends ObjectBD {

    public UpdEquipamientoBDIn()
    {
        super();
    }

    public UpdEquipamientoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
   	
	     addVariable("idclient");
		 addVariable("cdequipa");
		 addVariable("idmarcax");
		 addVariable("txmodelo");
		 addVariable("tipogama");
		 addVariable("cantidad");
		 addVariable("aniocomp");
		 addVariable("cdestado");    

    }
}
