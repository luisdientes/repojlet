var arrSelecci = [];
var arrNomsele = [];
var arrTipoFic = [];
var arrUsuario = [];

function inicio(){
	document.formInicio.services.value = "InitCloudSrv";
	document.formInicio.view.value	 = "cloud/principalCloud.jsp";
	document.formInicio.submit();	
}



function muestraFormCarpeta(){
	
	usuario = document.formMenu.cduserid.value;
	
	if(propieta == usuario || tipoperm == "S"){
	
		document.getElementById('formcarpeta').style.display = "block"; 
		document.getElementById('formsube').style.display 	 = "none"; 
		
		if(document.getElementById('mensaje')){
			document.getElementById('mensaje').style.display    = "none";
		}
		
	}else{
		alertify.alert("No dispones de permisos para crear directorios en esta carpeta", function () {});
	}
}


function subeFichero(){
	
	usuario = document.formMenu.cduserid.value;
	
	if(propieta == usuario || tipoperm == "S"){
		document.getElementById('formsube').style.display 	 = "block";
		document.getElementById('formcarpeta').style.display = "none"; 
		if(document.getElementById('mensaje')){
			document.getElementById('mensaje').style.display    = "none";
		} 
			
	}else{
		alertify.alert("No dispones de permisos para subir archivos en este directorio", function () {});
	}
	
}


function comprimir(){
	
	filepath = document.formMenu.txdirect.value;
	if(arrTipoFic.length >1){
		
		alertify.alert("Solo puedes marcar un directorio", function () {});
	}else{
		
		if(arrNomsele != "" && arrTipoFic =="D"){
				
			alertify.confirm("<p>&iquest;Desea comprimir el directorio seleccionado?</p>", function (e) {
				if (e) {
					filepath  = document.formMenu.txdirect.value;
					filepath += arrNomsele[0]+"/";
					document.formInicio.services.value = "ComprimeFicheroSrv";
					document.formInicio.view.value	 = "cloud/principalCloud.jsp";
					document.formInicio.filepath.value = filepath;
					document.formInicio.txnombre.value = arrNomsele[0];
					document.formInicio.txdirect.value = document.formMenu.txdirect.value;
					document.formInicio.listfile.value = listfile;
					document.formInicio.submit();
				}else {
					alertify.error("Has cancelado comprimir el directorio ");
				}
			}); 
			return false
		}else{
			alertify.alert("Debes seleccionar un directorio", function () {});
		}
  }
	
}

function descomprime(){
	
	filepath = document.formMenu.txdirect.value;
	if(arrTipoFic.length >1){
		alertify.alert("Solo puedes marcar un fichero", function () {});
	}else{
		if(arrNomsele != ""){ 
			alertify.confirm("<p>&iquest;Esta seguro que desea descomprimir el fichero seleccionado.?</p>", function (e) {
				if (e) {
					filepath  = document.formMenu.txdirect.value;
					filepath += arrNomsele[0]+"/";
					document.formInicio.services.value = "DesComprimeFicheroSrv";
					document.formInicio.view.value	 = "cloud/principalCloud.jsp";
					document.formInicio.filepath.value = filepath;
					document.formInicio.txnombre.value = arrNomsele[0];
					document.formInicio.txdirect.value = document.formMenu.txdirect.value;
					document.formInicio.listfile.value = listfile;
					document.formInicio.submit();
				}
				else { alertify.error("Has cancelado descomprimir el fichero ");
				}
			}); 
			return false
		}else{
			
			alertify.alert("Debes marcar un fichero ", function () {});
		}
  }
	
}

function descargarFichero(directcl,namefile){
	
	
	alertify.confirm("<p>&iquest;Esta seguro que desea descargar el fichero: "+namefile+".?</p>", function (e) {
		if (e) {
			document.fordownload.action = "/JLet/JLetDownload";
			document.fordownload.target = "_blank";
			document.fordownload.directcl.value = directcl;
			document.fordownload.filename.value = namefile;
			document.fordownload.submit();
			
			alertify.success("Has pulsado Descargar'");
		} else { alertify.error("Has cancelado la descarga del fichero ");
		}
	}); 
	return false
	
	
	
	/*if(confirm("Esta seguro que desea descargar el fichero: "+ namefile)){
		document.fordownload.action = "/JLet/JLetDownload";
		document.fordownload.target = "_blank";
		document.fordownload.directcl.value = directcl;
		document.fordownload.filename.value = namefile;
		document.fordownload.submit();
	}*/
	
}


function descargaMenu(){
	
	tipofich = arrTipoFic[0];
	
	
		
	
	if(arrTipoFic.length >1){
		alertify.alert("<b>Solo puedes marcar un fichero</b>", function () {});
	}else{
		
		if(tipofich =="F" || tipofich=="Z"){
			
			filepath  = document.formMenu.txdirect.value;
			txnombre  = arrNomsele[0];
			descargarFichero(filepath,txnombre);
		}else{
			alertify.alert("<b>Solo se pueden descargar ficheros</b>", function () {});
		}
	}
	
	
}

function delArchivo(){
	
	usuario = document.formMenu.cduserid.value;
	esficher  = 0;
	esdirect  = 0;
	
	
	if(arrTipoFic.length >1000){
		
		alertify.alert("<b>Solo puedes marcar un fichero o un directorio</b>", function () {});
	}else{
		
		for( i=0; i<arrTipoFic.length; i++){
			tipofich = arrTipoFic[i];
			
			if(tipofich =="F" || tipofich=="Z"){
				txtalert ="fichero";
				esficher = 1;
			}else{
				txtalert ="Directorio";
				esdirect = 1;
			}
		}
		
		if(esficher ==1 && esdirect == 1){
			
			alertify.alert("<b>No puedes eliminar un fichero y un directorio a la vez</b>", function () {});
		}else{

		if(usuario != arrUsuario[0]){
			
			if(arrTipoFic.length == 0){
				alertify.alert("<b>No has seleccionado directorio</b>", function () {});
			}else{
				alertify.alert("<b>No puedes eliminar el "+txtalert+" "+arrNomsele[0]+" por que no eres el propietario</b>", function () {});

			}
		}else{
			
			alertify.confirm("<p>&iquest;Esta seguro que desea eliminar el "+txtalert+" "+arrNomsele+".?</p>", function (e) {
				if (e) {
					filepath = document.formMenu.txdirect.value;
					
					//alert("Comprimir carpetas con idinodo: " +arrSelecci );
					document.formInicio.services.value = "DelDirectorioSrv";
					document.formInicio.view.value	 = "cloud/principalCloud.jsp";
					document.formInicio.filepath.value = filepath;
					document.formInicio.tipofich.value = tipofich;
					document.formInicio.txnombre.value = arrNomsele;
					document.formInicio.idinodox.value = arrSelecci;
					document.formInicio.submit();
					alertify.success("Has Eliminado el fichero'");
				} else { alertify.error("Has cancelado la eliminaci&oacute;n del fichero ");
				}

			}); return false
				
				

		   }
		}
	}
	
}


function versiones(){
	
	if(arrTipoFic.length >1){
		alertify.alert("<b>Solo puedes marcar un fichero</b>", function () {});
	}else{
		
		tipofich = arrTipoFic[0];
		
		if(arrTipoFic.length ==0){
			
			alertify.alert("<b>No has marcado ningun fichero</b>", function () {});
		}
		else{
		
			if(tipofich =="F" ){
				filepath = document.formMenu.txdirect.value;
		
				document.formInicio.services.value = "ListVersionesSrv";
				document.formInicio.view.value	 = "cloud/listVersionesFichero.jsp";
				document.formInicio.filepath.value = filepath;
				document.formInicio.txnombre.value = arrNomsele;
				document.formInicio.idinodox.value = arrSelecci;
				document.formInicio.submit();
			}else{
				alertify.alert("<b>Solo puedes marcar un fichero</b>", function () {});
			}
		}
	}
}

function subedirectorio(){
	
	arrDirec  = new Array(); 
	filepath  = document.formMenu.txdirect.value;
	arrDirec  = filepath.split("/");
	
	//alert(arrDirec.length);
	
	
	if(arrDirec.length != 2){
		nombreant = arrDirec[arrDirec.length-2];
		longfile  = filepath.length;
		directorio = filepath.substring(0,longfile -(nombreant.length+1));
		directorio = directorio.substring(0, directorio.length-1);
		document.formMenu.txnombre.value = nombreant;
		document.formMenu.txdirect.value = directorio;
		document.formMenu.services.value = "InitCloudSrv";
		document.formMenu.view.value	 = "cloud/principalCloud.jsp";
		document.formMenu.submit();
	}
	
	
}


function abreCarpeta(){
	
	if(arrNomsele.length ==1 && arrTipoFic=="D"){

		simulate(document.getElementById(arrNomsele), "dblclick");
	}else{
		alertify.alert("<b>Solo puedes marcar un directorio</b>", function () {});
	}

}
