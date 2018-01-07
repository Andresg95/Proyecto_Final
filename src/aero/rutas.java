package aero;


public class rutas {

	private String airline, origen, destino, codeshare, equipamento;

	private int airlineID, origenID, destinoID, paradas;

	//private Float distance = 0.0f; 

	rutas(String airline,int airlineID, String origen,int origenID,String destino, int destinoID,String codeshare,int paradas) {

		this.airline = airline;
		this.airlineID = airlineID;
		this.origen = origen;
		this.origenID =origenID;
		this.destino = destino;
		this.destinoID = destinoID;
		this.codeshare = codeshare;

		this.paradas = paradas;
		// this.equipamento = infoRuta[8].toString();
		//this.distance = Utils.getDistanceBetween(this.getOrigenID(), this.getDestinoID());
		
		
	}

	/*public Float getDistance() {
		return distance;
	}*/

	public String getOrigenDestino() {
		return this.getOrigenIDStr() + this.getDestinoIDStr();
	}

	public String getAirline() {
		return airline;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public int getAirlineID() {
		return airlineID;
	}

	public int getOrigenID() {
		return origenID;
	}

	public String getOrigenIDStr() {
		return Integer.toString(origenID);
	}

	public int getDestinoID() {
		return destinoID;
	}

	public String getDestinoIDStr() {
		return Integer.toString(destinoID);
	}

	public String getCodeshare() {
		return codeshare;
	}

	public int getParadas() {
		return paradas;
	}

	public String getEquipamento() {
		return equipamento;
	}

}

