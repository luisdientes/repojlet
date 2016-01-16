package gimnasio.bd;

import arquitectura.objects.ObjectBD;
import arquitectura.objects.ObjectIO;

public class UpdGimnasioBDIn extends ObjectBD {

    public UpdGimnasioBDIn()
    {
        super();
    }

    public UpdGimnasioBDIn(ObjectIO bdata)
    {
        super(bdata);
    }
    
    public void defineVars() throws Exception {
    	
    	 addVariable("idclient");
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
