<%@ include file="/common/jsp/include.jsp"%>

<%
	
	
	String idemisor = "";
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
	String iconoxxx = "";
	String filefact = "";
	String txpaisxx = "";
	String txcoment = "";
	String txdocume = "";
	
	
	
	
	Grid gdTienda   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdTienda = io.getGrid("gdTienda");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en mapastiendas/Al.jsp "+ e.getMessage());	
		}
	}


	
%>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
    
 

<head>

	<script>
	
	
	function abrirFactura(documento){
		 document.abriFactu.filename.value = documento; 
		 document.abriFactu.submit();
	}
	
	 function mostrarGoogleMaps()
     
     {
     	var myOptions = {
     	 		center: new google.maps.LatLng(40.4302993, -3.6926496),
     	 		zoom: 6,
			   	mapTypeId: google.maps.MapTypeId.ROADMAP
		}; 
		var map = new google.maps.Map(document.getElementById("mostrarMapa"),myOptions);
		<%
		if(idemisor.equals("1")){
			iconoxxx = "/JLet/common/img/icons/emisores/iconizumba.png";

		}else{
			iconoxxx = "/JLet/common/img/icons/emisores/mini_logo"+idemisor +".png";
		}
		%>
		
			  
			  <%
			   
			  for (int i = 0; i < gdTienda.rowCount(); i++) { 
				    txnombre = gdTienda.getStringCell(i,"txnombre");
					txciudad = gdTienda.getStringCell(i,"txciudad");
					txdirecc = gdTienda.getStringCell(i,"txdirecc");
					telefono = gdTienda.getStringCell(i,"telefono");
					longitud = gdTienda.getStringCell(i,"longitud");
					latitudx = gdTienda.getStringCell(i,"latitudx");	
					
					txpaisxx = gdTienda.getStringCell(i,"txpaisxx");
					txcoment = gdTienda.getStringCell(i,"txcoment");
					txdocume = gdTienda.getStringCell(i,"txdocume");
		
					if(!longitud.equals("")){
			  %>
			  
			  	var icon_gim = "<%=iconoxxx%>";
				var pos<%=i%> = new google.maps.LatLng(<%=latitudx%>,<%=longitud%>);
				var marker<%=i%> = new google.maps.Marker({
				    position: pos<%=i%>,
				    map: map,
				    ciudad:"<%=txciudad%>",
				    title:"<%=txnombre%>",
				    calle:" <%=txdirecc%>",
				    telefono:"<%=telefono%>",
				    comentario:"<%=txcoment%>",
				    pais:"<%=txpaisxx%>",
				    documento : "<%=txdocume%>",
				    icon: icon_gim
					});
				
				google.maps.event.addListener(marker<%=i%>, 'click', procesaClick);	

			  <% 
				}
			  }%>
     }  
			  
			 
			
   
    function procesaClick() {
    	cadena = "Marcador: " + this.title +"<br> Ciudad: "+ this.ciudad + "<br> Calle: " + this.calle + "<br>telefono: " + this.telefono+ "<br>Comentario: " + this.comentario+ "<br>Pais: " + this.pais+"<div style='cursor:pointer' onclick=abrirFactura('"+this.documento+"')><img width='30px;' height='30px' src='/JLet/cloud/img/pdf.png'> <b>Ver factura</b></div>";
    	alertify.alert(cadena, function () {});
}
 </script>
	
	
</head>


		<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			
			<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value=""/><!-- Cambiar -->
		</form>


<body onload="mostrarGoogleMaps()">

	<div class="fondo1">
    
		<div class="centrado4" style="width:95%; margin-left:8%; height:950px;">
			

			
			<table width="90%" align="center">
			<tr>
				<td><div>Mapa de Tiendas</div></td>
			</tr>
				<tr>
					<td> <div id="mostrarMapa" style="width: 90%; height: 650px;"> </div></td>
				</tr>
			</table>
					
		</div>
	</div>
</body>