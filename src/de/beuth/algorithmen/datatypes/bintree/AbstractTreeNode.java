package de.beuth.algorithmen.datatypes.bintree;


public abstract class AbstractTreeNode {
    
    /**
     * In diesem Feld wird der Inhalt des Knotens gespeichert.
     */
    protected KeyAble data;
    /**
     * Referenz auf die Wurzel des linken Teilbaums unter diesem Knoten.
     */
    protected AbstractTreeNode left;
    /**
     * Referenz auf die Wurzel des rechten Teilbaums unter diesem Knoten.
     */
    protected AbstractTreeNode right;
    /**
     * Referenz auf den Vaterknoten von diesem Knoten.
     */
    protected AbstractTreeNode parent;
    private int numberOfRemovals;
    
    
    protected AbstractTreeNode(KeyAble data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        numberOfRemovals = 0;
    }
    
    /**
     * Liefert den Wurzelknoten des Baumes zurück, zu dem dieser Knoten gehört.
     */ 
    public abstract AbstractTreeNode getRoot();
    /**
     * Liefert die Tiefe des Baums unter diesem Knoten zurück. 
     */ 
    public abstract int getDepth();
    /**
     * Liefert die Distanz dieses Knotens zum Wurzelknoten zurück.
     * Dabei ist die Distanz eines Wurzelknotens zu sich selbst gleich 0,
     * die der Kinder eines Wurzelknotens zum Wurzelknoten gleich 1 und so weiter.
     */
    public abstract int getRootDistance();
    /**
     * Erzwingt das Überschreiben der toString-Methode. 
     * 
     * Diese Methode gibt einen String der Form
     * (r:P,Q)
     * zurück, falls dieser Knoten mindestens ein Kind hat,
     * wobei r der Schlüssel des Inhalts dieses Knoten ist (data.getKey()), und
     * P und Q die Darstellung des linken und rechten Teilbaums unter diesem
     * Baum oder null, falls ein entsprechender teilbaum nicht existiert.
     * 
     * Diese Methode gibt r (als String) zurück, wobei r der Schlüssel des Inhalts dieses Knoten ist (data.getKey()),
     * falls dieser Knoten keine Kinder hat.
     * 
     * Der Rückgabewert dieser Methode enthält keine Leerzeichen, Tabs oder Zeilenumbrüche.
     */ 
    public abstract String toString();
    /**
     * Liefert den Knoten mit dem kleinsten Schlüssel im Baum unter diesem 
     * Knoten zurück.
     */
    public abstract AbstractTreeNode getMinimumNodeInSubTree();
    /**
     * Liefert den Knoten mit dem größten Schlüssel im Baum unter diesem 
     * Knoten zurück.
     */
    public abstract AbstractTreeNode getMaximumNodeInSubTree();
    /**
     * Liefert den Nachfolgerknoten dieses Knoten zurück, falls
     * ein solcher existiert, sonst null.
     */
    public abstract AbstractTreeNode getSuccessor();
    /**
     * Liefert den Vorgängerknoten dieses Knoten zurück, falls
     * ein solcher existiert, sonst null.
     */
    public abstract AbstractTreeNode getPredecessor();
    /**
     * Liefert die Größe des Baumes unter diesem Knoten zurück.
     * Dabei zählt dieser Knoten als Wurzelknoten zu diesem Baum.
     */
    public abstract int getSubtreeSize();
    
    
    
    /**
     * Gibt den Inhalt dieses Knoten zurück.
     */
    public KeyAble getData() {
        return data;
    }
    
    /**
     * Gibt den Schlüssel des Inhalts dieses Knoten zurück.
     */
    public int getKey() {
        return data.getKey();
    }
    
    /**
     * Gibt das linke Kind dieses knoten zurück und null, falls es kein linkes Kind gibt.
     */
    public AbstractTreeNode getLeftChild() {
        return left;
    }
    
    /**
     * Gibt das linke Kind dieses knoten zurück und null, falls es kein linkes Kind gibt.
     */
    public AbstractTreeNode getRightChild() {
        return right;
    }
    
    
    /**
     * Gibt den Vaterknoten dieses knoten zurück, falls ein solcher existiert.
     * Sonst wird null zurückgegeben.
     */
    public AbstractTreeNode getParent() {
        return parent;
    }
    
    /**
     * Fügt den gegebenen Knoten c als rechtes Kind dieses Knoten ein.
     */
    public void setRightChild(AbstractTreeNode c) {
        if (c == this) throw new IllegalArgumentException("Kann einen Knoten nicht als sein eigenes Kind einfügen.");
        right = c;
        c.parent = this;
    }
    
    /**
     * Fügt den gegebenen Knoten c als linkes Kind dieses Knoten ein.
     */
    public void setLeftChild(AbstractTreeNode c) {
        if (c == this) throw new IllegalArgumentException("Kann einen Knoten nicht als sein eigenes Kind einfügen.");
        left = c;
        c.parent = this;
    }
    
    
    /**
     * Sortiert das gegebene BasicTreeNode btn in den Baum unter diesen Knoten ein.
     * Die Methode nimmt keine Rücksicht auf die Baumtiefe, sorgt also nicht dafür,
     * dass der Baum sich nach dem einfügen nahe an einem balancierten Zustand befindet. 
     * Ist btn==null, passiert nichts.
     * 
     * Diese Methode gibt den neuen Wurzelknoten nach der Einfügeoperation zurück.
     * Dieser ändert sich zwar bei der Basismethode nie, möglicherweise aber
     * in einer Klasse, die diese Methode überschreibt.
     */ 
    public AbstractTreeNode insert(AbstractTreeNode btn) {        
        if (btn == null) return getRoot();
        if (btn.getKey() > this.getKey()) {
            if (right == null) setRightChild(btn);
            else right.insert(btn);
            
        } else {
            if (left == null) setLeftChild(btn);
            else left.insert(btn);
        }
        return getRoot();
    }
    
    
    /**
     * Löscht das gegebene Keyable k aus dem Teilbaum unter diesem Knoten.
     * Falls der Baum das Keyable k nicht ebthält, passiert nichts.
     * Die Methode liefert die Wurzel des Baums zurück, nachdem der 
     * Knoten entfernt wurde. 
     * 
     * Dazu ist zu bemerken, dass dieser Knoten
     * als Wurzel des Baumes unter Umständen beim Löschen ersetzt wird.
     * D.h. der Aufrufer sollte die Wurzel des Baumes nach dem Löschen
     * immer auf den Rückgabewert dieser Methode setzen.
     * 
     * Wird durch Löschen von k der letzte Knoten des Baums entfernt, so wird null
     * zurückgegeben.
     */ 
    public AbstractTreeNode delete(KeyAble k) {
        if (data == k) return remove();
        else if ((k.getKey() > getKey()) && (right != null)) return right.delete(k);
        else if (left != null) return left.delete(k);
        else return getRoot();
    }
    
    /**
     * Löscht einen Knoten mit dem gegebenen Schlüssel aus dem Baum, 
     * falls ein solcher Knoten existiert. Sonst hat ein Aufruf
     * der Methode keine Auswirkung.
     * Beim Löschen kann sich der Wurzelknoten ändern. 
     * 
     * Die Methode gibt ein zweielementiges Array zurück.
     * Das erste Element ist der aktuelle Wurzelknoten des 
     * Baumes nach dem Löschvorgang, der zweite enthält den
     * gelöschten Knoten (oder null, falls es keinen solchen 
     * Knoten gab).
     */
    public AbstractTreeNode[] delete(int key) {
        AbstractTreeNode[] r = new AbstractTreeNode[2];
        AbstractTreeNode k = search(key);
        r[1] = k;
        if (k != null) r[0]  = k.remove();
        else r[0]  = getRoot();
        return r;
    }
    
    /**
     * Gibt einen Knoten mit dem gegebenen Schlüssel zurück, falls ein
     * solcher im Baum existiert, sonst null.
     */
    public AbstractTreeNode search(int key) {
        if (getKey() == key) return this;
        else if ((key > getKey()) && (right != null)) return right.search(key); 
        else if ((key <= getKey()) && (left != null)) return left.search(key);
        return null;
    }
    
    
    /**
     * Gibt einen Knoten zurück, das genau das gegebene Element als Datum gespeichert 
     * hat, falls ein solcher im Baum existiert, sonst null.
     */
    public AbstractTreeNode search(KeyAble ka) {
        if (ka.getKey() == getKey()) return this;
        else if ((ka.getKey() > getKey()) && (right != null)) return right.search(ka); 
        else if ((ka.getKey() <= getKey()) && (left != null)) return left.search(ka);
        return null;
    }
    
    /**
     * Entfernt diesen Knoten aus dem Baum. Möglicherweise
     * ändert sich dadurch der Wurzelknoten des Baums.
     * Die Rückgabe dieser Methode ist daher der neue Wurzelknoten.
     */
    public AbstractTreeNode remove() {
        AbstractTreeNode p = getParent();
        if (left == null) {
            if (right == null) {
                if (p == null) return null;
                if (isLeftChild()) p.left = null;
                if (isRightChild()) p.right = null;
                parent = null;
                return p.getRoot();
            } else {
                if (p == null) return right;
                p.replaceChild(this, right);
                return p.getRoot();
            }
        } else {
            if (right == null) {
                if (p == null) return left;
                p.replaceChild(this, left);
                return p.getRoot();
            } else {
                AbstractTreeNode replacement = null;
                if (numberOfRemovals % 2 == 0) replacement = getMaximumNodeInSubTree();
                else replacement = getMinimumNodeInSubTree();
                AbstractTreeNode newRoot = replacement.remove();
                this.data = replacement.data;
                return newRoot;
            }
        }
    }
    
    
    /**
     * Tauscht das gegebene Kind oldCild dieses Knoten mit dem
     * neuen Kind newChild aus. Falls dieser Knoten kein Kind
     * oldChild besitzt, passiert nichts.
     */
    public void replaceChild(AbstractTreeNode oldChild, AbstractTreeNode newChild) {
        if (left == oldChild) left = newChild;
        if (right == oldChild) right = newChild;
        oldChild.parent = null;
    }
    
    
    /**
     * Gibt genau dann true zurück, wenn dieser Knoten das linke
     * Kind seines Vaterknotens ist.
     */
    public boolean isLeftChild() {
        return (parent != null) && (parent.left == this);
    }
    
    /**
     * Gibt genau dann true zurück, wenn dieser Knoten das linke
     * Kind seines Vaterknotens ist.
     */
    public boolean isRightChild() {
        return (parent != null) && (parent.right == this);
    }
    
    
    /**
     * Gibt ein Element mit dem gegebenen Schlüssel zurück,
     * falls der Baum unter diesem Knoten ein solches enthält.
     * Ansonsten wird null zurückgegeben.
     */
    public KeyAble getObjectForKey(int key) {
        if (getKey() == key) return data;
        if ((key > getKey()) && (right != null)) return right.getObjectForKey(key); 
        if ((key <= getKey()) && (left != null)) return left.getObjectForKey(key);
        return null;
    }
    

}
