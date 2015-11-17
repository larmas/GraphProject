////////////////////////////////////////////////////////////////////////////
//----------------Clase que define los vertices del grafo-----------------//
////////////////////////////////////////////////////////////////////////////

public class Vertex<T> {
	T element;				//Nombre o identificador del vertice
	boolean mark;			//Variable auxiliar
	EdgeList<T> adjVertex;	//Lista de vertices adyacentes
	
	/*Constructor de la clase*/
	public Vertex(T a){
		this.element = a;
		this.mark = false;
		this.adjVertex = new EdgeList<T>();
	}
	
	/*Insertar un vertice en la lista de adyacencia*/
	public void ins(Vertex<T> a, int cost){
		if (!adjVertex.contains(a)){		//Si no contiene el vertex a
			Edge<T> x= new Edge<T>(a,cost);	//lo agrega a la lista de adyacencia
			this.adjVertex.add(x);
		}
	}
	
	/*Devuelve el siguiente vertice no marcado en la lista*/
	public Vertex<T> nextAdj(){	
		if (this.adjVertex.size()==0){		//Verifico tamaño distinto de 0 (no vacia)
			return null;
		}
		else{
			int i=0;
			while ((i<this.adjVertex.size()) && (adjVertex.get(i).dest.mark==true)){
				i++;
			}
			if (i<this.adjVertex.size()){		//Recorro hasta encontrar un nodo no marcado
				return adjVertex.get(i).dest;	//o llegar a i=size
			}
			else{
				return null;
			}
		}
	}
}//Fin de la clase Vertex
