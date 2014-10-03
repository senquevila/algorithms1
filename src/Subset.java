public class Subset {

  /**
   * @param args
   */  
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    RandomizedQueue<String> rndq = new RandomizedQueue<String>();
    int k = Integer.parseInt(args[0]);    
        
    while (!StdIn.isEmpty()) {
      String  s = StdIn.readString();
      rndq.enqueue(s);
    }   
    
    for (int i = 0; i < k; i++) {
      StdOut.println(rndq.dequeue());
    }
  }
}
