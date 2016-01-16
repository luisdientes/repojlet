package upgrade.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListStockPiezasBDIn extends ObjectBD {

    public ListStockPiezasBDIn()
    {
        super();
    }

    public ListStockPiezasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("cdestado");
    	addVariable("imeicode");
    	addVariable("idclient");
    	addVariable("codprodu");
    	addVariable("cdgroupx");
    	
    }
}
