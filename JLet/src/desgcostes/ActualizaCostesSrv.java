package desgcostes;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import arquitectura.controller.Service;
import arquitectura.objects.Grid;
import arquitectura.objects.ObjectIO;
import desgcostes.bd.ListCodigoCostesBD;
import desgcostes.bd.ListCodigoCostesBDIn;
import desgcostes.bd.ListDesgloseCostesBD;
import desgcostes.bd.ListDesgloseCostesBDIn;
import desgcostes.bd.UpdCostesBD;
import desgcostes.bd.UpdCostesBDIn;
 

public class ActualizaCostesSrv extends Service {

	ArrayList<String> arcdcost = new ArrayList<String>();
	
	public ActualizaCostesSrv() {
		super();
		recuperaCodigosCostes();
    }
    
    public ObjectIO instanceOfInput() {
    	ObjectIO input = null;
		try {
			input = new ObjectIO();
			
			input.addVariable("idemisor");
			input.addVariable("idunicox");
			input.addVariable("cddivisa");
			input.addVariable("tipooper");
			
			for (int i = 0; i < arcdcost.size(); i++){
				input.addVariable(arcdcost.get(i).toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return input;
	}
    
	public ObjectIO instanceOfOutput() {
		ObjectIO output = null;
		try {
			
			output = new ObjectIO();
			output.addVariable("idemisor");
			output.addVariable("idunicox");
			output.addVariable("tipooper"); 
			output.addVariable("gdcdcost"); 
			output.addVariable("gddscost"); 
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return output;
	}
    
    
    public void process(ObjectIO input, ObjectIO output){
        
    	//Varibales de entrada
    	String tipooper = "";
    	String idemisor = "";
    	String idunicox = "";
    	String cddivisa = "";

        //Varibales de salida
        Grid gdcdcost = null;
        Grid gddscost = null;
    	
    	
        //Otras Variables
        
        
        try {
        	
        	idemisor = input.getStringValue("idemisor");
        	idunicox = input.getStringValue("idunicox");
        	cddivisa = input.getStringValue("cddivisa");
        	tipooper = input.getStringValue("tipooper");
        	
        	//idunicox = "PRUEBA0011";
        	cddivisa = "EUR";
        	
        	HashMap<String, String> hmCostRc = new HashMap();
        	
        	for (int i = 0; i < arcdcost.size(); i++){
        		hmCostRc.put(arcdcost.get(i).toString(), input.getStringValue(arcdcost.get(i).toString()));
        	}
        	
        	ListCodigoCostesBDIn codCosteBDIn = new ListCodigoCostesBDIn();
        	//codCosteBDIn.setValue("mcactivo","S");
        	ListCodigoCostesBD codCosteBD = new ListCodigoCostesBD(codCosteBDIn);
        	codCosteBD.setConnection(con);
			gdcdcost = codCosteBD.execSelect();

			for (int i = 0; i < gdcdcost.rowCount(); i++){
				
				String codedesg = gdcdcost.getStringCell(i,"codedesg");
				
				if (hmCostRc.containsKey(codedesg)){
					try {
						String desvalue = hmCostRc.get(codedesg);
						
						if ((desvalue != null) && (!desvalue.equals("")) && !desvalue.equals("0")){
							UpdCostesBDIn costesBDIn = new UpdCostesBDIn();
							costesBDIn.setValue("idunicox", idunicox);
							costesBDIn.setValue("idemisor", idemisor);
							costesBDIn.setValue("codedesg", codedesg);
							costesBDIn.setValue("desvalue", desvalue);
							costesBDIn.setValue("cddivisa", cddivisa);
							UpdCostesBD costesBD = new UpdCostesBD(costesBDIn);
							costesBD.setConnection(this.getConnection());
							try {
								costesBD.execInsert();
							} catch(Exception e) {
								System.out.println(idunicox +" - "+ idemisor +" - "+ codedesg +" ACTUALIZADO!!");
								costesBD.setConnection(this.getConnection());
								costesBD.execUpdate();
							}
							
						}
					} catch (Exception e) {
						System.out.println("ERROR "+ e.getMessage());
					}
					
				}
				
			}
			
			codCosteBDIn = new ListCodigoCostesBDIn();
        	codCosteBDIn.setValue("tipooper",tipooper);
        	codCosteBDIn.setValue("mcactivo","S");
        	codCosteBD = new ListCodigoCostesBD(codCosteBDIn);
        	codCosteBD.setConnection(con);
			gdcdcost = codCosteBD.execSelect();
			
			ListDesgloseCostesBDIn desCosteBDIn =	 new ListDesgloseCostesBDIn();
        	desCosteBDIn.setValue("idunicox",idunicox);
        	desCosteBDIn.setValue("idemisor",idemisor);
        	desCosteBDIn.setValue("tipooper",tipooper);
        	desCosteBDIn.setValue("mcactivo","S");
        	ListDesgloseCostesBD desCosteBD = new ListDesgloseCostesBD(desCosteBDIn);
        	desCosteBD.setConnection(con);
        	gddscost = desCosteBD.execSelect();
			
			output.setValue("idemisor", idemisor);
			output.setValue("idunicox", idunicox);
			output.setValue("tipooper", tipooper);
        	output.setGrid("gdcdcost", gdcdcost);
        	output.setGrid("gddscost", gddscost);
        	
        } catch (Exception e1) {
			e1.printStackTrace();
		}            
        
    }
    
    public void recuperaCodigosCostes(){
    	
    	try {
    		

			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/des_jletproj");
			Connection part_con = ds.getConnection();
    		
    		ListCodigoCostesBDIn codCosteBDIn = new ListCodigoCostesBDIn();
        	codCosteBDIn.setValue("mcactivo","S");
        	ListCodigoCostesBD codCosteBD = new ListCodigoCostesBD(codCosteBDIn);
        	codCosteBD.setConnection(part_con);
			Grid gdcdcost = codCosteBD.execSelect();
			
			for (int i = 0; i < gdcdcost.rowCount(); i++){
				arcdcost.add(gdcdcost.getStringCell(i, "codedesg"));
				arcdcost.add(gdcdcost.getStringCell(i, "codedesg").substring(0,2) +"IVA"+ gdcdcost.getStringCell(i, "codedesg").substring(2,7));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
}