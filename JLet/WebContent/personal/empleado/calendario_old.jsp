<%@page import="java.util.*,java.text.*,java.sql.ResultSet,arquitectura.objects.ObjectIO,arquitectura.objects.Grid" %>
 
<head>
  <title>Calendario Jsp - Modificado</title>
  <link rel="stylesheet" href="css/Hojacss.css" type="text/css" />
<link rel="stylesheet" href="gestion/css/Hojacss.css" type="text/css" />
<script src="gestion/js/validacionesComunes.js"></script>
<script src="js/validacionesComunes.js"></script>
</head>
<%

	System.out.println("FLAG 0");
	
	String cduserid	= "";
	Grid gridFech	= null;

	System.out.println("FLAG 1");

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			cduserid = io.getStringValue("cduserid");
			gridFech = io.getGrid("gridFech");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en calendario.jsp");	
		}
	}
	
	System.out.println("FLAG 2 numero de fechas "+gridFech.rowCount());
	
	//String[] arrFechas = new String[gridFech.rowCount()];
	
	ArrayList<String> arrFechas = new ArrayList<String>();
	
	try{
		for (int i = 0; i < gridFech.rowCount(); i++){
			arrFechas.add(gridFech.getStringCell(i,"fechatra"));
		}
		
	}catch(Exception ex){
	  	System.out.println("Excepcion al recorrer Grid fechas recibidas -----"+gridFech.getStringCell(50,"fechatra")+" --Error"+ex.toString());
	}
			
	System.out.println("FLAG 4asdas  ad asd " +gridFech.rowCount());

///?????????????????????????????

%>

<script type="text/javascript">
	var i=0;
	var fec=0;
	var fechas		= new Array();
	var FechasMar	= new Array();


<% 
 try{
	if( arrFechas!=null || !arrFechas.equals("")){
	
	for(int i=0;i<arrFechas.size();i++){
		%>
	fechas[fec] = "<%=arrFechas.get(i).toString() %>";
	fec++;
	<%	
 	}
	%>
	
	//cargarFechas();
<%	
	}
 }catch(Exception ex){
	 System.out.println("No recibe nada");
 }
	
%>

	function agregaFecha(fecha){
		
			if(!compruebaExiste(fecha)){
					pulsada = 0;
					document.getElementById(fecha).style.backgroundColor="#acbcd3";
					var elemento = document.getElementById(fecha);
					elemento.className = "input1";
					for(z=0;z<fechas.length;z++){
						if(fechas[z]==fecha){
							fechas.splice(z,1);
							document.getElementById(fecha).style.backgroundColor="#d9dcdd";
							pulsada = 1;
						}	
					}
				
					if(pulsada == 0){
			 	 		fechas[i]=fecha;
				 		i++;
					}
				
					if(fechas == ""){
						fechas.length = 0;
						i=0;
					}	
		}
	 }
	
	function cambiaMes(mesSel,anioo){
		
		document.pasaFecha.mes.value = mesSel;
		document.pasaFecha.fhfechax.value = fechas;
		document.pasaFecha.year.value = anioo;
		document.pasaFecha.posArr.value = fechas.length;
		document.pasaFecha.submit();
	}
	
	function selecFecha(){
			
			document.pasaFecha.mes.value = document.filtroFec.mes.value;
			document.pasaFecha.fhfechax.value = fechas;
			document.pasaFecha.year.value = document.filtroFec.year.value;
			document.pasaFecha.posArr.value = fechas.length;
			document.pasaFecha.submit();
	}
	
	function cargarFechas(){
		
		for(z=0;z<fechas.length;z++){
			//alert(fechas[z]);
			if(document.getElementById(fechas[z])){
				document.getElementById(fechas[z]).style.backgroundColor="#acbcd3";
				var elemento = document.getElementById(fechas[z]);
				elemento.className = "input1";
			}
			i++; //para continuar agregando elementos en el array
		}
	
	}
	
	function validar(){
	    
		for (x=0;x<fechas.length;x++){
			//alert(fechas[x]);
		}
		
		document.formFechas.fhfechax.value = fechas;
		
		
		if(fechas == ""){
			alert("No has seleccionado ninguna fecha");
		}
		else{
			//alert(document.formFechas.fhfechax.value);
			fechas.sort();
			document.formFechas.submit();
		}
	}
	
	
function fechaTraba(){
	var fetr = 0;
	<%	
	String fechaM = "";
	String anioT = "";
	String diaT = "";
	String mesT = "";
	
	try{
		for (int i = 0; i < gridFech.rowCount(); i++){
		  fechaM = gridFech.getStringCell(i,"fechatra");
		 	anioT  = fechaM.substring(0,4);
		  	mesT  =  fechaM.substring(5,7);
		  	diaT  =  fechaM.substring(8,10);
		  	fechaM = diaT+"/"+mesT+"/"+anioT;
		%>
		FechasMar[fetr] ="<%=fechaM%>"; 
		fetr++;
	<%
	   }
    }catch(Exception ex){
    	System.out.println("Error en fecha Trabajador asd"+ex.toString());
    }	
	%>
}	


function marcaFechasT(){
		
		for(x=0;x<FechasMar.length;x++){
			if(document.getElementById(FechasMar[x])){
				document.getElementById(FechasMar[x]).style.backgroundColor="#d4a3a9";
				document.getElementById(FechasMar[x]).style.color="#9c3235";		
			}
		}
	}


	function compruebaExiste(fecha){
		var ex=0;
		
			for(x=0;x<FechasMar.length;x++){
		   		if (FechasMar[x]==fecha){
		   			ex =1;
		   		}
		  }
		 
		if (ex==1){
		 return true
		}
		else{
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
			}
			
			else{
				cadena+="La fecha desde no tiene el formato correcto\n";		
			}
		}
		
		if(fhhastax != ""){
			if(ver_fecha(fhhastax) && fhhastax != ""){
				
				if(!fechaCorrecta(fhhastax)){
					cadena+="La fecha hasta no es correcta\n";
				}
			}
			else{
				cadena+="La fecha hasta no tiene el formato correcto\n";		
			}
		}	
		
		if (cadena != ""){
			alert(cadena);
		}
		else{
			document.filtroFechas.submit();
		}
		
		
	}
	
</script>

<body bgcolor="white">
<div class="fondo1">
<div class="testata"><img src="gestion/img/title-calendario.png"/></div>
<div class="centrado4">
<div id="calendario">
<table width=95% align="center">
<%  // First get the month and year from the form.
  boolean yyok = false;  // -1 is a valid year, use boolean
  int yy = 0, mm = 0;
  String yyString = request.getParameter("year");
  
 
  if (yyString != null && yyString.length(  ) > 0) {
    try {
      yy = Integer.parseInt(yyString);
      yyok = true;
    } catch (NumberFormatException e) {
      out.println("Year " + yyString + " invalid");
    }
  }
  Calendar c = Calendar.getInstance(  );
  if (!yyok)
    yy = c.get(Calendar.YEAR);
 
  String mmString = request.getParameter("mes");
  
  System.out.println("Recibido mes "+mmString);
  if (mmString == null) {
    mm = c.get(Calendar.MONTH);
  } else {
    for (int i=0; i<months.length; i++)
      if (months[i].equals(mmString)) {
        mm = i;
        System.out.println("Mes "+mm);
        break;
      }
  }
   String mesSigui = "";
  /*if(mm == 11){
	  mesSigui = months[mm];
	  mm = 0;
  }
  else{
	  mesSigui= months[mm+1]; 
  }*/
 
  	
%>
 <tr>
 <td align="center">
<form method=post name="filtroFec" action="/calenda/Control">
 <span class="fonts23"> Mes:</span>
   <select name="mes" class="input5">
  <% for (int i=0; i<months.length; i++) {
    if (i==mm)
      out.print("<option selected>");
    else
      out.print("<option>");
    out.print(months[i]);
    out.println("</option>");
  }
  %>
  </select>
 
  <span class="fonts23">A&ntilde;o</span>
    <input type="text" size="5" name="year"
      value="<%= yy %>" id="annio" class="input5"></input>
  <input type=button onClick="selecFecha()" value="Ir a Fecha" class="input5neg">
</form>

</td>
</tr>
</table>
<%!
  /** The names of the months */
  String[] months = {
    "Enero", "Febrero", "Marzo", "Abril",
    "Mayo", "Junio", "Julio", "Agosto",
    "Septiembre", "Octubre", "Noviembre", "Diciembre"
  };
 
  /** The days in each month. */
  int dom[] = {
      31, 28, 31, 30,  /* jan feb mar apr */
      31, 30, 31, 31, /* may jun jul aug */
      30, 31, 30, 31  /* sep oct nov dec */
  };

  /** The number of days to leave blank at the start of this month */
  int diasMesAnt = 0;
  int diasMesPost = 0;
  
%>
<table bordercolor="#FFFFFF" class="bg-calendario">
<%
int mesSiguiente = 0;
int mesAnt = 0;
int anioMen = yy;
int anioMas = yy;

	if (mm==0){
		mesSiguiente=11;
		mesAnt = 1;
		anioMen--;
	}
	else{
		
		if(mm==11){
			mesSiguiente=10;
			mesAnt = 0;
			anioMas++;
		}
		else{
			mesSiguiente = mm-1;
			mesAnt =mm+1;
		}
		
	}
%>
<tr><td><a style="cursor:pointer" onclick="cambiaMes('<%=months[mesSiguiente] %>','<%=anioMen %>')"> <<< <a/> </td><th colspan=5><%= months[mm] %>  <%= yy %></th><td><a style="cursor:pointer" onclick="cambiaMes('<%=months[mesAnt] %>','<%=anioMas%>')"> >>><a/> </td></tr>
 
<%    GregorianCalendar calendar = new GregorianCalendar(yy, mm, 1); 
	 
   diasMesAnt = calendar.get(Calendar.DAY_OF_WEEK)-1;
   if(diasMesAnt==0){
   	diasMesAnt=7;
   }
	//diasMesAnt=7;
	System.out.println("diames"+diasMesAnt);
   int mes =mm+1;
   int ultimo = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
   String fecha = ultimo+"/"+mes+"/"+yy;
	
   SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
   Date fechaActual = null;
	
	try {
	fechaActual = df.parse(fecha);
	} catch (ParseException e) {
	System.err.println("No se ha podido parsear la fecha.");
	e.printStackTrace();
	}
	GregorianCalendar fechaCalendario = new GregorianCalendar();
	fechaCalendario.setTime(fechaActual);
	
	int diaSemana = fechaCalendario.get(Calendar.DAY_OF_WEEK);
	int finCalen  = 7 - diaSemana; 
	//mes = mes-1;
	fecha = "01/"+mm+"/"+yy;
	
	try {
		fechaActual = df.parse(fecha);
		} catch (ParseException e) {
		System.err.println("No se ha podido parsear la fecha.");
		e.printStackTrace();
		}
	
	fechaCalendario.setTime(fechaActual);
	int ultimoMesAn = fechaCalendario.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	System.out.println(fecha+"Dia Semana="+diaSemana);
	System.out.println(fecha+"Ultime mes="+diasMesAnt);
	
	Date hoy = new Date();
	
		if(diasMesAnt == 0){
			diasMesAnt = 1;
		}


%>
 
<tr class="celle"><td width="14.3%">Lunes</td><td width="14.3%">Martes</td><td width="14.3%">Miercoles</td><td width="14.3%">Jueves</td><td width="14.3%">Viernes</td><td width="14.3%">Sabado</td><td width="14.3%">Domingo</td></tr>
 
<%
   
    
    int daysInMonth = dom[mm];
	int diaS=0;
    if (calendar.isLeapYear(calendar.get(Calendar.YEAR)) && mm == 1)
      ++daysInMonth;
 
    out.print("<tr>");
    
    ultimoMesAn = ultimoMesAn - diasMesAnt;
    ultimoMesAn = ultimoMesAn+2;
    // Blank out the labels before 1st day of month
    int x = 0;
    for (int i = 1; i < diasMesAnt; i++) {
      out.print("<td>"+ultimoMesAn+"");
      ultimoMesAn++;
    }
 
    // Fill in numbers for the day of month.
    String FechaSel ="";
	String  dia="";
	String  mesSel="";
    for (int i = 1; i <= daysInMonth; i++) {
      
	      if(i < 10){
	         dia = "0"+i;
	      }
	      else{
	    	  dia = Integer.toString(i);
	      }
	      
	      if (mes<10){
	    	  mesSel = "0"+mes;
	      }
	      else{
	    	  mesSel = Integer.toString(mes); 
	      }
	    FechaSel =dia+"/"+mesSel+"/"+yy; 
	     
	    try {
	    	fechaActual = df.parse(FechaSel);
	    	} catch (ParseException e) {
	    	System.err.println("No se ha podido parsear la fecha.");
	    	e.printStackTrace();
	    	}
	    	fechaCalendario.setTime(fechaActual);
	    	diaS = fechaCalendario.get(Calendar.DAY_OF_WEEK);
	    
	   if(diaS ==1 || diaS ==7){
 	    %>
        <td id="<%=FechaSel %>" class="celle" bgcolor="#F7BE81"><font style="color:#FF0000"><%= i%></font></td>
  	   <% }
  	   else{
  		 %>
  		<td id="<%=FechaSel %>" class="celle" style="cursor:pointer;" bgcolor="#d9dcdd" onClick="agregaFecha('<%=FechaSel %>')"><font style="color:#666"><%= i%></font></td>
  	   
  	<% 	   
  	   }
  	
      if ((diasMesAnt + x) % 7 == 0) {    // wrap if end of line.
        out.println("</tr>");
        out.print("<tr>");
      }
      
      if(ultimo == i && diaSemana != 1){
    	  finCalen++; 
    	
    	for (int z = 1; z <= finCalen; z++) {
    		if(z!=7){
    	  		out.print("<td>");
          		out.print(z);
          		out.print("</td>");  
  			}
       	}
     }
      x++;
  } 
    
%>
</tr>
</table>
</table>
<br>
</div>
<table width=70% align="center" style="width:100%">
<tr>
 <td class="icon-tab"> <img src="gestion/img/hora.png"> </td>
		<td class="tab-cal">
		 <form method="post" name="formFechas" action="/GSTNTRA/Control">
			<input type="hidden" name="fhfechax" />
	
			<input type="hidden" name="tipoda" value="P" />
			<input type="button" value="Establecer Horas" onClick="validar()" class="tab-cal">
		 </form>
		</td>
	</tr>   
    <tr>
		<td>&nbsp;</td>
	</tr>
   </table> 
 <table width=70% align="center" style="width:100%">   
	<tr>
    <td>
		 <form method="post" name="filtroFechas" action="/GSTNTRA/Control">
			<input type="text" name="fhdesdex" class="input4b" />
			<input type="text" name="fhhastax" class="input4b" />
			<input type="hidden" name="tipoda" value="D" />
			<input type="button"  title="Ver por fechas" onClick="compruebaFechas()" class="icon-tab2">
		</form>
	   </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
  </table>

		 <form method="post" name="pasaFecha" action="/GSTNTRA/Control">
			<input type="hidden" name="fhfechax" />
			<input type="hidden" name="mes" />
			<input type="hidden" name="year">
			<input type="hidden" name="posArr" />
			<input type="hidden" name="tipoda" value="SIG" />
	
		 </form>
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