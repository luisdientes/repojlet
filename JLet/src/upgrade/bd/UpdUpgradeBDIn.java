package upgrade.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdUpgradeBDIn extends ObjectBD {

    public UpdUpgradeBDIn()
    {
        super();
    }

    public UpdUpgradeBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {

		 addVariable("idemisor");
    	 addVariable("codprodu");
    	 addVariable("imeicode");
    	 addVariable("horacomi");
		 addVariable("costpiez");
		 addVariable("costsoft");
		 addVariable("costlimp");
		 addVariable("costtime");
		 addVariable("claseant");
		 addVariable("claseact");	
		 addVariable("manoobra");
		 addVariable("hardrese");
		 addVariable("fhrepara");
		 
		 
    }
}
