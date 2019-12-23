
import java.util.*;


public class Graph
{
	private HashMap<String, Set<String>> graph;


	public Graph()					//graph constructor 

	{
		graph = new HashMap<>();		
	}

	public void addEdge(String source, String target)			
	{
		if(source.toLowerCase().equals(target.toLowerCase()))
		{
			System.out.println("You've Entered the same actor");
			return;
		}

		else 
		{
			if(graph.containsKey(source.toLowerCase()))
			{
				graph.get(source.toLowerCase()).add(target.toLowerCase());
			}
			else
			{
				Set<String> h = new HashSet<>();
				h.add(target.toLowerCase());
				graph.put(source.toLowerCase(), h);
			}
			if(graph.containsKey(target.toLowerCase()))
			{
				graph.get(target.toLowerCase()).add(source.toLowerCase());
			}
			else
			{
				Set<String> hh = new HashSet<>();
				hh.add(source.toLowerCase());
				graph.put(target.toLowerCase(), hh);
			}		
		}
	}



	/*
	 * In Make Path we implement a modified BFS in order to find the shortest distance between the inputed actors
	 */

	public void makePath(String source, String target) 
	{
		if (!graph.containsKey(source) || !graph.containsKey(target)) 	//if either actor1 or actor2 are not found
		{
			System.out.println("One of the Actors Does not exist in Database");
			return;
		} 
		else 
		{


			ArrayList<String> construct = new ArrayList<>();
			LinkedList<String> linked = new LinkedList<>();
			Hashtable<String, String> path = new Hashtable<>();
			path.put(source, "x");				//x is being used as a placeholder  
			linked.add(source);
			construct.add(source);
			while (!linked.contains(target)) 
			{
				String actor = linked.removeFirst();

				for (String connect : graph.get(actor)) 
				{

					if (!construct.contains(connect)) 
					{
						construct.add(connect);
						linked.add((connect));
						path.put(connect,actor);
						if (connect.equals(target)) 
						{
							if (!path.containsKey(target)) 
							{
								path.put(target, actor);
							}


						}


					}

				}
			}

			//String person1 = path.get(start);
			String person = path.get(target);
			//System.out.println(person);
			//System.out.println(person1);
			Stack<String> shortPath = new Stack<>();
			//System.out.println(person);
			
			
			/*
			 * Here we create a stack in which we push the members of the path and when we print the connected actors 
			 * we are essentially popping them from the stack  and showing who is being popped
			 */

			do 
			{
				shortPath.push(person);
				person = path.get(person);

			} 
			while (!person.equals("x"));			//keep pushing until we reach the source which is marked with placeholder x
			System.out.println(shortPath.size());

			while(!shortPath.isEmpty())
			{
				if(!person.equals(target))
				{
					person = shortPath.pop();
					System.out.print(person + " -> ");
				}
				else
				{
					System.out.print(person);
				}
			}
			System.out.print(target+"\n");
		}
	}


} 

