package grafos;

public class TesteListaAdjacencia {

	public static void main(String[] args) {
		ListaAdjacencia G=new ListaAdjacencia(3,false);
		G.adicionarAresta(0, 1, 4);
		G.adicionarAresta(0, 2, 5);
		G.adicionarAresta(1, 2, 6);
		G.mostrarListaAdjacencia();
		G.mostrarVizinhos(1);
		System.out.println("1 e 1 sao vizinhos?");
		System.out.println(G.Vizinhos(1,1));
	}
}
