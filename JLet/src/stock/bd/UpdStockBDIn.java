package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdStockBDIn extends ObjectBD {

    public UpdStockBDIn()
    {
        super();
    }

    public UpdStockBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

		 addVariable("idemisor");
    	 addVariable("cdestado");
    	 addVariable("imeicode");
		 addVariable("txmarcax");
		 addVariable("txmodelo");
		 addVariable("idcolorx");
		 addVariable("pricechf");
		 addVariable("priceusd");
		 addVariable("pricecmp");
		 addVariable("diviscmp");
		 addVariable("prusdcmp");
		 addVariable("pricevnt");
		 addVariable("divisvnt");
		 addVariable("prusdvnt");
		 addVariable("txprovid");
		 addVariable("txbuyerx");
		 addVariable("txfundin");
		 addVariable("withboxx");
		 addVariable("withusbx");
		 addVariable("withheph");
		 addVariable("withchar");
		 addVariable("idcatego");
		 addVariable("porcmarg");
		 addVariable("idlineax");
		 addVariable("idclient");
		 addVariable("idproved");
		 addVariable("oldcdest");
		 addVariable("codprodu");
		 addVariable("tpclient");
		 addVariable("tpproduc");
		 addVariable("tipocone");
		 addVariable("fhcompra");

    }
}
