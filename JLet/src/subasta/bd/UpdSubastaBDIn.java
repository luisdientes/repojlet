package subasta.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdSubastaBDIn extends ObjectBD {

    public UpdSubastaBDIn()
    {
        super();
    }

    public UpdSubastaBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	addVariable("txwebxxx");
    	addVariable("txpaisxx");
    	addVariable("txusuari");
    	addVariable("tipovent");
    	addVariable("idproduc");
    	addVariable("txnombre");
    	addVariable("preciosa");
    	addVariable("preciotr");
    	addVariable("divisaxx");
    	addVariable("cdintern");
    	addVariable("urlexter");
    	addVariable("preciomi");
    	addVariable("fechvent");
    	addVariable("horavent");
    	addVariable("finfecve");
    	addVariable("finhorve");
    	addVariable("desactiv");
    	addVariable("idcodsub");
    	


    }
}
