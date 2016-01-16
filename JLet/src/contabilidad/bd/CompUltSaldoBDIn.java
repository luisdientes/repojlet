package contabilidad.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class CompUltSaldoBDIn extends ObjectBD {

    public CompUltSaldoBDIn()
    {
        super();
    }

    public CompUltSaldoBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
   	
	     addVariable("idemisor");
	     addVariable("idcuenta");
    }
}
