function objetoAjax(){
	var xmlhttp=false;
	   try {
	   	  xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
       } 
	   catch (e) {
     
	   try {
     	  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
     	}
      catch (E) { 
     	  xmlhttp = false;
     		}
		}        
		if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
			xmlhttp = new XMLHttpRequest();
    	}
    return xmlhttp;
}

function validarEmail(texto)
 { 

    var mailres = true;             
    var cadena = "abcdefghijklmn�opqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ1234567890@._-"; 
     
    var arroba = texto.indexOf("@",0); 
    if ((texto.lastIndexOf("@")) != arroba) arroba = -1; 
     
    var punto = texto.lastIndexOf("."); 
                 
     for (var contador = 0 ; contador < texto.length ; contador++)
	    { 
          if (cadena.indexOf(texto.substr(contador, 1),0) == -1)
		     { 
              mailres = false; 
              break; 
             } 
         } 

    if ((arroba > 1) && (arroba + 1 < punto) && (punto + 1 < (texto.length)) && (mailres == true) && (texto.indexOf("..",0) == -1)) 
         {mailres = true; 
		 }
    else
	     { 
          mailres = false;
	     }        
    return mailres; 
 }//fin funcion validar	


function ver_fecha(texto)
{	 
//var texto=clientes.desde.value;
re=/^[0-9][0-9]\/[0-9][0-9]\/[0-9][0-9][0-9][0-9]$/
	
	if(!re.exec(texto))
		{	
		//alert("La fecha no tiene formato DD/MM/AAAA");
		return false;
		}
	else
	{
	 return true;
	}
}

function fechaCorrecta(texto)
{	
var dia  =  parseInt(texto.substring(0,2),10);
var mes  =  parseInt(texto.substring(3,5),10);
var anio =  parseInt(texto.substring(6),10);

  switch(mes)
  {
	 case 1:
	 case 3:
	 case 5:
	 case 7:
	 case 8:
	 case 10:
	 case 12:
	 	numDias =31;
		break;
	 case 4: case 6: case 9: case 11:
	  numDias=30;
	  break;
	 case 2: 
	 if(comprobarSiBisiesto(anio)){numDias=29;} else{numDias=28;} 
	 break;
	 
	 default:
		return false;
	}
		
	 if (dia>numDias || dia==0)
	  {
	   return false;
	  }
	  else 
	  {return true;}
 	 
 }


function comprobarSiBisiesto(anio){
if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) 
{
   return true;
  }
else 
{
  return false;
  }
} 


function SoloTexto(car)//funcion comprueba solo texto y espacio
      {
		//  alert(car);
	   var keyPressed = (car.which) ? car.which : event.keyCode;
	   return ((keyPressed > 64 && keyPressed > 91 || keyPressed == 8 || keyPressed == 32 || keyPressed > 57));// || keyPressed = 32))
      }

function soloNumeros(e) {
//alert("Entra");
var keynum = window.event ? window.event.keyCode : e.which;
if ( keynum == 8 ) return true;
return /\d/.test(String.fromCharCode(keynum));
}
function validardec(e) {  
    tecla = (document.all) ? e.keyCode : e.which;  
    if (tecla==8) return true; //Tecla de retroceso (para poder borrar)  

    patron = /[1234567890.]/;// Solo acepta n�meros y el punto 
     
    te = String.fromCharCode(tecla);  
    return patron.test(te);   
} 

/*
 * Da formato a un n�mero para su visualizaci�n
 *
 * numero (Number o String) - N�mero que se mostrar�
 * decimales (Number, opcional) - N� de decimales (por defecto, auto)
 * separador_decimal (String, opcional) - Separador decimal (por defecto, coma)
 * separador_miles (String, opcional) - Separador de miles (por defecto, ninguno)
 */
function formato_numero(numero, decimales, separador_decimal, separador_miles){ // v2007-08-06
	numero=parseFloat(numero);
	if(isNaN(numero)){
		return "";
	}

	if(decimales!==undefined){
		// Redondeamos
		numero=numero.toFixed(decimales);
	}

	// Convertimos el punto en separador_decimal
	numero=numero.toString().replace(".", separador_decimal!==undefined ? separador_decimal : ",");

	if(separador_miles){
		// A�adimos los separadores de miles
		var miles=new RegExp("(-?[0-9]+)([0-9]{3})");
		while(miles.test(numero)) {
			numero=numero.replace(miles, "$1" + separador_miles + "$2");
		}
	}

	return numero;
}

function compare_dates(fecha, fecha2)  
{  
  var xMonth=fecha.substring(3, 5);  
  var xDay=fecha.substring(0, 2);  
  var xYear=fecha.substring(6,10);  
  var yMonth=fecha2.substring(3, 5);  
  var yDay=fecha2.substring(0, 2);  
  var yYear=fecha2.substring(6,10);  
  if (xYear> yYear)  
  {  
      return(true)  
  }  
  else  
  {  
    if (xYear == yYear)  
    {   
      if (xMonth> yMonth)  
      {  
          return(true)  
      }  
      else  
      {   
        if (xMonth == yMonth)  
        {  
          if (xDay> yDay)  
            return(true);  
          else  
            return(false);  
        }  
        else  
          return(false);  
      }  
    }  
    else  
      return(false);  
  }  
}  


function justNumber(input) {

	var val = parseFloat(input.value);
	if (isNaN(val)) {
		input.value = "0.00";
		return "0.00"; 
	}
	if (val <= 0) {
		input.value = "0.00";
		return "0.00"; 
	}
	val += "";
	// Next two lines remove anything beyond 2 decimal places
	if (val.indexOf('.') == -1) {
		input.value = val+".00";
		return val+".00"; 
	} else {
		val = val.substring(0,val.indexOf('.')+3); 
	}
	val = (val == Math.floor(val)) ? val + '.00' : ((val*10 == Math.floor(val*10)) ? val + '0' : val);
	
	input.value = val;
	return val;
	
}