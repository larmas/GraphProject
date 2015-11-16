
public class Edge<T> {

	Vertex<T> dest;			//vertice
	int cost;				//peso arista
	
	public Edge(Vertex<T> a, int b) {
		dest = a;
		cost = b;

	}

}
