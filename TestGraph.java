public class TestGraph{
	public static void loadingGraph(){
		Graph graph = new Graph();//<---Construimos al objeto grafo eh indicamos que estara definodo en  la clase Grafo
        
        graph.addVertex(new Vertex("A")); //Creacion de los vertices
        graph.addVertex(new Vertex("B"));      
        graph.addVertex(new Vertex("C"));
        graph.addVertex(new Vertex("D"));
        graph.addVertex(new Vertex("F"));
       
        graph.createEdges("A","B");// de A hacia B
        
        graph.createEdges("A","C");
        
        graph.createEdges("A","F");
        
        graph.createEdges("B","F");
        
        graph.createEdges("C","D");

	}
}