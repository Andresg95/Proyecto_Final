package aero;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.awt.List;

public class Creador {

	public static ArrayList<aeropuerto> airports = null;
	public static ArrayList<rutas> routes = null;

	public static boolean fetchData() throws Exception {
		if (airports == null) {
			airports = getAirports(false, 0);
		}
		if (routes == null) {
			routes = getRoutes(false, 0);
		}
		return true;
	}

	public static aeropuerto findOne(int id) {
		for (aeropuerto airport : airports) {
			if (airport.getId() == id) {
				return airport;
			}
		}
		return null;
	}

	// @returns Distance in Meters
	public static Float getDistanceBetween(int origin, int destination) {

		try {
			aeropuerto ori = findOne(origin);
			aeropuerto des = findOne(destination);
			if (ori == null && des == null) {
				return 0.0f;
			}

			return (float) distance(ori.getLat(), des.getLat(), ori.getLng(), des.getLng(), 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0.0f;
	}

	public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance) / 1000;
	}

	public static ArrayList<aeropuerto> searchAirPortByIds(int[] args1) throws Exception {
		if (airports == null) {
			fetchData();
		}
		ArrayList<aeropuerto> airportList = new ArrayList<aeropuerto>();

		for (aeropuerto ap : airports) {
			for (int arg : args1) {
				if (ap.getId() == arg) {
					airportList.add(ap);
				}
			}
		}

		return airportList;
	}

	public static ArrayList<aeropuerto> getAirports(boolean lim, int maxlimit) throws Exception {

		ArrayList<aeropuerto> airportList = new ArrayList<aeropuerto>();

		URL oracle = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		String inputLine;
		int limit = 0;
		while ((inputLine = in.readLine()) != null) {
			if (lim && limit == maxlimit) {
				break;
			}
			airportList.add(new aeropuerto(inputLine));
			// System.out.println(inputLine);
			limit++;
		}

		in.close();
		return airportList;

	}

	public static ArrayList<rutas> getRoutes(boolean lim, int maxlimit) throws Exception {

		URL oracle = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/routes.dat");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

		ArrayList<rutas> everyRoute = new ArrayList<rutas>();
		String inputLine;
		int limit = 0;
		while ((inputLine = in.readLine()) != null) {
			if (lim && limit == maxlimit) {
				break;
			}
			everyRoute.add(new rutas(inputLine));
			// System.out.println(inputLine);
			limit++;
		}

		in.close();

		return everyRoute;
	}

}
