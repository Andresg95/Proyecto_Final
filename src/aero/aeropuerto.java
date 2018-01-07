package aero;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aeropuerto {
	private int id;
	private String name, city, country, iata, icao;
	private Float lat, lng;

	public aeropuerto(String line) {

		final Pattern pattern = Pattern.compile("\"");
		final Matcher matcher = pattern.matcher(line);
		line = matcher.replaceAll("");

		Object[] airportinfo = line.split(",");

		this.id = Integer.parseInt(airportinfo[0].toString());
		this.name = (String) airportinfo[1];
		this.city = (String) airportinfo[2];
		this.country = (String) airportinfo[3];
		this.iata = (String) airportinfo[4];
		this.icao = (String) airportinfo[5];

		this.lat = this.validFloat(airportinfo[6].toString());
		this.lng = this.validFloat(airportinfo[7].toString());
	}

	private Float validFloat(String s) {
		if (s.matches("[-+]?[0-9]*\\.?[0-9]+")) {
			return Float.parseFloat(s);
		} else {
			return 0.0f;
		}
	}

	public String toString() {
		return this.getName() + ", " + this.getCity() + ", " + this.getCountry() + ", " + this.getIata();
	}

	public int getId() {
		return id;
	}

	public String getIcao() {
		return icao;
	}

	public Float getLat() {
		return lat;
	}

	public Float getLng() {
		return lng;
	}

	public String getIdStr() {
		return Integer.toString(this.getId());
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

}
