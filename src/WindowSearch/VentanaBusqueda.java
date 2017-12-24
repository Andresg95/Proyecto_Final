package WindowSearch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class VentanaBusqueda extends JFrame implements ActionListener{

	private JList<Object> lista;
    private JButton boton;          // boton con una determinada accion
	
	public VentanaBusqueda(String[] estaciones) {
		super();
		configurarVentana();        // configuramos la ventana
        inicializarComponentes(estaciones);  
	} 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			
	}
	
	private void configurarVentana() {
        this.setTitle("Metro Madrid");                   // colocamos titulo a la ventana
        this.setSize(1000, 1000);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes(String[] data) {
        // creamos los componentes    	
        boton = new JButton();
        lista = new JList<Object>();        
        lista.setListData(data);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(lista);
        // configuramos los componentes
        scrollPane.setBounds(10, 10, 150, 800); //Colocamosposicion y tamanio a donde se colocara la lista de estaciones
        boton.setText("Buscar Ruta");   // colocamos un texto al boton
        boton.setBounds(100, 850, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        // adicionamos los componentes a la ventana
        this.add(boton);
        this.add(scrollPane);
    }
		

}
