<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	String cdestado = "";
	Grid gridProd = null; 
	Grid grPrAgru = null;
	Grid gridDivi = null; 
	Grid gridMarc = null;
	Grid gridClie	= null;
	
	
	String imeicode = ""; 
	String txmarcax = ""; 
	String txmodelo = ""; 
	String idcolorx = ""; 
	String pricechf = "";  
	String pricecmp = ""; 
	String diviscmp = ""; 
	String prusdcmp = "";
	String margbene = "";  
	String pricevnt = ""; 
	String divisvnt = ""; 
	String prusdvnt = "";
	String impbenef = "";
	String txprovid = ""; 
	String txbuyerx = ""; 
	String txfundin = ""; 
	String withboxx = ""; 
	String withusbx = ""; 
	String idcatego = "";
	String idlineax = "";
	String idemisor = "";
	String tpclirec = "";
	String tpclient = "";
	String idclient = "";
	String valSelec = "";
	String logoemis = "";
	String tpproduc = "";
	String imagewpt = "";
	
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cdestado = io.getStringValue("cdestado");
			tpclirec = io.getStringValue("tpclient");
			idclient = io.getStringValue("idclient");
			logoemis = io.getStringValue("logoemis");
			txmarcax = io.getStringValue("txmarcax");
			tpproduc = io.getStringValue("tpproduc"); 
			gridProd = io.getGrid("gridProd"); 
			gridMarc = io.getGrid("gridMarc");
			
			System.out.println("CDESTADO == "+ cdestado);
			
			try{
				idclient = io.getStringValue("idclient");
				if(idclient !=null || idclient.equals("null") || idclient.equals("")){
					valSelec = tpclirec +"#"+idclient;
				}else{
					
				}
				
				System.out.println("sadsadsad "+valSelec);
			
			}catch(Exception ex) {
				idclient = "";
			}
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listadoStock.jsp "+ e.getMessage());	
		}
	}
	
	
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    simbolo.setDecimalSeparator(',');
    simbolo.setGroupingSeparator('.');
	
	DecimalFormat formatDivi = new DecimalFormat("###,##0.0000",simbolo);
	
	String disponib ="";
	if(cdestado.equals("STOCK")){
		
		disponib ="diponibles";
	}else if(cdestado.equals("DEPOSITO")){
		
		disponib =" en depósito";
	}else if(cdestado.equals("VENDIDO")){
		disponib =" vendidas";
	}
	
%>

<head>

<script>
	var Arrimeixx	= new Array();
	var cadImeisx	= "";	
	var chmarcado 	= 0;
	
	function cargaImeis(){
		i=0;
		
		<%
		if ((gridProd != null) && (gridProd.rowCount() > 0)) { 
  			for (int j = 0; j < gridProd.rowCount(); j++){
	 			imeicode 	= gridProd.getStringCell(j,"imeicode");
			%>
			Arrimeixx[i] = "<%=imeicode%>";
			i++;
		<%
	 		}
		}	
	 	%>
		
	}
	
	function filtraStock(){
		
		
		txmarcax = document.getElementById("txmarcax").value;
		tpproduc = document.getElementById("tpproduc").value;
		
		document.formMenu.services.value = "ListStockImgSrv";
		document.formMenu.view.value	 = "stock/listadoStockImagen.jsp";
		document.formMenu.idemisor.value = "<%=idemisor%>";
		document.formMenu.cdestado.value = "STOCK";
		document.formMenu.txmarcax.value = txmarcax;
		document.formMenu.tpproduc.value = tpproduc;
		document.formMenu.submit();
	}	

	
	

</script>
	
</head>


<body>
    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
	<div class="testata"></div>
	<div class=nompanta >Listado unidades <%=disponib %></div>	
		<div class="centradoFac" style="width:100%">
			<br>
			<br>
			<table width="100%" align="center">
				<tbody>
					<tr>
						<td width="15%" class="input-b1">Marca :</td>
						<td class="input-m">
							<select class="input-m2" id="txmarcax">
								<option value="" selected="selected">-- Todos --</option> 
								<% 
									for (int h = 0; h < gridMarc.rowCount(); h++){ 
										if(txmarcax !=null && txmarcax.equals(gridMarc.getStringCell(h, "txnombre"))){
										
										%>
										<option selected="selected" value="<%=gridMarc.getStringCell(h, "txnombre") %>"><%=gridMarc.getStringCell(h, "txnombre")%></option>
									<% }else{%>
										<option value="<%=gridMarc.getStringCell(h, "txnombre") %>"><%=gridMarc.getStringCell(h, "txnombre")%></option>
								
									<%}
									}
									%>
							</select>
					  </td>
					  <td width="15%" class="input-b1">Tipo :</td>
					  <td class="input-m">
					    <select class="input-m2" id="tpproduc">
								<option value="" selected="selected">-- Todos --</option> 
								<option value="PH"  <% if(tpproduc !=null && tpproduc.equals("PH")){%> selected="selected"<%} %>>-- Celular --</option>
								<option value="EL"   <% if(tpproduc !=null && tpproduc.equals("EL")){%> selected="selected"<%} %>>-- Electrodomestico --</option> 
								<option value="LA" <% if(tpproduc !=null && tpproduc.equals("LA")){%> selected="selected"<%} %>>-- Laptop --</option> 
								<option value="PI" <% if(tpproduc !=null && tpproduc.equals("PI")){%> selected="selected"<%} %>>-- Pieza --</option> 
								<option value="TA" <% if(tpproduc !=null && tpproduc.equals("TA")){%> selected="selected"<%} %>>-- Tablet --</option> 
								
								
						</select>
						</td>	
					</tr>
				  </tbody>
				  <tr>
				  	<td colspan="4" align="center">&nbsp;</td>
				  </tr>
				  <tr>
				  	<td colspan="4" align="center"><a class="boton" onclick="filtraStock()">Filtrar</a></td>
				  </tr>
			 </table>
				<br>
		
			<div id="lineastmp">
				
				<%
				
				if ((gridProd != null) && (gridProd.rowCount() > 0)) { %> 
			
					<table width="100%" border="0" id="tabStock" class="TablaGrande" align="center">
			  	   		<tr class="cab">
					
								
								<%
								int itemxrow = 4;
								int cont = 0;
								String codprodu ="";
								String rutaimga ="";
								    for (int h = 0; h < gridProd.rowCount(); h++){ 
								    	
								    	cont++;
								    	codprodu = gridProd.getStringCell(h,"codprodu");
								    
								    	if(codprodu.substring(0,2).equals("PI")){
								    		rutaimga ="http://mallproshop.com/images/pieces/";
								    	}else{
								    		rutaimga ="http://mallproshop.com/";
								    	}
								    
								    	imagewpt = gridProd.getStringCell(h,"imagewpt");
								    
								    	if(imagewpt == null || imagewpt.equals("null") || imagewpt.equals("") ){
								    		rutaimga = "/JLet/common/img/varios/imagen-no-disponible.png";
								    		imagewpt = "";		    
								    	}
								    	
								    		%>
									<td align="center">&nbsp;</td>
									<td width="15%" align="center" class="tablaGrandeW">
										<table border="0">
											<tr>
												<td align="center" class="fonts6" width=25%><img height=180px; width=180px; src="<%=rutaimga%><%=imagewpt%>"></td>
											</tr>
											<tr>
												<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=gridProd.getStringCell(h,"txmarcax")%> <%=gridProd.getStringCell(h,"txmodelo")%></td>
											</tr>
											<tr>
												<td align="center" class="fonts6"><%=gridProd.getStringCell(h,"unidades")%> Unid.</td>
											</tr>
										</table>
									</td>	
									<td width="0.5%" align="center">&nbsp;</td>
									<% 	if (cont % itemxrow == 0){ %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
									<tr>
		
										<% 
									   }
								   }%>
								    </tr>
        						</td>
							</table>
						 <% } %>			
							
					
					
			</div>
		</div>
		<form method="POST" name="formMenu" action="/JLet/App">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services" 	value=""/>
					<input type="hidden" name="view" 		value=""/>
					<input type="hidden" name="idemisor" 	value=""/>
					<input type="hidden" name="cdestado" 	value=""/>
					<input type="hidden" name="txmarcax" 	value=""/>
					<input type="hidden" name="tpproduc" 	value=""/>
		</form>
	</div>
</body>