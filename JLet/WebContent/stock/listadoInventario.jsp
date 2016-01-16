<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>
<script type="text/javascript" src="stock/js/altaStock.js"/></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");


	String idemisor = null;
	String cddivisa = null;
	Grid gridLine   = null; 

	String txmarsel = "";

	System.out.println("entraaaaaaaaaa");
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gridLine = io.getGrid("gridLine");	

		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Listado Recibos Facturas.jsp "+ e.getMessage());	
		}
	}

%>

<script>

function buscaInventario(nameinve){
	
	//if (chequeaSiExiste) {		
		document.formMenu.nameinve.value = nameinve;
		document.formMenu.submit();
	//}
	
}
</script>

	
	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Listado Inventarios</div>
	<div class="centradoFac" width="100%">
	<br>
	<br>
	<br>

<div id="lineastmp">

	<%

	if(gridLine!=null && gridLine.rowCount()>0){ %>
		<table align="center" class="tab-listado" width="100%" >
			<tr>
				<td width=15% align="center"><div align="center" class="input-b1">Num. Productos</div></td>
				<td width=15%><div class="input-b1">Nombre</div></td>
				<td width=10%><div class="input-b1">Fecha Inventario </div></td>
				<td width=10%><div class="input-b1">E. Actual </div></td>
				<td width=10%><div class="input-b1"> Tipo Inventario</div></td>
				
		   </tr>
			<% 
				String idinvent = null; 
				String nproduct = null; 
			
				String fhaltaxx = null; 
				String nameinve = null; 
				String tipoinve = null;
				String costpiez = null;
				String costlimp = null;
				String costsoft = null;
				String manoobra = null;
				String hardrese = null;
				String costtime = null;
				String claseant = null;
				String claseact = null;
				
			  for (int i = 0; i < gridLine.rowCount(); i++){ 
				  
				  
				  nproduct = gridLine.getStringCell(i,"nproduct");
				  fhaltaxx = gridLine.getStringCell(i,"fhaltaxx");
				  nameinve = gridLine.getStringCell(i,"nameinve");
				//  tipoinve = gridLine.getStringCell(i,"imeicode");
				 // costpiez = gridLine.getStringCell(i,"costpiez"); 
				
				/*  if(claseant.equals("N")){
					  claseant  ="Nuevo";
					}else if(claseant.equals("N7")){
						claseant  ="Nuevo 7 dias";
					}else if(claseant.equals("R")){
						claseant  ="Refurbished";
					}else if(claseant.equals("A")){
						claseant  ="Usado Clase A";
					}else if(claseant.equals("B")){
						claseant  ="Usado Clase B";
					}else if(claseant.equals("C")){
						claseant  ="Usado Clase C";
					}*/
				  
			if (i % 2 == 0) { %>
			  		<tr class="oddRow" style="border:1px solid">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid">
				<% } %>
				<td align="center" class="fonts6jej" style="font-size:12px;padding:5px;cursor:pointer" onclick="buscaInventario('<%=nameinve %>')" ><%= nproduct  %> </td>
				<td  align="center" class="fonts6jej" style="font-size:12px;cursor:pointer" onclick="buscaInventario('<%=nameinve %>')"><%=nameinve  %></td>
				<td align="center" class="fonts6jej"  style="font-size:12px;padding:5px;cursor:pointer" onclick="buscaInventario('<%=nameinve %>')"><%=fhaltaxx  %> </td>
				<td align="center" class="fonts6jej" style="font-size:12px;padding:5px;cursor:pointer" onclick="buscaInventario('<%=nameinve %>')" >- </td>
				
			</tr>
							
				<% 
		
			  } 
			  
			  %>
			  
			  <tr>
				<td colspan="8" align="center"><a class="boton" onclick="javascript:location.href ='/JLet/common/jsp/menuPrinc.jsp'">Aceptar</a></td>
			</tr>
						
			  
			  
			  
			</table>
			<%} %>
		</div>	
	</div>
	
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
		</form>
	</div>	

	
	

