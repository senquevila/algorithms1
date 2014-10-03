public class Test1 {
    public static void main(String[] args) {
	   Percolation p = new Percolation(5);
	   p.open(2, 1);
	   p.open(2, 2);
	   p.open(1, 3);
	   p.open(2, 3);
	   p.open(3, 3);
	   p.open(4, 3);
	   p.open(5, 3);
		
		System.out.println(p.toString());
		
		if (p.percolates())
			System.out.println("Percolates");
		else
			System.out.println("Not percolate");
	}
}
