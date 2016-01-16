<%@ include file="/common/jsp/include.jsp"%>
	
<html>


<head>
	<title>Alta productos</title>
</head>
<body class="fondo">

   <%
   	Grid gridMarc = null;
  	 Grid gridColo = null;
   	String idmarcax = "";
   	String txnombre = "";
   	
	String idcolorx = "";
   	String txcolorx = "";
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gridMarc = io.getGrid("gridMarc");
			gridColo = io.getGrid("gridColo");

		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta productos.jsp");	
		}
	}
   
%>

<script>
	function altaProd(){
		tpproduc = document.formproduc.txcatego.value;
		
		if(tpproduc == "PIEZA"){
			document.formproduc.tpproduc.value = "PI";
			document.formproduc.idmarcax.value =document.getElementById("marcapie").value; 
			
		}else{
			document.formproduc.tpproduc.value = "PR";
			document.formproduc.idmarcax.value =document.getElementById("marcapro").value; 
		}
		
		document.formproduc.submit();
		
	}
	
	
	function comprueba(tpproduc){
		
		if(tpproduc == "PIEZA"){
			document.getElementById("nopieza").style.display="none";
			document.getElementById("pieza").style.display="block";
			document.getElementById("marcapro").style.display="none";
			document.getElementById("txmarcapro").style.display="none";
		}else{
			document.getElementById("nopieza").style.display="block";
			document.getElementById("pieza").style.display="none";
			document.getElementById("marcapro").style.display="block";
			document.getElementById("txmarcapro").style.display="block";
			
		}
		
	}
</script>

<div class="fondo2">
	    <div class="testata"><img src="/JLet/common/img/icons/title-alta-subasta.png"></div>
	    <div class="nompanta" >Alta  Productos</div>
		<div class="centrado" style="width:85%">
			<form name="formproduc" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ProductosHttpHandler"/>			
				<input type="hidden" name="services" 	value="AltaProductosSrv"/>
				<input type="hidden" name="view" 		value="productos/resultado.jsp"/>
				<input type="hidden" name="tpproduc" />
				<input type="hidden" name="idmarcax" />
				
				
			
											
				<table border="0" width="100%" align="center" border="1">
					<tr>
					
						<td align="center" ><div id="txmarcapro" class="input-b1">Marca : </div></td>
						<td align="center">
							<select id="marcapro" class="input-m" style="width:100%" >
							<% 
								if(gridMarc !=null && gridMarc.rowCount()>0){
							
								 for (int j = 0; j < gridMarc.rowCount(); j++){ 
								 	idmarcax = gridMarc.getStringCell(j, "idmarcax");
								 	txnombre = gridMarc.getStringCell(j, "txnombre");
								 %>
								 <option value="<%=idmarcax%>"><%=txnombre%></option>
								 <%
								}
							} 
							 %>	
							 </select>
						</td>
						<td align="center" class="input-b1">Tipo Producto : </td>
						<td align="center">
							<select name="txcatego" onchange="comprueba(this.value)" class="input-m" style="width:100%">
								<option value="PHONE">Telefono</option>
								<option value="TABLET">Tablet</option>
								<option value="LAPTOP">Laptop</option>
								<option value="ELEC">Electrodoméstico</option>
								<option value="PIEZA">Pieza</option>	 	
						</td>

					</tr>
					</table>
					<div id="nopieza">
						<table border="0" width="100%" align="center" border="1">
							<tr>
								<td class="input-b1">Modelo:</td>
								<td><input type="text" name="txmodelo" class="input-m"/></td>
								<td>&nbsp;</td>
								<td class="input-b1">Importe compra:</td>
								<td><input type="text" name="impdefco" class="input-m"/></td>
								<td>&nbsp;</td>
								<td class="input-b1">Importe venta:</td>
								<td><input type="text" name="impdefve" class="input-m"/></td>
							</tr>
						</table>
					</div>
					<div id="pieza" style="display:none">
						<table border="0" width="70%" align="center" border="1">
							<tr>
								<td class="input-b1" style="width:25%">Descripción:</td>
								<td><input type="text" name="txdescri" class="input-m"/></td>
								<td>&nbsp;</td>
								<td class="input-b1" style="width:45%">Nombre Español:</td>
								<td><input type="text" name="nameespa" class="input-m"/></td>
								<td>&nbsp;</td>
								<td class="input-b1" style="width:45%">Modelos compatibles:</td>
								<td><input type="text" name="namephon" class="input-m"/></td>
							</tr>
							<tr>
								<td class="input-b1">Codigo pieza:</td>
								<td><input type="text" name="codepiez" class="input-m"/></td>
								<td>&nbsp;</td>
								<td class="input-b1">Precio venta:</td>
								<td><input type="text" name="preciopr" class="input-m"/></td>
								<td>&nbsp;</td>
								<td class="input-b1">Marca:</td>
								<td><select  id="marcapie" class="input-m" style="width:100%">
								<% 
									if(gridMarc !=null && gridMarc.rowCount()>0){
							
									 for (int j = 0; j < gridMarc.rowCount(); j++){ 
									 	txnombre = gridMarc.getStringCell(j, "txnombre");
									 %>
									 <option value="<%=txnombre%>" class="input-m"><%=txnombre%></option>
									 <%
									}
							} 
							 %>	
							 </select>
								</td>
								<td class="input-b1">Color:</td>
								<td><select name="cdcolorx" class="input-m">
								<% 
									if(gridColo !=null && gridColo.rowCount()>0){
							
								 for (int j = 0; j < gridColo.rowCount(); j++){ 
								 	idcolorx = gridColo.getStringCell(j, "idcolorx");
								 	txcolorx = gridColo.getStringCell(j, "txnombre");
								 %>
								 <option value="<%=idcolorx%>"><%=txcolorx%></option>
								 <%
								}
							} 
							 %>	
							 </select>
								</td>
							</tr>
						</table>
					<br>
					<br>
					</div>
						<table width="100%" align="center">
						<tr>
							<td align="center">
								<a class="boton" onClick="altaProd()">Alta Producto</a>
							</td>
						</tr>
					</table>
			</form>
		</div>
	</div>	
</body>
</html>

