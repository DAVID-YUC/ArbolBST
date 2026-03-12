
package arboles;

public class Arboles {

    public static void main(String[] args) {
       ArbolBinario arbol= new ArbolBinario();
       

System.out.println("Recorrido en Preorden:");
arbol.preOrden(); // Raíz, Izquierda, Derecha
System.out.println();
System.out.println("Recorrido en Inorden:");
arbol.Inorden(); // Izquierda, Raíz, Derecha
System.out.println();
System.out.println("Recorrido en Postorden:");
arbol.postOrden(); // Izquierda, Derecha, Raíz
System.out.println();

    }
     
}

