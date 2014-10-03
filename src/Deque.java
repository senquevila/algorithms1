import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

  /**
   * @param args
   */
  private Node first;
  private Node last;
  
  private class Node {
    private Item item;
    private Node next;
    private Node prev;
    
    public Node() {
      item = null;
      next = null;
      prev = null;
    }
    
    public Item getItem() {
      return this.item;
    }
    
    public void setItem(Item i) {
      this.item = i;
    }
    
    public Node getNext() {
      return this.next;
    }
    
    public void setNext(Node n) {
      this.next = n;
    }
        
    public Node getPrev() {
      return this.prev;
    }
    
    public void setPrev(Node p) {
      this.prev = p;
    }
  }
  
  public Deque() {
    first = null;
    last = null;
  }
  
  public boolean isEmpty() {
    if (first == null)
      return true;
    
    return false;
  }
  
  public int size() {
    if (this.isEmpty())
      return 0;
    else {
      int count = 1;
      Node n = first;
      
      while (n != null) {
        count++;
        n = n.getNext();
      }
      
      return count;
    }
  }
  
  private void addEmpty(Node n) {       
    first = n;
    last = n;
    n.setPrev(null);
    n.setNext(null);
  }
  
  public void addFirst(Item item) {
    if (item == null) 
      throw new java.lang.NullPointerException();
    
    Node n = new Node();
    n.setItem(item);
    
    if (this.isEmpty()) {
      this.addEmpty(n);
    } else {
      n.setNext(first);
      first.setPrev(n);
      n.setPrev(null);
      first = n;
    }
  }
  
  public void addLast(Item item) {
    if (item == null)
      throw new java.lang.NullPointerException();
    
    Node n = new Node();
    n.setItem(item);
    
    if (this.isEmpty()) {
      this.addEmpty(n);
    } else {
      n.setPrev(last);
      last.setNext(n);
      n.setNext(null);
      last = n;
    }    
  }
  
  public Item removeFirst() {
    if (first == null)
      throw new java.util.NoSuchElementException();
    
    Item i = first.getItem();
    first = first.getNext();
    
    if (this.isEmpty()) {
      last = null;
    } else {
      first.setPrev(null);
    }
    
    return i;
  }
  
  public Item removeLast() {
    if (last == null)
      throw new java.util.NoSuchElementException();
    
    Item i = last.getItem();
    last = last.getPrev();
    
    if (this.isEmpty()) {
      first = null;
    } else {
      last.setNext(null);
    }    
    
    return i;
  }
  
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }
  
  private class DequeIterator implements Iterator<Item> {
    public boolean hasNext() {
      return size() > 0;
    }
    
    public Item next() {
      if (isEmpty()) {
        throw new java.util.NoSuchElementException();
      }      
      
      return (Item) removeFirst();
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }
  
  public static void main(String[] args) {
    Deque<String> dq = new Deque<String>();      
        
    /*dq.addFirst("a");
    dq.addFirst("b");
    dq.addLast("c");
    dq.addFirst("");
    dq.removeFirst();
    dq.removeLast();*/
    
    Iterator<String> it = dq.iterator();
    
    if (dq.isEmpty())
      System.out.println("Vacio");
    else
      System.out.println("Lleno");
    
    if (it.hasNext())
      System.out.println("Siguiente");
    else
      System.out.println("Sin siguiente");
    
    while (it.hasNext()) {      
      System.out.println(it.next());
    }
  }
}
