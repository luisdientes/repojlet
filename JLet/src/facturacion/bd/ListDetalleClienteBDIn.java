package facturacion.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class ListDetalleClienteBDIn extends ObjectBD {

    public ListDetalleClienteBDIn()
    {
        super();
    }

    public ListDetalleClienteBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("idclient");
    	addVariable("idemisor");
    	addVariable("tpclient");
    }
}
