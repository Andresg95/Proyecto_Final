import java.io.IOException;

import Grafo_Estaciones.GrafoEstaciones;
import WindowSearch.VentanaBusqueda;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GrafoEstaciones grafo=new GrafoEstaciones();
		grafo.CrearNodoEstacion();
		VentanaBusqueda ventana = new VentanaBusqueda(grafo.getEstacionesName());
		ventana.setVisible(true);
	}

}
