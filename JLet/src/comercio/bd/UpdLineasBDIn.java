package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdLineasBDIn extends ObjectBD {

    public UpdLineasBDIn()
    {
        super();
    }

    public UpdLineasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("codeenvi");
    	 addVariable("imeicode");
		 addVariable("txmarcax");
		 addVariable("txmodelo");
		 addVariable("idcolorx");
		 addVariable("pricechf");
		 addVariable("priceusd");
		 addVariable("txprovid");
		 addVariable("txbuyerx");
		 addVariable("txfundin");
		 addVariable("withboxx");
		 addVariable("withusbx");
		 addVariable("idcatego");
		 addVariable("porcmarg");
		 addVariable("idlineax");
		 addVariable("quantity");
		 addVariable("tpproduc");
		 addVariable("idemisor");

    }
}
