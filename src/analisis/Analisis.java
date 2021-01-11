package analisis;

import java.util.Vector;
import punto.Punto;
import weka.classifiers.functions.LinearRegression;
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
    
}
