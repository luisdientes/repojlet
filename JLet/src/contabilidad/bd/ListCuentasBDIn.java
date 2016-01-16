package contabilidad.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListCuentasBDIn extends ObjectBD {

    public ListCuentasBDIn()
    {
        super();
    }

    public ListCuentasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
   	
	     addVariable("idemisor");
	     addVariable("idcuenta");
	     addVariable("ultsaldo");
	     addVariable("txnombre");
	     addVariable("txdetall");
	     addVariable("cdpaisxx");
	     addVariable("cddivisa");
	     addVariable("tipocuen");
	     addVariable("numeroid");
    }
}
