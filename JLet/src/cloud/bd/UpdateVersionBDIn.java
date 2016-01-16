package cloud.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdateVersionBDIn extends ObjectBD {

    public UpdateVersionBDIn()
    {
        super();
    }

    public UpdateVersionBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	String idinodox = "";
    	String usupload = "";
    	String versionx = "";
    	String milisegu = "";
    	
    	addVariable("idinodox");
    	addVariable("usupload");
    	addVariable("versionx");
    	addVariable("milisegu");

    }
}
