public class Main {
	
	public static void 	main(String[] args) {
		boolean dirigido = false, auto = false;
		
		Grafo grafo0 = new Grafo();
		Grafo grafo1 = new Grafo();
		Grafo grafo2 = new Grafo();
		Grafo grafo3 = new Grafo();
		
		//int n=30;
		//int n=100;
		int n=500;
		
		grafo0.ErdosRenyi(n, 2000, dirigido, auto);
		grafo1.Gilbert(n, 0.2, dirigido, auto);
		grafo2.Geografico(n, 0.3, dirigido, auto);
		grafo3.BarabasiAlbert(n, 7, dirigido, auto);
		
		new GrafoToViz("Grafo"+n+"Erdos", grafo0);
		new GrafoToViz("Grafo"+n+"Gilbert", grafo1);
		new GrafoToViz("Grafo"+n+"Geografico", grafo2);
		new GrafoToViz("Grafo"+n+"Barabasi", grafo3);
		
	}

}