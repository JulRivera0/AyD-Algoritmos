import java.io.File;
import java.io.FileWriter;
import java.util.Map;
	
public class GrafoToViz {
	String dir="--";
	public GrafoToViz(String name, Grafo grafo){
		if(grafo.dirigido) dir="->";
		try {
			File archivo=new File(name+".gv");
			FileWriter escribir=new FileWriter(archivo,true);
			if(grafo.dirigido) escribir.write("digraph {"+"\n");
			else escribir.write("graph {"+"\n");
			for(Map.Entry<Integer, Arista> entry:grafo.arista.entrySet()){      
		        Arista b=entry.getValue();  
				escribir.write(b.n1+dir+b.n2+"\n");
		    }

			escribir.write("}"+"\n");
			escribir.close();
			System.out.println("Archivo "+name+".gv guardado");
			}
	
		catch(Exception e) {
			System.out.println("Error al escribir");
		}
	
	}
}