public class ThreeSum {
	public static int count(int[] a) { // Count triples that sum to 0.
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					if (a[i] + a[j] + a[k] == 0)
						cnt++;
		return cnt;
	}

	public static void main(String[] args) {
		int[] a = new int[args.length];
		
		for (int i = 0; i < args.length; i++) {
			a[i] = Integer.parseInt(args[i]);
		}
		
		StdOut.println(count(a));
	}
}