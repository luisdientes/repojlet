package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdCodVentasBDIn extends ObjectBD {

    public UpdCodVentasBDIn()
    {
        super();
    }

    public UpdCodVentasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

		 addVariable("idemisor");
		 addVariable("codprodu");
		 addVariable("txmarcax");
		 addVariable("txdescri");
		 addVariable("impdefve");
		 addVariable("cantidad");
		 addVariable("divisaxx");
		 addVariable("tpproduc");
		 
		 
    }
}
