import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class BruteCollinearPoints {

    private ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("Argument is null.");
        }

        for (Point p : points) {
            if (p == null) {
                throw new java.lang.IllegalArgumentException("Null point exists.");
            }
        }

        int len = points.length;
        Point[] sortedPoints = Arrays.copyOf(points, len);
        Arrays.sort(sortedPoints);

        for (int i = 1; i < len; i++) {
            if (sortedPoints[i - 1].compareTo(sortedPoints[i]) == 0) {
                throw new java.lang.IllegalArgumentException("Repeated point exists.");
            }
        }

        segments = new ArrayList<>();

        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                double slope1 = sortedPoints[i].slopeTo(sortedPoints[j]);
                for (int k = j + 1; k < len - 1; k++) {
                    double slope2 = sortedPoints[i].slopeTo(sortedPoints[k]);
                    if (slope1 != slope2) {
                        continue;
                    }
                    for (int l = k + 1; l < len; l++) {
                        double slope3 = sortedPoints[i].slopeTo(sortedPoints[l]);
                        if (slope2 != slope3) {
                            continue;
                        }
                        segments.add(new LineSegment(sortedPoints[i], sortedPoints[l]));
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return this.segments.toArray(new LineSegment[this.segments.size()]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

