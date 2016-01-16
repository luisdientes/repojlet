<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%

	String txmensaj = "";
	String idfactur = "";
	String idemisor = "";
	Grid gridLine = null;
			
	String idlineax = "";
	double cantidad = 0;
	String concepto = "";
	String codigopr = "";
	String idunicox = "";
	String cddivisa = "";
	double precioun = 0;
	double descuent = 0;
	double precioto = 0;
	double baseimp  = 0;
	double impuesto = 0;
	double impreten = 0;
	double total    = 0;
	double retencio = 0;
	String porcenta = "";
	String porcrete = "";
	double iva  = 0;
	double irpf = 0;
	

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			idfactur = io.getStringValue("idfactur");
			txmensaj = io.getStringValue("txmensaj");
			cddivisa = io.getStringValue("cddivisa");
			gridLine = io.getGrid("gridLine");
			porcenta = io.getStringValue("porcenta");
			porcrete = io.getStringValue("porcrete");
			iva 	 = Float.parseFloat(porcenta);
			irpf 	 = Float.parseFloat(porcrete);
		} catch (Exception e) {
			irpf = 0;
			System.err.println("ERROR - Recibiendo los valores en ajaxok factura/index.jsp");	
		}
	}
		
	
	
	
%>
	
	<script>
		var base=0;
		var tmpfactu = "<%=idfactur%>";
		
		windows.parent.asignaTmpFactur(tmpfactu);
		
		<% if ((gridLine != null) && (gridLine.rowCount() > 0)) { %>
  	  		document.getElementById("vistapre").style.display = "block";
  	  	<%} %>
		
	</script>
	<div id="lineastmp">
		  
  		<table width="100%" border=0 class="TablaGrande">
  	   		<tr>
  	   			<td width="2%">&nbsp;</td>
  	   			<td width="12%"><div class="cabecera input-b1">C&oacute;digo R.</div></td>
  	   			<td width="12%"><div class="cabecera input-b1">Ident. Único.</div></td>
		  	    <td width="5%" ><div class="cabecera input-b1">Cant</div></td>
		  	    <td width="29%"><div class="cabecera input-b1">Concepto</div></td>
		  	    <td width="8%" ><div class="cabecera input-b1">Precio /U</div></td>
		  	    <td width="10%"><div class="cabecera input-b1">%Dto</div></td>
		  	    <td width="20%"><div class="cabecera input-b1">Total</div></td>
  	  		</tr>
  	  		<tr>
  	  			<td colspan="7"><hr></td>	
  	  		</tr>
  	  		
  		
  		<%
  		
	  		if ((gridLine != null) && (gridLine.rowCount() > 0)) { 
	  			
	  			for (int i = 0; i < gridLine.rowCount(); i++){
	  				
	  				try {
		  				idlineax = gridLine.getStringCell(i,"idtmpreg");
		  				codigopr = gridLine.getStringCell(i,"codprodu");
		  				idunicox = gridLine.getStringCell(i,"idunicox");
		  				cantidad = Float.parseFloat(gridLine.getStringCell(i,"cantidad"));
		  				concepto = gridLine.getStringCell(i,"concepto");
		  				precioun = Float.parseFloat(gridLine.getStringCell(i,"precioun"));
		  				descuent = Float.parseFloat(gridLine.getStringCell(i,"descuent"));
		  				precioto = Float.parseFloat(gridLine.getStringCell(i,"precioto"));
		  				
		  				baseimp += precioto;
		  				
		%>  	
				  		<tr>
				  			<td width="2%">&nbsp;</td>
				  			<td width="11%" class="fonts6"><%=codigopr%></td>
					  		<td width="12%" class="fonts6"><%=idunicox%></td>
					  	    <td width="5%" class="fonts6"><%=formateador.format(cantidad)%></td>
					  	    <td width="29%" class="fonts6"><%=concepto%></td>
					  	    <td width="8%" class="fonts6" onclick="MuestraBorraLinea('<%=formateador.format(precioun)%>')"><%=formateador.format(precioun)%> <%=cddivisa %></td>
					  	    <td width="10%" class="fonts6"><%=formateador.format(descuent)%></td>
					  	    <td width="20%" class="fonts6"><%=formateador.format(precioto)%> <%=cddivisa %></td>
							<td width="5%"><a class="boton" onclick="borraLinea(<%=idlineax%>,<%=idemisor%>,'S')" style="font-size:9px;font-weight:900;font-family:Arial;min-width:20px;border-radius:100%;min-height:20px;background-color:#C13030 !important">X</a> </td>
						</tr>
				<%	} catch (Exception e) { 
						e.printStackTrace();
						System.out.println("ERROR recuperando linea "+ i); %>
						<tr>
							<td align="center" colspan="6" style="color:#FF0000">-- ERROR --</td>
						</tr>
				<%	}
				}
			}
		%>			
			<tr>
				<td onclick="listadoProd('<%=idemisor%>')"><a class="boton" style="font-size:9px;font-weight:900;font-family:Arial;min-width:25px;border-radius:100%;min-height:25px;">+</a></td>  	
		  		<td width="11%"><input class="input-m" type="text" style="width:100%" name="idcodigo" id="codigo" onChange="muestraGrupoPhone(this.value)"  onkeyup="if (event.keyCode == 13) muestraGrupoPhone(this.value)"></td>
		  	   	<td width="12%"><input class="input-m" type="text" style="width:100%" name="idunicox" id="idunicox" onchange="muestraPhone(this.value)" onkeyup="if (event.keyCode == 13) muestraPhone(this.value)"></td>
		  	    <td width="5%" ><input class="input-m" type="text" style="width:100%" name="cantidad" id="cantidad"  onkeyup="if (event.keyCode == 13) acceso(1,'S')"></td>
		  	    <td width="29%"><input class="input-m" type="text" style="width:100% ; height:auto;" name="concepto" id="concepto"  onkeyup="if (event.keyCode == 13) acceso(1,'S')"></td>
		  	    <td width="8%" ><input class="input-m" type="text" style="width:100%" name="precioun" id="precioun"  onkeyup="if (event.keyCode == 13) acceso(1,'S')"></td>
		  	    <td width="10%"><input class="input-m" type="text" style="width:100%" name="descuent" id="descuent" onblur="acceso(1,'S')"  onkeyup="if (event.keyCode == 13) acceso(1,'S')"></td>
		  	    <td width="20%"><input class="input-m" readonly type="text" style="width:100%" name="precioto" id="precioto"></td>
				
		  	    <input type="hidden" name="miliseg" id="M0M" value="<%=System.currentTimeMillis() %>">
			</tr>		  					
		</table>  	
		<%
		impuesto =  baseimp *(iva);
		impreten =  baseimp *(irpf);
	    total = baseimp + impuesto - impreten;
	    
	    impuesto = Math.rint(impuesto*100)/100;
	    baseimp  = Math.rint(baseimp*100)/100;
	    total    = Math.rint(total*100)/100;
		
		 %>	
		 	<div id="prodexist" style="display:none"><h3 style="color:#ff0000">No existen datos para el c&oacute;digo de producto introducido</h3></div>
			   	<br><br>
		
		<table align="right" class="piefactu-ok">
			<tr>
				<td class="input-b1"><span>Base imponible</span></td>
			  	<td class="fonts6"><input style="border:none;width:110px"  class="fonts6" type="text" name="baseimp" id="baseimp" disabled value="<%=formateador.format(baseimp)%>" ><%=cddivisa %></td>
			</tr>
			<tr>
				<td class="input-b1"><span>Impuesto</span></td>
			  	<td class="fonts6"><input style="border:none;width:110px"  class="fonts6" type="text" name="impuesto" id="impuesto" disabled value="<%=formateador.format(impuesto)%>"><%=cddivisa %></td>
			</tr>
			
			<tr id="muesrete" <%if(irpf > 0){ %> style="table-row"<%}else{%>style="display:none" <%} %>>
				<td class="input-b1">Retención IRPF</td>
				<td class="fonts6"><input style="border:none;width:110px"  type="text" name="retencio" id="retencio" disabled class="fonts6" value="<%=formateador.format(impreten)%>"><%=cddivisa %></td>
			</tr>
			<tr>
				<td class="input-b1"><span>Total</span></td>
			  	<td class="fonts6"><input style="border:none;width:110px"  class="fonts6" type="text" name="txtotal" ID="total" disabled value="<%=formateador.format(total)%>"><%=cddivisa %></td>
			</tr>		
		</table>	
  	</div>		
  	