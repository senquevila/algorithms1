public class Percolation {
  private boolean[][] sites;
  private WeightedQuickUnionUF uf;
  private WeightedQuickUnionUF uf1;
  private int size, top, bottom;

   // create N-by-N grid, with all sites blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
		
    sites = new boolean[n][n];
    uf = new WeightedQuickUnionUF(n * n + 2);
    uf1 = new WeightedQuickUnionUF(n * n + 1);
    size = n;
    top = n * n;
    bottom = n * n + 1;
		
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        sites[i][j] = false;
      }
    }
		
    // create virtual points
    for (int i = 0; i < n; i++) {
      uf.union(i, top);
      uf1.union(i, top);
      uf.union(n * (n - 1) + i, bottom);
    }
  }

// open site(row i, column j) if it is not already
  public void open(int i, int j) {
    if (i <= 0 || i > size || j <= 0 || j > size) {
	    throw new IndexOutOfBoundsException();
    }
		
	sites[i - 1][j - 1] = true;
    if (i - 2 >= 0 && sites[i - 2][j - 1]) {
      uf.union((i - 1) * size + j - 1, (i - 2) * size + j - 1);
      uf1.union((i - 1) * size + j - 1, (i - 2) * size + j - 1);
	  }
    if (i < size && sites[i][j - 1]) {
      uf.union((i - 1) * size + j - 1, i * size + j - 1);
      uf1.union((i - 1) * size + j - 1, i * size + j - 1);
    }
    if (j - 2 >= 0 && sites[i - 1][j - 2]) {
      uf.union((i - 1) * size + j - 1, (i - 1) * size + j - 2);
      uf1.union((i - 1) * size + j - 1, (i - 1) * size + j - 2);
    }
    if (j < size && sites[i - 1][j]) {
      uf.union((i - 1) * size + j - 1, (i - 1) * size + j);
      uf1.union((i - 1) * size + j - 1, (i - 1) * size + j);
    }
  }

  // is site(row i, column j) open?
  public boolean isOpen(int i, int j) {
    if (i <= 0 || i > size || j <= 0 || j > size) {
      throw new IndexOutOfBoundsException();
    }
		
    return sites[i - 1][j - 1];
  }

	// is site(row i, column j) full?
	public boolean isFull(int i, int j) {
		if (i <= 0 || i > size || j <= 0 || j > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (uf1.connected((i - 1) * size + j - 1, top) && isOpen(i, j)) {
			return true;
		}
		
		return false;
	}

	// does the system percolate?
	public boolean percolates() {
		if (size == 1) {
			return isOpen(1, 1);
		}
		
		return uf.connected(top, bottom);
	}
}