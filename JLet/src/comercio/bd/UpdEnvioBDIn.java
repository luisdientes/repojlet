package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdEnvioBDIn extends ObjectBD {

    public UpdEnvioBDIn()
    {
        super();
    }

    public UpdEnvioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("codeenvi");
		 addVariable("fhcreaci");
		 addVariable("fileenvi");
		 addVariable("fhcotiza");
		 addVariable("filecoti");
		 addVariable("fhfactur");
		 addVariable("filefact");
		 addVariable("tpproduc");
		 addVariable("idemisor");

    }
}
