package graficas.constants;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import utils.PropiedadesJLet;

public class GraficoStackedBar {
    
	String idemisor = "1";
	public Color[] COLORS;
	
    double initiRan = 0;
    double finalRan = 100;
    double tramoRan = 10;
	
    String ejeVertica = "% Capital";
    String ejeLateral = "Fechas";
    
	int web_anchopx = 900;
    int web_altoxpx = 600;

    public String crearGrafica(String nombreGraf, String tituGraf, CategoryDataset dataset){
		
		String filename = "";
		String imagenam = "";		
		String prefijox = "";
		
		String[] subcolor = PropiedadesJLet.getInstance().getProperty("colors."+ idemisor +".orden0").split(",");
    	String color_r = subcolor[0];
   		String color_g = subcolor[1];
   		String color_b = subcolor[2];
		
   		setCorporateColors();
   		
   		String pathimgx = PropiedadesJLet.getInstance().getProperty("path.img.statistics");
   		Logger.getLogger("LRA").fine("[JEJ FLAG] PATH IMAGEN GraficoLineal "+ pathimgx +" - (path.img.statistics)");
		
		String forfecha = String.valueOf(System.currentTimeMillis());
		imagenam = prefijox + "_" + forfecha + "_"+ nombreGraf +".png";
		
        final JFreeChart graficoStacked = ChartFactory.createStackedBarChart(tituGraf, 
        								ejeLateral,						// x-axis label
        								ejeVertica,						// y-axis label
        								dataset,						// data
        								PlotOrientation.VERTICAL,		// type orientation
        								true,							// create legend?
        								true,							// generate tooltips?
        								false); 						// generate URLs?
        		
        graficoStacked.getTitle().setPaint(new Color(Integer.parseInt(color_r),Integer.parseInt(color_g),Integer.parseInt(color_b)));
        graficoStacked.setBackgroundPaint(new Color(255, 255, 255));              
        CategoryPlot plot = graficoStacked.getCategoryPlot();
        plot.setBackgroundPaint(new Color(255, 255, 255));
        
        final NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        configurarRangeAxis(rangeAxis);
        
        for (int i = 0; i < COLORS.length; i++){
        	plot.getRenderer().setSeriesPaint(i, COLORS[i]);
        }
            
        filename = pathimgx+imagenam;
        File fichero = new File(filename);
		String formato = "png";
		
		BufferedImage image = graficoStacked.createBufferedImage(web_anchopx,web_altoxpx);
        
		try {
			ImageIO.write(image, formato, fichero);
		} catch (IOException e) {
			Logger.getLogger("LRA").warning(this.getClass().getName() +" Error de escritura");
		}
		
		return imagenam;
    }

    private void configurarRangeAxis (NumberAxis rangeAxis) {
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        if (finalRan != 0){
        	rangeAxis.setTickUnit(new NumberTickUnit(tramoRan));
        	rangeAxis.setRange(initiRan, finalRan);
        }
    }
    
    public void setFinalRan(double ranFinal){
    	finalRan = ranFinal;
    }
    
    public void setNameEjeVertical(String txnombre){
    	ejeVertica = txnombre;
    }
	
    public void setNameEjeLateral(String txnombre){
    	ejeLateral = txnombre;
    }
    
	public void setCorporateColors(){
        
		COLORS = new Color[9];
        
        int i = 0;
        while ((PropiedadesJLet.getInstance().getProperty("colors."+ idemisor +".orden"+i) != null) && (!PropiedadesJLet.getInstance().getProperty("colors."+ idemisor +".orden"+i).equals("")) && (i <= 8)){
        	String[] subcolor = PropiedadesJLet.getInstance().getProperty("colors."+ idemisor +".orden"+i).split(",");
        	String color_r = subcolor[0];
       		String color_g = subcolor[1];
       		String color_b = subcolor[2];       			
        	
        	COLORS[i] = new Color(Integer.parseInt(color_r),Integer.parseInt(color_g),Integer.parseInt(color_b));
        	i++;
        }
        
    }


    public void setEmisor(String company) {
        
    	this.idemisor = idemisor;
    	
    }
    
}