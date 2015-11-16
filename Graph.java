import java.util.LinkedList;
import java.util.Stack;


public class Graph <T extends Comparable>{

	public LinkedList<Vertex<T>> nodesX;
	
	public Graph() {
		nodesX = new LinkedList<Vertex<T>>();
	}
	
	public Graph(Vertex<T> a){
		nodesX = new LinkedList<Vertex<T>>();
		nodesX.add(a);
	}
	
	public boolean empty(){
		return (this.nodesX.size()==0);
	}
	
	public int vertQ(){			//cant vertices = size de nodesX
		return nodesX.size();
	}
	
	public int edgeQ(){				//cuento aristas
		int i = 0;
		int j = 0;
		while (i<nodesX.size()){	//sumo los tamaños de listas de adyacencia
			j= j+nodesX.get(i).adjVertex.size();
			i++;
		}
		return j;
	}
	
	public boolean connected(Vertex<T> a, Vertex<T> b){				//si el nodo pert al grafo, y el otro 
		return ((nodesX.contains(a))&&(a.adjVertex.contains(b)));	//esta en su lista de adyacencias
	}
	
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
	
	public void delVert(Vertex<T> a) throws ExceptionGraph{ //elimino vertice
		if (this.belongs(a)){
			int i=0;								//primero de listas de adyacencia
			while (i<nodesX.size()){
				nodesX.get(i).adjVertex.remove(a);
				i++;
			}
			nodesX.remove(a);						//luego de la lista de vertices
		}else{
			throw new ExceptionGraph("Graph.delVert: El vertice no pertenece a la lista.");
		}
	}
	
	public void delEdge(Vertex<T> a, Vertex<T> b) throws ExceptionGraph{ //verificar que a pertenece a nodesX
		if (connected(a,b)){	
			a.adjVertex.remove(b);
		}else{
			throw new ExceptionGraph("Graph.delEdge: Los vertices no estan conectados");
		}
	}
	
	public boolean belongs(Vertex<T> a){		//pertenece al grafo
		return nodesX.contains(a);
	}
	
	public void insert(Vertex<T> a){			//inserta un vertice, solo si no pertenece al grafo
		if (!this.belongs(a))
			this.nodesX.add(a);
	}
	
	
	public void dfs(Vertex<T> v)throws ExceptionGraph{
		if (this.belongs(v)){
			LinkedList<T> a = new LinkedList<T>();
			Stack<Vertex<T>> s = new Stack<Vertex<T>>();
			s.push(v);			//defino un variables locales
			v.mark = true;		//trato primer elemento
			a.add(v.element);
			Vertex <T> x, w;
			while(! s.empty()){
				x=s.peek();			//tomo el elemento top del stack
				w=x.nextAdj();
				if (w != null){		//busco el siguiente adyacente no marcado
					w.mark = true;	//lo marco y lo proceso
					s.push(w);
					a.add(w.element);
				}
				else{				//si no hay elemento sig no marcado saco el anterior (backtracking)
					s.pop();
				}
			}
			showList(a);
			this.unmark();
		}else{
			throw new ExceptionGraph("Graph.dfs: El vertice de inicio no pertenece al grafo.");
		}
	}
	
	private void unmark(){				//desmarcar todos los vertices de un grafo
		int i = 0;
		while(i < nodesX.size()){
			nodesX.get(i).mark=false;
			i++;
		}
	}
	
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

	
	
	private void aux1(Vertex<T> a, LinkedList<T> auxList){
		auxList.add(a.element);					//dfs recursivo, el tratamiento de cada vertice es
		a.mark=true;							//ser insertado en lista
		while(a.nextAdj() != null){
			aux1(a.nextAdj(), auxList);
		}
	}
	
	private void showList(LinkedList<T> auxList){
			int i=0;									//recorro una lista imprimiendo valores
			while(i<auxList.size()){
				System.out.print(auxList.get(i));
				i++;
			}
			System.out.println("");
	}
	
	
	public Graph<T> prims(Vertex<T> beg)throws ExceptionGraph{
		if (this.belongs(beg)){	
			Graph<T> n = new Graph<T>(beg);			//arbol abarcador
			beg.mark=true;							//marco el vertice inicial
			Vertex<T> a = new Vertex<T> (beg.element);
			n.insert(a);							//inserto dicho vertice
			int i=0;
			while(i<(nodesX.size()-1)){				//busco vertice mas cercano al arbol y lo inserto
				bfs1(n);							//n-1 veces, cantidad mayor a insertar
				i++;								//teniendo en cuenta que ya se inserto el inicial
			}
			n.unmark();								//desmarco grafos
			this.unmark();
			return n;
		}else{
			throw new ExceptionGraph("Graph.prims: El vertice de inicio no pertenece al grafo.");
		}
	}
	
	
	private void bfs1(Graph<T> n){
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
		insArb(n,back, aux);			//inserto dicha arista, si todos estaban marcados es null
	}

	private void insArb(Graph<T> n, Vertex<T> back, Edge<T> x ){
		if ((back!=null) && (x!=null)){			//control de valores null
			x.dest.mark=true;					//marco e inserto en nuevo grafo (arbol abarcador)
			n.insert(x.dest);
			try{
				n.connect(back,x.dest,x.cost);
			}catch(ExceptionGraph z){
				System.out.println(z.getMessage());
			}
		}
	}
}
