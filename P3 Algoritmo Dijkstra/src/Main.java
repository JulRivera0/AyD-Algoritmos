public class Main {
	
	public static void 	main(String[] args) {
		boolean dirigido = false;
		boolean auto = false;
		int n=30;
		
		Grafo grafoER = Grafo.ErdosRenyi(n, 1800, dirigido, auto);
		grafoER.RandomCostosArista(1, 50);
		grafoER.GrafoToViz("Grafo"+n+"Erdos");
		Grafo grafoDjkER = grafoER.Dijkstra(2);
		grafoDjkER.GrafoToViz("Arbol"+n+"Dijkstra_Erdos");
		

		Grafo grafoGil = Grafo.Gilbert(n, 0.1, dirigido, auto);
		grafoGil.RandomCostosArista(1, 50);
		grafoGil.GrafoToViz("Grafo"+n+"Gilbert");
		Grafo grafoDjkGil = grafoGil.Dijkstra(2);
		grafoDjkGil.GrafoToViz("Arbol"+n+"Dijkstra_Gilbert");

		Grafo grafoGeo = Grafo.Geografico(n, 0.3, dirigido, auto);
		grafoGeo.RandomCostosArista(1, 50);
		grafoGeo.GrafoToViz("Grafo"+n+"Geografico");
		Grafo grafoDjkGeo = grafoGeo.Dijkstra(2);
		grafoDjkGeo.GrafoToViz("Arbol"+n+"Dijkstra_Geografico");
		
		Grafo grafoBA = Grafo.BarabasiAlbert(n, 7, dirigido, auto);
		grafoBA.RandomCostosArista(1, 50);
		grafoBA.GrafoToViz("Grafo"+n+"Barabasi");
		Grafo grafoDjkBA = grafoBA.Dijkstra(2);
		grafoDjkBA.GrafoToViz("Arbol"+n+"Dijkstra_Barabasi");
		
		
		//Grafo grafoBFS = grafoER.BFS(2);
		//grafoBFS.GrafoToViz("Grafo"+n+"Erdos_BFS");
		//Grafo grafoDFSI = grafoER.DFS_I(2);
		//grafoDFSI.GrafoToViz("Grafo"+n+"Erdos_DFS_I");
		//Grafo grafoDFSR = grafoER.DFS_I(2);
		//grafoDFSR.GrafoToViz("Grafo"+n+"Erdos_DFS_R");
			
		
	}

}