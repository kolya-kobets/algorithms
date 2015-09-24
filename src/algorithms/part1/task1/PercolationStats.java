//package algorithms.part1.task1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double m, dev, confLo, confHi;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        double[] treshold = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                Pair next = getFree1(N, p);
                p.open(next.first(), next.second());
                treshold[i]++;
            }
            treshold[i] /= N * N;
        }

        m = StdStats.mean(treshold);
        dev = StdStats.stddev(treshold);
        confLo = m - 1.96 * dev / Math.sqrt(T);
        confHi = m + 1.96 * dev / Math.sqrt(T);
    }

    public double mean() {
        return m;
    }

    public double stddev() {
        return dev;
    }

    public double confidenceLo() {
        return confLo;
    }

    public double confidenceHi() {
        return confHi;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            return;
        }
        StdRandom.setSeed(System.currentTimeMillis());
        
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        System.out.format("mean                    = %f\n", ps.mean());
        System.out.format("stddev                  = %f\n", ps.stddev());
        System.out.format("95%% confidence interval = %f, %f\n",
                ps.confidenceLo(), ps.confidenceHi());
    }

    private class Pair {
        private int v1, v2;

        public Pair(int a, int b) {
            v1 = a;
            v2 = b;
        }

        public int first() {
            return v1;
        }

        public int second() {
            return v2;
        }

    }

    private Pair getFree1(int N, Percolation p) {
        int next = 0;
        do {
            next = StdRandom.uniform(0, N * N);
        } while (p.isOpen(getRow(N, next) + 1, getCln(N, next) + 1));

        return new Pair(getRow(N, next) + 1, getCln(N, next) + 1);
    }

//    private Pair getFree2(int N, Percolation p) {
//        int start = StdRandom.uniform(0, N * N);
//        boolean right = StdRandom.uniform(2) == 1;
//
//        int next = start;
//        while (p.isOpen(getRow(N, next) + 1, getCln(N, next) + 1)) {
//            if (right) {
//                next++;
//            } else {
//                next--;
//            }
//            if ((next >= N * N && right) || (next < 0 && !right)) {
//                next = start;
//                right = !right;
//                continue;
//            }
//        }
//        return new Pair(getRow(N, next) + 1, getCln(N, next) + 1);
//    }

    private int getRow(int N, int rawInd) {
        return (rawInd) / N;
    }

    private int getCln(int N, int rawInd) {
        return (rawInd) % N;
    }
}