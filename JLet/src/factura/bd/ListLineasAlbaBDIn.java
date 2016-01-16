package factura.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListLineasAlbaBDIn extends ObjectBD {

    public ListLineasAlbaBDIn()
    {
        super();
    }

    public ListLineasAlbaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idtmpfra");
    	addVariable("idclient");
    	addVariable("cdestado");
    	addVariable("idemisor");
    	addVariable("estaalba");
    	
    }
}
