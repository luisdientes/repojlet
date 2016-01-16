<%@ include file="/common/jsp/include.jsp"%>
<head>
  <title>Calendario Jsp.</title>
	<script src="/JLet/common/js/validacionesComunes.js"></script>
	<link rel="stylesheet" href="common/css/calendario.css" type="text/css"/>
	<script type="text/javascript" src="common/js/calendar.js"></script>
	<script type="text/javascript" src="common/js/calendar-es.js"/></script>
	<script type="text/javascript" src="common/js/calendar-setup.js"/></script>
</head>
<%
	String[] fechMarca 	= request.getParameterValues("fhpulsad"); //recibo las fechas que he pulsado
	String txanioxx 	= request.getParameter("txanioxx");
	String txmesxxx 	= request.getParameter("txmesxxx");
	String cduserid		= "";
	//String mesSigui 	= "";
	String diaActua		= "";
	String FechaSel 	= "";
	String diaSelec		= "";
	String mesSelec		= "";
	String fecDiasx 	= ""; //para coger la fecha del ultimo dia

	Grid gridFech		= null;
	boolean recibeAn 	= false; 
	int anioNume 		= 0;
	int mesNumer 		= 0;
	int diasMeAn 		= 0;
	int diasMesP 		= 0;
	int mesSigui 		= 0;
	int mesAnter 		= 0;
	int anioAnte 		= 0;
	int anioPost 		= 0;
	int diasDelM 		= 0;
	int ultMesAn		= 0; //ultimo dia del mes anterior
	int ultimoDi		= 0; //ultimo dia del mes actual
	int diaSemUl		= 0; // dia de la semana del ultimo dia del mes
	int diaSemCa		= 0; // dia de la semana de cada uno de los dias del calendario, para saber si se puede marcar o no...
	int finCalen		= 0;
	int diasMesx[] = {
		    31, 28, 31, 30,  /* jan feb mar apr */
		    31, 30, 31, 31, /* may jun jul aug */
		    30, 31, 30, 31  /* sep oct nov dec */
		};
	
	
	String[] months = {
		    "Enero", "Febrero", "Marzo", "Abril",
		    "Mayo", "Junio", "Julio", "Agosto",
		    "Septiembre", "Octubre", "Noviembre", "Diciembre"
		  };
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			cduserid = io.getStringValue("cduserid");
			gridFech = io.getGrid("gridFech");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en calendario.jsp");	
		}
	}
	
	ArrayList<String> arrFechas = new ArrayList<String>();

	
	try{
		for (int i = 0; i < gridFech.rowCount(); i++){
			arrFechas.add(gridFech.getStringCell(i,"fechatra"));
		}
		
	}catch(Exception ex){
	  	System.out.println("Excepcion al recorrer Grid fechas recibidas -----"+gridFech.getStringCell(50,"fechatra")+" --Error"+ex.toString());
	}
	

		

			
	System.out.println("FLAG 4asdas  ad asd " +gridFech.rowCount());
	
	Calendar c = Calendar.getInstance(  );
	diaActua   = Integer.toString(c.get(Calendar.DATE));
	diasDelM   = diasMesx[mesNumer];
	 
	if (txanioxx != null && txanioxx.length(  ) > 0) {
	    try {
	      anioNume = Integer.parseInt(txanioxx);
	      recibeAn = true;
	    } catch (NumberFormatException e) {
	      out.println("A&ntilde;o " + txanioxx + " no v&aacute;lido");
	    }
	  }

	 if (!recibeAn){
		  anioNume = c.get(Calendar.YEAR);
	  }	  

	  System.out.println("Recibido mes "+txmesxxx);
	  
	 if (txmesxxx == null) {
		  mesNumer = c.get(Calendar.MONTH);
	  }else {
	    for (int i=0; i<months.length; i++)
	      if (months[i].equals(txmesxxx)) {
	    	  mesNumer = i;
	        break;
	      }
	  }
%>

<script type="text/javascript">
var contFech	= 0;
var fec			= 0;
var fechas		= new Array();
var FechasMa	= new Array();


<% 
/* CARGO LAS FECHAS pulsadas RECIBIDAS EN UN ARRAY*/
 try{
	if( fechMarca!=null && !fechMarca.equals("")){
	
	String[] fechaSe = fechMarca[0].split(",");
		for(int i=0;i<fechaSe.length;i++){
		%>
		fechas[fec] = "<%=fechaSe[i].toString()%>";
		fec++;
		<%	
		}
	}
 }catch(Exception ex){
	 System.out.println("No recibe nada");
 }
	
	
%>

	function agregaFecha(fecha){ /* agrego la fecha a un array o la borro del array dependiendo de si estaba pulsada o no*/
		
			if(!compruebaExiste(fecha)){
					pulsada = 0;
					document.getElementById(fecha).style.backgroundColor="#acbcd3";
					var elemento = document.getElementById(fecha);
					elemento.className = "input1";
					
					for(z=0;z<fechas.length;z++){
						
						if(fechas[z] == fecha){
							fechas.splice(z,1);
							document.getElementById(fecha).style.backgroundColor="#d9dcdd";
							var elemento = document.getElementById(fecha);
							elemento.className = "celle";
							pulsada = 1;
						}	
					}
				
				//	alert(fechas);
					if(pulsada == 0){
			 	 		fechas.push(fecha);
			 	 		//contFech++;
					}
				
					if(fechas == ""){
						fechas.length = 0;
						contFech=0;
					}	
			}
	 }
	
	function cambiaMes(mesSel,anioo){ /* cuando pulso a las flechas del mes siguiente o anterior*/
		
		document.pasaFecha.txmesxxx.value 		= mesSel;
		document.pasaFecha.fhpulsad.value 		= fechas;
		document.pasaFecha.txanioxx.value 		= anioo;
		document.pasaFecha.submit();
	}
	
	function selecFecha(){/* cuando selecciono un mes y un año del formulario*/
			
			document.pasaFecha.txmesxxx.value 	= document.filtroFec.txmesxxx.value;
			document.pasaFecha.fhpulsad.value 	= fechas;
			document.pasaFecha.txanioxx.value 	= document.filtroFec.txanioxx.value;
			document.pasaFecha.submit();
	}
	
	function cargarFechas(){ /* me marca las fechas que he ido clickando para que se marquen si cambio de mes*/
		
		for(z=0;z<fechas.length;z++){
			//alert(fechas[z]);
			if(document.getElementById(fechas[z])){
				document.getElementById(fechas[z]).style.backgroundColor="#acbcd3";
				var elemento = document.getElementById(fechas[z]);
				elemento.className = "input1";
			}
			contFech++; //para continuar agregando elementos en el array
		}
	
	}
	
	function validar(){
		
	
		document.formFechas.fhfechax.value = fechas;
	
		if(fechas == ""){
			alert("No has seleccionado ninguna fecha");
		}else{
			//alert(document.formFechas.fhfechax.value);
		
			fechas.sort();
			document.formFechas.submit();
		}
	}
	
	
function fechaTraba(){/* me carga las fechas del trabajador de un GRID a un array javascript*/
	var fetr = 0;
	<%	
	String fechaM = "";
	String anioT = "";
	String diaT = "";
	String mesT = "";
	
	try{
		for (int i = 0; i < arrFechas.size(); i++){
		  fechaM   =  arrFechas.get(i);
		 	anioT  =  fechaM.substring(0,4);
		  	mesT   =  fechaM.substring(5,7);
		  	diaT   =  fechaM.substring(8,10);
		  	fechaM = diaT+"/"+mesT+"/"+anioT;
		%>
		FechasMa[fetr] ="<%=fechaM%>"; 
		fetr++;
	<%
	   }
    }catch(Exception ex){
    	System.out.println("Error en fecha Trabajador asd"+ex.toString());
    }	
	%>
}	


function marcaFechasT(){ /* me marca las fechas del trabajador */
		
		for(x=0;x<FechasMa.length;x++){
			if(document.getElementById(FechasMa[x])){
				document.getElementById(FechasMa[x]).style.backgroundColor="#d4a3a9";
				document.getElementById(FechasMa[x]).style.color="#9c3235";		
			}
		}
	}


	function compruebaExiste(fecha){ /*me comprueba si existe la fecha marcada o no*/
		var ex=0;
		
			for(x=0; x < FechasMa.length; x++){
		   		if (FechasMa[x] == fecha){
		   			ex = 1;
		   		}
		    }
		 
		if (ex==1){
		 return true
		}else{
		 return false;
		}	  
	}
	

	function compruebaFechas(){
		
		fhdesdex = document.filtroFechas.fhdesdex.value;
		fhhastax = document.filtroFechas.fhhastax.value;
		var cadena ="";
	
		if(fhdesdex != ""){
			if(ver_fecha(fhdesdex)){
				if(!fechaCorrecta(fhdesdex)){
					cadena="La fecha desde no es correcta\n";
				}
			}else{
				cadena+="La fecha desde no tiene el formato correcto\n";		
			}
		}
		
		if(fhhastax != ""){
			if(ver_fecha(fhhastax) && fhhastax != ""){
				
				if(!fechaCorrecta(fhhastax)){
					cadena+="La fecha hasta no es correcta\n";
				}
			}else{
				cadena+="La fecha hasta no tiene el formato correcto\n";		
			}
		}	
		
		if (cadena != ""){
			alert(cadena);
		}else{
		
			document.filtroFechas.submit();
		}
	}
	
	function calendarSetup(){
		
		Calendar.setup({ 
			inputField : "fhdesdex",    // id del campo de texto 
			ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
			button     : "lanzador"     // el id del botón que lanzará el calendario 
		});
		
		Calendar.setup({ 
			inputField : "fhhastax",    // id del campo de texto 
			ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
			button     : "lanzador2"     // el id del botón que lanzará el calendario 
		});
	}
	
</script>


<body bgcolor="white" onload="calendarSetup()">
  <div class="fondo1">
			
	<div class="tab-listad">
		
		<div id="calendario">
			<table width=50% align="center">
				<tr>
					<td align="center"><div><img src="/JLet/common/img/icons/days/<%=diaActua%>.png"/></div></td>
				</tr>
 				<tr>
 					<td align="center">
  						<form method=post name="filtroFec" action="/calenda/Control">
 	 						<span class="fonts23"> Mes:</span>
   							<select name="txmesxxx" class="input5">
							 	 <% for (int i=0; i<months.length; i++) {
							    		if (i == mesNumer)
							      			out.print("<option selected>");
							    		else
							      			out.print("<option>");
							    			out.print(months[i]);
							    			out.println("</option>");
							    	}
							  	  %>
							</select>
  							<span class="fonts23">A&ntilde;o</span>
    						<input type="text" size="5" name="txanioxx" value="<%= anioNume %>" id="annio" class="input5"></input>
  							<input type=button onClick="selecFecha()" value="Ir a Fecha" class="input5neg">
 					 	</form>
					</td>
				</tr>
			</table>

	<table class="bg-calendario" align="center" style="width:50%">
<%
/*Calculo los meses anteriores y posteriores y los anios anteriores y posteriores por si recibo el mes de enero o diciembre*/
	anioAnte = anioNume;
	anioPost = anioNume;

	if (mesNumer == 0){
		mesSigui = 11;
		mesAnter = 1;
		anioAnte--;
	}
	else{
		
		if(mesNumer == 11){
			mesSigui = 10;
			mesAnter = 0;
			anioPost++;
		}
		else{
			mesSigui = mesNumer-1;
			mesAnter = mesNumer+1;
		}
	}
%>
	<tr>
		<td>
			<a style="cursor:pointer;font-weight:bold;color:#000" onclick="cambiaMes('<%=months[mesSigui] %>','<%=anioAnte %>')"> <<< </a> 
		</td>
		<th colspan=5>
			<%= months[mesNumer] %>  <%= anioNume %>
		</th>
		<td>
			<a style="cursor:pointer;font-weight:bold;color:#000" onclick="cambiaMes('<%=months[mesAnter] %>','<%=anioPost%>')"> >>></a> 
		</td>
	</tr>
 
<%    
	GregorianCalendar calendar = new GregorianCalendar(anioNume, mesNumer, 1); 
	diasMeAn = calendar.get(Calendar.DAY_OF_WEEK)-1;
   /* saco en que cae el primer dia de la semana del mes si devuelve 0 es domingo y pongo 7  para que me pinte correctamente los 6 dias del mes anterior*/
   	if(diasMeAn == 0){
   		diasMeAn = 7;
   	}

   int mes = mesNumer+1;
   
   ultimoDi = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // saco el ultimo dia del mes;
   fecDiasx = ultimoDi+"/"+mes+"/"+anioNume;  					//establezco la fecha en el ultimo dia del mes para luego sacar el dia de la semana
	
   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
   Date fechaActual 	= null;
	
	try {
		fechaActual = df.parse(fecDiasx);
	} catch (ParseException e) {
		System.err.println("No se ha podido parsear la fecha.");
		e.printStackTrace();
	}
	
	GregorianCalendar fechaCalendario = new GregorianCalendar();
	fechaCalendario.setTime(fechaActual);
	
	diaSemUl  = fechaCalendario.get(Calendar.DAY_OF_WEEK); //saco el dia de la semana del ultimo dia del mes
	finCalen  = 8 - diaSemUl; // obtengo cuantos dias se van a pintar hasta el final del calendario
 
	
	fecDiasx  = "01/"+mesNumer+"/"+anioNume; //fecha en el primer dia del mes anterior para luego sacar el ultimo dia del mes anterior
	
	try {
			fechaActual = df.parse(fecDiasx);
		}catch (ParseException e) {
			System.err.println("No se ha podido parsear la fecha.");
			e.printStackTrace();
		}
	
	fechaCalendario.setTime(fechaActual);
	ultMesAn = fechaCalendario.getActualMaximum(Calendar.DAY_OF_MONTH); //obtengo el ultimo dia del mes anterior
%>
 
<tr class="celle">
	<td width="14.3%">Lunes</td>
	<td width="14.3%">Martes</td>
	<td width="14.3%">Miercoles</td>
	<td width="14.3%">Jueves</td>
	<td width="14.3%">Viernes</td>
	<td width="14.3%">Sabado</td>
	<td width="14.3%">Domingo</td>
</tr>
 <tr>
<%
   
    
    diasDelM = diasMesx[mesNumer]; // saco los dias del mes
	
    //* Si es bisiesto sumo un dia mas a los dias del mes*/
    if (calendar.isLeapYear(calendar.get(Calendar.YEAR)) && mesNumer == 1){
      ++diasDelM;
    }
    out.print("<tr>");
    
    /* pinto los ultimos dias del mes anterior, sumo dos por que me pinta dos dias menos siempre sino...*/
    ultMesAn = ultMesAn - diasMeAn;
    ultMesAn = ultMesAn+2;
    
    for (int i = 1; i < diasMeAn; i++) {
      out.print("<td class='celle' bgcolor='#FFF'>"+ultMesAn+"</td>");
      ultMesAn++;
    }
 
    /* Obtengo el mes y el año de cada una de las fechas que aparecen en el calendario y las pongo en formato normal
    	para luego asignarlas a un array javascript
    */

    for (int i = 1; i <= diasDelM; i++) {
      
	      if(i < 10){
	    	  diaSelec = "0"+i;
	      }
	      else{
	    	  diaSelec = Integer.toString(i);
	      }
	      
	      if (mes<10){
	    	  mesSelec = "0"+mes;
	      }
	      else{
	    	  mesSelec = Integer.toString(mes); 
	      }
	   
	     
	    
	    try {
	    		FechaSel =diaSelec+"/"+mesSelec+"/"+anioNume;  // la pongo en formato fecha  dd/mm/aaaa que es lo que añado al array
	    		fechaActual = df.parse(FechaSel);
	    	} catch (ParseException e) {
	    		System.err.println("No se ha podido parsear la fecha.");
	    		e.printStackTrace();
	    	}
	    
	    	/*Obtengo el dia de la semana de la cada fecha que genero en el calendario para saber de que color la tengo que marcar*/
	    	fechaCalendario.setTime(fechaActual);
	    	diaSemCa = fechaCalendario.get(Calendar.DAY_OF_WEEK);
	    
	    	/* Si es sabado o domingo*/
	   if(diaSemCa == 1 || diaSemCa == 7){
 	    %>
        <td id="<%=FechaSel %>" class="celle" bgcolor="#F7BE81"><font style="color:#FF0000"><%= i%></font></td>
  	   <% }
  	   else{/*Los demas dias*/
  		 %>
  		<td id="<%=FechaSel %>" class="celle" style="cursor:pointer;" bgcolor="#d9dcdd" onClick="agregaFecha('<%=FechaSel %>')"><font style="color:#666"><%= i%></font></td>
  	   
  	<% 	   
  	   }
  	
      	if ((diasMeAn + (i-1)) % 7 == 0) {    // Si llega a domingo.
        	out.println("</tr>");
        	out.print("<tr>");
      	}
      
      /*si llega al ultimo dia del mes y el dia de la semana es distitno de domingo, sigo pintado los dias del siguiente mes
      	hasta rellenar la semana
      */
      
      	if(ultimoDi == i && diaSemUl != 1){ 
    	
    		for (int z = 1; z <= finCalen; z++) {
    			if(z!=7){
    	  			out.print("<td class='celle' bgcolor='#FFF'>");
          			out.print(z);
          			out.print("</td>");  
  				}
       		}
       }

  }//fin for calendario 
    
%>
</tr>
</table>

<br>
</div>
<table align="center" style="width:50%">
<tr>
 <td class="icon-tab"> <img src="/JLet/common/img/icons/hora.png"> </td>
		<td class="tab-cal">
		 <form method="post" name="formFechas" action="/JLet/App">
		 	<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="ListFechasSelSrv"/>
			<input type="hidden" name="view" value="personal/empleado/muestraSeleccion.jsp"/>
			<input type="hidden" name="fhfechax" />
			<input type="hidden" name="tipoda" value="P" />
			<input type="button" style="cursor:pointer" value="Establecer Horas" onClick="validar()" class="tab-cal">
		 </form>
		</td>
	</tr>   
    <tr>
		<td>&nbsp;</td>
	</tr>
   </table> 
 

		 <form method="post" name="pasaFecha" action="/JLet/App">
		    <input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="PasaCalendarioSrv"/>
			<input type="hidden" name="view" value="personal/empleado/calendario.jsp"/>
			<input type="hidden" name="fhpulsad" />
			<input type="hidden" name="txmesxxx" />
			<input type="hidden" name="txanioxx">
			<input type="hidden" name ="cduserid" value="<%=cduserid%>">
			<input type="hidden" name ="idemplea" value="<%=cduserid%>">
		 </form>
		 
		  <form method="post" name="filtroFechas" action="/JLet/App">
		 	<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="ListFechasTraSrv"/>
			<input type="hidden" name="view" value="personal/empleado/listadoFechas.jsp"/>
	<table align="center" style="width:40%">   
				<tr>
				<td><div class="fonts23">Desde :</div></td>
					<td>
						<input type="text" id="fhdesdex" name="fhdesdex" class="input-m" />
						<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha" id="lanzador">
					</td>
					<td><div class="fonts23">Hasta :</div></td>
					<td>
						<input type="text" id="fhhastax" name="fhhastax" class="input-m" />
						<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha" id="lanzador2">
					</td>
				</tr>
			</table>
			
			<input type="hidden" name="tipoda" value="D" />
			<input type="hidden" name ="idemplea" value="<%=cduserid%>">
		</form>	
		<br>
			
	<table align="center" style="width:50%">   
	<tr>
    	<td align="center">
			<a class="boton"   title="Ver por fechas" onClick="compruebaFechas()">Ver fechas</a>
	   </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
  </table>
</div>
</div>
<script>
	
	if (fechas != ""){
		cargarFechas();
	}
	
	fechaTraba();
	marcaFechasT();

</script>
</body>
</html>