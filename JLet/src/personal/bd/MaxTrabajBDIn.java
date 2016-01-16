package personal.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class MaxTrabajBDIn extends ObjectBD {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MaxTrabajBDIn()
    {
        super();
    }

    public MaxTrabajBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	//addVariable("idemisor");
    }
}
