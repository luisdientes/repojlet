package tiendas.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdTiendasBDIn extends ObjectBD {

    public UpdTiendasBDIn()
    {
        super();
    }

    public UpdTiendasBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("idtienda");
    	 addVariable("idemisor");
    	 addVariable("txnombre");
		 addVariable("nifcifxx");
		 addVariable("txcatego");
		 addVariable("txciudad");
		 addVariable("txdirecc");
		 addVariable("cdpostal");
		 addVariable("telefono");
		 addVariable("txmailxx");
		 addVariable("txrespon");
		 addVariable("sexoxxxx");
		 addVariable("txnomres");
		 addVariable("tfnomovi");
		 addVariable("mailresp");
		 addVariable("longitud");
		 addVariable("latitudx");

    }
}
