package grafos;
import java.util.*;

class Aresta
	{int origem;
	 int destino;
	 int peso;
	 
	 public Aresta(int origem, int destino, int peso)
	 	{this.origem=origem;
	 	 this.destino=destino;
	 	 this.peso=peso;}
	 }

public class ListaAdjacencia {
	
	private int nVertices;
	//private boolean ponderado;
	private boolean direcionado;
	
	private List<List<Aresta>> adjacencias;
	
	public ListaAdjacencia(int nVertices, boolean direcionado)
		{this.nVertices=nVertices;
		 this.direcionado=direcionado;
		 this.adjacencias=new ArrayList<>(nVertices);
		 for (int i=0;i<nVertices;i++) 
		 	{adjacencias.add(new ArrayList<Aresta>());}
		 }
	
	private void addArestaSimples(int origem, int destino, int peso)
		{adjacencias.get(origem).add(new Aresta(origem, destino, peso));}

	public void adicionarAresta(int origem, int destino, int peso)
		{addArestaSimples(origem, destino, peso);
		 if (!direcionado) {addArestaSimples(destino, origem, peso);}}

	public void adicionarAresta(int origem, int destino)
		{adicionarAresta(origem, destino,1);}	

	public void removerArestaSimples(int origem, int destino)
		{List<Aresta> arestasOrigem = adjacencias.get(origem);
		 for (Aresta aresta: arestasOrigem)
			 {if (aresta.destino==destino) 
			 	{arestasOrigem.remove(aresta); break;}}
		 }	

	public void removerAresta(int origem, int destino)
		{removerArestaSimples(origem, destino); 
		 if (!direcionado) {removerArestaSimples(destino, origem);}}	
	
	
	public void mostrarListaAdjacencia()
		{for (int i=0; i<nVertices; i++)
			{System.out.print("\n Vertice "+i+": ");
			 for (Aresta aresta : adjacencias.get(i))
				 {System.out.print("("+aresta.destino+", Peso: "+aresta.peso+")");}}
		 }
	
	public void mostrarVizinhos(int i) 
		{System.out.println("\n---\nLista de arestas de "+i+":");
		 for (Aresta aresta : adjacencias.get(i))
			{System.out.print("("+aresta.destino+", Peso: "+aresta.peso+")");}
		 System.out.println("\n---");
		 }
	
	public boolean Vizinhos(int i, int j)
		{for (Aresta aresta : adjacencias.get(i))
			{if (aresta.destino==j) return true;}
		 return false;
		 }
	
}
