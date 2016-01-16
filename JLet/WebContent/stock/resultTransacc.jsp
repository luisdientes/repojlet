<%@ include file="/common/jsp/include.jsp"%>

<%
	String txresult = "";
	String idemisor = "";
	String tipotran = "";
	String tpclient = "";
	String txrazons = "";
	String tximagen = "";
	
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
	String filecrea = "";

	Grid gridLine = null;

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tipotran = io.getStringValue("tptransa");
			txrazons = io.getStringValue("txrazons");
			txresult = io.getStringValue("txmensaj");
			filecrea = io.getStringValue("filecrea");
			gridLine = io.getGrid("gridLine"); 
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en resultado.jsp");	
		}
	}
	
	tximagen = "ok3.png";
	
	
	if(tipotran.equals("D")){
		
		tipotran = "Depósito";
	}else{
		tipotran = "Factura";
	}

	%>

<script>
	
	function irPantalla(){
		
		document.formresu.services.value = "ListStockSrv";
		document.formresu.view.value 	 = "stock/listadoStock.jsp";
		document.formresu.idemisor.value = "<%=idemisor%>";
		document.formresu.cdestado.value = "STOCK";
		document.formresu.submit();
		
	}
		
</script>

<body>
	<form name="formresu" action="/JLet/App" method="POST">
		<input type="hidden" name="controller" 		value="StockHttpHandler"/>
		<input type="hidden" name="services"		value="ListStockSrv"/>
		<input type="hidden" name="view" 			value="stock/listadoStock.jsp"/>
		<input type="hidden" name="idemisor" 		value="<%=idemisor%>"/>
		<input type="hidden" name="cdestado" 		value=""/>
	</form>
	
	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
	</form>	
		
		<div class="testata">
			<img src="/JLet/common/img/icons/<%=tximagen%>"/>
		</div>
		<br>
			<h2 class="txt1" style="margin-top:35px !important;color:#363636">Se ha realizado un <%=tipotran %> a <%=txrazons %></h2>
		<br>
		<br> 	
		
		<table width="95%" border="0" id="tabStock" class="TablaGrande" align="center">
			  	   		<tr class="cab">
							<td align="center"  width="4%"><div class="cabecera input-b1">&nbsp;</div></td>
							<td align="center" width="20%"><div class="cabecera input-b1">Codigo</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Marca</div></td>
							<td align="center" width="20%"><div class="cabecera input-b1">Modelo</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Color</div></td>
							<td align="center" width="10%"><div class="cabecera input-b1">Precio</div></td>
							<td align="center" width="5%" ><div class="cabecera input-b1">Caja</div></td>
							<td align="center" width="5%" ><div class="cabecera input-b1">USB</div></td>
							<td align="center" width="8%" ><div class="cabecera input-b1">Clase</div></td>
							<td align="center" width="2%" ><div class="cabecera input-b1">&nbsp;</div></td>
						</tr>
						<%	
						System.out.println("HAY --------------- "+gridLine.rowCount());
						
						for (int i = 0; i < gridLine.rowCount(); i++){
									try {
										
						  					imeicode = gridLine.getStringCell(i,"imeicode");
						  					txmarcax = gridLine.getStringCell(i,"txmarcax");
						  					txmodelo = gridLine.getStringCell(i,"txmodelo");
						  					idcolorx = gridLine.getStringCell(i,"idcolorx");	  					
						  					pricevnt = gridLine.getStringCell(i,"pricevnt");	  					
						  					divisvnt = gridLine.getStringCell(i,"divisvnt");	  					
						  					prusdvnt = gridLine.getStringCell(i,"prusdvnt");
						  					withboxx = gridLine.getStringCell(i,"withboxx");
						  					withusbx = gridLine.getStringCell(i,"withusbx");
						  					idcatego = gridLine.getStringCell(i,"idcatego");
						  					
						  					String imageche = "";
						
						  					if (pricevnt.equals("")) {
						  						pricevnt = "0";
						  					}
						  					
						  					if (withboxx.equals("N")) {
						  						withboxx = "-";
						  					}else{
						  						withboxx="<img src ='/JLet/common/img/varios/checked3.png' style='width:32px'";
						  					}
						  					
						  					if (withusbx.equals("N")) {
						  						withusbx = "-";
						  					}else{
						  						withusbx="<img src ='/JLet/common/img/varios/checked3.png' style='width:32px'";
						  					}
						  					
						  					
						  					
						  					if(idcolorx.equals("AM")){
						  						idcolorx  ="Amarillo";
						  					}else if(idcolorx.equals("AZ")){
						  						idcolorx  ="Azul";
						  					}else if(idcolorx.equals("BL")){
						  						idcolorx  ="Blanco";
						  					}else if(idcolorx.equals("GR")){
						  						idcolorx  ="Granate";
						  					}else if(idcolorx.equals("GS")){
						  						idcolorx  ="Gris";
						  					}else if(idcolorx.equals("MA")){
						  						idcolorx  ="Marron";
						  					}else if(idcolorx.equals("NA")){
						  						idcolorx  ="Naranja";
						  					}else if(idcolorx.equals("NE")){
						  						idcolorx  ="Negro";
						  					}else if(idcolorx.equals("RO")){
						  						idcolorx  ="Rojo";
						  					}else if(idcolorx.equals("Ve")){
						  						idcolorx  ="Verde";
						  					}else if(idcolorx.equals("")){
						  						idcolorx ="-";
						  					}
						  					
						  					
						  					if(idcatego.equals("N")){
						  						idcatego  ="Nuevo";
						  					}else if(idcatego.equals("N7")){
						  						idcatego  ="Nuevo 7 dias";
						  					}else if(idcatego.equals("R")){
						  						idcatego  ="Refurbished";
						  					}else if(idcatego.equals("A")){
						  						idcatego  ="Usado Clase A";
						  					}else if(idcatego.equals("B")){
						  						idcatego  ="Usado Clase B";
						  					}else if(idcatego.equals("C")){
						  						idcatego  ="Usado Clase C";
						  					}
						  				
						  					//CAST A DOUBLE PARA DAR FORMATO
						  					double dpricevnt = 0;
						  					
						  					try {
						  						dpricevnt = Double.parseDouble(pricevnt);
						  					} catch (Exception e) {
						  						System.err.println(this.getClass().getName() +" ERROR recuperando Price - "+ gridLine.getStringCell(i,"pricevnt") +". (e) -> "+ e.getMessage());
						  					}
						  					
						  					pricevnt = format2d.format(dpricevnt);
						  					
												%>  	
											  		<tr>
											  			<td class="fonts6" style="background-color:ccc;"><input type="checkbox" id="<%=imeicode %>" value="<%=imeicode%>"> </div></td>
													  	<td class="fonts6" style="background-color:ccc;"><%=imeicode%></td>
														<td class="fonts6" style="background-color:ccc;"><%=txmarcax%></td>
														<td class="fonts6" style="background-color:ccc;"><%=txmodelo%></td>
														<td class="fonts6" style="background-color:ccc;"><%=idcolorx%></td>
													<%	if (pricevnt.equals("0,00")) { %>
															<td class="fonts6" style="background-color:ccc;"><div style="color:#FB0000"><b><%=pricevnt%> <%=divisvnt%></b></div></td>
													<%	} else { %>
															<td class="fonts6" style="background-color:ccc;"><%=pricevnt%> <%=divisvnt%></div></td>
													<%	} %>
														<td class="fonts6" style="background-color:ccc;"><div><%=withboxx%></td>
														<td class="fonts6" style="background-color:ccc;"><div><%=withusbx%></td>
														<td class="fonts6" style="background-color:ccc;"><div><%=idcatego%></td>
														<td class="fonts6" style="background-color:ccc;" onclick="altaCostes('<%=imeicode%>');">C</td>
													</tr>
										<% 
										} catch (Exception e) { 
													e.printStackTrace();
													System.out.println(this.getClass().getName() +" -- ERROR -- recuperando linea "+ i); %>
													<tr>
														<td align="center" colspan="10" style="color:#FF0000">-- ERROR --</td>
													</tr>
											<%	
										 }	
										}%>
	
		<table width="100%" align="center">
			<tr>
			 	<td align="center">
					<a class="boton" onclick="document.abriFactu.submit()">Ver factura</a>
				</td>
			</tr>
		</table>
		
	</form>

</body>
