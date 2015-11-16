
public class TestGraph {

	public static void main(String[] args) {
		/* Defino 2 grafos y 5 vertices, para testear*/
		Graph<Integer> test= new Graph<Integer>();
		Graph<Integer> test2= new Graph<Integer>();
		Vertex<Integer> a, b, c, d, e;
		a=new Vertex<Integer>(1);
		b=new Vertex<Integer>(2);
		c=new Vertex<Integer>(3);
		d=new Vertex<Integer>(4);
		e=new Vertex<Integer>(5);
		
		System.out.println(test.empty());			//true *1

		test.insert(a);								//inserto todos los vertices menos e (5)
		test.insert(b);
		test.insert(c);
		test.insert(d);

		System.out.println(test.empty());			//false *2
		System.out.println(test.belongs(a));		//true *3
		System.out.println(test.belongs(e));		//false *4

		test.insert(e);								//inserto nodo faltante

		try{
			test.connect(a,b,1);						//conecto y defino pesos de aristas
			test.connect(b,c,2);
			test.connect(b,d,3);
			test.connect(c,d,4);
			test.connect(c,a,8);
			test.connect(c,e,4);
			test.connect(d,e,5);
			test.connect(e,d,6);
			test.connect(e,c,4);
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		//pruebo contar vert, contar aristas, existe arista

		System.out.println(test.connected(a,b));	//true *5
		System.out.println(test.connected(a,d));	//false *6
		System.out.println(test.vertQ());			//5 *8
		System.out.println(test.edgeQ());			//9 *9
		
		//Pruebo dfs, dfsRec y prims

		try{
			test.dfs(b);				//23451	*10
			test.dfsRec(a); 			//12345	*11
			test2=test.prims(d);
			test2.dfsRec(b);			//23451 *13
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.delVert(c);			//elimino c
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.dfs(b);				//245 *14
			test.dfsRec(a);				//1245 *15
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		try{
			test2=test.prims(d);
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		test2=test;  //Haciendo esto se soluciona el problema de la excepcion (pero no es lo mismo que test2=test.prims(d) 
		try{
			test2.dfsRec(b);			//245 *16    
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.delEdge(d,e);			//elimino arista d-e
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.dfs(b);				//24  *17
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		try{
			test.dfsRec(a);
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		try{
			test2=test.prims(d);		//124 *18
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		test2=test;  //Haciendo esto se soluciona el problema de la excepcion (pero no es lo mismo que test2=test.prims(d) 
		try{
			test2.dfsRec(b);			//24  *19
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
	}

}
