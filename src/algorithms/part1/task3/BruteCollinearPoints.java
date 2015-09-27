package algorithms.part1.task3;

public class BruteCollinearPoints {

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException();
            }
        }

        final int N = points.length;
        for (int i = 0; i < N-3; i++) {
            for(int j=i+1; j<N-2; j++) {
                double slope1 = points[i].slopeTo(points[j]);
                if(Double.isInfinite(slope1) && slope1 < 0) {
                    throw new NullPointerException();
                }
                for(int k=j+1; k<N-1; k++) {
                    double slope2 = points[i].slopeTo(points[k]);
                    for(int m=k+1; m<N; m++) {
                        
                    }
                }
            }
        }
    }

    public int numberOfSegments() {

    }

    public LineSegment[] segments() {

    }
}