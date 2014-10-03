public class PercolationStats {
	private double[] threshold;
	private int times;

	// perform t independent computational experiments on an N-by-N grid
	public PercolationStats(int n, int t) {
		if (n <= 0 || t <= 0)
			throw new java.lang.IllegalArgumentException();
		threshold = new double[t];
		times = t;
		for (int i = 0; i < t; i++) {
			Percolation p = new Percolation(n);
			threshold[i] = 0;
			while (!p.percolates()) {
				int j = StdRandom.uniform(1, n + 1);
				int k = StdRandom.uniform(1, n + 1);
				if (!p.isOpen(j, k)) {
					p.open(j, k);
					threshold[i]++;
				}
			}
			threshold[i] = threshold[i] / (n * n);
		}
	}

	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(threshold);
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return StdStats.stddev(threshold);
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return mean() - stddev() * 1.96 / Math.sqrt(times);
	}

	// return upper bound of the 95% confidence interval
	public double confidenceHi() {
		return mean() + stddev() * 1.96 / Math.sqrt(times);
	}

	// test client
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats per = new PercolationStats(n, t);
		System.out.println("mean                    = " + per.mean());
		System.out.println("stddev                  = " + per.stddev());
		System.out.println("95% confidence interval = " + per.confidenceLo()
				+ ", " + per.confidenceHi());
	}
}
