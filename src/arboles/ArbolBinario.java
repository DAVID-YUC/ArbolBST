
package arboles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
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
     recorrido.add(actual.getValor()+ (" ,"));
     recorridoInordenRecursivo(actual.getDerecho(), recorrido);
     System.out.println("InOrden :"+actual.valor +"");
}
}
    
     private void recorridoPreordenRecursivo(Nodo actual, LinkedList recorrido) {
     if (actual != null) {
     recorrido.add(actual.getValor()+ (" ,"));
     recorridoPreordenRecursivo(actual.getIzquierdo(),recorrido);
     recorridoPreordenRecursivo(actual.getDerecho(), recorrido);
     System.out.println("PreOrden :"+actual.valor +"");
}
}
     
     private void recorridoPostordenRecursivo(Nodo actual, LinkedList recorrido) {
     if (actual != null) {
     recorridoPostordenRecursivo(actual.getIzquierdo(),recorrido);
     recorridoPostordenRecursivo(actual.getDerecho(), recorrido);
     recorrido.add(actual.getValor()+ (" ,"));
     System.out.println("PostOrden :"+actual.valor +"");
}
}
         
    
    public boolean existe(int dato) {
        Nodo buscar = raiz;
        while (buscar != null) {
            if (dato == buscar.getValor()) {
                return true;
            } else if (dato > buscar.getValor()) {
                buscar = buscar.getDerecho();
            } else {
                buscar = buscar.getIzquierdo();
            }
        }
        return false;
    }
    
     public int obtenerAltura(Nodo actual){
        if(actual == null) return -1;
        return 1 + Math.max(obtenerAltura(actual.izquierdo), obtenerAltura(actual.derecho));
    }
    
    public int obtenerNivel(Nodo actual, int nodo, int nivel){
        if(actual == null) return -1;
        if(actual.valor == nodo) return nivel;
        
        if(nodo<actual.valor) 
            return obtenerNivel(actual.izquierdo, nodo, nivel+1);
        else
            return obtenerNivel(actual.derecho, nodo, nivel+1);
    }
    
    public Nodo eliminar(Nodo raiz, int valor) {
    if (raiz == null) {
        return null;
    }
    if (valor < raiz.valor) {
        raiz.izquierdo = eliminar(raiz.izquierdo, valor);
    } 
    else if (valor > raiz.valor) {
        raiz.derecho = eliminar(raiz.derecho, valor);
    } 
    else {
        if (raiz.izquierdo == null) {
            return raiz.derecho;
        }
        if (raiz.derecho == null) {
            return raiz.izquierdo;
        }
        raiz.valor = minimo(raiz.derecho);
        raiz.derecho = eliminar(raiz.derecho, raiz.valor);
    }
    return raiz;
}
    
    
    public int minimo(Nodo nodo) {
    int min = nodo.valor;
    while (nodo.izquierdo != null) {
        min = nodo.izquierdo.valor;
        nodo = nodo.izquierdo;
    }
    return min;
    }
    
    void cargarFile(String ruta){
        try{
            File archivo = new File(ruta);
            Scanner lector = new Scanner(archivo);
            lector.useDelimiter("[,\\s]+");
            
            while(lector.hasNextInt()){
                int valor = lector.nextInt();
                this.agregar(valor);
            }
            lector.close();
            System.out.println("Datos cargados exitosamente desde: " + ruta);
        } catch(FileNotFoundException e) {
            System.out.println("Error: Nose encontro el archivo en la ruta");
        } catch (Exception e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
     public JPanel getdibujo() {
        return new ArbolGrafico(this);
    }
 }
     
 
