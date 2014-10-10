import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

  /**
   * @param args
   */
  private Item[] queue;
  private int N;
  private int capacity;
  
  public RandomizedQueue() {
    N = 0;
    capacity = 1;
    queue = ((Item[]) new Object[capacity]);
  }
  
  public boolean isEmpty() {
    return N == 0;
  }
  
  public int size() {
    return N;
  }
  
  private void resize(char typeResize) {   
    Item[] newQueue = null;
    int newCapacity;
    
    if (typeResize == '+') {
      newCapacity = capacity * 2;
      newQueue = ((Item[]) new Object[newCapacity]);
      
      for (int i = 0; i < capacity; i++) {
        newQueue[i] = queue[i];
      }
      capacity = newCapacity;      
    } else if (typeResize == '-') {
      capacity = capacity / 2;
      newQueue = ((Item[]) new Object[capacity]);
      
      for (int i = 0; i < capacity; i++) {
        newQueue[i] = queue[i];
      }
    }      
        
    queue = newQueue;
  }
  
  public void enqueue(Item item) {
    if (item == null)
      throw new java.lang.NullPointerException();
    
    if (capacity < N + 1) {
      resize('+');
    }
    
    queue[N++] = item;
  }
  
  public Item dequeue() {
    if (isEmpty())
      throw new java.util.NoSuchElementException();
    
    int random = StdRandom.uniform(N);
    Item itemRnd = queue[random];
    queue[random] = queue[--N];
    queue[N] = null;
    
    if (capacity / 4 > N) {
      resize('-');
    }
    
    return itemRnd;
  }
  
  public Item sample() {
    if (isEmpty()) 
      throw new java.util.NoSuchElementException();
    
    return queue[StdRandom.uniform(N)];
  }
  
  public Iterator<Item> iterator() {
    return new ListIterator();    
  }
  
  private class ListIterator implements Iterator<Item> {
    private int current = 0;
    private int[] shuffledIndexes = new int[N];
    
    public boolean hasNext() {
      if (current == 0) {
        for (int i = 0; i < N; i++) {
          shuffledIndexes[i] = i;
        }
        
        StdRandom.shuffle(shuffledIndexes);
      }
      
      return current < N;
    }
    
    public Item next() {
      if (current == 0) {
        for (int i = 0; i < N; i++) {
          shuffledIndexes[i] = i;
        }
        
        StdRandom.shuffle(shuffledIndexes);
      }
      
      if (current >= N || size() == 0) {
        throw new java.util.NoSuchElementException();
      }
      
      return queue[shuffledIndexes[current++]];
    }
    
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }
  
}
