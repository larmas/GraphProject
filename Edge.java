//////////////////////////////////////////////////////////////////////////
//------------------Clase que define las aristas del grafo--------------//
//////////////////////////////////////////////////////////////////////////

public class Edge<T> {

	Vertex<T> dest;			//Vertice de destino
	int cost;				//Costo de la arista
	
	/*Constructor de la clase*/
	public Edge(Vertex<T> a, int b) {
		dest = a;
		cost = b;
	}

}//fin de la clase Edge<T>
