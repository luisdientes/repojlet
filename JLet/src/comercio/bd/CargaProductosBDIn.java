package comercio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class CargaProductosBDIn extends ObjectBD {

    public CargaProductosBDIn()
    {
        super();
    }

    public CargaProductosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("imeicode");
		 addVariable("idproduc");
		 addVariable("idcolorx");
		 addVariable("pricechf");
		 addVariable("idcatego");
		 addVariable("tablaBDX");
		 addVariable("codeenvi"); 

    }
}
