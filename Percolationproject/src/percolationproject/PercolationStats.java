
package percolationproject;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] results;
    private int trials;
    private int n;
    
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        
        this.n = n;
        this.trials = trials;
        double NxN = n * n;        
        results = new double[trials];
        Percolation p;
        for (int k = 0; k < trials; ++k) {
            p = new Percolation(n);
            
            int i = 0;
            int j = 0;
            int openNum = 0;
            while (!p.percolates()) {
                i = StdRandom.uniform(1, n+1);
                j = StdRandom.uniform(1, n+1);
                if (p.isOpen(i,j)) 
                    continue;
                p.open(i,j);
                openNum++;
            }
                   
            results[k] = openNum / NxN;
        }
    }
    
    // find the mean
    public double mean() {
        return StdStats.mean(results);
    }
    
    // find the standard deviation
    public double stddev() {
        return StdStats.stddev(results);
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }
    
    // take two args N and T
    public static void main(String[] args) {
        
        int i = Integer.parseInt(args[0]);
        int j = Integer.parseInt(args[1]);
        
        PercolationStats stats = new PercolationStats(i, j);
        
        System.out.println("Mean " + stats.mean());
        System.out.println("Standard deviation "+ stats.stddev());
        System.out.println("Confidence intervals low and hihg: "+ stats.confidenceLo() + " "+ stats.confidenceHi());
        
    }
}
