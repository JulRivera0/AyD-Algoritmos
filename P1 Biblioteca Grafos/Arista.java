import java.util.HashMap;
import java.util.Map;

public class Arista {
	
	Map<Integer, Arista> arista = new HashMap<Integer, Arista>();
	int n1,n2;
	
	public Arista(int n1, int n2) {
		this.n1=n1;
		this.n2=n2;
	}
}