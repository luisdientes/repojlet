package upgrade.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListUpgradeBDIn extends ObjectBD {

    public ListUpgradeBDIn()
    {
        super();
    }

    public ListUpgradeBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("cdestado");
    	addVariable("imeicode");
    	addVariable("idclient");
    	addVariable("txmarcax");
    	addVariable("tpproduc");
    }
}
 