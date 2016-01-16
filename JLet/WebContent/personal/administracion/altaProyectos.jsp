<%@ include file="/common/jsp/include.jsp"%>  
<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
<script type="text/javascript" src="common/js/calendar.js"></script>
<script type="text/javascript" src="common/js/calendar-es.js"/></script>
<script type="text/javascript" src="common/js/calendar-setup.js"/></script>      
<html>
<head>
<title>Alta Proyectos</title>


 <%

	//response.sendRedirect("gestion/Login.jsp");
 
  	
   	String idproyec = "";
	String txnombre = "";
	String idempres = "";
	String tpproyec = "";
	String numperso = "";
	String txduraci = "";
	String txdescri = "";
	String fhinicio = "";
	String fhfinxxx = "";
	String txbotonx = "";
	String tipoalta = "";
	String txempres = "";
	String idlistem = "";
	String idemisor = "";
	
	Grid gridEmpre	= null;
	Grid gridProy	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
			try {
				gridEmpre = io.getGrid("gridEmpr");
			} catch (Exception e) {
				System.err.println("ERROR - Recibiendo en el listado de empresas");	
			}
			
			try {
				gridProy = io.getGrid("gridProy");
			} catch (Exception e) {
				System.err.println("ERROR - Recibiendo en el listado de proyectos");	
			}
			try {
				gridProy = io.getGrid("gridProy");
			} catch (Exception e) {
				System.err.println("ERROR - Recibiendo en el listado de proyectos");	
			}
			
			try {
				idemisor = io.getStringValue("idemisor");
			} catch (Exception e) {
				System.err.println("ERROR - Recibiendo el idemisor");	
			}
			
			try{
		    	idproyec = gridProy.getStringCell(0,"idproyec");  
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo idproyec");
		    	}
		    	try{
		    		txnombre = gridProy.getStringCell(0,"txnombre");
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo txnombre");
		    	}
			    	
		    	try{
		    		idempres = gridProy.getStringCell(0,"idempres");
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo idempres");
		    	}
		    	
		    	try{
		    		tpproyec = gridProy.getStringCell(0,"tpproyect"); 
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo tpproyect");
		    	}
		    	
		    	try{
		    		numperso = gridProy.getStringCell(0,"numperso"); 
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo numperso");
		    	}
		    	
		    	try{
		    		txdescri = gridProy.getStringCell(0,"txdescri");
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo txdescri");
		    	}
		    	
		    	try{
		    		txduraci = gridProy.getStringCell(0,"txduracio");
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo txduracio");
		    	}
		    	
		    	try{
		    		fhinicio = gridProy.getStringCell(0,"fhinicio");
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo fhinicio");
		    		
		    	}
		    	
		    	try{
		    		fhfinxxx = gridProy.getStringCell(0,"fhfinxxx");
		    	}catch(Exception ex){
		    		System.out.println("Error recibiendo fhfinxxx");
		    	
		    	}
		    	                                                  
			   
			    if(txnombre !=null && txnombre.equalsIgnoreCase("")){
			    	tipoalta = "IPR";
			    	txbotonx = "Alta Proyecto";
			    }
			    else{
			    	tipoalta = "APR";
			    	txbotonx = "Modificar Proyecto";
			    }
			    
			    System.out.println("LLEGAAAAAAAAAAAAAAAAAo.");
    
	}
    %>

<script>


function enviar(urldesti){
	document.location.href=urldesti;
}

	
	function validar(){
		var cadena = "";
		txnombre = document.altaProyecto.txnombre.value;
		
		fhinicio = document.altaProyecto.fhinicio.value;
		fhfinxxx = document.altaProyecto.fhfinxxx.value;
		
	
		if(fhinicio != ""){
			if(ver_fecha(fhinicio)){
				if(!fechaCorrecta(fhinicio)){
					cadena="La fecha inicio no es correcta\n";
				}
			}
			
			else{
				cadena+="La fecha inicio no tiene el formato correcto\n";		
			}
		}
		
		if(fhfinxxx != ""){
			if(ver_fecha(fhfinxxx) && fhfinxxx != ""){
				
				if(!fechaCorrecta(fhfinxxx)){
					cadena+="La fecha fin no es correcta\n";
				}
			}
			else{
				cadena+="La fecha fin no tiene el formato correcto\n";		
			}
		}	
		
		if(txnombre == ""){
			cadena +="No se puede dejar el nombre vacio";
		}
		
		if (cadena == ""){
			document.altaProyecto.submit();
		}
		else{
			alert(cadena);
		}
	}
	
	function calendarSetup(){
		
		Calendar.setup({ 
			inputField : "fhinicio",    // id del campo de texto 
			ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
			button     : "lanzador"     // el id del botón que lanzará el calendario 
		});
		
		Calendar.setup({ 
			inputField : "fhfinxxx",    // id del campo de texto 
			ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
			button     : "lanzador2"     // el id del botón que lanzará el calendario 
		});
	}
	
	
	
</script>
   
<html>	
 
<body onLoad="calendarSetup();">
<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/title-project.png"/></div>
		
	<div class="centrado4B">
	<form name="altaProyecto" method="post" action="/JLet/App">
			<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="GestProSrv"/>
			<input type="hidden" name="view" value="personal/administracion/resultado.jsp"/>
			<input type="hidden" value="<%=tipoalta%>" name="tipoOper"></input>
			<input type="hidden" value="<%=idproyec%>" name="idproyec" ></input>	
			<input type="hidden" value="<%=idemisor%>" name="idemisor" ></input>
		<table cellspacing="10" border="0" align="center">
			  <tr>
			  	 <td class="input-b1" style="width:220px"><span>Nombre Proyecto:</span></td>
			  	 <td><input type="text" maxlength=100 name="txnombre" value="<%=txnombre%>" size="100" class="input-m3" style="width:100%"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px"><span>Empresa:</span></td>
			  	 <td>
			  	 <select name="idempres" class="input-m3" style="width:100%">
			  	 		<% 
			  	     	try{
	   						for(int i=0; i< gridEmpre.rowCount();i++){
	    						txempres = gridEmpre.getStringCell(i,"txrazons");
	    						idlistem = gridEmpre.getStringCell(i,"idempres");
							%>
								<option id="E<%=idlistem%>E" value="<%=idlistem%>"><%=txempres %></option>
							
							<% 
	    					}
    					}catch(Exception ex){
    						System.out.println("Error recibiendo empresas.");
    					}%>
			  	 </select>
			  	 </td>
			  </tr>
			   <tr>
			  	 <td class="input-b1" style="width:220px"><span>Tipo de proyecto:</span></td>
			  	 <td><input type="text" maxlength=100 name="tpproyec" value="<%=tpproyec%>" size="100" class="input-m3" style="width:100%"></td>
			  </tr>
			  <tr>	 
			  	 <td class="input-b1" style="width:220px">Numero de personas</td>
			  	 <td ><input type="text" maxlength=3 name="numperso" value="<%=numperso%>" size="30" class="input-m3" style="width:100%"></td>

			  <tr>
			  	 <td class="input-b1" style="width:220px">Duraci&oacute;n</td>
			  	 <td><input type="text" maxlength=12 name="txduraci"  value="<%=txduraci%>" size="30" class="input-m3" style="width:100%"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px">Descripci&oacute;n</td>
			  	 <td ><textarea  name="txdescri" class="input-m3" style="width:100%"><%=txdescri%></textarea></td>
			  </tr>
			  <tr>
				<td class="input-b1"style="width:220px"> Fecha Inicio:</td>
				<td>
				<input type="text" name="fhinicio" id="fhinicio" value="<%=fhinicio%>" class="input-m3" style="width:80%"> <img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Inicio" id="lanzador">
				</td>
			  </tr>
			  <tr>
				<td class="input-b1" style="width:220px"> Fecha Fin:</td>
				<td>
					<input type="text" name="fhfinxxx" id="fhfinxxx" value="<%=fhfinxxx%>"   class="input-m3" style="width:80%"> <img src="common/img/varios/fechas.png" style="cursor:pointer" width="24" height="24" border="0" title="Fecha Fin" id="lanzador2">
				</td>
			  </tr>
			 
	  	</table>
	  	<table align="center" width="80%">
	  	
	  	<br>
	  	<br>
	  	<br>
	  	<br>	
	  	<br>
	  		<tr>
	  		<td align="center">
	  		 	<a onclick="validar();" class="boton marginboton" ><%=txbotonx %></a>
	  		</td>
	
	  		</tr>
		</table>
		
	</form>	
	</div>
    </div>	
    <script>
	
	    <% if (idempres != null && !idempres.equals("")) {%>
	    	empresa ="<%=idempres%>"; 
	    	empresa = "E"+empresa+"E";
	    	document.getElementById(empresa).selected="true";
	    <%}%>
    </script>	
</body>
</html>


