package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdStockWebBDIn extends ObjectBD {

    public UpdStockWebBDIn()
    {
        super();
    }

    public UpdStockWebBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

		 addVariable("id_shopx");
    	 addVariable("refeprod");
    	 addVariable("idproduc");
    }
}
