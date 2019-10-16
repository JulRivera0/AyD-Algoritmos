import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Grafo {
	
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	boolean dirigido;
	int n;
	int ContArista=1;
	
	
	public static Grafo ErdosRenyi(int n, int m, boolean dirigido, boolean auto) {
		Grafo grafER  =new Grafo();
		grafER.dirigido=dirigido;
		grafER.n=n;
		
		for(int i=1; i<=m; i++) {
			int [] nod=RandomPar(n,auto);
			Arista ai = new Arista(nod[0], nod[1]);
			grafER.arista.put(i,ai);
				
			for(int j=1;j<i;j++) {
				Arista aj = grafER.arista.get(j);
				if(aj.n1==nod[0] && aj.n2==nod[1] ||(aj.n1==nod[1] && aj.n2==nod[0] && dirigido==false)) {
					i--;
					break;
				}	
			}
		}
		grafER.NodoIncidentes();
		return grafER;
	}

	public static Grafo Gilbert(int n, double p, boolean dirigido, boolean auto) {
		Grafo grafGil  =new Grafo();
		grafGil.dirigido=dirigido;
		grafGil.n=n;
		int k=1;
		Random rand = new Random();

		for(int i=1; i<n; i++) {
			for(int j=i+1; j<=n;j++) {
				if(p>rand.nextDouble() ) {
					if(j!=i) {
						Arista ai = new Arista(j,i);
						grafGil.arista.put(k,ai);
						k++;
						if(dirigido && p>rand.nextDouble()) {
							Arista aj = new Arista(i,j);
							grafGil.arista.put(k,aj);
							k++;
						}
					}
					else if(auto) {
						Arista ai = new Arista(j,i);
						grafGil.arista.put(k,ai);
						k++;
					}
				}
			}
		}
		grafGil.NodoIncidentes();
		return grafGil;
	}

	public static Grafo Geografico(int n, double r, boolean dirigido, boolean auto) {
		Grafo grafGeo  =new Grafo();
		grafGeo.dirigido=dirigido;
		grafGeo.n=n;
		Random rand = new Random();
		int k=1;
		double[][] xy = new double[n][2];
		for(int x=0; x<n;x++) {
			xy[x][0]=rand.nextDouble();
			xy[x][1]=rand.nextDouble();
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1;j<=i;j++) {
				double d=Dist(xy[i-1][0],xy[i-1][1],xy[j-1][0],xy[j-1][1]);
				if(i!=j && d<=r) {
					Arista aij = new Arista(i,j);
					grafGeo.arista.put(k,aij);
					k++;
					if(dirigido) {
						Arista aji = new Arista(j,i);
						grafGeo.arista.put(k,aji);
						k++;
					}
				}
				if(i==j && auto) {
					Arista ai = new Arista(i,j);
					grafGeo.arista.put(k,ai);
					k++;
				}
			}
		}

		grafGeo.NodoIncidentes();
		return grafGeo;
	}

	public static Grafo BarabasiAlbert(int n, double d, boolean dirigido, boolean auto) {
		Grafo grafBA  =new Grafo();
		grafBA.dirigido=dirigido;
		grafBA.n=n;
		double[] degnodo = new double[n];
		Random rand = new Random();
		int k=0;
		
		for(int i=0; i<n; i++) {
			for(int j=0;j<i;j++) {
				double rnd = rand.nextDouble();
				double rnd1 = rand.nextDouble();
				if(i!=j && rnd<=(1-(degnodo[j]/d)) && degnodo[i]<d) {
					degnodo[j]++;
					degnodo[i]++;
					Arista aji = new Arista(j+1,i+1);
					grafBA.arista.put(k,aji);
					k++;
				}
				if(i!=j && dirigido && rnd<=(1-(degnodo[i]/d)) && degnodo[j]<d) {
					degnodo[j]++;
					degnodo[i]++;
					Arista aij = new Arista(i+1,j+1);
					grafBA.arista.put(k,aij);
					k++;
				}
				if(i==j && auto && rnd1<=(1-(degnodo[j]/d))) {
					degnodo[j]=degnodo[j]+2;
					Arista ai = new Arista(i+1,i+1);
					grafBA.arista.put(k,ai);
					k++;
				}
			}
		}
		grafBA.NodoIncidentes();
		return grafBA;
	}
		
	public Grafo BFS(int s) {
		Grafo graf  =new Grafo();
		NodoIncidentes();
		Nodo S = this.nodo.get(s);
		S.discovered=true;
		graf.nodo.put(s,S);
		graf.arista.clear();
	    int arista=1;
	    Set<Integer> L = new HashSet<Integer>();
		L.add(s);
	    while(!L.isEmpty()) {
	    	Set<Integer> Li = L;
	    	Set<Integer> Lj = new HashSet<Integer>();
	    	Iterator<Integer> Lu = Li.iterator(); 
	    	while(Lu.hasNext()){
	    		int u=Lu.next(); 
	    		Nodo U = this.nodo.get(u);
	    		Iterator<Integer> Lv = U.all.iterator(); 
	    		while (Lv.hasNext()){
	    			int v=Lv.next(); 
	    			Nodo V = this.nodo.get(v);
	    			if(V.discovered==false){
	    				Arista Euv = new Arista(u,v);
	    				graf.arista.put(arista, Euv);
	    				arista++;
	    				V.discovered=true;
	    				graf.nodo.put(v,V);
	    				Lj.add(v);
	    			}
	    		}
	    	} 
		L=Lj;
	    }
	    return(graf);
	}

	public Grafo DFS_I(int u) {
		Grafo grafDFSi  =new Grafo();
		NodoIncidentes();
		Nodo U = this.nodo.get(u);
		U.discovered=true;
		grafDFSi.nodo.put(u,U);
		grafDFSi.arista.clear();
	    int arista=1;
	    LinkedList<Integer> K = new LinkedList<Integer>();
		K.add(u);
	    while(!K.isEmpty()) { 
	    	if(!U.all.isEmpty()) {
	    		int v =U.all.getFirst();
	    		Nodo V = this.nodo.get(v);
	    		if(!V.discovered) {
    				Arista Euv = new Arista(u,v);
    				grafDFSi.arista.put(arista, Euv);
	    			V.discovered=true;
	    			grafDFSi.nodo.put(v,V);
    				arista++;
	    			K.add(v);
	    		}
    			U.all.removeFirst();
    			grafDFSi.nodo.put(u,U);
	    	}
	    	else {
	    		K.removeLast();
	    	}
	    	if(!K.isEmpty()) {
	    		u=K.getLast();
	    		U=this.nodo.get(u);
	    	}
	    }
	    return grafDFSi;
	}

	public Grafo DFS_R(int u) {
		NodoIncidentes();
		ContArista=1;
		Grafo grafDFSi  =this;
		grafDFSi.arista.clear();
		
		DFS_Rec(grafDFSi,u);
		return grafDFSi;
	}

	public Grafo Dijkstra(int u) {
		Grafo grafDij  =new Grafo();
		//NodoIncidentes();
		grafDij.dirigido=this.dirigido;
		double w [][]=MatrizCostosArista();
		double[] d = new double[this.nodo.size()];
		int[] pi = new int[this.nodo.size()];
		LinkedList<Integer> Pila = new LinkedList<Integer>();
		int v;
		int carista=1;
		for(int i=0; i<this.nodo.size(); i++) {
		d[i]= Float.MAX_VALUE;
		pi[i]=-1;
		}
		d[u-1]=0;
		Pila.add(u);
		while(!Pila.isEmpty()) {
			u=Pila.pollFirst();
			Nodo U = this.nodo.get(u);
			if(U.discovered) continue;
			U.discovered=true;
			
			while(!U.all.isEmpty()) {
				v = U.all.pollFirst();
				Nodo V = this.nodo.get(v);

				V.cost=d[v-1];
				U.cost=d[u-1];
				grafDij.nodo.put(v,V);
				grafDij.nodo.put(u,U);
				
				if (!V.discovered) {
					if(d[v-1] > d[u-1] + w[u][v]) {
						d[v-1] = d[u-1] + w[u][v];
						pi[v-1]= u;
						Pila.add(v);
						//System.out.println("Pil = "+Pila); 
					}
				}
			}
		}
	
        System.out.printf( "Distancias mas cortas iniciando en Nodo %d\n" , 2 );
        for( int i = 0 ; i < this.nodo.size(); ++i ){
            System.out.printf("Nodo %d , distancia mas corta = %f\n" , i+1 , d[i]);
        }
        
        for( int i = 0 ; i < pi.length; ++i ){
            if(pi[i]!=-1) {
        	System.out.println((i+1)+"  E="+pi[i]+(i+1) + "  W=" + w[pi[i]][i+1]);
            Arista Euv = new Arista(pi[i],i+1);
            Euv.costo=w[pi[i]][i+1];
			grafDij.arista.put(carista, Euv);
			
			carista++;
			}
        }
		return grafDij; 
	}
	
////////////////////////////// Funciones /////////////////////////////////////////
	
	public static int[] RandomPar(int n, boolean auto ){
		int[] par = new int[2];
		Random rand = new Random();
		boolean f=true;
		while(f) {
			f=false;
			par[0] = (rand.nextInt(n) + 1);
			par[1] = (rand.nextInt(n) + 1);			
			if(auto==false && par[0]==par[1]) {
				f=true;
			}
		}
	   return(par);            
	}

	public static double Dist(double x1, double y1, double x2, double y2){
		double d=  Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
		return(d);            
	}
		
	public void NodoIncidentes() {
		 int cont[] = new int [n];
		 int cont2[] = new int [n];
		 this.nodo.clear();
		 for(int i=0;i<n;i++) {
			 cont[i]=1;
			 cont2[i]=1;
			 Nodo V = new Nodo(i+1);
			 this.nodo.put(i+1,V);
		 }
		 for(Map.Entry<Integer, Arista> entry:arista.entrySet()){      
		        Arista E=entry.getValue(); 
		        Nodo A = this.nodo.get(E.n1);
	        	Nodo B = this.nodo.get(E.n2);
	        	if(E.n1!=E.n2) {
	        		if(!dirigido){ 
	        			A.all.add(E.n2);  		
	        			B.all.add(E.n1);		
	        		}
	        		if(dirigido) {
	        			A.all.add(E.n2); 
	        			A.out.add(E.n2);  				
	        			B.in.add(E.n1);					
	        		}
	        	}
		        this.nodo.put(E.n1,A);
		        this.nodo.put(E.n2,B);
		    }
		 
	for(int i=1;i<=this.n;i++) {
			 Nodo X = nodo.get(i);
			 System.out.println("Nodo "+i);
			 if(dirigido==false) {
				 Set<Integer> tree_Set = new TreeSet<Integer>(X.all); 
			     System.out.println(tree_Set); 
			 }
			 if(dirigido) {
				 Set<Integer> tree_Set0 = new TreeSet<Integer>(X.all); 
			     System.out.println(tree_Set0); 
			     //Set<Integer> tree_Set = new TreeSet<Integer>(X.in); 
			     //System.out.println(tree_Set); 
			     //Set<Integer> tree_Set1 = new TreeSet<Integer>(X.out); 
			     //System.out.println(tree_Set1); 
			 }
		 }
		 
	 }

	public void GrafoToViz(String name){
		String dir="--";
		if(this.dirigido) dir="->";
		try {
			File archivo=new File(name+".gv");
			archivo.delete();
			FileWriter escribir=new FileWriter(archivo,false);
			if(this.dirigido) escribir.write("digraph {"+"\n");
			else escribir.write("graph {"+"\n");
			for(Map.Entry<Integer, Arista> entry:this.arista.entrySet()){      
		        Arista E=entry.getValue();
		        Nodo N1= this.nodo.get(E.n1);
		        Nodo N2= this.nodo.get(E.n2);
		        //String N1c = "("+N1.cost+")";
		        //String N2c = "("+N2.cost+")";
		        String N1c=(String.format("(%.2f)",(float) N1.cost));
		        String N2c=(String.format("(%.2f)",(float) N2.cost));
		        String Ec=String.format("%.2f",(float) E.costo);
		        if(N1.cost==Double.MAX_VALUE) {
			        N1c ="";
			        N2c ="";
		        }
				escribir.write(E.n1+N1c+dir+E.n2+N2c+"[label="+Ec+"]"+"\n");
		    }

			escribir.write("}"+"\n");
			escribir.close();
			System.out.println("Archivo "+name+".gv guardado");
			}
	
		catch(Exception e) {
			System.out.println("Error al escribir");
		}
	
	}
	
	public void DFS_Rec(Grafo Graf,int u) {
		Nodo U = Graf.nodo.get(u);
		U.discovered=true;
		Graf.nodo.put(u,U);
	
	    while(!U.all.isEmpty()) {
	    		int v =U.all.pollFirst();
	    		Nodo V = Graf.nodo.get(v);
	    		if(!V.discovered) {
    				Arista Euv = new Arista(u,v);
    				Graf.arista.put(ContArista, Euv);
    				ContArista++;
    				DFS_Rec(Graf,v);
	    		}
	    }
	}

	public void RandomCostosArista(double min, double max) {
		for(Map.Entry<Integer, Arista> entry:this.arista.entrySet()){      
	        Arista A=entry.getValue();  
	        Random rand = new Random();
	        double d= max-min;
	        A.costo=((d*rand.nextDouble())+min);
	        System.out.println(A.costo);
	    }
	}
	
	public double[][] MatrizCostosArista() {
		double w[][] = new double[n+1][n+1];
		for(Map.Entry<Integer, Arista> entry:this.arista.entrySet()){      
	        Arista A=entry.getValue();  
	        w[A.n1][A.n2] =A.costo;
	        if (!this.dirigido) w[A.n2][A.n1] =A.costo;
	    }
		return w;
		
	}
	
}