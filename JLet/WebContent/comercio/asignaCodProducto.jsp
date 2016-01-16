<%@ include file="/common/jsp/include.jsp"%>

<%

	String txmensaj = "";
	Grid gridLine   = null;
	Grid gridPhon   = null;
	Grid gridProd   = null;
	int tontalLin = 0;
	String txmarcax	= "";
	String txmodelo	= "";
	String idphonex	= "";
	String codProdu = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			txmensaj = io.getStringValue("txmensaj");
			gridLine = io.getGrid("gdEnvios");
			gridPhon = io.getGrid("gridPhon");
			gridProd = io.getGrid("gridProd");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/asignaCodigoProd.jsp "+ e.getMessage());	
		}
	}
	
%>

<script>
	var MarcaMov    = new Array();
	var ModelMov    = new Array();
	var idPhonex    = new Array();
	
	var MarcaPro    = new Array();
	var ModelPro    = new Array();
	var idProduc    = new Array();
	var i =0;
	
	function cargaProducts(){
		
		
		<%
	 	for (int j = 0; j < gridPhon.rowCount(); j++){
	 	  	txmarcax 	= gridPhon.getStringCell(j,"txmarcax");
	 	  	txmodelo 	= gridPhon.getStringCell(j,"txmodelo");
	 	  	idphonex	= gridPhon.getStringCell(j,"cdgrupox");
		%>
			MarcaMov[i] = "<%=txmarcax%>";
			ModelMov[i] = "<%=txmodelo%>";
		 	idPhonex[i] = "<%=idphonex%>";
			i++;
		<%
	 	}
	 	%>
	 	
	 	
	 	i=0;
		<%
	 	for (int j = 0; j < gridProd.rowCount(); j++){
	 	  	txmarcax 	= gridProd.getStringCell(j,"txmarcax");
	 	  	txmodelo 	= gridProd.getStringCell(j,"txmodelo");
	 	  	idphonex	= gridProd.getStringCell(j,"idgrupox");
		%>
			MarcaPro[i] = "<%=txmarcax%>";
			ModelPro[i] = "<%=txmodelo%>";
			idProduc[i] = "<%=idphonex%>";
			i++;
		<%
	 	}
	 	%>
		
	}
	cargaProducts();
	
	
	function muestraPhone(idphone,linea){
		
		var tipoProd 	=	idphone.substring(0,2);
		var idProd 		=	idphone.substring(2);
		var existe	 	=	0;
		tipoProd		=	tipoProd.toUpperCase();
		idlinea = "nombrepro"+linea;
	
		
		if(!isNaN(idProd)){
			idProd = parseInt(idProd);
		}	
		
		if(tipoProd == "PH"){
			
			for (i = 0; i < idPhonex.length; i++){
				if(idPhonex[i] == idProd){
					existe=1;
					cadenaEnc	= MarcaMov[i] +" "+ModelMov[i] ;
				}
			}
			if( existe == 0){
				document.getElementById(idlinea).innerHTML = "No encontrado";
			}
			else{
				document.getElementById(idlinea).innerHTML = cadenaEnc;
				
			}
		}
		
		if(tipoProd == "PR"){
			
			for (i = 0; i < idProduc.length; i++){
				if(idProduc[i] == idProd){
					existe=1;
					cadenaEnc	= MarcaPro[i] +" "+ModelPro[i] ;
					
				}
			}
			if( existe == 0){
				document.getElementById(idlinea).innerHTML = "No encontrado";
			}
			else{
				document.getElementById(idlinea).innerHTML = cadenaEnc;
				
			}
		}	
	}
	
	var arrayIdLinea = new Array();
	var arrayIdProdu = new Array();
	
	function recorreArray(){
		
		totallineas = document.lineasPedido.totallin.value;
		
		for(x = 0; x< totallineas;x++){
			idproenvi	= "idproe"+x;
			idproduct	= "idpro"+x;
			
			idprodu = document.getElementById(idproduct).value;
			
			if (idprodu == ""){
				idprodu = "-1";
			}
			
			arrayIdLinea[x] = document.getElementById(idproenvi).value;
			arrayIdProdu[x] = idprodu;
		}
		
		document.formvali.idproenv.value = arrayIdLinea;
		document.formvali.idproduc.value = arrayIdProdu;
		
		document.formvali.submit();
		
	}
</script>


<body>

	<div class="fondo2">
		<div class="centrado" style="width:90%">
			<div id="lineastmp">
			<form name="lineasPedido">
			 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
			 		<table width="100%" border="0" class="TablaGrande">
			  	   	<tr class="fonts">
						<td align="center">&nbsp;</td>
						<td align="center" width="5%">Cod. Product</td>
						<td align="center">Make</td>
						<td align="center">Model</td>
						<td align="center">Nombre completo</td>
						<td align="center">&nbsp;</td>
					</tr>
			  	  		<tr>
			  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
			  	  		</tr>
			  		
			  		<%
			  		
				  		String idproenv = ""; 
 
			  		
				  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
				  			
				  			for (int i = 0; i < gridLine.rowCount(); i++){
				  				
				  				try {
				  					idproenv = gridLine.getStringCell(i,"idproenv");
				  					txmarcax = gridLine.getStringCell(i,"txmarcax");
				  					txmodelo = gridLine.getStringCell(i,"txmodelo");
				  					codProdu = gridLine.getStringCell(i,"idproduc");
				
				  					
								%>  	
							  		<tr>
							  		<td><input type="hidden" id="idproe<%=i %>" value="<%=idproenv%>"></td>
									  	<td><input id="idpro<%=i %>" type="text" value="<%=codProdu %>" / onblur="muestraPhone(this.value,<%=i%>)"></td>
										<td><div class="input-j"><%=txmarcax%></div></td>
										<td><div class="input-j"><%=txmodelo%></div></td>
										<td><div class="input-j" id="nombrepro<%=i%>"> ------ </div></td>
									</tr>
								<script>
								    
									muestraPhone('<%=codProdu%>',<%=i%>)
								</script>
							<%
							
				  				} catch (Exception e) { 
									e.printStackTrace();
									System.out.println("ERROR recuperando linea "+ i); %>
									<tr>
										<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
									</tr>
							<%	}
							}
						}
					%>
					</table>
					<input type = "hidden" value="<%=gridLine.rowCount()%>" name ="totallin" >
				</form>
					<form name="formvali" method="POST" action="/JLet/App">
						<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
						<input type="hidden" name="services"	value="AsignaProdEnvioSrv"/>
						<input type="hidden" name="view" 		value="common/jsp/menuPrinc.jsp"/>	
						<input type="hidden" name="idproenv" 	value="" />	
						<input type="hidden" name="idproduc" 	value="" />					
						
						<br/>
						<br/>
						<table width="100%" align="center">
							<tr>
								<td align="center">
									<a class="boton" onClick="recorreArray()">Asignar Código</a>
								</td>
							</tr>
						</table>
					</form>
				<% } %>
			</div>
			
		</div>
	</div>
</body>