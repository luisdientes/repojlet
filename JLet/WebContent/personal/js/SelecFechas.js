var horasSel = 8;
var countArr = 0;
var countSel = 0;
var countHor = 0;
var primera = 0;
var cuantosPr = 0;


function enviar(){
		
		document.enviaDatos.fechas.value 	= fechaCaja;
		document.enviaDatos.horas.value 	= horasCaja;
		document.enviaDatos.proyectos.value = idproCaja;
		if(countHor != totalFe ){
		}
		else{
		document.enviaDatos.submit();
		}
	}

function confirmar(fecha,id){
	horas 		= 	"H"+id+"H";
	proyecto	=	"P"+id+"P";
	capaId		=	"CA"+id+"CA";
	hora = document.getElementById(horas).value;
	proy = document.getElementById(proyecto).value;
	var capa = document.getElementById(capaId);
	var h1 = document.createElement("h1");
	h1.innerHTML = "PRUEBA";
	capa.appendChild(h1);
	
}

 function tableCreate(fecha,id,viene,proy) {
        capaId	=	"CA"+id+"CA";
        horas 	= 	"H"+id+"H";
        celda	=	"CE"+id+"CE";
        celda2  = "CE"+numCelda+"CE";
        
        if (viene == "S"){
        	proyecto =  capaId + proy;
        	horas	 = fecha+proy;
        	document.getElementById(celda2).style.backgroundColor="green";
        	numCelda++;
        }
        else{
        	proyecto =	"P"+id+"P";
        	horas 	 = 	"H"+id+"H";
        	document.getElementById(celda).style.backgroundColor="green";
        }
    
        proy 		= document.getElementById(proyecto).value;
        var body 	= document.getElementById(capaId);
        hora 		= document.getElementById(horas).value;

        // create elements <table> and a <tbody>
        var tbl     = document.createElement("table");
        var tblBody = document.createElement("tbody");
        var selectHor = creaSelHoras(capaId,hora,fecha,proy,id,cuantosPr);
        
      	document.getElementById(horas).disabled		=	"true";
    	document.getElementById(proyecto).disabled	=	"true";
    		
        if(selectHor != false){
      
        for (var j = 0; j <1 ; j++) {
            var row = document.createElement("tr");
            for (var i = 0; i < 8; i++) {
             var cell = document.createElement("td");  
                switch(i){
                	case 0:
                		var cellText = document.createTextNode("Fecha: ");
                		cell.appendChild(cellText);
						cell.setAttribute("class", "fonts21b");
	                	row.appendChild(cell);
	                	break;
                	case 1:
                		var cellText = document.createTextNode(fecha);
                		cell.appendChild(cellText);
                		cell.setAttribute("align", "center");
						cell.setAttribute("class", "input7b");
	                	row.appendChild(cell);
	                	break;
                	case 2:
                		var cellText = document.createTextNode("Proyecto: ");
                		creaSelProyec
                		cell.appendChild(cellText);
						cell.setAttribute("class", "fonts21b");
	                	row.appendChild(cell);
	                	break;
                	case 3:
                		cell.appendChild(creaSelProyec(capaId,proy));
						cell.setAttribute("class", "input7b");
	                	row.appendChild(cell);
	                	break;
                	case 4:
                		var cellText = document.createTextNode("Horas ");
                		cell.appendChild(cellText);
						cell.setAttribute("class", "fonts21b");
	                	row.appendChild(cell);
	                	break;
                	case 5:
                			cell.appendChild(selectHor);
							cell.setAttribute("class", "input7b");
	                		row.appendChild(cell);
	                	break;
                	case 6:
                		var cellText = document.createElement("A");
                		var cellText2 = document.createTextNode("Confirmar ");
						cellText.appendChild(cellText2);
                		cellText.setAttribute("onclick", "tableCreate('"+fecha+"','"+id+"','S','"+proy+"'); this.style.display='none';this.style.backgroundColor='green'");
                		cellText.setAttribute("style","cursor:pointer");
                		cellText.setAttribute("style","font-weight:bold");
                		cell.appendChild(cellText);
						cell.setAttribute("class", "fonts3");
						cell.setAttribute("id","CE"+numCelda+"CE");
	                	row.appendChild(cell);
	                	break;	
                } 	
                
            }
            tblBody.appendChild(row);
        }

        tbl.appendChild(tblBody);
        body.appendChild(tbl);
        tbl.setAttribute("border", "1");
        tbl.setAttribute("align", "center");
        tbl.setAttribute("width", "60%");
        tbl.setAttribute("class", "centrado2");
        tbl.setAttribute("style", "border:0px");
        creaSelProyec(capaId);
        }//FIN IF
       
        fechaCaja[countArr] = fecha;
        horasCaja[countArr] = hora;
        idproCaja[countArr] = proy;
        countArr++;
       
    }
 
 function compruebaExiste(id){
		var ret = true;
		for (x = 0; x < idselPro.length; x++){
			if (idselPro[x]==id){
				ret=false;
			}
		}
		return ret;
		
	}
	 
	function creaSelProyec(capaId,id){
			
		idcli=-1;
		select 	= document.createElement("SELECT");
		select.setAttribute("id",capaId+id);
		select.setAttribute("class", "input7b");
		idselPro[countSel] = id;
	
	  	for (i = 0; i < txproyec.length; i++){
	  		
	   		if(compruebaExiste(idproyec[i])){
			  	selCli 	= document.getElementById(capaId);
		      	nombre	=	txproyec[i];
		      	idcli	=	idproyec[i];
		      	option 	= document.createElement("OPTION");
				option.value = idcli;
				option.text =nombre;
				//option.selected=true;
				select.appendChild(option);
				
	  		}
		} 
	 countSel++;
	 cuantosPr--;
	 return select;
}

function creaSelHoras(capaId,hora,fecha,idpro,id,quedauno){
	
	document.getElementById("confirma").disabled=true;

	if (horasSel==0){
		horasSel = 8;
		idselPro.length=0;	
	}
	
	horasSel = horasSel - hora;
	
	if (horasSel==0){
		countHor++;
		id++;
		if (totalFe != countHor){
		idEnlace = "EN"+id+"EN";
		document.getElementById(idEnlace).style.display="block";
		
		}
	}
	//fechaAc =fecha;
	//x=0;
	select 	= document.createElement("SELECT");
	select.setAttribute("id",fecha+idpro);
	select.setAttribute("class", "input7b");
	
	if(quedauno == 1){
		selCli 	= document.getElementById(capaId);
	 	option 	= document.createElement("OPTION");
		option.value = horasSel;
		option.text =horasSel;
	    select.appendChild(option);
	    cuantosPr = txproyec.length;
		
	}
	else{

	 for (i = horasSel; i >=1 ; i--){
	  	selCli 	= document.getElementById(capaId);
	 	option 	= document.createElement("OPTION");
		option.value = i;
		option.text =i;
	    select.appendChild(option);
	 } 
	}
	
	if(horasSel == 0){
		return false;
	}
	else{
	   	return select;
	}
}
