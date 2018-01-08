package aero;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;


public class Creador {

	public static ArrayList<aeropuerto> airports = null;
	public static ArrayList<rutas> routes = null;
	private static ArrayList<String> nombres;
	private static Graph graph;

	public static boolean fetchData() throws Exception {
		nombres = new ArrayList<String>();
		graph = new MultiGraph("Grafo");
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
	
	public static aeropuerto findOneByName(String name) {
		for (aeropuerto airport : airports) {
			if (airport.getName().equals(name)) {
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
		//BufferedReader in = new BufferedReader(new FileReader("/home/ermc/git/Proyecto_Final/src/DataCSV/Aeropuertos"));
		String inputLine;
		int limit = 0;
		while ((inputLine = in.readLine()) != null) {
			if (lim && limit == maxlimit) {
				break;
			}
			final Pattern pattern = Pattern.compile("\"");
			final Matcher matcher = pattern.matcher(inputLine);
			inputLine = matcher.replaceAll("");

			Object[] airportinfo = inputLine.split(",");
			int id = Integer.parseInt(airportinfo[0].toString());
			String name = (String) airportinfo[1];
			String city = (String) airportinfo[2];
			String country = (String) airportinfo[3];
			String iata = (String) airportinfo[4];
			String icao = (String) airportinfo[5];

			Float lat = validFloat(airportinfo[6].toString());
			Float lng = validFloat(airportinfo[7].toString());
			airportList.add(new aeropuerto(id,name,city,country,iata,icao,lat,lng));
			
			if(!airportinfo[0].toString().equals("\\N") && !airportinfo[1].toString().equals("\\N") && graph.getNode((String)airportinfo[0])==null) {
				graph.addNode((String)airportinfo[0]).addAttribute("xy", lng,lat);;
				nombres.add(name);
			}

			limit++;
		}

		in.close();
		return airportList;

	}
	
	public String[] getAirports() {
		return  nombres.toArray(new String[nombres.size()]);		
	}
	
	private static Float validFloat(String s) {
		if (s.matches("[-+]?[0-9]*\\.?[0-9]+")) {
			return Float.parseFloat(s);
		} else {
			return 0.0f;
		}
	}

	public static ArrayList<rutas> getRoutes(boolean lim, int maxlimit) throws Exception {

		URL oracle = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/routes.dat");
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
		//BufferedReader in = new BufferedReader(new FileReader("/home/ermc/git/Proyecto_Final/src/DataCSV/Rutas"));
		ArrayList<rutas> everyRoute = new ArrayList<rutas>();
		String inputLine;
		int limit = 0;
		while ((inputLine = in.readLine()) != null) {
			if (lim && limit == maxlimit) {
				break;
			}
			Object[] infoRuta = inputLine.split(",");
			String airline = (String) infoRuta[0];
			int airlineID = validString(infoRuta[1].toString());
			String origen = (String) infoRuta[2];
			int origenID = validString(infoRuta[3].toString());
			String destino = (String) infoRuta[4];
			int destinoID = validString(infoRuta[5].toString());
			String codeshare = infoRuta[6].toString();
			int paradas = validString(infoRuta[7].toString());			
			
			everyRoute.add(new rutas( airline, airlineID,  origen, origenID, destino,  destinoID, codeshare, paradas));
			
			if(!infoRuta[3].toString().equals("\\N") && !infoRuta[5].toString().equals("\\N") && graph.getEdge((String)infoRuta[3]+(String)infoRuta[5])==null && graph.getNode((String)infoRuta[3])!=null &&graph.getNode((String)infoRuta[5])!=null)
				graph.addEdge(infoRuta[3].toString()+infoRuta[5].toString() ,infoRuta[3].toString(),infoRuta[5].toString()).addAttribute("length", getDistanceBetween(origenID,destinoID));
			limit++;
		}

		in.close();

		return everyRoute;
	}
	
	private static int validString(String s) {

		if (s.equals("\\N")) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}

	}
	
	public void viewAll() {
		graph.display();
	}
	
	public void rutaCorta(String origen,String destino) throws IOException {
		BufferedReader br=null;
		aeropuerto origenA = findOneByName(origen);
		aeropuerto destinoA =findOneByName(destino);
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
		// Compute the shortest paths in g from A to all nodes
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(String.valueOf(origenA.getId())));
		dijkstra.compute();
		graph.display(false);
		for (Node node : graph)
			System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
					dijkstra.getPathLength(node));
		
		for (Node node : dijkstra.getPathNodes(graph.getNode(String.valueOf(destinoA.getId()))))
			node.addAttribute("ui.style", "fill-color: yellow;");

		// Color in red all the edges in the shortest path tree
		for (Edge edge : dijkstra.getTreeEdges())
			edge.addAttribute("ui.style", "fill-color: green;");
		
		System.out.println(dijkstra.getPath(graph.getNode(String.valueOf(destinoA.getId()))));

		// Build a list containing the nodes in the shortest path from A to B
		// Note that nodes are added at the beginning of the list
		// because the iterator traverses them in reverse order, from B to A
		List<Node> list1 = new ArrayList<Node>();
		for (Node node : dijkstra.getPathNodes(graph.getNode(String.valueOf(destinoA.getId()))))
			list1.add(0, node);		
			
	}
	
	

}
