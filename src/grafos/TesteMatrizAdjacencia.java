package grafos;

public class TesteMatrizAdjacencia 
{
	public static void main(String[] args) 
	{
		MatrizAdjacencia grafo1 = new MatrizAdjacencia(3, false, false);
		grafo1.inserirAresta(0, 1);
		grafo1.inserirAresta(1, 2);
		grafo1.inserirAresta(2, 0);
		System.out.println("Grafo 1");
		grafo1.info();

		MatrizAdjacencia grafo2 = new MatrizAdjacencia(3, false, true);
		grafo2.inserirAresta(0, 1);
		grafo2.inserirAresta(1, 2);
		grafo2.inserirAresta(2, 0);
		System.out.println("Grafo 2");
		grafo2.info();
		

		MatrizAdjacencia grafo3 = new MatrizAdjacencia(3, true, false);
		grafo3.inserirAresta(0, 1, 5);
		grafo3.inserirAresta(1, 2, 4);
		grafo3.inserirAresta(2, 0, 3);
		System.out.println("Grafo 3");
		grafo3.info();
	
		MatrizAdjacencia grafo4 = new MatrizAdjacencia(4, true, false);
		grafo4.inserirAresta(0, 1, 1);
		grafo4.inserirAresta(1, 2, 2);
		grafo4.inserirAresta(2, 0, 3);
		grafo4.inserirAresta(0, 3, 4);
		System.out.println("Grafo 4");
		grafo4.info();
	
		grafo4.inserirAresta(2, 3, 4);
		grafo4.info();

		grafo4.imprimirDijkstra(0);
		grafo4.imprimirDijkstra(1);
		grafo4.imprimirDijkstra(2);
		grafo4.imprimirDijkstra(3);
	
	System.out.println("==========\nGrafo do exercício 2, letra a:");
	MatrizAdjacencia grafo5 = new MatrizAdjacencia(5, true, false);
	grafo5.inserirAresta(0, 1, 1);
	grafo5.inserirAresta(0, 4, 10);
	grafo5.inserirAresta(0, 3, 3);
	grafo5.inserirAresta(1, 2, 5);
	grafo5.inserirAresta(2, 4, 1);
	grafo5.inserirAresta(3, 2, 2);
	grafo5.inserirAresta(3, 4, 6);
	
	grafo5.info();
	grafo5.imprimirDijkstra(0);
	
	grafo5.mostrarMatrizAdjacencia();
	grafo5.removerVertice(1);
	System.out.println("-");
	grafo5.mostrarMatrizAdjacencia();
	
	
}}
