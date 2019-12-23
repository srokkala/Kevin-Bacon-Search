import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.opencsv.*;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Kevin {

	public Graph readFile(String file, Graph graph) {
		try {

			// Parsing CSV
			FileReader fileReader = new FileReader(file);
			CSVReader csvReader = new CSVReader(fileReader);
			JSONParser p = new JSONParser();

			System.out.println("Processing CSV file to create graph...");

			String[] toParse;
			while ((toParse = csvReader.readNext()) != null) 
			{
				try {

					ArrayList<String> list = new ArrayList<>();
					// Trying to parse as JSON
					// start at index 2 to skip the first two columns which are
					// not JSON
					JSONArray items = (JSONArray) p.parse(toParse[2]);

					for (int j = 0; j < items.size(); j++) {
						JSONObject temp = (JSONObject) items.get(j);
						String name = (String) temp.get("name");
						list.add(name);
						// Here we take in all the data from the .csv file and
						// read it into a hashmap that we declared in the
						// contructor of graph
						for (int k = 0; k < items.size(); k++) {
							JSONObject tempk = (JSONObject) items.get(k);
							String namek = (String) tempk.get("name");
							if (!name.toLowerCase().equals(namek.toLowerCase())) {
								graph.addEdge(name.toLowerCase(), namek.toLowerCase());
							}
						}

					}

				} 
				catch (Exception e) 
				{
					continue;
					// Is exception occur that means it's not JSON data

				}

			}
			csvReader.close(); // precaution with memory usage
			return graph;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) throws IOException {

		Kevin test = new Kevin();
		Graph graph = new Graph();

		test.readFile(args[0], graph);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter First Actor: ");
		String actor1 = scanner.nextLine().toLowerCase(); // makes search non
															// case sensitive
		System.out.println("Enter Second Actor: ");
		String actor2 = scanner.nextLine().toLowerCase();
		scanner.close(); // closed scanner and csvreader to avoid using extra
							// memory
		graph.makePath(actor1, actor2);

	}
}
