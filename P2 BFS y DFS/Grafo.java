import java.util.*;

public class Grafo {
	
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	boolean dirigido;
	int n;
	int ContArista=0;
	
	public void ErdosRenyi(int n, int m, boolean dirigido, boolean auto) {
		this.dirigido=dirigido;
		this.n=n;
		
		for(int i=1; i<=m; i++) {
			int [] nod=RandomPar(n,auto);
			Arista ai = new Arista(nod[0], nod[1]);
			this.arista.put(i,ai);
				
			for(int j=1;j<i;j++) {
				Arista aj = arista.get(j);
				if(aj.n1==nod[0] && aj.n2==nod[1] ||(aj.n1==nod[1] && aj.n2==nod[0] && dirigido==false)) {
					i--;
					break;
				}	
			}
		}
	}

	public void Gilbert(int n, double p, boolean dirigido, boolean auto) {

		this.dirigido=dirigido;
		this.n=n;
		int k=1;
		Random rand = new Random();

		for(int i=1; i<n; i++) {
			for(int j=i+1; j<=n;j++) {
				if(p>rand.nextDouble() ) {
					if(j!=i) {
						Arista ai = new Arista(j,i);
						this.arista.put(k,ai);
						k++;
						if(dirigido && p>rand.nextDouble()) {
							Arista aj = new Arista(i,j);
							this.arista.put(k,aj);
							k++;
						}
					}
					else if(auto) {
						Arista ai = new Arista(j,i);
						this.arista.put(k,ai);
						k++;
					}
				}
			}
		}
	}

	public void Geografico(int n, double r, boolean dirigido, boolean auto) {

		this.dirigido=dirigido;
		this.n=n;
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
					this.arista.put(k,aij);
					k++;
					if(dirigido) {
						Arista aji = new Arista(j,i);
						this.arista.put(k,aji);
						k++;
					}
				}
				if(i==j && auto) {
					Arista ai = new Arista(i,j);
					this.arista.put(k,ai);
					k++;
				}
			}
		}
	}

	public void BarabasiAlbert(int n, double d, boolean dirigido, boolean auto) {

		this.dirigido=dirigido;
		this.n=n;
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
					this.arista.put(k,aji);
					k++;
				}
				if(i!=j && dirigido && rnd<=(1-(degnodo[i]/d)) && degnodo[j]<d) {
					degnodo[j]++;
					degnodo[i]++;
					Arista aij = new Arista(i+1,j+1);
					this.arista.put(k,aij);
					k++;
				}
				if(i==j && auto && rnd1<=(1-(degnodo[j]/d))) {
					degnodo[j]=degnodo[j]+2;
					Arista ai = new Arista(i+1,i+1);
					this.arista.put(k,ai);
					k++;
				}
			}
		}
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
		Grafo graf1  =new Grafo();
		NodoIncidentes();
		Nodo U = this.nodo.get(u);
		U.discovered=true;
		graf1.nodo.put(u,U);
		graf1.arista.clear();
	    int arista=1;
	    LinkedList<Integer> K = new LinkedList<Integer>();
		K.add(u);
	    while(!K.isEmpty()) { //K.size()!=0
	    	if(!U.all.isEmpty()) {
	    		int v =U.all.getFirst();
	    		Nodo V = this.nodo.get(v);
	    		if(!V.discovered) {
    				Arista Euv = new Arista(u,v);
    				graf1.arista.put(arista, Euv);
	    			V.discovered=true;
	    			graf1.nodo.put(v,V);
    				arista++;
	    			K.add(v);
	    		}
    			U.all.removeFirst();
    			graf1.nodo.put(u,U);
	    	}
	    	else {
	    		K.removeLast();
	    	}
	    	if(!K.isEmpty()) {
	    		u=K.getLast();
	    		U=this.nodo.get(u);
	    	}
	    }
	    return(graf1);
	}

	public void DFS_R(int u) {
		if(this.ContArista==0) {
		NodoIncidentes();
		this.arista.clear();
	    ContArista=1;
	    }
		
		Nodo U = this.nodo.get(u);
		U.discovered=true;
		this.nodo.put(u,U);
	
	    while(!U.all.isEmpty()) {
	    		int v =U.all.pollFirst();
	    		Nodo V = this.nodo.get(v);
	    		if(!V.discovered) {
    				Arista Euv = new Arista(u,v);
    				this.arista.put(ContArista, Euv);
    				ContArista++;
    				DFS_R(v);
	    		}
	    	}
	}
////////////////////////////// Funciones /////////////////////////////////////////
	
	public int[] RandomPar(int n, boolean auto ){
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
	        		if(dirigido==false){ 
	        			A.all.add(E.n2);  		
	        			B.all.add(E.n1);		
	        		}
	        		if(dirigido) {
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
				 Set<Integer> tree_Set = new TreeSet<Integer>(X.in); 
			     System.out.println(tree_Set); 
			     Set<Integer> tree_Set1 = new TreeSet<Integer>(X.out); 
			     System.out.println(tree_Set1); 
			 }
		 }

	 }

	}