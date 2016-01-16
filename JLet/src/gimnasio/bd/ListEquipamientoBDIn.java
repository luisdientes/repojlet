package gimnasio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListEquipamientoBDIn extends ObjectBD {

    public ListEquipamientoBDIn()
    {
        super();
    }

    public ListEquipamientoBDIn(ObjectIO bdata)
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
