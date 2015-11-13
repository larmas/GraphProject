import java.util.LinkedList;
public class Vertex{

	public Object name;//Define a dato como un objeo simple
	public LinkedList<Edge> listAdj;//Define a los lugares en la lista como nodos
	public boolean visited = false;// Define un parametro del tipo booleano para la marca visitado

	public Vertex(){//Prepara espacio para el vertice
    	this(new Object());
    }

	public Vertex(Object elem){//Metodo sobre cargado de Vertex
        this.name = elem;//Define un puntero para dato
        listAdj = new LinkedList<Edge>();//Creacion del vertice en la lista
    }

    public void createEdge(Vertex d, int c){
        Edge edge = new Edge(d,c);
        this.listAdj.addFirst(edge); 
    }

    public LinkedList<Edge> getAdjacents (){
    	return this.listAdj;
    }

    public Vertex firstUnvisited(){
    }
}