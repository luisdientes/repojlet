<%@ include file="/common/jsp/include.jsp"%>

<%
	
	String aniofact = null;

	String idclient = "";
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
	
	
	
	Grid gdGimnas   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdGimnas = io.getGrid("gdGimnas");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}
	
	try {
		idclient = gdGimnas.getStringCell(0,"idclient"); 
		txnombre = gdGimnas.getStringCell(0,"txnombre");
		cdpostal = gdGimnas.getStringCell(0,"cdpostal");
		nifcifxx = gdGimnas.getStringCell(0,"nifcifxx");
		txcatego = gdGimnas.getStringCell(0,"txcatego");
		txciudad = gdGimnas.getStringCell(0,"txciudad");
		txdirecc = gdGimnas.getStringCell(0,"txdirecc");
		txciudad = gdGimnas.getStringCell(0,"txciudad");
		telefono = gdGimnas.getStringCell(0,"telefono");
		txmailxx = gdGimnas.getStringCell(0,"txmailxx");
		txrespon = gdGimnas.getStringCell(0,"txrespon");
		txnomres = gdGimnas.getStringCell(0,"txnomres"); 
		sexoxxxx = gdGimnas.getStringCell(0,"sexoxxxx");
		tfnomovi = gdGimnas.getStringCell(0,"tfnomovi");
		mailresp = gdGimnas.getStringCell(0,"mailresp");
		longitud = gdGimnas.getStringCell(0,"longitud");
		latitudx = gdGimnas.getStringCell(0,"latitudx");
	} catch (Exception e) {
		System.out.println(" ERROR  Recuperando Valores");
	}

	
%>
<script type="text/javascript" src="gimnasio/js/altaGym.js"/></script>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
    
 

<head>

	<script>
	
	 function mostrarGoogleMaps()
     
     {
     	var myOptions = {
     	 center: new google.maps.LatLng(<%=latitudx%>, <%=longitud%>),
     	 zoom: 5,
			   mapTypeId: google.maps.MapTypeId.ROADMAP
			  }; 
			  var map = new google.maps.Map(document.getElementById("mostrarMapa"),
			    myOptions);
			  
			 	var pos1 = new google.maps.LatLng(<%=latitudx%>,<%=longitud%>);
			 /*	var pos2 = new google.maps.LatLng(40.196950846096186, -4.572601612890594 );
			 	var pos3 = new google.maps.LatLng(40.61002367403743, -4.757995899999969);
			 	var pos4 = new google.maps.LatLng(42.143277235022566, -5.636902149999969);
			 	var pos5 = new google.maps.LatLng(37.7839913062396, -7.196960743749969);
			 	var pos6 = new google.maps.LatLng(36.310954908227856,  -5.395202931249969);
			 	var pos7 = new google.maps.LatLng(38.90704452946005, 1.4206120406738592);*/
			 	
			 	var icon_gim = '/JLet/common/img/icons/weights.png';
				var marker = new google.maps.Marker({
			    position: pos1,
			    map: map,
			     title:"<%=txnombre%>",
			    calle:" <%=txdirecc%>",
			    telefono:"<%=telefono%>",
			    icon: icon_gim
				});
				/*var marker2 = new google.maps.Marker({
			    position: pos2,
			    map: map,
			      title:"Hola, Prueba 2!",
			    calle:" nombre calle 2",
			    telefono:"688521039",
			    icon: icon_gim
				});
				var marker3 = new google.maps.Marker({
			    position: pos3,
			    map: map,
			      title:"Hola, Prueba 3!",
			    calle:" nombre calle 3",
			    telefono:"686985210",
			    icon: icon_gim
				});
				var marker4 = new google.maps.Marker({
			    position: pos4,
			    map: map,
			    title:"Hola, Prueba 44!",
			    calle:" nombre calle 4",
			    telefono:"925799235",
			    icon: icon_gim
				});
				var marker5 = new google.maps.Marker({
			    position: pos5,
			    map: map,
			      title:"Hola, Prueba 5!",
			    calle:" nombre calle 5",
			    telefono:"653267510",
			    icon: icon_gim
				});
					var marker6 = new google.maps.Marker({
			    position: pos6,
			    map: map,
			    title:"Hola, Prueba!",
			    calle:" nombre calle 6",
			    telefono:"644435100",
			    icon: icon_gim
				});
				var marker7 = new google.maps.Marker({
			    position: pos7,
			    map: map,
			    title:"Hola, Prueba!",
			    calle:" nombre calle ibiza",
			    telefono:"656658186",
			    icon: icon_gim
				});*/

google.maps.event.addListener(marker, 'click', procesaClick);						
      }

    // ]]>
    
    function procesaClick() {
		alert("Marcador: " + this.title + ", calle: " + this.calle+ ", telefono: " + this.telefono);
}
 </script>
	
	
</head>


<body onload="mostrarGoogleMaps()">

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/title-alta-gym.png"></div>
    <div class="nompanta" >Alta  Gimnasio</div>
		<div class="centrado4" style="width:85%; margin-left:8%; height:950px;">
			<form name="formAltaCli" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="GimnasioHttpHandler"/>
				<input type="hidden" name="services"	value="AltaGimnasioSrv"/>
				<input type="hidden" name="view" 		value="gimnasio/altaEquipos.jsp"/>
				<input type="hidden" name="idclient" 		value="<%=idclient%>"/>
				
				
				<br>
				<br>
			
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Nombre</td>
						<td class="input-m"><input type="text" name="txnombre" size="20" class="input-m" style="width:100%;border:none" value="<%=txnombre %>"></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">NIF /CIF </td>
						<td class="input-m"><input type="text" name="nifcifxx" size="20" class="input-m" value="<%=nifcifxx %>" style="width:100%;border:none"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1">Categoria</td>
						<td class="input-m"><input style="width:100%;border:none"  type="text" name="txcatego" value="<%=txcatego %>" size="20" class="input-m" style="border:none"/></td>		
					</tr>
					
					<tr class="fonts">
						<td align="center" class="input-b1">Ciudad</td>
						<td class="input-m"><input style="width:100%;border:none"  type="text" name="txciudad" value="<%=txciudad %>" size="20" class="input-m" style="border:none"/></td>		
					</tr>
					<tr>
						<td align="center" class="input-b1">Direcci&oacute;n</td>
						<td class="input-m"><input style="width:100%;border:none" type="text" name="txdirecc" value="<%=txdirecc %>" size="20" class="input-m" style="border:none"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Cod. Postal</td>
						<td class="input-m" style="width:230px;"><input style="width:100%;border:none"  type="text" name="cdpostal" value="<%=cdpostal %>" size="20" class="input-m" style="border:none"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Teléfono</td>
						<td class="input-m" style="width:230px;"><input style="width:100%;border:none" type="text" maxlength=20 name="telefono" value="<%=telefono %>" size="20" class="input-m" style="border:none"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">E-MAIL: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%;border:none"  type="text" name="txmailxx" value="<%=txmailxx %>" size="20" class="input-m" style="border:none"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Persona responsable: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%;border:none"  type="text" name="txrespon" value="<%=txrespon %>" size="20" class="input-m" style="border:none"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Nombre responsable: </td>
						<td class="input-m" style="width:230px;"><input style="width:100%;border:none;border:none"  type="text" name=txnomres value="<%=txnomres %>" size="20" class="input-m"/></td>
					</tr>
						<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Sexo: </td>
						<td class="input-m" style="width:230px">
						<input type=radio name="sexoxxxx" value="H" size="20" class="input-m" checked/> Hombre
						 &nbsp;&nbsp;<input type=radio  name="sexoxxxx" value="M" size="20" class="input-m"/> Mujer
					</td>
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Movil responsable: </td>
						<td class="input-m"><input style="width:100%;border:none" type="text" name="tfnomovi" value="<%=tfnomovi %>" maxlength=20  size="20" class="input-m" style="boredr:none"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Mail responsable: </td>
						<td class="input-m" ><input style="width:100%;border:none" type="text" name="mailresp" value="<%=mailresp %>"  size="20" class="input-m" style="boredr:none"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Longitud: </td>
						<td class="input-m" ><input style="width:100%;border:none" type="text" name="longitud" value="<%=longitud %>"  size="20" class="input-m" style="boredr:none"/></td>		
					</tr>
					<tr class="fonts">
						<td align="center" class="input-b1" style="width:110px;">Latitud: </td>
						<td class="input-m" ><input style="width:100%;border:none" type="text" name="latitudx" value="<%=latitudx %>"  size="20" class="input-m" style="boredr:none"/></td>		
					</tr>
					<tr>
						<td colspan="5">&nbsp;</td>						
					</tr>
				</table>
	
			</form>
			
			<table width="50%" align="center">
				<tr>
					<td> <div id="mostrarMapa" style="width: 350px; height: 350px;"> </div></td>
				</tr>
			</table>
			
			<table width="100%" align="center">
				<tr>
					<td align="center">
					<%if(idclient.equals("")){ %>
						<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:18px;" onClick="validarAlta()">Alta Gimnasio</a>
						<%}else{%>
						<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:22px;" onClick="validarAlta()">Actualizar</a>
						<% } %>
					</td>
				</tr>
			</table>
					
		</div>
	</div>
</body>