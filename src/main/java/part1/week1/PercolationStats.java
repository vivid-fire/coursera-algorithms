package part1.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        final double numberOfSite = n * n;
        final double[] openSitesFraction = new double[trials];
        int x, y;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                x = StdRandom.uniform(n) + 1;
                y = StdRandom.uniform(n) + 1;
                p.open(x, y);
            }
            openSitesFraction[i] = p.numberOfOpenSites() / numberOfSite;
        }
        mean = StdStats.mean(openSitesFraction);
        stddev = StdStats.stddev(openSitesFraction);
        double marginOfError = marginOfError(stddev, openSitesFraction.length);
        confidenceLo = mean - marginOfError;
        confidenceHi = mean + marginOfError;
    }

    private static double marginOfError(double stddev, int n) {
        return 1.96 * stddev / Math.sqrt(n);
    }

    public static void main(String[] args) {
        if (args.length != 2)
            return;
        final int n = Integer.parseInt(args[0]);
        final int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, T);
        String format = "%1$-23s = %2$s%n";
        StdOut.printf(format, "mean", stats.mean());
        StdOut.printf(format, "stddev", stats.stddev());
        StdOut.printf(format, "95% confidence interval", "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }
}
