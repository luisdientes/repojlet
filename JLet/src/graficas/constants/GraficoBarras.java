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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import utils.PropiedadesJLet;
 
public class GraficoBarras {
 
	String idemisor = "1";
	public Color[] COLORS;
	
    //private static Color COLOR_RECUADROS_GRAFICA = new Color(31, 87, 4);
	private static Color COLOR_RECUADROS_GRAFICA = new Color(64, 64, 64);
 
    private static Color COLOR_FONDO_GRAFICA = Color.white;
 
    double initiRan = 0;
    double finalRan = 100000;
    double tramoRan = 10;
    
    int web_anchopx = 600;
    int web_altoxpx = 400;
    
    String[] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
    		"V", "W", "X", "Y", "Z" };
    
    public String crearGrafica(String nombreGraf, String tituGraf, DefaultCategoryDataset dataset,String ejeYtitulo) {
    	
    	String filename = "";
		String imagenam = "";
    	
		if (COLORS == null){
			setDefaultColors();
		}
		
   		
    	String pathimgx = PropiedadesJLet.getInstance().getProperty("path.img.statistics");
    	Logger.getLogger("LRA").fine("[JEJ FLAG] PATH IMAGEN GraficoLineal "+ pathimgx +" - (path.img.statistics)");
		
		String strdifer = abecedario[(int) Math.round(Math.random() * 25)];
		strdifer+= abecedario[(int) Math.round(Math.random() * 25)];
		strdifer+= abecedario[(int) Math.round(Math.random() * 25)];
		
		imagenam = tituGraf.replaceAll(" ","") +"_"+ idemisor +"_Lineal"+ strdifer +".png";
		
		JFreeChart graficoBarras = ChartFactory.createBarChart3D(
				tituGraf,  					// title
	            "Fecha",            		// x-axis label
	            ejeYtitulo,   				// y-axis label
	            dataset,           			// data
	            PlotOrientation.VERTICAL,	// orientation
	            false,             			// create legend?
	            false,               		// generate tooltips?
	            false               		// generate URLs?
	        );
		
		
		
        // color de fondo de la gráfica
		graficoBarras.setBackgroundPaint(COLOR_FONDO_GRAFICA);
		graficoBarras.getTitle().setPaint(COLORS[0]);
		
        final CategoryPlot plot = (CategoryPlot) graficoBarras.getPlot();
        configurarPlot(plot);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        BarRenderer render = (BarRenderer)plot.getRenderer();
        render.setSeriesPaint(0, COLORS[0]);
        render.setSeriesPaint(1, COLORS[1]);
        
        
        //plot.setDomainGridlinesVisible(false);
        //plot.setRangeGridlinesVisible(false);
        
        
        //ImageIcon imageicon = new ImageIcon("C:\\Users\\xIS09894\\Pictures\\demo.png");
        //plot.setBackgroundImage(imageicon.getImage());
        //plot.setBackgroundImageAlignment(Align.LEFT);

        //plot.setBackgroundPaint(new GradientPaint(0, 0, new Color(Integer.parseInt(color_r),Integer.parseInt(color_g),Integer.parseInt(color_b)), 0, 1000, Color.white)); 
        plot.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.white));
        
        filename = pathimgx+imagenam;
        File fichero = new File(filename);
		String formato = "png";
		BufferedImage image = graficoBarras.createBufferedImage(web_anchopx,web_altoxpx);
        
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
    private void configurarPlot (CategoryPlot plot) {
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
    private void configurarRendered (BarRenderer  renderer) {
    	
        for (int i = 0; i < COLORS.length; i++){
        	renderer.setSeriesPaint(i, COLORS[i]);
        }
    }
    

    public void setCorporateColors(Color[] colores){
        
    	COLORS = colores;
    	
    }
 
    
    public void setDefaultColors(){
    	
    	COLORS = new Color[6];
    	
    	COLORS[0] = new Color(0,0,160);
    	COLORS[1] = new Color(230,230,0);
    	COLORS[2] = new Color(230,230,230);
    	COLORS[3] = new Color(0,230,230);
    	COLORS[4] = new Color(0,0,230);
    	COLORS[5] = new Color(230,0,230);
    	
    }

	
    public void setEmisor(String company) {
        
    	this.idemisor = idemisor;
    	
    }
}