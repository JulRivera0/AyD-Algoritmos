public class Main {
	
	public static void 	main(String[] args) {
		boolean dirigido = false, auto = false;
		
		Grafo grafo0 = new Grafo();
		Grafo grafo1 = new Grafo();
		Grafo grafo2 = new Grafo();
		
		
		int n=500;
		//int n=100;
		//int n=500;
/*		
		grafo0.ErdosRenyi(n, 2500, dirigido, auto);
		new GrafoToViz("Grafo"+n+"Erdos", grafo0);
		grafo1=grafo0.BFS(2);
		new GrafoToViz("Grafo"+n+"Erdos_BFS", grafo1);
		grafo2=grafo0.DFS_I(2);
		new GrafoToViz("Grafo"+n+"Erdos_DFS_I", grafo2);
		grafo0.DFS_R(2);
		new GrafoToViz("Grafo"+n+"Erdos_DFS_R", grafo0);

		grafo0.Gilbert(n, 0.2, dirigido, auto);
		new GrafoToViz("Grafo"+n+"Gilbert", grafo0);
		grafo1=grafo0.BFS(2);
		new GrafoToViz("Grafo"+n+"Gilbert_BFS", grafo1);
		grafo2=grafo0.DFS_I(2);
		new GrafoToViz("Grafo"+n+"Gilbert_DFS_I", grafo2);
		grafo0.DFS_R(2);
		new GrafoToViz("Grafo"+n+"Gilbert_DFS_R", grafo0);
	
		grafo0.Geografico(n, 0.3, dirigido, auto);
		new GrafoToViz("Grafo"+n+"Geografico", grafo0);
		grafo1=grafo0.BFS(2);
		new GrafoToViz("Grafo"+n+"Geografico_BFS", grafo1);
		grafo2=grafo0.DFS_I(2);
		new GrafoToViz("Grafo"+n+"Geografico_DFS_I", grafo2);
		grafo0.DFS_R(2);
		new GrafoToViz("Grafo"+n+"Geografico_DFS_R", grafo0);
*/	
		grafo0.BarabasiAlbert(n, 7, dirigido, auto);
		new GrafoToViz("Grafo"+n+"Barabasi", grafo0);
		grafo1=grafo0.BFS(2);
		new GrafoToViz("Grafo"+n+"Barabasi_BFS", grafo1);
		grafo2=grafo0.DFS_I(2);
		new GrafoToViz("Grafo"+n+"Barabasi_DFS_I", grafo2);
		grafo0.DFS_R(2);
		new GrafoToViz("Grafo"+n+"Barabasi_DFS_R", grafo0);


	}

}