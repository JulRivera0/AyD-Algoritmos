import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
	
	Map<Integer, Nodo> nodo = new HashMap<Integer, Nodo>();
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	boolean dirigido;
	
	public void ErdosRenyi(int n, int m, boolean dirigido, boolean auto) {
		//int [] nod1=RandomPar(n,auto);
		//Arista a1 = new Arista(nod1[0],nod1[1]);
		//this.arista.put(1,a1);
		this.dirigido=dirigido;
		
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
	
	}