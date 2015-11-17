/////////////////////////////////////////////////////////////////////////////////
//--------Clase utilizada para testear los algoritmos implementados------------//
/////////////////////////////////////////////////////////////////////////////////

public class TestGraph {
	
	public static void main(String[] args) {
		int testNumb=0;
		
		/* Defino 2 grafos y 5 vertices, para testear*/
		Graph<Integer> test = new Graph<Integer>();
		Graph<Integer> test2= new Graph<Integer>();
		Vertex<Integer> a, b, c, d, e;
		
		a=new Vertex<Integer>(1);
		b=new Vertex<Integer>(2);
		c=new Vertex<Integer>(3);
		d=new Vertex<Integer>(4);
		e=new Vertex<Integer>(5);
		System.out.println(("1) "+test.empty()));		//Debe devolver True (1)

		test.insert(a);									//Inserto todos los vertices menos e (5)
		test.insert(b);
		test.insert(c);
		test.insert(d);
		System.out.println("2) "+test.empty());			//Debe devolver false (2)
		System.out.println("3) "+test.belongs(a));		//Debe devolver true (3)

		System.out.println("4) "+test.belongs(e));		//Debe devolver false (4)

		test.insert(e);									//Inserto vertice faltante

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

		//pruebo contar vert, contar aristas, existe arista
		

		System.out.println("5) "+test.connected(a,b));	//Debe devolver true (5)

		System.out.println("6) "+test.connected(a,d));	//Debe devolver false (6)

		System.out.println("7) "+test.vertQ());			//Debe devolver 5 (7)

		System.out.println("8) "+test.edgeQ());			//Debe devolver 9 (8)
		
		//Pruebo dfs, dfsRec y prims

		try{
			System.out.print("9) ");
			test.dfs(b);				//Debe devolver 2,3,4,5,1 (9)
			System.out.print("10) ");
			test.dfsRec(a); 			//Debe devolver 1,2,3,4,5 (10)
			System.out.print("11) ");
			test2=test.prims(c);		//Debe devolver 3,4,5,1,2 (11)
			System.out.print("12) ");
			test2.dfsRec(b);			//Debe devolver 2,3,4,5,1 (12)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.delVert(c);			//Elimino el vertice c
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			System.out.print("13) ");
			test.dfs(b);				//Debe devolver 245 (13)
			System.out.print("14) ");
			test.dfsRec(a);				//Debe devolver 1245 (14)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		try{
			System.out.print("15) ");
			test2=test.prims(b);		//Debe devolver 245 (15)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		//test2=test;  //Haciendo esto se soluciona el problema de la excepcion (pero no es lo mismo que test2=test.prims(d) 
		try{
			System.out.print("16) ");
			test2.dfsRec(d);			//Debe devolver 45 (16)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			test.delEdge(d,e);			//elimino arista d-e
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}

		try{
			System.out.print("17) ");
			test.dfs(b);				//Debe devolver 24 (17)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		try{
			System.out.print("18) ");
			test.dfsRec(a);				//Debe devolver 124 (18)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		try{
			System.out.print("19) ");
			test2=test.prims(b);		//Debe devolver 24 (19)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
		
		try{
			System.out.print("20) ");
			test2.dfsRec(d);			//Debe devolver 4  (20)
		}catch(ExceptionGraph z){
			System.out.println(z.getMessage());
		}
	}
}//Fin de la clase TestGraph
