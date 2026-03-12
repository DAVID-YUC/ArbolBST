
package arboles;

import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Toloza XD
 */
public class Simulador {

    ArbolBinario miArbol = new ArbolBinario();

    public Simulador() {
    }

    public boolean insertar(Integer valor) {
        return (this.miArbol.agregar(valor));
    }
    //metodo para mostrar los recorridos del arbol
    public String preOrden() {
        LinkedList rec = this.miArbol.preOrden();
        return (recorrido(rec, "Recorrido PreOrden    "));
    }

    public String inOrden() {
        LinkedList rec = this.miArbol.Inorden();
        return (recorrido(rec, "Recorrido InOrden    "));
    }

    public String postOrden() {
        LinkedList rec = this.miArbol.postOrden();
        return (recorrido(rec, "Recorrido PosOrden    "));
    }
    
    //metodo para poder mostrar los tipos d recorrido
     private String recorrido(LinkedList rec, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < rec.size()) {
            r += "\t" + rec.get(i).toString() + "";
            i++;
        }
        return (r);
    }


    
    //Metodo para buscar dato en el nodo
    public String buscar(Integer dato) {
    boolean Encontrado = this.miArbol.existe(dato);
    String resultado = "Nodo: " + dato;
    if (Encontrado) {
        int nivel = this.miArbol.obtenerNivel(this.miArbol.getRaiz(), dato, 1);
        int altura = this.miArbol.obtenerAltura(this.miArbol.getRaiz());
        resultado += " Encontrado \n";
        resultado += "Nivel: " + nivel + "\n";
        resultado += "Altura : " + altura + "\n";
    } else {
        resultado += "No se encuentra en el arbol";
    }
    return resultado;
}
    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
