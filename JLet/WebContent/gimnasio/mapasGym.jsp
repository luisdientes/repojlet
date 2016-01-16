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
	
	
	
	Grid gdGimnas   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdGimnas = io.getGrid("gdGimnas");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}


	
%>
<script type="text/javascript" src="https://maps.google.com/maps/api/js?sensor=false"></script>
    
 

<head>

	<script>
	
	 function mostrarGoogleMaps()
     
     {
     	var myOptions = {
     	 		center: new google.maps.LatLng(40.196950846096186, -4.572601612890594),
     	 		zoom: 6,
			   	mapTypeId: google.maps.MapTypeId.ROADMAP
		}; 
		var map = new google.maps.Map(document.getElementById("mostrarMapa"),myOptions);
		var icon_gim = '/JLet/common/img/icons/weights.png';
			  
			  <%
			   for (int i = 0; i < gdGimnas.rowCount(); i++) { 
				    txnombre = gdGimnas.getStringCell(i,"txnombre");
					txciudad = gdGimnas.getStringCell(i,"txciudad");
					txdirecc = gdGimnas.getStringCell(i,"txdirecc");
					telefono = gdGimnas.getStringCell(i,"telefono");
					longitud = gdGimnas.getStringCell(i,"longitud");
					latitudx = gdGimnas.getStringCell(i,"latitudx");			  
		
					if(!longitud.equals("")){
			  %>
				var pos<%=i%> = new google.maps.LatLng(<%=latitudx%>,<%=longitud%>);
				var marker<%=i%> = new google.maps.Marker({
				    position: pos<%=i%>,
				    map: map,
				    ciudad:"<%=txciudad%>",
				     title:"<%=txnombre%>",
				    calle:" <%=txdirecc%>",
				    telefono:"<%=telefono%>",
				    icon: icon_gim
					});
				
				google.maps.event.addListener(marker<%=i%>, 'click', procesaClick);	

			  <% 
				}
			  }%>
     }  
			  
			 
			
   
    function procesaClick() {
		alert("Marcador: " + this.title + ", Ciudad: " + this.ciudad+ ", calle: " + this.calle+ ", telefono: " + this.telefono);
}
 </script>
	
	
</head>


<body onload="mostrarGoogleMaps()">

	<div class="fondo1">
    <div class="testata"><img src="/JLet/common/img/icons/title-mapgim.png"></div>
		<div class="centrado4" style="width:85%; margin-left:8%; height:950px;">
			

			
			<table width="50%" align="center">
				<tr>
					<td> <div id="mostrarMapa" style="width: 650px; height: 650px;"> </div></td>
				</tr>
			</table>
					
		</div>
	</div>
</body>