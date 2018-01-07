package aero;

import java.util.ArrayList;

public class rutas {

	private String airline, origen, destino, codeshare, equipamento;

	private int airlineID, origenID, destinoID, paradas;

	//private Float distance = 0.0f;

	rutas(String r) {

		Object[] infoRuta = r.split(",");
		this.airline = (String) infoRuta[0];
		this.airlineID = this.validString(infoRuta[1].toString());
		this.origen = (String) infoRuta[2];
		this.origenID = this.validString(infoRuta[3].toString());
		this.destino = (String) infoRuta[4];
		this.destinoID = this.validString(infoRuta[5].toString());
		this.codeshare = infoRuta[6].toString();

		this.paradas = this.validString(infoRuta[7].toString());
		// this.equipamento = infoRuta[8].toString();
		//this.distance = Utils.getDistanceBetween(this.getOrigenID(), this.getDestinoID());
		
		
	}

	private int validString(String s) {

		if (s.equals("\\N")) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}

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

