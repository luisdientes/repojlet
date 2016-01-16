package desgcostes.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListCodigoCostesBDIn extends ObjectBD {

    public ListCodigoCostesBDIn()
    {
        super();
    }

    public ListCodigoCostesBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("cdgrupox");
    	addVariable("tipooper");
    	addVariable("mcactivo");

    }
}

 