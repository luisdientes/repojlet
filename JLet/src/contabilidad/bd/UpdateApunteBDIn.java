package contabilidad.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdateApunteBDIn extends ObjectBD {

    public UpdateApunteBDIn()
    {
        super();
    }

    public UpdateApunteBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
   	
	     addVariable("idemisor");
	     addVariable("idcuenta");
	     addVariable("cantidad");
	     addVariable("debhaber");
	     addVariable("concepto");
	     addVariable("coddocum");
	     addVariable("fhapunte");
	     addVariable("tpapunte");
	     addVariable("cduserid");
	     addVariable("documint");
	     addVariable("filedocu");
	     addVariable("idapunte");
	     addVariable("fhdesdex");
	     addVariable("fhhastax");
	     
	     

    
    }
}
