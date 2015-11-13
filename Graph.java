import java.util.LinkedList;

public class Graph{
	
	private LinkedList<Vertex> listVertex;//Declaracion de un listado que contendra a los vertices

	public Graph(){
		listVertex = new LinkedList<Vertex>(); //Crea la lista donde van todos los vertices del grafo
	}

	 public Vertex searchVertex(Object elem){
       Vertex temp = null;//Crea un nodo temporal nulo , por si no se encuentra el nodo en el listado
        for(int i = 0; i < listVertex.size(); i++){//Recorre el listado de nodos
            if(((String)elem).equals((String)listVertex.get(i).name)){
                return listVertex.get(i);// Si el nombre del nodo esta en la lista y regresa el ndodo y su marca(visitado o no visitado)
            }
        }
        return temp;//Si todo falla regresa dato nulo
    }

    public void addVertex(Object name){
    	if (this.searchVertex(name) == null){
            Vertex vertex = new Vertex(name);
        	listVertex.add(vertex);//Ingreso del nodo a la lista
        }else{ 
            System.out.println("Ya existe un vertice con ese nombre");
        }
    }

    public void createEdges(Object nameVertexParent,Object nameVertexChild,int costEdge){//Creacion de los enlaces
        Vertex parent = searchVertex(nameVertexParent); //De donde va     
        Vertex child = searchVertex(nameVertexChild);//A donde va
        if(parent != null && child != null){  
        	parent.createEdge(child,costEdge);//Indica que no esta dirigido el nodo
        }
    }
}