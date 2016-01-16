package graficas.constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;

import utils.PropiedadesJLet;
import arquitectura.objects.Grid;

public class GraficoCircular {

	String idemisor = "1";
	
	String[] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
    		"V", "W", "X", "Y", "Z" };
	
	public Color[] COLORS;
	public HashMap<String,Color> hmColors = null;
	
	public String crearGrafica(String nombreGraf, String tituGraf, Grid gDatos){
		
		/*
		String[] subcolor = PropiedadesJLet.getInstance().getProperty("colors."+ idemisor +".orden0").split(",");
    	String color_r = subcolor[0];
   		String color_g = subcolor[1];
   		String color_b = subcolor[2];
		*/
   		//setCorporateColors();
   		
		String pathimgx = PropiedadesJLet.getInstance().getProperty("path.img.statistics");
		
		String filename = "";
		String imagenam = "";		
		String prefijox = "";
    	
    	String strdifer = abecedario[(int) Math.round(Math.random() * 25)];
		strdifer+= abecedario[(int) Math.round(Math.random() * 25)];
		strdifer+= abecedario[(int) Math.round(Math.random() * 25)];
		
		imagenam = tituGraf.replaceAll(" ","") +"_"+ idemisor +"_Lineal"+ strdifer +".png";
    	
		//Datos del gráfico 
        DefaultPieDataset datos = new DefaultPieDataset();
        
        for (int i=0; i < gDatos.rowCount(); i++){
        	double numData = Double.valueOf(gDatos.getStringCell(i, 1)).doubleValue();
        	datos.setValue(gDatos.getStringCell(i, 0),numData);
        }
        
        try {
        	
        	JFreeChart graficoPie = ChartFactory.createPieChart("", //título del gráfico 
	                           datos,            //datos 
	                           true,             //mostrar leyendas 
	                           true,             //mostrar tooltips 
	                           false);           //Locale, sin gran importancia
	        
        	//graficoPie.getTitle().setPaint(new Color(Integer.parseInt(color_r),Integer.parseInt(color_g),Integer.parseInt(color_b)));
        	PiePlot plot = (PiePlot)graficoPie.getPlot();
		    //Color de las etiquetas	    
		    plot.setLabelGenerator(null);
		    //Color de el fondo del gráfico
		    plot.setBackgroundPaint(Color.WHITE);		    
		    plot.setNoDataMessage("No hay data");
		    int nCampos = gDatos.rowCount();
		    String [] categories = new String[nCampos];
		    
		    for (int i = 0; i < gDatos.rowCount(); i++){
		    	categories[i] = gDatos.getStringCell(i, 0);
		    }
		    
		    if (hmColors != null) {
		    	colorCategoryChart(plot);
		    } else {
		    	colorChart(plot,categories);
		    }
		    
		    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
	        simbolo.setDecimalSeparator('.');
	        simbolo.setGroupingSeparator(',');
		    

	        final double tamanhoLabel=0.10;	
	        
	        
	        plot.setOutlineVisible(false);					//Recuadro
	        StandardPieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{2}",new DecimalFormat("0"), new DecimalFormat("0.00%",simbolo));	//0 - Nombre ; 1 - Valor ; 2 - Porcentaje
	        plot.setLabelBackgroundPaint(new Color(255,255,255));
	        plot.setLabelLinksVisible(true);
	        plot.setLabelShadowPaint(null);	        
	        plot.setLabelGenerator(labelGenerator);
	        plot.setMaximumLabelWidth(tamanhoLabel);
	        
	        plot.setLabelFont(new java.awt.Font("Helvetica",Font.BOLD,8));
	        plot.setLabelOutlinePaint(null);
	        //plot.setSeparatorPaint(new GradientPaint(5.0f,10.0f,Color.white,5.0f,10.0f,Color.white));				//Color Separador
	        //plot.setInnerSeparatorExtension(0.1);
		    
		    filename = pathimgx+imagenam;
	        File fichero = new File(filename);
	        File ficheroBig = new File(filename.replaceAll(".png", "_480x480.png"));
			String formato = "png";
			BufferedImage image = graficoPie.createBufferedImage(300,300);
			BufferedImage imageBig = graficoPie.createBufferedImage(480,480);
			// Escribimos la imagen en el archivo.
			try {
				ImageIO.write(image, formato, fichero);
				ImageIO.write(imageBig, formato, ficheroBig);
			} catch (IOException e) {
				Logger.getLogger("LRA").warning(this.getClass().getName() +" Error de escritura");
			}
			
        } catch (Exception e){
        	e.printStackTrace();
        } 
        return imagenam;
    }
    
    
	public void setCorporateColors(Color[] colores){
        
		COLORS = colores;
        
    }
   
	public void setCategoryColors(HashMap<String,Color> catColor){
        
		hmColors = catColor;
        
    }
	
    public void colorChart( Plot plot, String[] categories ) {
        if ( categories.length > COLORS.length ) {
            // More categories than colors. Do something!
            return;
        }

        // Use the standard colors as a list so we can shuffle it and get 
        // a different order each time.
        List myColors = Arrays.asList( COLORS );
        //Collections.shuffle( myColors );

        for ( int i = 0; i < categories.length; i++ ) {
            ((PiePlot) plot).setSectionPaint( categories[i], (Paint) myColors.get( i ) );
        }
    }
    
    public void colorCategoryChart( Plot plot) {
    	
    	for ( String key : hmColors.keySet() ) {
    		((PiePlot) plot).setSectionPaint( key, (Paint) hmColors.get(key) );
    	}	
        	
    }
    
    public void setEmisor(String idemisor) {
        
    	this.idemisor = idemisor;
    	
    }
	
	
}
