package productos.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdPiezasBDIn extends ObjectBD {

    public UpdPiezasBDIn()
    {
        super();
    }

    public UpdPiezasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	addVariable("txdescri");
    	addVariable("nameespa");
    	addVariable("namephon");
    	addVariable("codepiez");
    	addVariable("preciopr");
    	addVariable("txmarcax");
    	addVariable("cdcolorx");
    	addVariable("imgdefau");
    }
}
