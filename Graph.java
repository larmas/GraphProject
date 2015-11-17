///////////////////////////////////////////////////////////////
//-------------Clase que define a un grafo-------------------//
///////////////////////////////////////////////////////////////

import java.util.LinkedList;
import java.util.Stack;

public class Graph <T extends Comparable>{

	public VertexList<T> nodesX;	//Lista de vertices que contiene el grafo
	
	/*Constructor de la clase*/
	public Graph() {
		nodesX = new VertexList<T>();
	}
	
	/*Constructor de la clase*/
	public Graph(Vertex<T> a){
		nodesX = new VertexList<T>();
		nodesX.add(a);
	}
	
	/*Metodo empty devuelve true si el grafo esta vacio*/
	public boolean empty(){
		return (this.nodesX.size()==0);
	}
	
	/*Metodo vertQ devuelve la cantidad de vertices que tiene el grafo*/
	public int vertQ(){				//Cant vertices = size de nodesX
		return nodesX.size();
	}
	
	/*Metodo edgeQ devuelve la cantidad de aristas que tiene el grafo*/
	public int edgeQ(){				//cuento aristas
		int i = 0;
		int j = 0;
		while (i<nodesX.size()){	//sumo los tamaños de listas de adyacencia
			j= j+nodesX.get(i).adjVertex.size();
			i++;
		}
		return j;
	}
	
	/*Metodo connected devuelve true si existe una arista en el grafo desde a hasta b*/
	public boolean connected(Vertex<T> a, Vertex<T> b){				//si el nodo pert al grafo, y el otro 
		return ((nodesX.contains(a))&&(a.adjVertex.contains(b)));	//esta en su lista de adyacencias
	}
	
	/*Metodo connect crea una arista con su costo desde a hasta b*/
	/*Arroja una excepcion si alguno de los vertices no pertenece al grafo*/
	public void connect(Vertex<T> a, Vertex<T> b, int cost) throws ExceptionGraph{ //VERIFICAR QUE PERTENECEN A nodesX
		if (this.belongs(a) && this.belongs(b)){
			a.ins(b, cost);
		}else{
			if (! this.belongs(a)){
				throw new ExceptionGraph("Graph.connect: El vertice "+ a.element +" no pertenece al grafo.");
			}else{
				throw new ExceptionGraph("Graph.connect: El vertice "+ b.element +" no pertenece al grafo.");
			}
		}
	}
	
	/*Metodo delVert elimina un vertice del grafo*/
	/*Arroja una excepcion si el vertice no pertenece al grafo*/
	public void delVert(Vertex<T> a) throws ExceptionGraph{
		if (this.belongs(a)){
			int i=0;										//Primero elimino de listas de adyacencia
			while (i<nodesX.size()){
				nodesX.get(i).adjVertex.remove(a);
				i++;
			}
			nodesX.remove(a);								//Luego de la lista de vertices
		}else{
			throw new ExceptionGraph("Graph.delVert: El vertice no pertenece a la lista.");
		}
	}
	
	/*Metodo delEdge elimina una arista del grafo*/
	/*Arroja una excepcion si la arista no pertenece al grafo*/
	public void delEdge(Vertex<T> a, Vertex<T> b) throws ExceptionGraph{
		if (connected(a,b)){	
			a.adjVertex.remove(b);
		}else{
			throw new ExceptionGraph("Graph.delEdge: Los vertices no estan conectados");
		}
	}
	
	/*Metodo belong devuelve true si el vertice a pertenece al grafo*/
	public boolean belongs(Vertex<T> a){
		return nodesX.contains(a);
	}
	
	/*Metodo insert ingresa un vertice, solo si no pertenece al grafo y devuelve true si*/
	/*se ingreso un nuevo vertice*/
	public boolean insert(Vertex<T> a){	
		if (!this.belongs(a)){
			this.nodesX.add(a);
			return true;
		}
		else{
			return false;
		}
	}
	
	/* Metodo dfs recorre el grafo primero en profundidad*/
	/*Arroja una excepcion si el vertice de inicio del recorrido no pertenece al grafo*/
	public void dfs(Vertex<T> v)throws ExceptionGraph{
		if (this.belongs(v)){
			LinkedList<T> a = new LinkedList<T>();
			Stack<Vertex<T>> s = new Stack<Vertex<T>>();
			s.push(v);					//defino un variables locales
			v.mark = true;				//trato primer elemento
			a.add(v.element);
			Vertex <T> x, w;
			while(! s.empty()){
				x=s.peek();				//tomo el elemento top del stack
				w=x.nextAdj();
				if (w != null){			//busco el siguiente adyacente no marcado
					w.mark = true;		//lo marco y lo proceso
					s.push(w);
					a.add(w.element);
				}
				else{					//si no hay elemento sig no marcado saco el anterior (backtracking)
					s.pop();
				}
			}
			showList(a);
			this.unmark();
		}else{
			throw new ExceptionGraph("Graph.dfs: El vertice de inicio no pertenece al grafo.");
		}
	}
	
	/* Metodo unmark utilizado para desmarcar todos los vertices del grafo*/
	private void unmark(){
		int i = 0;
		while(i < nodesX.size()){
			nodesX.get(i).mark=false;
			i++;
		}
	}
	
	/* Metodo dfs recorre el grafo recursivamente primero en profundidad*/
	/*Arroja una excepcion si el vertice de inicio del recorrido no pertenece al grafo*/
	public void dfsRec(Vertex<T> a)throws ExceptionGraph{
		if (this.belongs(a)){
			LinkedList<T> auxList = new LinkedList<T>();
			aux1(a, auxList);					//defino una lista para la traza y llamo a aux1, que hara el proceso
			showList(auxList);
			this.unmark();
		}else{
			throw new ExceptionGraph("Graph.dfsRec: El vertice de inicio no pertenece al grafo.");
		}
	}
	
	/*Metodo aux1 utilizado en el metodo dfsRec, realiza el recorrido*/
	private void aux1(Vertex<T> a, LinkedList<T> auxList){
		auxList.add(a.element);					//dfs recursivo, el tratamiento de cada vertice es
		a.mark=true;							//ser insertado en lista
		while(a.nextAdj() != null){
			aux1(a.nextAdj(), auxList);
		}
	}
	
	/*Metodo showList metodo utilizado para mostrar el contenido de una lista*/
	private void showList(LinkedList<T> auxList){
			int i=1;									//recorro una lista imprimiendo valores
			System.out.print(auxList.get(0));
			while(i<auxList.size()){
				System.out.print(","+auxList.get(i));
				i++;
			}
			System.out.println("");
	}
	
	/*Metodo prims utilizado para encontrar un arbol abarcador minimo*/
	/*Arroja una excepcion si el vertice de inicio no pertenece al grafo*/
	public Graph<T> prims(Vertex<T> beg)throws ExceptionGraph{
		if (this.belongs(beg)){	
			Graph<T> n = new Graph<T>(beg);			//arbol abarcador
			LinkedList<T> auxList =new LinkedList<T>();
			beg.mark=true;							//marco el vertice inicial
			Vertex<T> a = new Vertex<T> (beg.element);
			n.insert(a);							//inserto dicho vertice
			auxList.add(a.element);
			int i=0;
			while(i<(nodesX.size()-1)){				//busco vertice mas cercano al arbol y lo inserto
				bfs1(n,auxList);					//n-1 veces, cantidad mayor a insertar
				i++;								//teniendo en cuenta que ya se inserto el inicial
			}
			n.unmark();								//desmarco grafos
			this.unmark();
			showList(auxList);
			return n;
		}else{
			throw new ExceptionGraph("Graph.prims: El vertice de inicio no pertenece al grafo.");
		}
	}
	
	/*Metodo bfs1 auxiliar del metodo prims, utilizado para encontrar el vertice mas cercano al arbol e insertarlo*/
	private void bfs1(Graph<T> n, LinkedList<T> auxList){
		int i,j;						//defino e inicializo variables locales
		Vertex<T> aux2,back,back2;
		Edge<T> aux,aux3,aux4;
		back = null;
		aux= null;
		i=0;
		while (i<nodesX.size()){								//recorro lista vertices
			aux2 = nodesX.get(i);
			if((aux2.mark) && (aux2.adjVertex.size()>0)){		//recorre marcados (nodos agregados al arbol)
				j=0;
				aux3 = aux2.adjVertex.get(0);					//primer vertice
				back2=aux2;
				while (j<aux2.adjVertex.size()){		 		//recorre los vertices adyacentes
					aux4= aux2.adjVertex.get(j);
					if (!aux4.dest.mark){ 						// si no esta marcado (no esta en el arbol)
						if(((aux3.cost>aux4.cost) || (aux3.dest.mark))&&(!aux4.dest.mark)){  
							aux3=aux4;							//si es menor a la arista menor actual, sobreescribo
							back2 = aux2;
						}
					}
					j++;	
				}
				if ((aux==null) || (((aux3.cost<aux.cost)||(aux.dest.mark)) && (!aux3.dest.mark))){
					aux=aux3;			//arista menor no marcada en el vertice cuya ady se ha recorrido
					back=back2;
				}
			}
			i++;
		}
		insArb(n,back, aux,auxList);			//inserto dicha arista, si todos estaban marcados es null
	}
	
	/*Metodo insArb uxiliar del metodo bfs1, utilizado para insertar el vertice al arbol*/
	private void insArb(Graph<T> n, Vertex<T> back, Edge<T> x, LinkedList<T> auxList ){
		if ((back!=null) && (x!=null)){			//control de valores null
			x.dest.mark=true;					//marco e inserto en nuevo grafo (arbol abarcador)
			if (n.insert(x.dest)){				//Si no estaba en el arbol, se inserta y
				auxList.add(x.dest.element);	//se agrega a la lista
				try{
					n.connect(back,x.dest,x.cost);
				}catch(ExceptionGraph z){
					System.out.println(z.getMessage());
				}
			}
		}
	}
	
}//Fin de la clase Graph
