package analisis;

import java.util.Vector;
import punto.Punto;
import punto.PuntoDispersion;
import weka.classifiers.functions.LinearRegression;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class Analisis {

    private Vector datos; 
    
    public Analisis(Vector datos){
        this.datos = datos; 
    }
    
    public LinearRegression regresion() throws Exception{
        FastVector fv = new FastVector(2);
        fv.addElement(new Attribute("Hora"));
        fv.addElement(new Attribute("Temperatura"));
        Instances dataset = new Instances("dataset",fv,datos.size());
        dataset.setClassIndex(dataset.numAttributes()-1);
        
        for (int i = 0; i < datos.size(); i++) {
            Instance instance = new Instance(2);
            Punto temp=(Punto)datos.get(i);
            instance.setValue((Attribute)fv.elementAt(0),temp.getHora());
            instance.setValue((Attribute)fv.elementAt(1), temp.getTemperatura());
            dataset.add(instance);
        }
        LinearRegression lr = new LinearRegression();
        lr.buildClassifier(dataset);
        return lr; 
    }
    
    public SimpleKMeans clustering() throws Exception{
        FastVector fv = new FastVector(2);
        fv.addElement(new Attribute("X"));
        fv.addElement(new Attribute("Y"));
        Instances dataset = new Instances("dataset",fv,datos.size());
        
        PuntoDispersion punto;
        for (int i = 0; i < datos.size(); i++) {            
            punto=(PuntoDispersion)datos.get(i);
            Instance instance = new Instance(2);
            instance.setValue((Attribute)fv.elementAt(0), punto.getValueX());
            instance.setValue((Attribute)fv.elementAt(1), punto.getValueY());
            dataset.add(instance);
        }
        
        SimpleKMeans skm = new SimpleKMeans();
        skm.setNumClusters(3);         
        skm.setPreserveInstancesOrder(true);
        skm.buildClusterer(dataset);
        int arr[] = skm.getAssignments();
        
        PuntoDispersion puntoAux;
        for (int i = 0; i < datos.size(); i++) {            
            puntoAux = (PuntoDispersion)datos.get(i);
            puntoAux.setCluster(arr[i]);
        }
        //Minuto 48:00
        return skm;
    }
    
}
