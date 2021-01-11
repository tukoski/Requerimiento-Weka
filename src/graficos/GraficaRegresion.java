package graficos;

import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import punto.Punto;

public class GraficaRegresion {
    
    private Vector datos; 
    private double[] coef; 
    
    public GraficaRegresion(Vector datos, double coef[]){
        this.datos = datos; 
        this.coef = coef; 
    }
    
    public ChartPanel generarGrafica(){
        XYDataset dataset = crearDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Regresión Temperatura",
            "Hora",
            "Temperaruta",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            false,
            false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);
        ChartPanel cp = new ChartPanel(chart);
        return cp; 
    }
    
    public XYDataset crearDataset(){
        XYSeries series1 = new XYSeries("Temperatura");
        Punto temp; 
        for (int i = 0; i < datos.size(); i++) {
            temp=(Punto)datos.get(i);
            series1.add(temp.getHora(), temp.getTemperatura());
        }
        XYSeries series2 = new XYSeries("Regresión");
        for (int i = 0; i < datos.size(); i++) {
            temp=(Punto)datos.get(i);
            double pred=coef[0]*temp.getHora()+coef[2];
            series2.add(temp.getHora(), pred);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }
    
}
