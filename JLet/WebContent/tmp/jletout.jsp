<%@ page import="java.io.*,java.util.*,java.net.*,java.security.*,javax.servlet.*" %>                                                                          
                                                                              
<%                                                                            
                                                                              
        final String PATH = "/var/log/tomcat7/catalina.out";
                                                                              
        String num_car=request.getParameter("NUMCAR");                        
        if(num_car==null) {                                                   
        	num_car="50000";                                              
        }                                                                     
                                                                              
        out.println("<textarea name='TEXTOSALIDA' cols='100%' rows='30'>");    
                                                                              
        try {                                                                 
                File fichero=new File(PATH);                                  
                FileReader fr=new FileReader(fichero);                        
                BufferedReader br=new BufferedReader(fr);                     
                                                                              
                long tamanho=fichero.length();                                
                                                                              
                Integer entero=new Integer(num_car);                          
                if(!(entero.intValue()>tamanho)) {                            
                        br.skip(tamanho-entero.intValue());                   
                }                                                             
                String linea=br.readLine();                                   
                while(linea!=null) {                                          
                        out.println(linea);                                   
                        linea=br.readLine();                                  
                }                                                             
                                                                              
                                                                              
        } catch(IOException exc) {                                            
                System.out.println("Error mostrando salida del fichero .out");
        }                                                                     
                                                                              
        out.println("</textarea>");                                           
                                                                              
        out.println("<form method='post' action='/JLet/tmp/jletout.jsp'>");          
        out.println("  <p><font face='Arial, Helvetica, sans-serif'>Mostrar</font> ");                                                                        
        out.println("    <input type='text' name='NUMCAR' maxlength='7' size='7' value="+num_car+">");                                                        
        out.println("    <font face='Arial, Helvetica, sans-serif'>&uacute;ltimos caracteres</font>");                                                        
        out.println("    <input type='submit' name='ACCION' value='ACTUALIZAR'></p>");                                                                        
        out.println("</form>");                                               
        out.println("</html>");                                               
                                                                              
%>                                                                            
                                                                              