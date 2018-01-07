package aero;

public class aeropuerto {
	private int id;
	private String name, city, country, iata, icao;
	private Float lat, lng;

	public aeropuerto(int id,String name,String city,String country,String iata,String icao,Float lat, Float lng) {

		
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		this.iata = iata;
		this.icao = icao;

		this.lat = lat;
		this.lng = lng;
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
