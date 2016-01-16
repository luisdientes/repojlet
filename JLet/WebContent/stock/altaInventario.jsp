<%@ include file="/common/jsp/include.jsp"%>

<%


	String idemisor = null; 
    String tpclient = null; 
    String nameinve = null;
    String logoemis = null;
	Grid gridLine = null; 
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			
			idemisor = io.getStringValue("idemisor");
			nameinve = io.getStringValue("nameinve");
			gridLine = io.getGrid("gridLine");
			logoemis = io.getStringValue("logoemis");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/altaStock.jsp "+ e.getMessage());	
		}
		
		if(nameinve == null){
			nameinve = "";
		}
	}
%>	
<script type="text/javascript" src="stock/js/altaStock.js"/></script>

<script>

	var codigoin = new Array();
	var tpproduc = "";
	var tipocheq = "";

	function pulsar(e) {
		  tecla = (document.all) ? e.keyCode : e.which;
		  if (tecla==13){
			  //alert("entra");
			 // validar(); 
		  }
		}
	
	function foco(){
		document.getElementById("idunicox").focus();
	}

	function buscaInventario(nameinve){
		
		if (chequeSiExiste) {		
			document.formMenu.nameinve.value = nameinve;
			document.formMenu.submit();
		}
		
	}
	
	function validar(){
		
		idunicox = document.formpedi.idunicox.value;
		
		if(idunicox == ""){
			alert("Error en IMEI");
		}else if (!chequeaSiExiste(idunicox)){
		
			document.formpedi.controller.value 	= "StockHttpHandler";
			document.formpedi.services.value	= "TempInventSrv";
			document.formpedi.view.value 		= "stock/altaInventario.jsp";
			
			idemisor = document.formpedi.idemisor.value;
			tpclient = document.formpedi.tpclient.value;
			//idunicox = document.formpedi.idunicox.value;
			nameinve = document.formpedi.nameinve.value; 
			entraV(idemisor,tpclient, idunicox, nameinve,tpproduc);	
			}
			
	}

	function chequear(){
		
		if (confirm("Seguro que quiere realizar el chequeo?")){
			tipocheq = "";
			checkboxes = document.getElementsByTagName('input');
			for (var x=0; x < checkboxes.length; x++) {
				if(checkboxes[x].type == "checkbox") //solo si es un checkbox entramos
	            {
					if (checkboxes[x].checked) {
							tipocheq+= "'"+checkboxes[x].value+"',";
					}
	            }
			}
			
			tipocheq =  tipocheq.substring(0, tipocheq.length-1); //ELIMINO ULTIMA COMA
			document.formpedi.controller.value 	= "StockHttpHandler";
			document.formpedi.services.value	= "ChequeaInventarioSrv";
			document.formpedi.view.value 		= "stock/resultInventario.jsp";
			document.formpedi.tipocheq.value  = tipocheq;
			
			idemisor = document.formpedi.idemisor.value;
			nameinve = document.formpedi.nameinve.value;
			document.formpedi.tpproduc.value = tpproduc;
			document.formpedi.submit();
			
		}
	}
	
	function chequeaSiExiste(codigo){
		
		for	(i = 0; i < codigoin.length; i++) {
		    if (codigoin[i] == codigo) {
		    	alert("El codigo "+ codigo +" ya esta introducido en el inventario: <%=nameinve%>");
		    	return true;
		    }
		    
		} 
		
		codigoin.push(codigo);
		
		return false;
		
	}
	
	function cargaInicial(){
		
		<% for (int i = 0; i < gridLine.rowCount(); i++){ %>
			codigoin.push('<%=gridLine.getStringCell(i,"idunicox")%>');
		<% } %>
		
	}
	
	function cambia(tipopro){
		
		if(tipopro=='TA'){
			document.getElementById("TA").className = "fonts6v2";
			document.getElementById("PH").className = "fonts6";
			document.getElementById("PI").className = "fonts6";
			document.getElementById("OT").className = "fonts6";
		}
		if(tipopro=='PH'){
			document.getElementById("PH").className = "fonts6v2";
			document.getElementById("TA").className = "fonts6";
			document.getElementById("PI").className = "fonts6";
			document.getElementById("OT").className = "fonts6";
		}
		if(tipopro=='PI'){
			document.getElementById("PI").className = "fonts6v2";
			document.getElementById("PH").className = "fonts6";
			document.getElementById("TA").className = "fonts6";
			document.getElementById("OT").className = "fonts6";
		}
		if(tipopro=='OT'){
			document.getElementById("OT").className = "fonts6v2";
			document.getElementById("PH").className = "fonts6";
			document.getElementById("PI").className = "fonts6";
			document.getElementById("TA").className = "fonts6";
			
		}
		tpproduc = tipopro;
		
	}
	
	
function cambiaChequeo(tipopro){
		
		if(tipopro=='TA'){
			document.getElementById("CHTA").className = "fonts6v2";
			document.getElementById("CHPH").className = "fonts6";
			document.getElementById("CHPI").className = "fonts6";
			document.getElementById("CHTO").className = "fonts6";
		}
		if(tipopro=='PH'){
			document.getElementById("CHPH").className = "fonts6v2";
			document.getElementById("CHTA").className = "fonts6";
			document.getElementById("CHPI").className = "fonts6";
			document.getElementById("CHTO").className = "fonts6";
		}
		if(tipopro=='PI'){
			document.getElementById("CHPI").className = "fonts6v2";
			document.getElementById("CHPH").className = "fonts6";
			document.getElementById("CHTA").className = "fonts6";
			document.getElementById("CHTO").className = "fonts6";
		}
		if(tipopro=='TO'){
			document.getElementById("CHTO").className = "fonts6v2";
			document.getElementById("CHPH").className = "fonts6";
			document.getElementById("CHPI").className = "fonts6";
			document.getElementById("CHTA").className = "fonts6";
			
		}
		tpproduc = tipopro;
		
	}
	

</script>
<body onload="cargaInicial();foco();">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
		<div class="testata"><img title="Usuario" src="/JLet/common/img/icons/emisores/<%=logoemis%>">&nbsp;&nbsp;<img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
		<div class="nompanta" >Crear Inventario</div>
			<div class="centrado" style="width:100%">
				
				<form method="POST" name="formMenu" action="/JLet/App">
						<input type="hidden" name="controller" 	value="StockHttpHandler"/>
						<input type="hidden" name="services" 	value="ListInventSrv"/>
						<input type="hidden" name="view" 		value="stock/altaInventario.jsp"/>
						<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
						<input type="hidden" name="tpclient" 	value=""/>
						<input type="hidden" name="cdestado" 	value="N"/>
						<input type="hidden" name="tipocons" 	value=""/>
						<input type="hidden" name="cdpantal" 	value=""/>
						<input type="hidden" name="nameinve" 	value=""/>
						<input type="hidden" name="tpproduc" 	value=""/>
				</form>
			
				<form name="formpedi" method="POST" action="">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services"	value="TempInventSrv"/>
					<input type="hidden" name="view" 		value="stock/altaInventario.jsp"/>	
					<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
					<input type="hidden" name="tpclient" value="<%=tpclient%>"/>
					<input type="hidden" name="tpproduc" 	value=""/>
					<input type="hidden" name="tipocheq" 	value=""/>
					<br/>
					<br/>
					
					<br/>
					
					<table border="0" width="30%" align="center" border="1">
						<tr>
							<td class="input-b1">Cod. Inventario</td>
							<td align="center"><input class="input-m" type="text" readonly value="<%=nameinve %>" id="nameinve" name="nameinve" onchange="buscaInventario(this.value);" onkeypress = "pulsar(event)" /></td>						
						</tr>
						
						<tr>
							<td class="input-b1">INTRODUCE IMEI</td>                                      				        
							<td align="center"><input class="input-m" type="text" maxlength=20 id="idunicox" name="idunicox" onchange="validar();" onkeypress = "pulsar(event)" /></td>
						</tr>
						
						<tr>
							<td class="input-b1">COD PRODUCTO</td>                                      				        
							<td align="center"><input class="input-m" type="text" id="codprodu" name="codprodu"/></td>
						</tr>
						
					</table>
					
					<br/>
					<table width="45%" align="center" class="tdRound" style="background-color:#BBBBBB;">
						<tr style="cursor:pointer">
							<td align="center" width="25%" class="fonts6" onclick="cambia('TA')" id='TA'>Tablet</td>
							<td align="center" width="25%" class="fonts6" onclick="cambia('PI')" id='PI'>Piezas</td>
							<td align="center" width="25%" class="fonts6" onclick="cambia('PH')" id='PH'>Telefonos</td>
							<td align="center" width="25%" class="fonts6v2" onclick="cambia('OT')" id='OT'>Otros</td>
						</tr>
					</table>
					<br>
					<br>
					
					<table width="100%" align="center">
						<tr>
							
							<td align="center">
								<a class="boton" onClick="validar()">Aceptar</a>
							</td>
						</tr>
					</table>
				 
				</form>
				
				<div id="lineastmp">
				
				 	<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %> 
				 		<table border="0" class="TablaGrande" style="width: 100%;text-align: center">
				  	   		<tr class="fonts">
								<td class="input-b1" align="center" width="15%">N. Producto</td>
								<td class="input-b1" align="center" width="50%">IMEI / Id. &Uacute;nico</td>
								<td class="input-b1" align="center" width="25%">Fecha alta</td>
								<td class="input-b1" align="center" width="10%">Tipo producto</td>
								<td align="center" width="10%"></td>
							</tr>
				  	  		<tr>
				  	  			<td colspan="13"><hr style="color: #E1E1E1"></td>	
				  	  		</tr>
				  		
				  		<%
				  		
					  		String idinvent = ""; 
					  		String idunicox = ""; 
					  		String fhaltaxx = ""; 
					  		String consolid = "";
					  		String tpproduc = "";
					  		String txproduc = "";
					  
				  		
					  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
					  			
					  			for (int i = 0; i < gridLine.rowCount(); i++){
					  				
					  				try {
					  					idinvent = gridLine.getStringCell(i,"idinvent");
					  					idunicox = gridLine.getStringCell(i,"idunicox");
					  					fhaltaxx = gridLine.getStringCell(i,"fhaltaxx");
					  					idemisor = gridLine.getStringCell(i,"idemisor");
					  					tpclient = gridLine.getStringCell(i,"tpclient");	  					
					  					consolid = gridLine.getStringCell(i,"consolid");	
					  					tpproduc = gridLine.getStringCell(i,"tpproduc");
					  					
					  					if(tpproduc.equals("TA")){
					  						txproduc ="TABLET";
					  					}else if(tpproduc.equals("PH")){
					  						txproduc ="TELEFONO";
					  					}else if(tpproduc.equals("PI")){
					  						txproduc ="PIEZA";
					  					}else if(tpproduc.equals("") || tpproduc.equals("TO")){
					  						txproduc ="SIN ESPECIFICAR";
					  					}
					  					
									if (i % 2 == 0) { %>
										<tr class="oddRow"  style="border:1px solid">
									<% } else { %>
										<tr class="evenRow" style="border:1px solid">
									<% }%>
								  			<td ><div class="fonts6jej">&nbsp;&nbsp;&nbsp;&nbsp;--<%=idinvent%>--</div></td>
										  	<td><div class="fonts6jej"><%=idunicox%></div></td>
											<td><div class="fonts6jej" align="center"><%=fhaltaxx%></div></td>
											<td><div class="fonts6jej"><%=txproduc%></div></td>
											<td width="5%" class="fonts6jej"><img src="common/img/varios/delete.jpg" width=20 height=20 style="cursor:pointer" onclick="borraLineaInve('<%=idemisor%>','<%=idinvent%>','<%=nameinve%>')"></td>
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
				 	<%	} %>
						
				 	<br/>
				 	<br/>
				 	
				</div>
				
				<table width="60%" align="center">
					<tr>
						<td>Tipo de chequeo:</td>
						<td><input type="checkbox" value="TA" id="CTA"> Tablets</td>
						<td><input type="checkbox" value="PH" id="CPH"> Telefonos</td>
						<td><input type="checkbox" value="PI" id="CPI"> Piezas</td>
						<td><input type="checkbox" value="OT" id="CTO"> Otros</td>
					<tr>
						
						<td align="center" colspan="5">
							<a class="boton" onClick="chequear()">Chequear</a>
						</td>
					</tr>
				</table>
				
			</div>
		
	</div>
		
</body>