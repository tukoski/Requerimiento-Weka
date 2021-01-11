package graficos;

import java.util.Arrays;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.editor.ChartEditorFactory;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYIntervalSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import punto.PuntoDispersion;

public class Dispersion {
    
    Vector datos; 
    
    public Dispersion(Vector datos){
        this.datos=datos;
    }
    
    
    public ChartPanel generarGraficaTvsH(){
        
        XYDataset dataset=crearDataset();
        JFreeChart chart=ChartFactory.createScatterPlot("Temp vs Humedad","Temperatura", "Humedad", dataset);
        ChartPanel cp=new ChartPanel(chart);
        return cp; 
    }
    
    public ChartPanel generarGraficaTvsVV(){
        
        XYDataset dataset=crearDataset();
        JFreeChart chart=ChartFactory.createScatterPlot("Temp vs Vel. Viento","Temperatura", "Velocidad de Viento", dataset);
        ChartPanel cp=new ChartPanel(chart);
        return cp; 
    }
    public XYDataset crearDataset(){
        XYSeriesCollection dataset=new XYSeriesCollection();
        XYSeries c1=new XYSeries("C1");
        XYSeries c2=new XYSeries("C2");
        XYSeries c3=new XYSeries("C3");
        
        PuntoDispersion punto;
        for (int i = 0; i < datos.size(); i++) {
            punto = (PuntoDispersion) datos.get(i);
            if(punto.getCluster() == 0){
                c1.add(punto.getValueX(),punto.getValueY());
            }
            else if(punto.getCluster() == 1){
                c2.add(punto.getValueX(),punto.getValueY());
            }
            else {
                c3.add(punto.getValueX(),punto.getValueY());
            }
        }
        dataset.addSeries(c1);
        dataset.addSeries(c2);
        dataset.addSeries(c3);
        return dataset; 
    }
    
}
