package WindowSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import Grafo_Estaciones.GrafoEstaciones;


public class VentanaBusqueda extends JFrame implements ActionListener{

	private static final long serialVersionUID = 3029161281030070160L;
	private GrafoEstaciones grafo;
	private JList<Object> listaOrigen;
	private JList<Object> listaDestino;
    private JButton boton;          // boton para la ruta mas corta entre estaciones
    private JButton botonR;          // boton para todas las rutas entre las estaciones
    private JButton botonM;          // boton para mostrar todo el mapa 
	
	public VentanaBusqueda() throws IOException {
		super();
		grafo=new GrafoEstaciones();
		grafo.CrearNodoEstacion();
		configurarVentana();        // configuramos la ventana
        inicializarComponentes(grafo.getEstacionesName());  
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			
	}
	
	private void configurarVentana() {
        this.setTitle("Metro Madrid");                   // colocamos titulo a la ventana
        this.setBounds(5, 5, 550, 660);                // colocamos tamanio y posicion a la ventana (x , y , ancho, alto)        
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes(String[] data) {
        // creamos los componentes
    	botonR = new JButton("Todas las rutas");
    	botonM = new JButton("Mostrar todo el mapa");
        boton = new JButton("Ruta mas corta");
        listaOrigen = new JList<Object>();
        listaDestino=new JList<Object>();
        listaDestino.setListData(data);
        listaOrigen.setListData(data);
        JScrollPane scrollPaneO = new JScrollPane();
        JScrollPane scrollPaneD = new JScrollPane();
        scrollPaneO.setViewportView(listaOrigen);
        scrollPaneD.setViewportView(listaDestino);
        // configuramos los componentes
        botonR.setBounds(39, 580, 200, 30); // colocamos posicion y tamanio a los botones (x, y, ancho, alto)
        boton.setBounds(313, 580, 200, 30);
        botonM.setBounds(175, 15, 200, 30);  
        scrollPaneO.setBounds(20, 60, 238, 500); //Colocamosposicion y tamanio a donde se colocara la lista de estaciones
        scrollPaneD.setBounds(294, 60, 238, 500);                
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        botonR.addActionListener(this);
        botonM.addActionListener(this);
        // adicionamos los componentes a la ventana
        this.add(boton);
        this.add(botonM);
        this.add(botonR);
        this.add(scrollPaneO);
        this.add(scrollPaneD);
    }
		

}
