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
      int count = 0;
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
    
    if (last == null) {
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
    private Node pointer = first;
    
    public boolean hasNext() {
      return (pointer != null);        
    }
    
    public Item next() {
      if (pointer == null) {
        throw new java.util.NoSuchElementException();
      }
      
      Node aux = pointer;      
      pointer = pointer.getNext();
      
      return aux.getItem();
    }

    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }
  
}
