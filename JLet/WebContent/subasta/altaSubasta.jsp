<%@ include file="/common/jsp/include.jsp"%>


<%
	HttpSession sesion = request.getSession();
	String cdemisor = (String)sesion.getAttribute("cduserid");
	
	Grid gdPaises = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdPaises = io.getGrid("gdPaises");	
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/listEnvio.jsp "+ e.getMessage());	
		}
	}
	

%>


<head>
	
	<script>
	
		function compruebaSub(tipov){
			
			if(tipov =="V"){
				document.formsubas.finfecve.value = "";
				document.formsubas.finhorve.value = "";
				document.getElementById("subasta").style.display="none";
			
			}else{
				document.getElementById("subasta").style.display="block";
				
			}
		}
		
		function validarEnvio(){
			
			var cadena = "";
			
			if(document.formsubas.preciosa.value == "" || isNaN(document.formsubas.preciosa.value)){
				cadena+="Precio de salida no valido\n";
			}
			if(document.formsubas.preciomi.value == "" || isNaN(document.formsubas.preciomi.value)){
				cadena+="Precio de minimo no valido\n";
			}
			
			    /*  if(!ver_fecha(document.getElementById.finfecve.value) || !fechaCorrecta(document.formsubas.fechvent.value)){
			 		alert("Fechas incorrectas");
				  }
			      else{*/
			    	  
			    	  if (cadena == ""){
			    	   document.formsubas.submit();
			    	  }else{
			    		  alert(cadena);
			    	  }
				  //}	
		}
		
	</script>
	
</head>


<body>

	<div class="fondo2">
	    <div class="testata"><img src="/JLet/common/img/icons/title-alta-subasta.png"></div>
	    <div class="nompanta" >Alta  Subasta</div>
		<div class="centrado" style="width:90%">
			<form name="formsubas" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="SubastaHttpHandler"/>
				<input type="hidden" name="services"	value="AltaSubastaSrv.ListSubastaSrv"/>
				<input type="hidden" name="view" 		value="subasta/listSubasta.jsp"/>
				<input type="hidden" name="emisor" 		value="<%=cdemisor%>"/>
											
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">Web</td>
						<td align="center" class="input-b1">Pais</td>
						<td align="center" class="input-b1">Usuario</td>
						<td align="center" class="input-b1">Tipo Venta</td>
					</tr>
					<tr>
						<td align="center" class="input-m">&nbsp;</td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="txwebxxx" size="20"/></td>
						<td align="center" class="input-m">
							<select name="txpaisxx" style="width:120px;">
								<% for (int i = 0; i < gdPaises.rowCount(); i++){ %>
									<option value='<%=gdPaises.getStringCell(i,"isocodex")%>'><%=gdPaises.getStringCell(i,"txnombre")%></option>							
								<% } %>
							</select>
						</td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="txusuari" size="20"/></td>
						<td align="center" class="input-m">
							<select name="tipovent" onchange="compruebaSub(this.value)"  class="input-m" style="border:none">
								<option value="V">Venta</option>
								<option value="S">Subasta</option>
							</select>
						</td>
					
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">ID product Web</td>
						<td align="center" class="input-b1">Nombre</td>
						<td align="center" class="input-b1">Precio Salida</td>
						<td align="center" class="input-b1">Divisa</td>
					</tr>
					
					<tr>
						<td align="center" class="input-m">&nbsp;</td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="idproduc" style="width:90px;"/></td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="txnombre" style="width:200px;"/></td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="preciosa" style="width:90px;"/></td>
						<td align="center"class="input-m">
							<select name="divisaxx"  class="input-m" style="border:none">								
								<option value='EUR'>EUR - Euros</option>
								<option value='CHF'>CHF - Franco Suizo</option>
								<option value='GBP'>GBP - Libra</option>
								<option value='USD'>USD - Dolares</option>
								<option value='DOP'>DOP - Pesos Dominicanos</option>
							</select>
						</td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">Cod. Interno</td>
						<td align="center" class="input-b1">URL</td>
						<td align="center" class="input-b1">Precio mínimo</td>
						<td align="center" class="input-b1">Precio transporte</td>
					</tr>
					<tr>
						<td align="center" class="input-m">&nbsp;</td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="cdintern" style="width:90px;"/></td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="urlexter"/></td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="preciomi" style="width:90px;"/></td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="preciotr" style="width:90px;"/></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-b1">&nbsp;</td>
						<td align="center" class="input-b1">Fecha venta</td>
						<td align="center" class="input-b1">Hora inicio venta</td>
						<td align="center" colspan=2 class="input-b1">&nbsp;</td>
					</tr>
					
					<tr>
						<td align="center" class="input-m">&nbsp;</td>
						<td align="center" class="input-m"><input  class="input-m" style="border:none" type="text" name="fechvent" style="width:90px;"/></td>
						<td align="center" class="input-m">
							<select name="horavent"  class="input-m" style="border:none">
							<%
								String cadminuto = "";
								String cadhoraxx = "";
								for (int hora=0; hora < 24;hora++){
									
									cadhoraxx = String.valueOf(hora);
									if(cadhoraxx.length()< 2 ){
										cadhoraxx ="0"+cadhoraxx;
									}
									
									for( int minuto =0;minuto<60; minuto++){
										
										cadminuto = String.valueOf(minuto);
										if(cadminuto.length()< 2 ){
											cadminuto ="0"+cadminuto;
										}
										%>
										<option value ="<%=cadhoraxx %>:<%=cadminuto %>"><%=cadhoraxx %>:<%=cadminuto %></option>
										<% 
									}
								
								}  %>
							</select>
					
						<td align="center" colspan=2 class="fonts6">&nbsp;</td>
					</tr>
					
					<tr>
					<td colspan=5>
						<div id="subasta" style="display:none">
							<table width =53%>
								<tr class="fonts">
								<td align="center">&nbsp;</td>
								<td align="center" class="input-b1">Fecha venta fin</td>
								<td align="center">&nbsp;</td>
								<td align="center" class="input-b1">Hora venta fin</td>
								<td align="center" colspan=2>&nbsp;</td>
							  </tr>
						
							 <tr>
								<td align="center" width=5% class="input-m">&nbsp;</td>
								<td align="center" class="input-m"><input class="input-m" style="border:none" type="text" name="finfecve" style="width:90px;"/></td>
								<td align="center" width=13% class="input-m">&nbsp;</td>
								<td align="center" class="input-m">
								<select name="finhorve">
										<%
											for (int hora=0; hora < 24;hora++){
												
												cadhoraxx = String.valueOf(hora);
												if(cadhoraxx.length()< 2 ){
													cadhoraxx ="0"+cadhoraxx;
												}
												
												for( int minuto =0;minuto<60; minuto++){
													
													cadminuto = String.valueOf(minuto);
													if(cadminuto.length()< 2 ){
														cadminuto ="0"+cadminuto;
													}
													%>
													<option value ="<%=cadhoraxx %>:<%=cadminuto %>"><%=cadhoraxx %>:<%=cadminuto %></option>
													<% 
												}
											
										}  %>
									</select>
								</td>
							   <td align="center" colspan=2>&nbsp;</td>
							</tr>
						</table>	 
					</div>
				</td>
			 </tr>
			</table>
		</form>
			<table width="100%" align="center">
				<tr>
					<td align="center">
						<a class="boton" onClick="validarEnvio()">Alta Subasta</a>
					</td>
				</tr>
			</table>
					
		</div>
	</div>
</body>