package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private double[] threshold;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("Wrong arguments!");
        }

        threshold = new double[T];

        for (int t = 0; t < T; t += 1) {
            Percolation experiment = pf.make(N);
            while (!experiment.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                experiment.open(row, col);
            }
            threshold[t] = experiment.numberOfOpenSites();
        }


    }
    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (double x: threshold) {
            sum += x;
        }
        return sum/threshold.length;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        double partialSum = 0;
        for (double x: threshold) {
            partialSum += (x - mean())*(x - mean());
        }
        return Math.sqrt(partialSum/(threshold.length - 1));
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev())/Math.sqrt(threshold.length);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev())/Math.sqrt(threshold.length);
    }
}
