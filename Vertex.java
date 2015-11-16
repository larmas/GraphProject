
public class Vertex<T> {
	T element;
	boolean mark;
	EdgeList<T> adjVertex;
	//Vertex Back;
	
	public Vertex(T a){
		this.element = a;
		this.mark = false;
		this.adjVertex = new EdgeList<T>();
	}
	
	public void ins(Vertex<T> a, int cost){
		if (!adjVertex.contains(a)){		//si no contiene el vertex a
			Edge<T> x= new Edge<T>(a,cost);	//lo agrega a la llista de adyacencia
			this.adjVertex.add(x);
		}
	}
	
	public Vertex<T> nextAdj(){			//siguiente adyacente no marcado
		if (this.adjVertex.size()==0){		//verifico tamaño distinto de 0
			return null;
		}
		else{
			int i=0;
			while ((i<this.adjVertex.size()) && (adjVertex.get(i).dest.mark==true)){
				i++;
			}
			if (i<this.adjVertex.size()){		//recorro hasta encontrar un nodo no marcado
				return adjVertex.get(i).dest;	//o llegar a i=size
			}
			else{
				return null;
			}
		}
	}
}
