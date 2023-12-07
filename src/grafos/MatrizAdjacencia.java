package grafos;

import java.util.Arrays;

public class MatrizAdjacencia {
	private int[][] G;
	private int numVertices;
	private boolean ponderado;
	private boolean direcionado;

//Construtor
	public MatrizAdjacencia(int numVertices, boolean ponderado, boolean direcionado)
		{this.numVertices=numVertices;
		 this.ponderado=ponderado;
		 this.direcionado=direcionado;
		 G=new int[numVertices][numVertices];}

//Modificando um elemento específico da matriz
	private void setAresta(int vertice1,int vertice2, int peso)
		{G[vertice1][vertice2]=peso;
		 if (!direcionado) {G[vertice2][vertice1]=peso;}}

//Inserir aresta	
	public void inserirAresta(int vertice1, int vertice2, int peso)
		{if (ponderado) {setAresta(vertice1,vertice2, peso);}
		else {System.out.println("Grafo não ponderado. Não pode ter o peso da aresta");}}
	
	public void inserirAresta(int vertice1, int vertice2)
		{if (!ponderado) 
			  {setAresta(vertice1,vertice2,1);}
		 else {System.out.println("Grafo ponderado. Precisa do peso da aresta");}}
	
//Apagar aresta	
	public void apagarAresta(int vertice1, int vertice2)
		{setAresta(vertice1,vertice2,0);}

//Print	
	public void mostrarMatrizAdjacencia()
		{for (int i=0; i<numVertices;i++)
			{for (int j=0; j<numVertices; j++)
				{System.out.print(G[i][j]+" ");}
			 System.out.println();}
		 }

//Grau de um vértice
	public int grau(int vertice)
		{int grau=0;
		 for (int i=0;i<numVertices;i++)
			{if (G[i][vertice]!=0 || G[vertice][i]!=0) grau+=1;}
		 return grau;}

	public int grauEntrada(int vertice)
		{int grau=0;
		 for (int i=0;i<numVertices;i++)
			{if (G[i][vertice]!=0) grau+=1;}
		 return grau;}

	public int grauSaida(int vertice)
		{int grau=0;
		 for (int i=0;i<numVertices;i++)
			{if (G[vertice][i]!=0) grau+=1;}
		 return grau;}

	
//São adjacentes?	
	public boolean saoAdjacentes(int vertice1,int vertice2)
		{return (G[vertice1][vertice2]!=0);}
	
//Exercício 1: Remover um vértice	
	public void removerVertice(int vertice)
		{for (int i=0; i<numVertices;i++)
			{apagarAresta(vertice, i);
			apagarAresta(i, vertice);}
		 }

//Exercício 2: Verificar se um grafo é conexo
	public boolean isConexo()
		{boolean[] parteConexa = new boolean[numVertices];
		isConexoRec(0,parteConexa);
        for (boolean i : parteConexa) 
        	{if (!i) {return false;}}
		return true;}
	
    private void isConexoRec(int vertice, boolean[] parteConexa) 
    	{parteConexa[vertice] = true;
         for (int i = 0; i < numVertices; i++) 
        	{if ((G[vertice][i]!=0) && !parteConexa[i]) 
        		{isConexoRec(i, parteConexa);}
        	if ((G[i][vertice]!=0) && !parteConexa[i]) 
    			{isConexoRec(i, parteConexa);}}
    	}

	public boolean isFortConexo()
		{for (int i = 0; i < numVertices; i++) 
			{boolean[] parteConexa = new boolean[numVertices];
			 isFortConexoRec(i, parteConexa);
			 for (boolean visitado : parteConexa) 
			 	{if (!visitado) return false;}
			 }
		 return true;
		 }
		

    private void isFortConexoRec(int vertice, boolean[] parteConexa) 
		{parteConexa[vertice] = true;
		 for (int i = 0; i < numVertices; i++) 
		 	{if (G[vertice][i] != 0 && !parteConexa[i]) 
		 		{isFortConexoRec(i, parteConexa);}}
	    }


//Exercício 3:  Verificar se um grafo é completo.
    public boolean isCompleto()
    	{for (int i=0;i<numVertices;i++)
    		{for (int j=0;j<numVertices;j++)
    			{if (i!=j && G[i][j]==0) {return false;}}}
    	 return true;}
//Exercício 4: Implementar o algoritmo de Dijkstra para encontrar o caminho mais curto entre dois vértices em um grafo ponderado.   
    public int[] dijkstra(int inicio) 
    	{int[] distancias = new int[numVertices];
         boolean[] visitado = new boolean[numVertices];

         Arrays.fill(distancias, Integer.MAX_VALUE); //preenchendo tudo com infinito
         distancias[inicio] = 0;

         for (int count = 0; count < numVertices - 1; count++) 
         	{int i = encontrarVerticeMenorDistancia(distancias, visitado);
             visitado[i] = true;

             for (int j = 0; j < numVertices; j++) 
             	{if ((!visitado[j] && G[i][j] != 0) 
             		 && (distancias[i] != Integer.MAX_VALUE) 
             		 && (distancias[i] + G[i][j] < distancias[j])) 
             		 {distancias[j] = distancias[i] + G[i][j];}}
         	 }
	      return distancias;}

    private int encontrarVerticeMenorDistancia(int[] distancias, boolean[] visitado) 
    	{int min = Integer.MAX_VALUE, minIndex = -1;

         for (int i = 0; i < numVertices; i++) 
         	{if (!visitado[i] && distancias[i] <= min) 
         		{min = distancias[i];
                 minIndex = i;}}
         return minIndex;}
    
    
    
//Exercício 5: Verificar se um grafo é euleriano, semieuleriano ou não euleriano.
//###Grafos eulerianos:
//# Se G é direcionado, https://www.ibilce.unesp.br/Home/Departamentos/MatematicaAplicada/docentes/socorro/grafoseulerianos.pdf
//é euleriano <=> fortemente conexo e o grau de entrada é igual ao grau de saída pra todos os vértices
//# Se G não é direcionado, https://www.ibilce.unesp.br/Home/Departamentos/MatematicaAplicada/docentes/socorro/grafoseulerianos.pdf 
//é euleriano <=> conexo e o grau de cada vértice é par

//###Grafos semieulerianos:
//# Se G é direcionado, https://www.ibilce.unesp.br/Home/Departamentos/MatematicaAplicada/docentes/socorro/grafoseulerianos.pdf
//é semieuleriano <=> fortemente conexo e no máximo dois vértices têm grau de entrada=grau de saída+-1, o resto tem os graus iguais
//# Se G não é direcionado, https://www.ibilce.unesp.br/Home/Departamentos/MatematicaAplicada/docentes/socorro/grafoseulerianos.pdf
//é semieuleriano <=> conexo e exatamente 2 vértices têm grau ímpar e os outros têm grau par 

	public enum ternary {Sim, Nao, Semi}    
	
	private ternary calcularTernary(int qtdErrados)
		{if (qtdErrados==0) {return ternary.Sim;}
		 else if (qtdErrados==2) {return ternary.Semi;}
		 else return ternary.Nao;}
	
	public ternary isEuleriano() //0:não é, 1: semieuleriano, 2: euleriano    
		{if (!isDirecionado())
			{//Grafos direcionados
			 if (isConexo())
			 	{int qtdGrausImpares=0;
			 	 for (int i=0;i<numVertices;i++)
			 	 	{if (grau(i)%2==1) {qtdGrausImpares+=1;}}
			 	 return calcularTernary(qtdGrausImpares);
			 	 }
			 else {return ternary.Nao;}
			}
		else {//Grafos não direcionados
			  if (isFortConexo())
			  	{int qtdGrausDiferentes=0;
			  	for (int i=0;i<numVertices;i++)
		 	 		{int diferenca=grauEntrada(i)-grauSaida(i);
		 	 		 if ((diferenca<-2)||(diferenca>2)) {return ternary.Nao;}
		 	 		 else if (diferenca!=0) {qtdGrausDiferentes+=1;}}
			  	return calcularTernary(qtdGrausDiferentes);  
			  	}
			  else {return ternary.Nao;}}
		 }
	    
	//Exercício 6: Verificar se um grafo é Hamiltoniano, semi-hamiltoniano ou não hamiltoniano.    
	    
	public ternary isHamiltoniano() 
		{int[] caminho = new int[numVertices];
	     boolean[] visitado = new boolean[numVertices];
	
	     caminho[0] = 0;
	     visitado[0] = true;
	     if (hamiltonianoRec(caminho, 1, visitado)&&(caminho[numVertices - 1] != -1))
	     	{return ternary.Sim;}
	     else {for (int i=1;i<numVertices;i++)
			     	{Arrays.fill(caminho, -1);
	    		     caminho[0]=i;
			     	 Arrays.fill(visitado, false);
			     	 visitado[i]=true;
			    	 if (hamiltonianoRec(caminho, 1, visitado)) return ternary.Semi;}
	     		return ternary.Nao;}
		 }
	
	private boolean hamiltonianoRec(int[] caminho, int posicao, boolean[] visitado) 
		{if (posicao == numVertices) {return true;}
	
	     for (int i = 0; i < numVertices; i++) 
	     	{if (saoAdjacentes(caminho[posicao - 1], i) && !visitado[i]) 
	     		{caminho[posicao] = i;
	             visitado[i] = true;
	
	             if (hamiltonianoRec(caminho, posicao + 1, visitado)) return true;
	
	             visitado[i] = false;}
			     //for (int j=0;j<numVertices;j++)
			     //	{System.out.print(caminho[j]+",");}
		     	 //System.out.println("");
	     	 }
	     caminho[posicao] = -1;  
	     return false;}


	public void imprimirDijkstra(int inicio)
		{System.out.println("Distância em relação ao vértice "+inicio);
		 int[] dij = dijkstra(inicio);
		 for (int i = 0; i < numVertices; i++)
		 	{System.out.println("para o vértice "+i+": "+dij[i]);}
		 }
	
	public void info()
		{System.out.println("________________\nMatriz de adjacência:");
		 mostrarMatrizAdjacencia();
		 System.out.println("Completo: "+isCompleto());
		 System.out.println("Conexo: "+isConexo());
		 System.out.println("Hamiltoniano: "+isHamiltoniano());
		 System.out.println("Euleriano: "+isEuleriano());}




    
    
    
	public int[][] getG() {return G;}
	public void setG(int[][] g) {G = g;}
	public int getNumVertices() {return numVertices;}
	//public void setNumVertices(int numVertices) {this.numVertices = numVertices;}
	public boolean isPonderado() {return ponderado;}
	public void setPonderado(boolean ponderado) {this.ponderado = ponderado;}
	public boolean isDirecionado() {return direcionado;}
	//public void setDirecionado(boolean direcionado) {this.direcionado = direcionado;}
	
	
	
	
	
	
	
}
