//////////////////////////////////////////////////////////////////////////////
//------------Clase utilizada para redefinir los metodos de la--------------//
//------------clase LinkedList en terminos de una arista--------------------//
//////////////////////////////////////////////////////////////////////////////

import java.util.LinkedList;

public class EdgeList<T> extends LinkedList<Edge<T>>{
	
	public EdgeList(){
		super();
	}
	
	/* Redefino el metodo contains en terminos de un vertex<T> */
	public boolean contains(Vertex<T> a){	
		int i=0;							
		while ((i<this.size()) && (this.get(i).dest.element!=a.element)){
			i++;
		}
		return (i<this.size());
	}
	
	/*Redefino el metodo remove(object o) en terminos de un vertex<T>*/
	public boolean remove(Vertex<T>a){
		int i=0;						
		while ((i<this.size()) && (this.get(i).dest.element!=a.element)){
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
}//Fin de la clase EdgeList<T>
