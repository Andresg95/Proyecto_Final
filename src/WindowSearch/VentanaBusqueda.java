package WindowSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Grafo_Estaciones.GrafoEstaciones;
import aero.Creador;


public class VentanaBusqueda extends JFrame implements ActionListener{

	private static final long serialVersionUID = 3029161281030070160L;
	private GrafoEstaciones grafo;
	private Creador Airports;
	private JList<Object> listaOrigen,listaDestino;
    private JButton boton,botonR,botonM;          	// boton para la ruta mas corta entre estaciones ->boton
    												// boton para todas las rutas entre las estaciones ->botonR
    												// boton para mostrar todo el mapa  ->botonM
	private String destino,origen;
	private JLabel destinoL,origenL;
    
	@SuppressWarnings("static-access")
	public VentanaBusqueda() throws Exception {
		super();
		Airports = new Creador();
		Airports.fetchData();
		grafo=new GrafoEstaciones();
		grafo.CrearNodoEstacion();
		configurarVentana();        // configuramos la ventana
        inicializarComponentes(grafo.getEstacionesName());  
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Corta":
			JOptionPane.showMessageDialog(null, "Mostrar ruta mas corta entre: "+origen+" y "+destino);
			break;
		case "Todas":
			JOptionPane.showMessageDialog(null, "Mostrar todas las rutas entre las estaciones: "+origen+" y "+destino);
			break;
		case "Mapa":
			Airports.viewAll();
			//JOptionPane.showMessageDialog(null, "Mostrar el mapa");
			break;

		default:
			break;
		}
			
	}
	
	private void configurarVentana() {
        this.setTitle("Metro Madrid");                   // colocamos titulo a la ventana
        this.setBounds(5, 5, 560, 670);                // colocamos tamanio y posicion a la ventana (x , y , ancho, alto)        
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes(String[] data) {
        // creamos los componentes
    	destinoL=new JLabel("Por favor elija su origen");
    	origenL = new JLabel("Por favor elija su destino");
    	botonR = new JButton("Todas las rutas");
    	botonM = new JButton("Mostrar todo el mapa");
        boton = new JButton("Ruta mas corta");
        listaOrigen = new JList<Object>();
        listaDestino=new JList<Object>();
        listaDestino.setListData(data);
        listaOrigen.setListData(data);
        JScrollPane scrollPaneO = new JScrollPane();
        JScrollPane scrollPaneD = new JScrollPane();
        JLabel panelO = new JLabel("Origen: ");
        JLabel panelD = new JLabel("Destino: ");
        scrollPaneO.setViewportView(listaOrigen);
        scrollPaneD.setViewportView(listaDestino);
        // configuramos los componentes
        destinoL.setBounds(352, 50, 238, 15);
        origenL.setBounds(62, 50, 238, 15);
        panelO.setBounds(10, 50, 50, 15);
        panelD.setBounds(300, 50, 50, 15);
        botonR.setBounds(39, 590, 200, 30); // colocamos posicion y tamanio a los botones (x, y, ancho, alto)
        boton.setBounds(313, 590, 200, 30);
        botonM.setBounds(175, 10, 200, 30);  
        scrollPaneO.setBounds(20, 70, 238, 500); //Colocamosposicion y tamanio a donde se colocara la lista de estaciones
        scrollPaneD.setBounds(294, 70, 238, 500);
        boton.setEnabled(false); 				//Los botones de rutas no se habilitan hasta tener una estacion de origen y otra de ddestino
        botonR.setEnabled(false);
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        boton.setActionCommand("Corta");
        botonR.addActionListener(this);
        botonR.setActionCommand("Todas");
        botonM.addActionListener(this);
        botonM.setActionCommand("Mapa");
        JlistActions();
        // adicionamos los componentes a la ventana
        this.add(boton);
        this.add(botonM);
        this.add(botonR);
        this.add(scrollPaneO);
        this.add(scrollPaneD);
        this.add(panelD);
        this.add(panelO);
        this.add(destinoL);
        this.add(origenL);
    }

	private void JlistActions() {		
		// TODO Auto-generated method stub
		listaOrigen.addMouseListener( new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		          @SuppressWarnings("unchecked")
				JList<Object> theList = (JList<Object>) mouseEvent.getSource();
		            int index = theList.locationToIndex(mouseEvent.getPoint());
		            if (index >= 0) {
		              Object o = theList.getModel().getElementAt(index);
		              origen=o.toString();
		              origenL.setText(origen);
		            }
		          }
		    	}
			);
		
		listaDestino.addMouseListener( new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		    	  @SuppressWarnings("unchecked")
					JList<Object> theList = (JList<Object>) mouseEvent.getSource();
		            int index = theList.locationToIndex(mouseEvent.getPoint());
		            if (index >= 0) {
		              Object o = theList.getModel().getElementAt(index);
		              destino=o.toString();
		              destinoL.setText(destino);
		              if(origen!=null && destino !=null) {
		      			boton.setEnabled(true); 		
		      	        botonR.setEnabled(true);
		      			}
		            }
		          }
		    	}
			);
	}
		

}
