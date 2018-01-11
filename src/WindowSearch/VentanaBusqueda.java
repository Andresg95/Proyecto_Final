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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mxrck.autocompleter.TextAutoCompleter;

import aero.Creador;


public class VentanaBusqueda extends JFrame implements ActionListener{

	private static final long serialVersionUID = 3029161281030070160L;
	private Creador Airports;
	private JList<Object> listaOrigen,listaDestino;
    private JButton boton,botonR,botonM;          	// boton para la ruta mas corta entre estaciones ->boton
    												// boton para todas las rutas entre las estaciones ->botonR
    												// boton para mostrar todo el mapa  ->botonM
	private String destino,origen;
    private JTextField destinoL,origenL;
    private TextAutoCompleter txtOrigen,txtDestino;
	@SuppressWarnings("static-access")
	public VentanaBusqueda() throws Exception {
		super();
		Airports = new Creador();
		Airports.fetchData();
		configurarVentana();        // configuramos la ventana
        inicializarComponentes(Airports.getAirports());  
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "Corta":
			try {
				Airports.rutaCorta(origenL.getText(),destinoL.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Mapa":
			Airports.viewAll();
			break;

		default:
			break;
		}
			
	}
	
	private void configurarVentana() {
        this.setTitle("Metro Madrid");                   // colocamos titulo a la ventana
        this.setBounds(5, 5, 638, 670);                // colocamos tamanio y posicion a la ventana (x , y , ancho, alto)        
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes(String[] data) {
        // creamos los componentes
    	origenL= new JTextField("Busca o selecciona aeropuerto de origen");
    	destinoL=new JTextField("Busca o selecciona aeropuerto de destino");
    	txtOrigen = new TextAutoCompleter( origenL );
    	txtDestino = new TextAutoCompleter( destinoL );
    	botonR = new JButton("Todas las rutas posibles");
    	botonM = new JButton("Mostrar todo el mapa");
        boton = new JButton("Ruta mas corta");
        listaOrigen = new JList<Object>();
        listaDestino=new JList<Object>();
        listaDestino.setListData(data);
        txtOrigen.addItems(data);
        txtDestino.addItems(data);
        listaOrigen.setListData(data);
        JScrollPane scrollPaneO = new JScrollPane();
        JScrollPane scrollPaneD = new JScrollPane();
        JLabel panelO = new JLabel("Origen: ");
        JLabel panelD = new JLabel("Destino: ");
        scrollPaneO.setViewportView(listaOrigen);
        scrollPaneD.setViewportView(listaDestino);
        // configuramos los componentes
        destinoL.setBounds(380, 50, 238, 18);
        origenL.setBounds(70, 50, 238, 18);
        panelO.setBounds(10, 50, 50, 15);
        panelD.setBounds(328, 50, 50, 15);
        boton.setBounds(380, 590, 200, 30);
        botonR.setBounds(70, 590, 200, 30);
        botonM.setBounds(240, 10, 200, 30);  
        scrollPaneO.setBounds(70, 70, 238, 500); //Colocamosposicion y tamanio a donde se colocara la lista de estaciones
        scrollPaneD.setBounds(380, 70, 238, 500);
        boton.setEnabled(false); 				//Los botones de rutas no se habilitan hasta tener una estacion de origen y otra de ddestino
        botonR.setEnabled(false);
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        boton.setActionCommand("Corta");
        botonM.addActionListener(this);
        botonM.setActionCommand("Mapa");
        botonR.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        botonR.setActionCommand("Todas");
        JlistActions();
        JTextActions();
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
    
    private void JTextActions() {
    	origenL.getDocument().addDocumentListener(new DocumentListener() {
			
    		@Override
    		 public void changedUpdate(DocumentEvent e) {
     		    seleccionado();
     		  }
    		@Override
     		  public void removeUpdate(DocumentEvent e) {
    			seleccionado();
     		  }
    		@Override
     		  public void insertUpdate(DocumentEvent e) {
     		    seleccionado();
     		  }

     		  public void seleccionado() {
     		     if (txtOrigen.getItemSelected()!=null){
     		    	origen =txtOrigen.getItemSelected().toString();
     		     }
     		  }
		});
    	
    	destinoL.getDocument().addDocumentListener(new DocumentListener() {
			
    		@Override
    		 public void changedUpdate(DocumentEvent e) {
     		    seleccionado();
     		  }
    		@Override
     		  public void removeUpdate(DocumentEvent e) {
    			seleccionado();
     		  }
    		@Override
     		  public void insertUpdate(DocumentEvent e) {
     		    seleccionado();
     		  }

     		  public void seleccionado() {
     			 if(txtDestino.getItemSelected()!=null) destino =txtDestino.getItemSelected().toString();
                 if(origen!=null && destino !=null) {
 	      			boton.setEnabled(true);
 	      			botonR.setEnabled(true);
 	      			}
     		  }
		});
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
