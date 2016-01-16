package productos;


import common.bd.ListMarcasBD;
import common.bd.ListMarcasBDIn;
import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;

import common.bd.ListColoresBD;
import common.bd.ListColoresBDIn;



public class InitProductosSrv extends Service {

    public InitProductosSrv() {
      super();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();						 			
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("gridColo");
			output.addVariable("gridMarc");	
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output) throws Exception{
        
    
        Grid gridMarc = null;
        Grid gridColo = null;

        try{        	
        	ListMarcasBDIn marcaBDIn = new ListMarcasBDIn();
        	ListMarcasBD marcaBD = new ListMarcasBD(marcaBDIn);
        	marcaBD.setConnection(con);
        	gridMarc = marcaBD.execSelect();
        	
	
        } catch (Exception e1) {
			e1.printStackTrace();
		}
        
        try {
			ListColoresBDIn coloresBDIn = new ListColoresBDIn();
		  	ListColoresBD coloresBD = new ListColoresBD(coloresBDIn);
		  	coloresBD.setConnection(con);
			gridColo = coloresBD.execSelect();
		} catch (Exception e) {
			System.err.println(this.getClass().getName() +" ERROR recogiendo los colores. "+ e.getMessage());
		}
        
        output.setGrid("gridMarc", gridMarc);
        output.setGrid("gridColo", gridColo);
        
    }
       
}