<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gridLine = null;
	Grid gridDivi = null;
	String txmensaj = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gridLine = io.getGrid("gdEnvios");			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/validarEnvio.jsp");	
		}
	}		

	
	String strprchf = "";
	String strmgchf = "";
	String strttchf = "";
	String strprusd = "";
	String strmgusd = "";
	String strttusd = "";
	String idlineax = "";
	
%>


<head>
	<script>
		
	arraLinea = new Array();
	<%
	 for (int j = 0; j < gridLine.rowCount(); j++){ 
		 idlineax = gridLine.getStringCell(j, "idproenv");
	   %>
	   arraLinea[<%=j%>] = "<%=idlineax%>";
	   
	 <% }%>
	

		function validarEnvio(){
			
			clickCheckbox();
			document.formvaen.submit();
			
		}
		
		function clickCheckbox(){
			
			arrCusTax = new Array(); 
			arrConTax = new Array();
			arrfletec = new Array();
			arrItbisx = new Array();
			arrTramad = new Array();
			arrAlmace = new Array();
			arrMovCon = new Array();
			arrCargaN = new Array();
			
			
			
			 for (j = 0; j < arraLinea.length; j++){
				 
				 idCustoTax = "chcustotax"+arraLinea[j];
				 idConsuTax = "chconsutax"+arraLinea[j];
				 idfletecst = "chfletecst"+arraLinea[j];
				 idItbisimp = "chitbisimp"+arraLinea[j];
				 idTramadua = "chtramadua"+arraLinea[j];
				 idAlmacena = "chalmacena"+arraLinea[j];
				 idMovconte = "chmovconte"+arraLinea[j];
				 idCargnavi = "chcargnavi"+arraLinea[j];
			
				 
				 if(document.getElementById(idCustoTax).checked){
					 custotax = "S";
				 }else{
					 custotax ="N"; 
				 }
				 
				 if(document.getElementById(idConsuTax).checked){
					 consutax = "S";
				 }else{
					 consutax ="N"; 
				 }
				 
				 if(document.getElementById(idfletecst).checked){
					 fletecst = "S";
				 }else{
					 fletecst ="N"; 
				 }
				 
				 if(document.getElementById(idItbisimp).checked){
					 itbisimp = "S";
				 }else{
					 itbisimp ="N"; 
				 }
				 
				 if(document.getElementById(idTramadua).checked){
					 tramadua = "S";
				 }else{
					 tramadua ="N"; 
				 }
				 
				 if(document.getElementById(idAlmacena).checked){
					 almacena = "S";
				 }else{
					 almacena ="N"; 
				 }
				 
				 if(document.getElementById(idMovconte).checked){
					 movconte = "S";
				 }else{
					 movconte ="N"; 
				 }
				 
				 if(document.getElementById(idCargnavi).checked){
					 cargnavi = "S";
				 }else{
					 cargnavi ="N"; 
				 }
				 
				 arrCusTax[j] = custotax;
				 arrConTax[j] = consutax;
				 arrfletec[j] = fletecst;
				 arrItbisx[j] = itbisimp;
				 arrTramad[j] = tramadua;
				 arrAlmace[j] = almacena;
				 arrMovCon[j] = movconte;
				 arrCargaN[j] = cargnavi;
				 
			}
			 document.formvaen.custotax.value = arrCusTax ;
			 document.formvaen.consutax.value = arrConTax ;
			 document.formvaen.fletecst.value = arrfletec ;
			 document.formvaen.itbisimp.value = arrItbisx ;
			 document.formvaen.tramadua.value = arrTramad ;
			 document.formvaen.almacena.value = arrAlmace ;
			 document.formvaen.movconte.value = arrMovCon ;
			 document.formvaen.cargnavi.value = arrCargaN ;
			 document.formvaen.idlineas.value = arraLinea ;
			 

		}
	
	  function marcarTodosCheck()
	    {
	        checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
	        for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
	        {
	            if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
	            {
	                checkboxes[i].checked = true; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
	            }
	        }
	    }
	  
	  function marcarCheck(idcheck){

		  if(document.getElementById(idcheck).checked){
			  
			  for (j = 0; j < arraLinea.length; j++){
					 idCheck = idcheck+arraLinea[j];
					 document.getElementById(idCheck).checked = true;
		     }
		  
	      }else{
	    	  for (j = 0; j < arraLinea.length; j++){
					 idCheck = idcheck+arraLinea[j];
					 document.getElementById(idCheck).checked = false;
		     }
	    	  
	      }
	  }
		
	</script>
</head>


<body>
	<div class="fondo2">
		<div class="centrado" style="width:95%">
			<form name="formvaen" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="AplicaTaxesEnvioSrv"/>
				<input type="hidden" name="view" 		value="comercio/listEnvio.jsp"/>
				<input type="hidden" name="idlineas" 		value=""/>
				<input type="hidden" name="custotax" 		value=""/>
				<input type="hidden" name="consutax" 		value=""/>
				<input type="hidden" name="fletecst" 		value=""/>
				<input type="hidden" name="itbisimp" 		value=""/>
				<input type="hidden" name="tramadua" 		value=""/>
				<input type="hidden" name="almacena" 		value=""/>
				<input type="hidden" name="movconte" 		value=""/>
				<input type="hidden" name="cargnavi" 		value=""/>
				

				<br/>
				
			   <table width="80%" border=0 class="TablaGrande" align="center">
				   <tr algin=center">
						<td align="center"><a class="boton" onclick='marcarTodosCheck()'>All Check Taxes </a></td>
					</tr>
			   </table>
				
				<table width="100%" border=0 class="TablaGrande">
		  	   		<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">Imei</td>
						<td align="center" class="input-b1">Make</td>
						<td align="center" class="input-b1">Model</td>
						<td align="center" class="input-b1">Gravamen Aduana</td>
						<td align="center" class="input-b1">Selectivo al Consumo</td>
						<td align="center" class="input-b1">Flete</td>
						<td align="center" class="input-b1">ITBIS Imp</td>
						<td align="center" class="input-b1">Tram. Aduana</td>
						<td align="center" class="input-b1">Almacenaje</td>
						<td align="center" class="input-b1">Trans. Contenedor</td>
						<td align="center" class="input-b1">Carga Naviera</td>
						
					</tr>
					<tr>
					    <td colspan=4></td>
						<td align="center" width="8%"><input type="checkbox" name="chcustotax[]" id="chcustotax" onclick="marcarCheck('chcustotax')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chconsutax[]" id="chconsutax" onclick="marcarCheck('chconsutax')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chfletecst[]" id="chfletecst" onclick="marcarCheck('chfletecst')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chitbisimp[]" id="chitbisimp" onclick="marcarCheck('chitbisimp')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chtramadua[]" id="chtramadua" onclick="marcarCheck('chtramadua')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chalmacena[]" id="chalmacena" onclick="marcarCheck('chalmacena')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chmovconte[]" id="chmovconte" onclick="marcarCheck('chmovconte')" value="S" style="cursor:pointer"/></td>
						<td align="center" width="8%"><input type="checkbox" name="chcargnavi[]" id="chcargnavi" onclick="marcarCheck('chcargnavi')" value="S" style="cursor:pointer"/></td>
					</tr>
		  	  		<tr>
		  	  			<td colspan="13"><hr></td>	
		  	  		</tr>
		  		
		  		<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
			  			
			  			String idproenv = "";
		  				String milisegu = "";
			  			String imeicode = ""; 
				  		String txmarcax = ""; 
				  		String txmodelo = ""; 
				  	
		  			
		  			
			  			for (int i = 0; i < gridLine.rowCount(); i++){
			  				
			  				try {
			  					idproenv = gridLine.getStringCell(i,"idproenv");
			  					milisegu = gridLine.getStringCell(i,"milisegu");
			  					imeicode = gridLine.getStringCell(i,"imeicode");
			  					txmarcax = gridLine.getStringCell(i,"txmarcax");
			  					txmodelo = gridLine.getStringCell(i,"txmodelo");
			
			  					
							%>  	
						  		<tr>
						  			<td>&nbsp;</td>
								  	<td class="fonts6"><%=imeicode%></td>
									<td class="fonts6"><%=txmarcax%></td>
									<td><div class="fonts6"><%=txmodelo%></div></td>
							
									<form name="formCheck" id="formCheck">
									    <input type="hidden" name="lineaEnvi" value="<%=idproenv%>">
									    <td align="center" class="fonts6"><input type="checkbox" name="chcustotax[]" id="chcustotax<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chconsutax[]" id="chconsutax<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chfletecst[]" id="chfletecst<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chitbisimp[]" id="chitbisimp<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chtramadua[]" id="chtramadua<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chalmacena[]" id="chalmacena<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chmovconte[]" id="chmovconte<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									    <td align="center" class="fonts6"><input type="checkbox" name="chcargnavi[]" id="chcargnavi<%=idproenv%>" value="S" class="fonts6" style="cursor:pointer"/></td>
									 </form>
									
								</tr>
								<tr>
									<td colspan="13"><hr></td>
								</tr>
						<%	} catch (Exception e) { 
								e.printStackTrace();
								System.out.println("ERROR recuperando linea "+ i); %>
								<tr>
									<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
								</tr>
						<%	}
						} 
		
			  			
		  			} %>			
				</table>						
		</table>
		
	
		<br>
		<br>
		<br>
			<table width="100%" align="center">
				<tr>
				 	<td align="center">
						<a class="boton" onClick="validarEnvio()"> Confirmar Tasas</a>
				</td>
				</tr>
			</table>

		
	</form>
</body>