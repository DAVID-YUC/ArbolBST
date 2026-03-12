
package arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

 public class ArbolBinario {
    private Nodo raiz ;
    int num_nodos;
    int alt;
    
    
    public ArbolBinario(){
    raiz = null;
    }
    
    public boolean agregar(int valor) {
    raiz = insertarRecursivo(raiz, valor);
        return true;
    }
    
    private Nodo insertarRecursivo(Nodo actual, int valor){
    if(actual == null) return new Nodo (valor);
    
    if (valor< actual.valor)
        actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
    else if (valor> actual.valor)
        actual.derecho = insertarRecursivo(actual.derecho, valor);
    return actual;
    
    }
    
    public LinkedList Inorden() {
        LinkedList recorrido = new LinkedList();
        recorridoInordenRecursivo(raiz, recorrido);
        return recorrido;
    }
    
    public LinkedList preOrden() {
        LinkedList recorrido = new LinkedList();
        recorridoPreordenRecursivo(raiz, recorrido);
        return recorrido;
    }
    
    public LinkedList postOrden() {
        LinkedList recorrido = new LinkedList();
        recorridoPostordenRecursivo(raiz, recorrido);
        return recorrido;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    
    
    
    //METODOS
    
    private void recorridoInordenRecursivo(Nodo actual, LinkedList recorrido) {
     if (actual != null) {
     recorridoInordenRecursivo(actual.getIzquierdo(),recorrido);
     recorrido.add(actual.getValor());
     recorridoInordenRecursivo(actual.getDerecho(), recorrido);
}
}
    
     private void recorridoPreordenRecursivo(Nodo actual, LinkedList recorrido) {
     if (actual != null) {
     recorrido.add(actual.getValor());
     recorridoPreordenRecursivo(actual.getIzquierdo(),recorrido);
     recorridoPreordenRecursivo(actual.getDerecho(), recorrido);
}
}
     
     private void recorridoPostordenRecursivo(Nodo actual, LinkedList recorrido) {
     if (actual != null) {
     recorridoPostordenRecursivo(actual.getIzquierdo(),recorrido);
     recorridoPostordenRecursivo(actual.getDerecho(), recorrido);
     recorrido.add(actual.getValor());
     System.out.println(actual.valor +"");
}
}
     
     public boolean existe(int dato) {
        Nodo aux = raiz;
        while (aux != null) {
            if (dato == aux.getValor()) {
                return true;
            } else if (dato > aux.getValor()) {
                aux = aux.getDerecho();
            } else {
                aux = aux.getIzquierdo();
            }
        }
        return false;
    }
     
     private void altura(Nodo aux, int nivel) {
        if (aux != null) {
            altura(aux.getIzquierdo(), nivel + 1);
            alt = nivel;
            altura(aux.getDerecho(), nivel + 1);
        }
    }
     
     public int getAltura() {
        altura(raiz, 1);
        return alt;
    }
    
     public JPanel getdibujo() {
        return new ArbolGrafico(this);
    }
 }
     
 
