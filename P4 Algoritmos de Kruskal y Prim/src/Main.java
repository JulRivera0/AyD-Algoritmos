public class Main {
	
	public static void 	main(String[] args) {
		boolean dirigido = false;
		boolean auto = false;
		int n=30;
		
		Grafo grafoER = Grafo.ErdosRenyi(n, 85, dirigido, auto);
		grafoER.RandomCostosArista(1, 50);
		grafoER.GrafoToViz(n+"Grafo"+"Erdos");
		Grafo grafoKD = grafoER.Kruskal_D();
		grafoKD.GrafoToViz(n+"Arbol"+"Kruskal_D_Erdos");
		Grafo grafoKI = grafoER.Kruskal_I();
		grafoKI.GrafoToViz(n+"Arbol"+"Kruskal_I_Erdos");
		Grafo grafoPrim = grafoER.Prim();
		grafoPrim.GrafoToViz(n+"Arbol"+"Prim_Erdos");
		
		/*
		Grafo grafoGil = Grafo.Gilbert(n, 0.05, dirigido, auto);
		grafoGil.RandomCostosArista(1, 50);
		grafoGil.GrafoToViz("Gilbert_"+n+"Grafo");
		Grafo grafoKD = grafoGil.Kruskal_D();
		grafoKD.GrafoToViz("Gilbert_"+n+"Arbol"+"Kruskal_D");
		Grafo grafoKI = grafoGil.Kruskal_I();
		grafoKI.GrafoToViz("Gilbert_"+n+"Arbol"+"Kruskal_I");
		Grafo grafoPrim = grafoGil.Prim();
		grafoPrim.GrafoToViz("Gilbert_"+n+"Arbol"+"Prim");
		
		Grafo grafoGeo = Grafo.Geografico(n, 0.13, dirigido, auto);
		grafoGeo.RandomCostosArista(1, 50);
		grafoGeo.GrafoToViz("Geografico_"+n+"Grafo");
		Grafo grafoKD = grafoGeo.Kruskal_D();
		grafoKD.GrafoToViz("Geografico_"+n+"Arbol"+"Kruskal_D");
		Grafo grafoKI = grafoGeo.Kruskal_I();
		grafoKI.GrafoToViz("Geografico_"+n+"Arbol"+"Kruskal_I");
		Grafo grafoPrim = grafoGeo.Prim();
		grafoPrim.GrafoToViz("Geografico_"+n+"Arbol"+"Prim");
		
		
		Grafo grafoBA = Grafo.BarabasiAlbert(n, 8, dirigido, auto);
		grafoBA.RandomCostosArista(1, 50);
		grafoBA.GrafoToViz("Barabasi_"+n+"Grafo");
		Grafo grafoKD = grafoBA.Kruskal_D();
		grafoKD.GrafoToViz("Barabasi_"+n+"Arbol"+"Kruskal_D");
		Grafo grafoKI = grafoBA.Kruskal_I();
		grafoKI.GrafoToViz("Barabasi_"+n+"Arbol"+"Kruskal_I");
		Grafo grafoPrim = grafoBA.Prim();
		grafoPrim.GrafoToViz("Barabasi_"+n+"Arbol"+"Prim");
		*/ 

		//Grafo grafoER = Grafo.ErdosRenyi(n, 100, dirigido, auto);
		//grafoER.RandomCostosArista(1, 50);
		//grafoER.GrafoToViz("Grafo"+n+"Erdos");
		//Grafo grafoDjkER = grafoER.Dijkstra(2);
		//grafoDjkER.GrafoToViz("Arbol"+n+"Dijkstra_Erdos");
		
		//Grafo grafoBFS = grafoER.BFS(2);
		//grafoBFS.GrafoToViz("Grafo"+n+"Erdos_BFS");
		//Grafo grafoDFSI = grafoER.DFS_I(2);
		//grafoDFSI.GrafoToViz("Grafo"+n+"Erdos_DFS_I");
		//Grafo grafoDFSR = grafoER.DFS_I(2);
		//grafoDFSR.GrafoToViz("Grafo"+n+"Erdos_DFS_R");
			
		
	}

}