//////////////////////////////////////////////////////////////////////////////
//------------Clase utilizada para redefinir los metodos de la--------------//
//------------clase LinkedList en terminos de un vertice--------------------//
//////////////////////////////////////////////////////////////////////////////

import java.util.LinkedList;

public class VertexList<T> extends LinkedList<Vertex<T>> {
	
	public VertexList(){
		super();
	}
	
	/*Redefino el metodo contains en terminos de un vertex<T>*/
	public boolean contains(Vertex<T> a){
		int i=0;							
		while ((i<this.size()) && (this.get(i).element!=a.element)){
			i++;
		}
		return (i<this.size());
	}
	
	/*Redefino el remove(object o) en terminos de un vertex<T>*/
	public boolean remove(Vertex<T>a){  
		int i=0;						
		while ((i<this.size()) && (this.get(i).element!=a.element)){
			i++;
		}
		if(i<this.size()){
			this.remove(i);
			return true;
		}
		else{
			return false;
		}
	}
}//Fin de la clase VertexList<T>