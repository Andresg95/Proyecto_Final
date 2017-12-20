package Grafo_Estaciones;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.SingleGraph;

public class GrafoEstaciones {
	public void CrearGrafo() throws IOException {
		ArrayList<Estacion> list = new ArrayList<Estacion>();
		BufferedReader br =null;
		Estacion estacion = null;
		Graph graph = new SingleGraph("Grafo");
		try {
		 br=new BufferedReader(new FileReader("/Proyecto_Metro_Madrid/src/DataCSV/M4_Estaciones.csv"));
		String line = br.readLine();
	    do {
		       String [] fields = line.split(",");
		       System.out.println(Arrays.toString(fields));
		       //Creacion el objeto Estacion
		       if(fields.length>3) {
			       estacion = new Estacion(Integer.parseInt(fields[0]), fields[1], Integer.parseInt(fields[3]),Integer.parseInt(fields[4]));
			       list.add(estacion);
			       graph.addNode(fields[0]);
		       }
		       
		       }while((line = br.readLine()) != null);
		    } catch (Exception e) {
		    e.printStackTrace();
		 } finally {
		    if (br!=null) {
		       br.close();
		    }
		 }
		for (Iterator<Estacion> iterator = list.iterator(); iterator.hasNext();) {
			Estacion e = iterator.next();
			System.out.println(e.getCodigo()+" estacion: "+e.getNombre());
		
		}//Aqui se conectaran los nodos o Estaciones
	}
}
