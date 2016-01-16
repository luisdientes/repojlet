package excelXLSX.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListPiezasBDIn extends ObjectBD {

    public ListPiezasBDIn()
    {
        super();
    }

    public ListPiezasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idemisor");
    	addVariable("idpiezas");
    	addVariable("txmarcax");
    }
}
