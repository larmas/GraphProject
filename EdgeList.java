import java.util.LinkedList;

public class EdgeList<T> extends LinkedList<Edge<T>>{
	
	public EdgeList(){
		super();
	}
	
	public boolean contains(Vertex<T> a){	//redefino contains en terminos de un vertex<T>
		int i=0;							
		while ((i<this.size()) && (this.get(i).dest!=a)){
			i++;
		}
		return (i<this.size());

	}
	
	public boolean remove(Vertex<T>a){  //redefino el remove(object o) en terminos de un vertex<T>
		int i=0;						
		while ((i<this.size()) && (this.get(i).dest!=a)){
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

}
