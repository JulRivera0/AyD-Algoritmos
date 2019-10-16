import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Arista {
	
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
    LinkedList<Integer> in = new LinkedList<Integer>();
    
	int n1,n2;
	double costo=0;
	
	public Arista(int n1, int n2) {
		this.n1=n1;
		this.n2=n2;		
	}
}