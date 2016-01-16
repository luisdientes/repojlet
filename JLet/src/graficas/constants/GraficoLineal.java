package graficas.constants;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;

import utils.PropiedadesJLet;
 
public class GraficoLineal {
 
	String idemisor = "1";
	public Color[] COLORS;
	
    //private static Color COLOR_RECUADROS_GRAFICA = new Color(31, 87, 4);
	private static Color COLOR_RECUADROS_GRAFICA = new Color(64, 64, 64);
 
    private static Color COLOR_FONDO_GRAFICA = Color.white;
 
    double initiRan = 0;
    double finalRan = 100000;
    double tramoRan = 10;
    
    int web_anchopx = 900;
    int web_altoxpx = 600;
    
    public String crearGrafica(String nombreGraf, String tituGraf, TimeSeriesCollection dataset,String ejeYtitulo) {
    	
    	String filename = "";
		String imagenam = "";
    	
    	String[] subcolor = PropiedadesJLet.getInstance().getProperty("colors."+ idemisor +".orden0").split(",");
    	String color_r = subcolor[0];
   		String color_g = subcolor[1];
   		String color_b = subcolor[2];
    	
   		setCorporateColors();
   		
    	String pathimgx = PropiedadesJLet.getInstance().getProperty("path.img.statistics");
    	Logger.getLogger("LRA").fine("[JEJ FLAG] PATH IMAGEN GraficoLineal "+ pathimgx +" - (path.img.statistics)");
		
		String forfecha = String.valueOf(System.currentTimeMillis());
		
		imagenam = forfecha + "_Lineal.png";
		
		JFreeChart graficoLineal = ChartFactory.createTimeSeriesChart(
				tituGraf,  			// title
	            "Fecha",            // x-axis label
	            ejeYtitulo,   		// y-axis label
	            dataset,            // data
	            true,               // create legend?
	            true,               // generate tooltips?
	            false               // generate URLs?
	        );
		
        // color de fondo de la gráfica
        graficoLineal.setBackgroundPaint(COLOR_FONDO_GRAFICA);
        graficoLineal.getTitle().setPaint(new Color(Integer.parseInt(color_r),Integer.parseInt(color_g),Integer.parseInt(color_b)));
                
        final XYPlot plot = (XYPlot) graficoLineal.getPlot();
        configurarPlot(plot);
        
        //plot.setDomainGridlinesVisible(false);
        //plot.setRangeGridlinesVisible(false);
        
        
        //ImageIcon imageicon = new ImageIcon("C:\\Users\\xIS09894\\Pictures\\demo.png");
        //plot.setBackgroundImage(imageicon.getImage());
        //plot.setBackgroundImageAlignment(Align.LEFT);

        //plot.setBackgroundPaint(new GradientPaint(0, 0, new Color(Integer.parseInt(color_r),Integer.parseInt(color_g),Integer.parseInt(color_b)), 0, 1000, Color.white)); 
        plot.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.white));
        
        /*
        final NumberAxis domainAxis = (NumberAxis)plot.getDomainAxis();
        configurarDomainAxis(domainAxis);
        */
        
        final NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
        configurarRangeAxis(rangeAxis);
 
        final XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
        configurarRendered(renderer);
 
        
        filename = pathimgx+imagenam;
        File fichero = new File(filename);
		String formato = "png";
		BufferedImage image = graficoLineal.createBufferedImage(web_anchopx,web_altoxpx);
        
		try {
			ImageIO.write(image, formato, fichero);
		} catch (IOException e) {
			Logger.getLogger("LRA").warning(this.getClass().getName() +"Error de escritura");
		}
		
        return imagenam;
    }
     
    public void asignarRango(double iniciora, double finalran, double tramoran){
    	this.initiRan = iniciora;
    	this.finalRan = finalran;
    	this.tramoRan = tramoran;
    }
    
    public void asignarTamanoWeb(int ancho, int alto){
    	this.web_anchopx = ancho;
    	this.web_altoxpx = alto;
    }
    
    // configuramos el contenido del gráfico (damos un color a las líneas que sirven de guía)
    private void configurarPlot (XYPlot plot) {
        plot.setDomainGridlinePaint(COLOR_RECUADROS_GRAFICA);
        plot.setRangeGridlinePaint(COLOR_RECUADROS_GRAFICA);
    }
     
    // configuramos el eje X de la gráfica (se muestran números enteros y de uno en uno)
    private void configurarDomainAxis (NumberAxis domainAxis) {
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        domainAxis.setTickUnit(new NumberTickUnit(1));
    }
     
    // configuramos el eje y de la gráfica (números enteros de dos en dos y rango entre 120 y 135)
    private void configurarRangeAxis (NumberAxis rangeAxis) {
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickUnit(new NumberTickUnit(tramoRan));
        rangeAxis.setRange(initiRan, finalRan);
    }
    
    // configuramos las líneas de las series (añadimos un círculo en los puntos y asignamos el color de cada serie)
    private void configurarRendered (XYLineAndShapeRenderer renderer) {
        /*
    	renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesShapesVisible(2, true);
        */
        for (int i = 0; i < COLORS.length; i++){
        	renderer.setSeriesShapesVisible(i, true);
	        renderer.setSeriesPaint(i, COLORS[i]);
        }
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


    public void setEmisor(String idemisor) {
        
    	this.idemisor = idemisor;
    	
    }
}