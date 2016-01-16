<%@ include file="/common/jsp/include.jsp"%>
<%@ include file="/cloud/menuCloud.jsp"%>
<script type="text/javascript" src="cloud/js/funciones.js"/></script>
<script type="text/javascript" src="common/js/alertas/lib/alertify.js"/></script>
	<link rel="stylesheet" href="common/js/alertas/themes/alertify.core.css" />
		<link rel="stylesheet" href="common/js/alertas/themes/alertify.default.css" />

<%
  String idemisor = "";
  String tpclient = ""; 
  String folderin = "";
  String creadire = "";
  String rutaabso = "";
  String cduserid = "";
  String tppermis = "";
  String usuariox = "";
  String txmensaj = "";
  Grid grArchiv = null;

if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		
		try{
			tppermis = io.getStringValue("tipoperm");
			
			if(tppermis == null){
				tppermis ="S";
			}
		}catch(Exception ex){
			System.out.println("Error recibiendo el tipo permiso del directorio");
		}
		
		try{
			creadire = io.getStringValue("creadire");
			
			if(creadire == null){
				creadire ="S";
			}
		}catch(Exception ex){
			System.out.println("Error recibiendo el tipo creadire del directorio");
		}
		
		try{
			usuariox = io.getStringValue("propieta");
			
			if(usuariox == null){
				usuariox ="0";
			}
		}catch(Exception ex){
			System.out.println("Error recibiendo el propietario del directorio");
		}
		
		try{
			txmensaj = io.getStringValue("txmensaj");
		}catch(Exception ex){
			txmensaj = "";
			System.out.println("Error recibiendo el mensaje");
		}
		idemisor = io.getStringValue("idemisor");	
		folderin = io.getStringValue("folderin");
		rutaabso = io.getStringValue("rutaabso"); 
		cduserid = io.getStringValue("cduserid");
		grArchiv = io.getGrid("grArchiv");
	}catch( Exception ex){
		System.out.println("Errror al recibir datos en CloudIni");	
	}
	
	System.out.println("Folder in "+folderin);
}

%>


<script>

var arrSelecci = [];
var arrTipoFic = [];
var listfile = "";
var tipoperm = "<%=tppermis%>";
var propieta = "<%=usuariox%>";
var creadire = "<%=creadire%>";

	<% for ( int i= 0; i < grArchiv.rowCount(); i++){ 
		if(grArchiv.getStringCell(i,"tipofich").equals("F")){
	%>
		
	listfile += "<%=grArchiv.getStringCell(i, "txnombre") %>,";
	
	<%}
	}
	%>

function cambiaImagen(tipofich,txnombre, idinodox,tipofile , tipoperm, propieta){
	
	encuentra = 0;
	imagen  = "";
	imagen2 = "";
	
	idceldax = "C"+txnombre+"C";
	
	
	
	if(tipofile == "png" || tipofile == "jpeg" || tipofile == "jpg" ){
		imagen = "imagen.png";	
		imagen2 = "on-imagen.png";	
	}else if(tipofile == "xls" || tipofile == "xlsx" ){
		imagen = "xls.png";
		imagen2 = "on-xls.png";	
	}else if(tipofile == "iso" ){
		imagen  = "iso.png";
		imagen2 = "on-iso.png";
	}else if(tipofile == "pdf" ){
		imagen  = "pdf.png";
		imagen2 = "on-pdf.png";
	}else if(tipofile == "doc" || tipofile == "docx" ){
		imagen  = "doc.png";
		imagen2 = "on-doc.png";
	}else if(tipofile == "ppt" ){
		imagen  = "ppt.png";
		imagen2 = "on-ppt.png";
	}else if(tipofile == "zip" || tipofile == "rar" ){
		imagen  = "winrar.png";
		imagen2 = "on-winrar.png";
	}else{
		imagen  = "desconoscido.png";
		imagen2 = "on-desconoscido.png";
	}
	
	
	for( i=0; i< arrSelecci.length; i++){
		
		if(arrSelecci[i] == idinodox){
			encuentra = 1;
			arrSelecci.splice(i,1);
			arrNomsele.splice(i,1);
			arrTipoFic.splice(i,1);
			arrUsuario.splice(i,1);
		}
	}
	
	if (encuentra == 0){
		arrSelecci.push(idinodox);
		arrNomsele.push(txnombre);
		arrTipoFic.push(tipofich);
		arrUsuario.push(propieta);
		
		if (tipofich =="D"){
			document.getElementById(txnombre).src = "/JLet/cloud/img/directorio2.png";
			
			 
		}else{
			document.getElementById(txnombre).src = "/JLet/cloud/img/"+imagen2;
		}
		document.getElementById(idceldax).style.border="3px solid #ff0000";
		
	}else{
		if (tipofich =="D"){
			document.getElementById(txnombre).src = "/JLet/cloud/img/directorio.png";
		}else{
			document.getElementById(txnombre).src = "/JLet/cloud/img/"+imagen;
		}
		document.getElementById(idceldax).style.border="1px solid #878585";
		
	}
	
	
}

	function abrirFicheroDirec(tipofich,txnombre,tipoperm, propieta){
		
		filepath = document.formMenu.txdirect.value;
		
		if (tipofich =="D"){

			document.formMenu.services.value = "InitCloudSrv";
			document.formMenu.view.value	 = "cloud/principalCloud.jsp";
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.filepath.value = txnombre;
			document.formMenu.tipoperm.value = tipoperm;
			document.formMenu.propieta.value = propieta;
			document.formMenu.submit();
		}else{
			descargarFichero(filepath,txnombre)
		}
	}	
		
	function creaCarpeta(){
			
			
			if(document.getElementById('permgru').checked){
	
				document.formMenu.permgrup.value = "S";
			}else{
				document.formMenu.permgrup.value = "N";
			}
			
			document.formMenu.services.value = "CreaDirectorioSrv";
			document.formMenu.view.value	 = "cloud/principalCloud.jsp";
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.tipofich.value = "D";
			document.formMenu.submit();
	}
	
function compruebaFich(){
	
	if(document.formSubida.archivo.value != ""){
		return true;
	}else{
		alert("Debes seleccionar un documento para subir");
		return false;
	}
}


function simulate(element, eventName)
{
    var options = extend(defaultOptions, arguments[2] || {});
    var oEvent, eventType = null;

    for (var name in eventMatchers)
    {
        if (eventMatchers[name].test(eventName)) { eventType = name; break; }
    }

    if (!eventType)
        throw new SyntaxError('Only HTMLEvents and MouseEvents interfaces are supported');

    if (document.createEvent)
    {
        oEvent = document.createEvent(eventType);
        if (eventType == 'HTMLEvents')
        {
            oEvent.initEvent(eventName, options.bubbles, options.cancelable);
        }
        else
        {
            oEvent.initMouseEvent(eventName, options.bubbles, options.cancelable, document.defaultView,
            options.button, options.pointerX, options.pointerY, options.pointerX, options.pointerY,
            options.ctrlKey, options.altKey, options.shiftKey, options.metaKey, options.button, element);
        }
        element.dispatchEvent(oEvent);
    }
    else
    {
        options.clientX = options.pointerX;
        options.clientY = options.pointerY;
        var evt = document.createEventObject();
        oEvent = extend(evt, options);
        element.fireEvent('on' + eventName, oEvent);
    }
    return element;
}

function extend(destination, source) {
    for (var property in source)
      destination[property] = source[property];
    return destination;
}

var eventMatchers = {
    'HTMLEvents': /^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,
    'MouseEvents': /^(?:click|dblclick|mouse(?:down|up|over|move|out))$/
}
var defaultOptions = {
    pointerX: 0,
    pointerY: 0,
    button: 0,
    ctrlKey: false,
    altKey: false,
    shiftKey: false,
    metaKey: false,
    bubbles: true,
    cancelable: true
}
</script>

 <table width="98%" align="center" style="position:absolute;top:5px;margin-left:380px;width:40%">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>




<table class="TablaGrande" align="center" style="position:absolute;top:50px;margin-left:380px;width:40%" >
	<tr>
		<td align="center">
			<b>Subir</b>
			<br>
			<img onclick="subedirectorio()" style="cursor:pointer" src="/JLet/cloud/img/boton_subir2.jpg">
		</td>
		<td>Directorio actual : </td>
		<td><input type="text" style="width:100%" value="<%=folderin%>"></td>
	</tr>

</table>


		<%
		if(txmensaj != null && !txmensaj.equals("")){
	%>
	<table id="mensaje" class="TablaGrande" align="center" style="position:absolute;top:130px;margin-left:380px;width:40%" >
		<tr>
			<td align="center"><h2 ><%=txmensaj%></h2></td>
		</tr>	
	<% 	}
%>
</table>


	<form method="post" enctype="multipart/form-data" name="formSubida" action="/JLet/UploadFile" onsubmit="return compruebaFich()" >
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="tipofich" 	value="F"/>
			<input type="hidden" name="permgrup" 	value="S"/>
			<input type="hidden" name="propieta" value="1"/>
			<input type="hidden" name="txdirect" value="<%=folderin%>"/>
			<input type="hidden" name="filepath" value="<%=folderin%>"/><!-- Cambiar -->
			<input type="hidden" name="filename" value=""/><!-- Cambiar -->


			<table class="TablaGrande" align="center" id="formsube" style="display:none;position:absolute;top:110px;margin-left:380px;width:40%">
				<tr>
					<td><input type="file" name="archivo[]" multiple=true class="boton"/></td>
					<td><input type="submit" value="Subir archivo" class="boton" /></td>
				</tr>
				
			</table>
		
	</form>


<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="CloudHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="filepath" 	value=""/>
			<input type="hidden" name="tipofich" 	value=""/>
			<input type="hidden" name="permgrup" 	value=""/>
			<input type="hidden" name="tipoperm" 	value=""/>
			<input type="hidden" name="propieta" 	value=""/>
			<input type="hidden" name="cduserid" 	value="<%=cduserid%>"/>
			<input type="hidden" name="idinodox" 	value=""/>
			<input type="hidden" name="txdirect" id="txdirect" value="<%=folderin%>">

<table id="formcarpeta" class="TablaGrande" align="center" style="display:none;position:absolute;top:130px;margin-left:380px;width:40%">	

		<tr>
			<td>
				Nombre carpeta:
			</td>
			<td>
				<input type="text" name="txnombre">
			</td>
		</tr>
		<tr>
			<td>
				Permiso de grupo:
			</td>
			<td>
				<input type="checkbox" id="permgru">
			</td>
			<td>
				<a class="boton" onclick="creaCarpeta()">Crear carpeta</a>
			</td>
		</tr>
		
		<tr>
			
		</tr>

</table>	

</form>

<div id="lineastmp">

			<% if ((grArchiv != null) && (grArchiv.rowCount() > 0)) { %> 
			
					<table class="TablaGrande" align="center" style="position:absolute;top:230px;margin-left:330px;width:80%">
			  	   		<tr class="cab">
			
								<%
								int itemxrow = 4;
								int cont = 0;
								String tipofich = "";
								String txnombre = "";
								String imgfilex = "";
								String idinodox = "";
								String tipofile = "";
								String tipoperm = "";
								String propieta = "";
								int indexfile = 0;
								
								    for (int h = 0; h < grArchiv.rowCount(); h++){ 
								    	cont++;
								    	tipofich = grArchiv.getStringCell(h,"tipofich");
								    	txnombre = grArchiv.getStringCell(h,"txnombre");
								    	idinodox = grArchiv.getStringCell(h,"idinodox");
								    	tipoperm = grArchiv.getStringCell(h,"permgrup");
								    	propieta = grArchiv.getStringCell(h,"propieta");
								    	
								    	indexfile = txnombre.lastIndexOf('.');
								    	
								    	if(tipofich.equals("D")){
								    		imgfilex ="/JLet/cloud/img/directorio.png";
								    	}else{
								    		tipofile = txnombre.substring(indexfile + 1);
								    		System.out.println("DOCUEMNTOOOOOOOOOOOOOOOOOO "+tipofile);
								    		if(tipofile.equals("xls") || tipofile.equals("xlsx")){
								    			imgfilex ="/JLet/cloud/img/xls.png";
								    		}else if(tipofile.equals("iso")){
								    			imgfilex ="/JLet/cloud/img/iso.png";
								    		}else if(tipofile.equals("rar") || tipofile.equals("zip")){
								    			imgfilex ="/JLet/cloud/img/winrar.png";
								    		}else if(tipofile.equals("pdf")){
								    			imgfilex ="/JLet/cloud/img/pdf.png";
								    		}else if(tipofile.equals("png") || tipofile.equals("jpg") || tipofile.equals("jpeg")){
								    			imgfilex = "/JLet/cloud/img/imagen.png";
								    			
								    		}else if(tipofile.equals("doc") || tipofile.equals("docx")){
								    			imgfilex  = "/JLet/cloud/img/doc.png";
								    		}else{
								    			imgfilex  = "/JLet/cloud/img/desconoscido.png";
								    		}
								    	}
								    	
								    	tipofile = txnombre.substring(indexfile + 1);
								    	
								    	System.out.println("es tipooooo  "+tipofile);

							 		%>
								
									<td width="15%" align="center" class="tablaGrandeW">
										<table border="0">
											<tr>
												<td id="C<%=txnombre%>C" style="cursor:pointer" align="center" class="fonts6" width=25%><img id="<%=txnombre%>" ondblclick="abrirFicheroDirec('<%=tipofich %>','<%=txnombre %>','<%=tipoperm %>','<%=propieta %>')" onclick="cambiaImagen('<%=tipofich %>','<%=txnombre %>','<%=idinodox %>','<%=tipofile%>','<%=tipoperm %>','<%=propieta %>')" height=80px; width=80px; src="<%=imgfilex%>"></td>
											</tr>
											<tr>
												<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=txnombre%></td>
											</tr>
										
										</table>
									</td>	
									<td width="0.5%" align="center">&nbsp;</td>
									<% 	if (cont % itemxrow == 0){ %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
									<tr>
										<% 
									  }
								   }%>
								   
								   <% for (int i = cont; i % 4 != 0; i++){ %>
								   		<td>&nbsp;</td>
								   		<td>&nbsp;</td>
								   <% }	 %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
								   
								    </tr>
        						</td>
							</table>
						 <%} %>		
			</div>