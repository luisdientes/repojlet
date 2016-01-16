package recibos.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdRecibosBDIn extends ObjectBD {

    public UpdRecibosBDIn()
    {
        super();
    }

    public UpdRecibosBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("idclient");
    	 addVariable("idemisor");
		 addVariable("tpclient");
		 addVariable("cantidad");
		 addVariable("concepto");
		 addVariable("formpago");
		 addVariable("txcajero");
		 addVariable("cdfactur");
		 addVariable("valortot");

    }
}
