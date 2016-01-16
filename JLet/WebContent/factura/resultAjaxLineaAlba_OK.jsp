<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%

	String txmensaj = "";
	String idfactur = "";
	Grid gridFact = null;
			
	String idlineax = "";
	String imgfilex = "";
	String codprodu = "";
	double cantidad = 0;
	String concepto = "";
	String codigopr = "";
	String idunicox = "";
	double precioun = 0;
	double descuent = 0;
	double precioto = 0;
	double baseimp  = 0;
	double impuesto = 0;
	double total    = 0;
	String porcenta = "";
	String idclient ="";
	String cdfactur ="";
	String idemisor ="";
	double iva = 0;

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idfactur = io.getStringValue("idfactur");
			txmensaj = io.getStringValue("txmensaj");
			gridFact = io.getGrid("gridLine");
			porcenta = io.getStringValue("porcenta");
			iva 	 = Float.parseFloat(porcenta);
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ajaxok factura/index.jsp");	
		}
		
		try {
			cdfactur	= io.getStringValue("cdfactur");
		}catch(Exception ex){
			System.err.println("ERROR - Recibiendo cdfactur");	
		}
		
		try {
			idclient	= io.getStringValue("idclient");
		}catch(Exception ex){
			System.err.println("ERROR - Recibiendo idclient");	
		}
		
		try {
			idemisor	= io.getStringValue("idemisor");
		}catch(Exception ex){
			System.err.println("ERROR - Recibiendo idemisor");	
		}
		
	}
	
	
		
	
	
	
%>
	
	<script>
		var base=0;
		var tmpfactu = "<%=idfactur%>";
		
		windows.parent.asignaTmpFactur(tmpfactu);
		
		<% if ((gridFact != null) && (gridFact.rowCount() > 0)) { %>
  	  		document.getElementById("vistapre").style.display = "block";
  	  	<%} %>
		
	</script>
		<div id="lineastmp">
		  
  		<table width="100%" border=0 >
  	   		<tr align="center">
  	   			<td width="5%" class="input-b">Imagen</td>
  	     		<td width="15%" class="input-b">C&oacute;digo R.</td>
		  	     <td width="5%" class="input-b">Cant</td>
		  	     <td width="25%" class="input-b">Concepto</td>
		  	     <td width="20%" class="input-b">Precio /U</td>
		  	     <td width="10%" class="input-b">%Dto</td>
		  	     <td width="20%" class="input-b">Total</td>
				
  	  		</tr>
  	  		<tr>
  	  			<td colspan="7"><hr></td>	
  	  		</tr>
  		
  		<%
  			try {
	  			for (int j = 0; j < gridFact.rowCount(); j++){
	  		 	  	
	  				try{
	  					imgfilex 	= gridFact.getStringCell(j,"imagedet");
	  				}catch(Exception ex){
	  					System.out.println("No tiene Imagen");
	  					imgfilex = "";
	  				}
	  				idlineax 	= gridFact.getStringCell(j,"idtmpreg");
	  				codprodu 	= gridFact.getStringCell(j,"codprodu");
	  				cantidad 	= Double.parseDouble(gridFact.getStringCell(j,"cantidad"));
	  				concepto    = gridFact.getStringCell(j,"concepto");
	  				precioun	= Double.parseDouble(gridFact.getStringCell(j,"precioun"));
	  				descuent	= Double.parseDouble(gridFact.getStringCell(j,"descuent"));
	  		 	 	precioto	= Double.parseDouble(gridFact.getStringCell(j,"precioto"));
	  		 	 	baseimp += precioto;	
	  		 	 	%>  	
				 <tr>
				  	<td width="5%">
				  		<div>
		  					<%	if(!imgfilex.equals("")) {%>
		  							<img style="width:80;height:80" src="http://www.mallproshop.com<%=imgfilex%>">
		  					<%}else{
	  							%>
	  							&nbsp;
	  						<% 
	  						  }	
	  					     %>
				  		</div>
				  	</td>
			  		<td width="15%" class="input-m"><%=codprodu%></td>
			  	    <td width="5%"  class="input-m"><div id="C<%=j %>"><%=formateador.format(cantidad)%></div></td>
			  	    <td width="20%" class="input-m"><%=concepto%></td>
			  	    <td width="20%" class="input-m" ><input type="text" class="input-m" id="P<%=j %>"  value="<%=formateador.format(precioun)%>"></td>
			  	    <td width="10%" class="input-m"><input class="input-m" type="text" id="D<%=j %>" onblur="obtieneDatos('<%=j%>','<%=idlineax%>','<%=idclient%>','<%=cdfactur%>','<%=idemisor %>')" value="<%=formateador.format(descuent)%>"></td>
			  	    <td width="20%" class="input-m"><%=formateador.format(precioto)%></td>
			  	    <td width="5%"><input type="checkbox" value="<%=idlineax%>" name="idlineas[]"></td>
				</tr>
				<%	} 
				}catch (Exception e) { 
					e.printStackTrace();
					System.out.println("ERROR recuperando linea factura");
				}
			
		%>					  					
		</table>  	
		<%
		impuesto =  baseimp *(iva);
	    total=baseimp + impuesto;
	    impuesto = Math.rint(impuesto*100)/100;
	    baseimp  = Math.rint(baseimp*100)/100;
	    total    = Math.rint(total*100)/100;
		
		 %>	
		 	<br><br>
		
		<table align="right" class="piefactu">
			<tr>
				<td class="input-txt">Base imponible</td>
			  	<td class="input-m"><div id="baseimp"><%=formateador.format(baseimp)%></td>
			</tr>
			<tr>
				<td class="input-txt">Impuesto</td>
			  	<td class="input-m" ><div id="impuesto"><%=formateador.format(impuesto)%></div></td>
			</tr>
			<tr>
				<td class="input-txt">Total</td>
			  	<td class="input-m"><div id="total"><%=formateador.format(total)%></div></td>
			</tr>		
		</table>	
  	</div>	
  	