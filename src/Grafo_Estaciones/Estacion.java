package Grafo_Estaciones;

public class Estacion {
	private int codigo;
	private String nombre;
	private int posX;
	private int posY;
	public Estacion(int c,String n,int x, int y) {
		this.setNombre(n);
		this.setCodigo(c);
		this.setPosX(x);
		this.setPosY(y);
	}
	
	public int getEstacionByname(String name) {
		if(name.equals(this.getNombre())) {
			return this.getCodigo();
		}else return -1;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
}
