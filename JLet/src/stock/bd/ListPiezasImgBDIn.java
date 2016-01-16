package stock.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPiezasImgBDIn extends ObjectBD {

    public ListPiezasImgBDIn()
    {
        super();
    }

    public ListPiezasImgBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idpiezas");
    	addVariable("txmarcax");
    	addVariable("idgrupox");
    	
    }
}
