<%@ include file="/common/jsp/include.jsp"%>

<%
	
	String aniofact = null;
	String idemisor = "";
	String idtienda = "";
	String txnombre = "";
	String cdpostal = "";
	String nifcifxx = "";
	String txcatego = "";
	String txdirecc = "";
	String txciudad = "";
	String telefono = "";
	String txmailxx = "";
	String txrespon = "";
	String tfnomovi = "";
	String mailresp = "";
	String sexoxxxx = "";
	String txnomres = "";
	String longitud = "";
	String latitudx = "";
	String logoemis = "";
	String iconoxxx = "";
	
	
	
	Grid gdTienda   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdTienda = io.getGrid("gdTienda");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}
	
	try {
		idtienda = gdTienda.getStringCell(0,"idtienda"); 
		txnombre = gdTienda.getStringCell(0,"txnombre");
		cdpostal = gdTienda.getStringCell(0,"cdpostal");
		nifcifxx = gdTienda.getStringCell(0,"nifcifxx");
		txcatego = gdTienda.getStringCell(0,"txcatego");
		txciudad = gdTienda.getStringCell(0,"txciudad");
		txdirecc = gdTienda.getStringCell(0,"txdirecc");
		txciudad = gdTienda.getStringCell(0,"txciudad");
		telefono = gdTienda.getStringCell(0,"telefono");
		txmailxx = gdTienda.getStringCell(0,"txmailxx");
		txrespon = gdTienda.getStringCell(0,"txrespon");
		txnomres = gdTienda.getStringCell(0,"txnomres"); 
		sexoxxxx = gdTienda.getStringCell(0,"sexoxxxx");
		tfnomovi = gdTienda.getStringCell(0,"tfnomovi");
		mailresp = gdTienda.getStringCell(0,"mailresp");
		longitud = gdTienda.getStringCell(0,"longitud");
		latitudx = gdTienda.getStringCell(0,"latitudx");
	} catch (Exception e) {
		System.out.println(" ERROR  Recuperando Valores");
	}

	
%>

<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
    
    
 

<head>

	<script>
	
	function validarAlta(){
		
		var cadena = "";
		var mailres = document.formAltaTienda.mailresp.value ;
		var mailgim = document.formAltaTienda.txmailxx.value ;
		
		if(document.formAltaTienda.txnombre.value == ""){
			cadena+="Campo nombre social no válido<br>";
		}
		
		if(!validarEmail(mailres)){
			cadena+="Mail responsable no válido<br>";
		}
		
		if(!validarEmail(mailgim)){
			cadena+="Mail tienda no válido";
		}
	  
	  	if (cadena == ""){
	   		document.formAltaTienda.submit();
	  	}else{
	  		alertify.alert(cadena, function () {});
		  
	  	}
  	}
	
	 function mostrarGoogleMaps()
     
     {
		 
		<%if(latitudx.equals("") || latitudx.equals("0")){
			latitudx ="0";
			longitud="0";
		}
		%>
		
		
	     	var myOptions = {
	     	 center: new google.maps.LatLng(<%=latitudx%>, <%=longitud%>),
	     	 zoom: 5,
				   mapTypeId: google.maps.MapTypeId.ROADMAP
			}; 
			
	     	var map = new google.maps.Map(document.getElementById("mostrarMapa"), myOptions);
		    var pos1 = new google.maps.LatLng(<%=latitudx%>,<%=longitud%>);
				 	
				 	<%
					if(idemisor.equals("1")){
						iconoxxx = "/JLet/common/img/icons/emisores/iconizumba.png";
	
					}else{
						iconoxxx = "/JLet/common/img/icons/weights.png";
					}
					%>
					var icon_gim = "<%=iconoxxx%>";
					var marker = new google.maps.Marker({
				    position: pos1,
				    map: map,
				    ciudad:"<%=txciudad%>",
				    title:"<%=txnombre%>",
				    calle:" <%=txdirecc%>",
				    telefono:"<%=telefono%>",
				    icon: icon_gim
					});
	
				google.maps.event.addListener(marker, 'click', procesaClick);						
	      }


	    // ]]>
	    
    function procesaClick() {
    	cadena = "Marcador: " + this.title +"<br> Ciudad: "+ this.ciudad + "<br> Calle: " + this.calle + "<br>telefono: " + this.telefono;
    	alertify.alert(cadena, function () {});
}
 </script>
	
	
</head>


<body onload="mostrarGoogleMaps()">


	<div class="fondo2">
    <div class="nompanta" >Alta  Tiendas</div>
		<div class="centrado" style="width:85%;">
			<form name="formAltaTienda" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="TiendasHttpHandler"/>
				<input type="hidden" name="services"	value="AltaTiendasSrv"/>
				<input type="hidden" name="view" 		value="tiendas/listTiendas.jsp"/>
				<input type="hidden" name="idtienda" 		value="<%=idtienda%>"/>
				<input type="hidden" name="idemisor" 		value="<%=idemisor%>"/>
				
				<br>
				<br>
			
				<table align="center" width=70% class="tdRound">
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Nombre</b></td>
						<td><input type="text" name="txnombre" size="20" class="input-m" style="width:100%;" value="<%=txnombre %>"></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>NIF /CIF </b></td>
						<td><input type="text" name="nifcifxx" size="20" class="input-m" value="<%=nifcifxx %>" style="width:100%;"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Categoria</b></td>
						<td><input style="width:100%;"  type="text" name="txcatego" value="<%=txcatego %>" size="20" class="input-m" style="border:none"/></td>		
						<td width=25%>&nbsp;</td>
					</tr>
					
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Ciudad</b></td>
						<td><input style="width:100%"  type="text" name="txciudad" value="<%=txciudad %>" size="20" class="input-m" style="border:none"/></td>		
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Direcci&oacute;n</b></td>
						<td><input style="width:100%;" type="text" name="txdirecc" value="<%=txdirecc %>" size="20" class="input-m" style="border:none"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Cod. Postal</b></td>
						<td><input style="width:100%;"  type="text" name="cdpostal" value="<%=cdpostal %>" size="20" class="input-m" style="border:none"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr >
						<td width=25%>&nbsp;</td>
						<td><b>Teléfono</b></td>
						<td><input style="width:100%" type="text" maxlength=20 name="telefono" value="<%=telefono %>" size="20" class="input-m" style="border:none"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr >
						<td width=25%>&nbsp;</td>
						<td><b>E-MAIL: </b></td>
						<td><input style="width:100%;"  type="text" name="txmailxx" value="<%=txmailxx %>" size="20" class="input-m" style="border:none"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr >
						<td width=25%>&nbsp;</td>
						<td><b>Persona responsable: </b></td>
						<td><input style="width:100%;"  type="text" name="txrespon" value="<%=txrespon %>" size="20" class="input-m" style="border:none"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr >
						<td width=25%>&nbsp;</td>
						<td><b>Nombre responsable: </b></td>
						<td><input style="width:100%;"  type="text" name="txnomres" value="<%=txnomres %>" size="20" class="input-m"/></td>
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Sexo: </b></td>
						<td>
						<input type=radio name="sexoxxxx" value="H" size="20" class="input-m" checked/> Hombre
						 &nbsp;&nbsp;<input type=radio  name="sexoxxxx" value="M" size="20" class="input-m"/> Mujer
					</td>
					<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Movil responsable: </b></td>
						<td><input style="width:100%;" type="text" name="tfnomovi" value="<%=tfnomovi %>" maxlength=20  size="20" class="input-m" style="boredr:none"/></td>		
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Mail responsable: </b></td>
						<td><input style="width:100%;" type="text" name="mailresp" value="<%=mailresp %>"  size="20" class="input-m" style="boredr:none"/></td>		
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Longitud: </b></td>
						<td><input style="width:100%;" type="text" name="longitud" value="<%=longitud %>"  size="20" class="input-m" style="boredr:none"/></td>		
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td width=25%>&nbsp;</td>
						<td><b>Latitud: </b></td>
						<td><input style="width:100%;" type="text" name="latitudx" value="<%=latitudx %>"  size="20" class="input-m" style="boredr:none"/></td>		
						<td width=25%>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
				</table>
	
			</form>
			<%if(!idtienda.equals("") && !latitudx.equals("0")){ %>
				<table width="50%" align="center">
					<tr>
						<td> <div id="mostrarMapa" style="width: 550px; height: 350px;"> </div></td>
					</tr>
				</table>
			<%} %>
			
			<table width="100%" align="center">
				<tr>
					<td align="center">
					<%if(idtienda.equals("")){ %>
						<a class="boton" onClick="validarAlta()">Alta Tienda</a>
						<%}else{%>
						<a class="boton" onClick="validarAlta()">Actualizar</a>
						<% } %>
					</td>
				</tr>
			</table>
					
		</div>
	</div>
</body>