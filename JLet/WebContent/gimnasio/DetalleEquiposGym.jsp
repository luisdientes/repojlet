<%@ include file="/common/jsp/include.jsp"%>

<%
	

	String maxgimna = "";
	String idlineax = "";
	String rzsocial = "";
	String idemisor = "";
	String tpclient = "";
	String cdintern = "";
	String idfiscal = "";
	String txdirecc = "";
	String txciudad = "";
	String cdpostal = "";
	String txmailxx = "";
	String tfnofijo = "";
	String tfnomovi = "";
	String tfnofaxx = "";
	String txwebxxx = "";
	
	String accionxx = "";


	
	Grid gdEquipo   = null;
	Grid gdMarcas   = null;
	Grid gdEquipa   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
		
			gdEquipa = io.getGrid("gdEquipa");
			
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
	}
	
	

System.out.println("ACCION       --------------  "+accionxx);
	
%>


<head>

	
</head>


<body>

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/title-alta-gym.png"></div>
    <div class="nompanta" >Equipación  Gimnasio</div>
		<div class="centrado4" style="width:85%; margin-left:8%; height:auto;">
			<table border="0" width="80%" align="center" border="1">
			
						<tr>
							<td><div class="input-b1">Familia </div></td>
							<td><div class="input-b1">Nombre</div></td>
							<td><div class="input-b1">Marca</div></td>
							<td><div class="input-b1">Modelo</div></td>
							<td><div class="input-b1">Gama</div></td>
							<td><div class="input-b1">Cantidad</div></td>
							<td><div class="input-b1">Año compra</div></td>
							<td><div class="input-b1">Estado</div></td>
						</tr>
				<%	
					String cdequipa ="";
					String familiax ="";
					String famiAuxi ="";
					String nomfamil ="";
					
					
					/* variables recogidas para comparar*/
					String cdfamili = "";
					String equipaxx = "";
					String marcaxxx = "";
					String modeloxx = "";
					String cantidxx = "";
					String anioreco = "";
					String tpgamaxx = "";
					String estadoxx = "";
				
					if(gdEquipa !=null && gdEquipa.rowCount()>0){
						
						for(int i = 0; i<gdEquipa.rowCount();i++){
							
							cdfamili = gdEquipa.getStringCell(i, "cdfamili");
							nomfamil = gdEquipa.getStringCell(i, "txnombre");
							marcaxxx = gdEquipa.getStringCell(i, "txmarcax");
							equipaxx = gdEquipa.getStringCell(i, "cdequipa"); 
							
							modeloxx = gdEquipa.getStringCell(i, "txmodelo");
							cantidxx = gdEquipa.getStringCell(i, "cantidad");
							anioreco = gdEquipa.getStringCell(i, "aniocomp");
							tpgamaxx = gdEquipa.getStringCell(i, "tipogama");
							estadoxx = gdEquipa.getStringCell(i, "cdestado");
						%>
								
								<tr>
									<td><div class="fonts6"><%=cdfamili %></div></td>
									<td><div class="fonts6"><%=nomfamil %></div></td>
									<td><div class="fonts6"><%=marcaxxx%></div></td>
									<td><div class="fonts6"><%=modeloxx%></div></td>
									<td><div class="fonts6">
										<%if(tpgamaxx.equals("B")){%> Baja   <%} %>
									    <%if(tpgamaxx.equals("M")){%> Media  <%} %>
									    <%if(tpgamaxx.equals("A")){%> Alta   <%} %>
										</div>
									</td>
									<td><div class="fonts6"><%=cantidxx%></div></td>
									<td><div class="fonts6"><%=anioreco%></div></td>
									<td><div class="fonts6">
										<%if(estadoxx.equals("MB")){%> Muy bien<%} %>
									    <%if(estadoxx.equals("B")){%>  Bien    <%} %>
									    <%if(estadoxx.equals("R")){%>  Regular <%} %>
									    <%if(estadoxx.equals("M")){%>  Mal     <%} %>
									    <%if(estadoxx.equals("MM")){%> Muy Mal <%} %>
									</div></td>
								</tr>
								<%	
					       }
						}
					%>
			</table>
	
		
					
		</div>
	</div>
</body>