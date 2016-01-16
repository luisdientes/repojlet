package cloud.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdateCloudBDIn extends ObjectBD {

    public UpdateCloudBDIn()
    {
        super();
    }

    public UpdateCloudBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("idemisor");
    	addVariable("propieta");
    	addVariable("permgrup");
    	addVariable("filepath");
    	addVariable("tipofich");
    	addVariable("txnombre");
    	addVariable("idtamano");
    	addVariable("cdestado"); 

    }
}
