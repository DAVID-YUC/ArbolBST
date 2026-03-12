package arboles;

/**
 *
 * @author Toloza XD
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ArbolGrafico extends JPanel {

    private ArbolBinario miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 30, child2child = 30;
    private Dimension empty = new Dimension(0, 0);
    private FontMetrics fm = null;

    public ArbolGrafico(ArbolBinario miArbol) {
        this.miArbol = miArbol;
        this.setBackground(Color.WHITE);
        posicionNodos = new HashMap();
        subtreeSizes = new HashMap();
        dirty = true;
        repaint();
    }

    private void calcularPosiciones() {
        posicionNodos.clear();
        subtreeSizes.clear();

        Nodo root = this.miArbol.getRaiz();

        if (root != null) {
            calcularTamañoSubarbol(root);
            calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    private Dimension calcularTamañoSubarbol(Nodo n) {

        if (n == null)
            return new Dimension(0, 0);
        Dimension ld = calcularTamañoSubarbol(n.getIzquierdo());
        Dimension rd = calcularTamañoSubarbol(n.getDerecho());
        int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
        int w = ld.width + child2child + rd.width;
        Dimension d = new Dimension(w, h);
        subtreeSizes.put(n, d);

        return d;
    }

    private void calcularPosicion(Nodo n, int left, int right, int top) {

        if (n == null)
            return;

        Dimension ld = (Dimension) subtreeSizes.get(n.getIzquierdo());
        if (ld == null)
            ld = empty;

        Dimension rd = (Dimension) subtreeSizes.get(n.getDerecho());
        if (rd == null)
            rd = empty;

        int center = 0;

        if (right != Integer.MAX_VALUE)
            center = right - rd.width - child2child / 2;
        else if (left != Integer.MAX_VALUE)
            center = left + ld.width + child2child / 2;

        int width = fm.stringWidth(n.getValor() + "");

        posicionNodos.put(n,
                new Rectangle(center - width / 2 - 15, top, width + 30, fm.getHeight() + 20));

        calcularPosicion(n.getIzquierdo(), Integer.MAX_VALUE, center - child2child / 2,
                top + fm.getHeight() + parent2child);

        calcularPosicion(n.getDerecho(), center + child2child / 2, Integer.MAX_VALUE,
                top + fm.getHeight() + parent2child);
    }

    private void dibujarArbol(Graphics2D g, Nodo n, int puntox, int puntoy, int yoffs) {

        if (n == null)
            return;

        Rectangle r = (Rectangle) posicionNodos.get(n);

        int centroX = r.x + r.width / 2;
        int centroY = r.y + r.height / 2;

        int radio = 10;

        g.setColor(Color.CYAN);
        g.fillOval(centroX - radio, centroY - radio, radio * 2, radio * 2);

        g.setColor(Color.BLACK);
        g.drawOval(centroX - radio, centroY - radio, radio * 2, radio * 2);

        String texto = n.getValor() + "";
        int anchoTexto = fm.stringWidth(texto);

        g.drawString(texto, centroX - anchoTexto / 2, centroY + fm.getAscent() / 2 - 2);

        if (puntox != Integer.MAX_VALUE)
            g.drawLine(puntox, puntoy, centroX, centroY - radio);

        dibujarArbol(g, n.getIzquierdo(), centroX, centroY + radio, yoffs);
        dibujarArbol(g, n.getDerecho(), centroX, centroY + radio, yoffs);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();
        if (dirty) {
            calcularPosiciones();
            dirty = false;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, parent2child);
        dibujarArbol(g2d, this.miArbol.getRaiz(),
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                fm.getLeading() + fm.getAscent());

        fm = null;
    }
}