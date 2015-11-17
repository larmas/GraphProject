
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
		
		System.out.println(test.empty());			//Debe devolver True (1)

		test.insert(a);								//inserto todos los vertices menos e
		test.insert(b);
		test.insert(c);
		test.insert(d);
		System.out.println(test.empty());			//Debe devolver false (2)
		System.out.println(test.belongs(a));		//Debe devolver true (3)
		System.out.println(test.belongs(e));		//Debe devolver false (4)

		test.insert(e);								//Inserto vertice faltante

		try{
			test.connect(a,b,1);						//Conecto y defino pesos de aristas
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

		//Pruebo contar vert, contar aristas, existe arista

		System.out.println(test.connected(a,b));	//Debe devolver true (5)
		System.out.println(test.connected(a,d));	//Debe devolver false (6)
		System.out.println(test.vertQ());			//Debe devolver 5 (8)
		System.out.println(test.edgeQ());			//Debe devolver 9 (9)
		
		//Pruebo dfs, dfsRec y prims

		try{
			test.dfs(b);				//Debe devolver 23451 (10)
			test.dfsRec(a); 			//Debe devolver 12345 (11)
			test2=test.prims(c);		//Debe devolver 34512 (12)
			test2.dfsRec(b);			//Debe devolver 23451 (13)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.delVert(c);			//Elimino el vertice c
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.dfs(b);				//Debe devolver 245 (14)
			test.dfsRec(a);				//Debe devolver 1245 (15)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		try{
			test2=test.prims(b);//Debe devolver 245 (16) 
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		try{
			test2.dfsRec(d);			//Debe devolver 45 (17)   
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.delEdge(d,e);			//Elimino arista d-e
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.dfs(b);				//Debe devolver 24  (18)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		try{
			test.dfsRec(a);				//Debe devolver 124 (19)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		try{
			test2=test.prims(b);		//Debe devolver 24 (20)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		try{
			test2.dfsRec(d);			//Debe devolver 4  (21)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
	}
}//fin de la clase TestGraph
