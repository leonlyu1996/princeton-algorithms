import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

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
        Point[] equalSlopePoints = new Point[len];

        for (int i = 0; i < len; i++) {
            Point p = sortedPoints[i];
            Comparator<Point> pComparator = p.slopeOrder();
            Point[] otherPoints = Arrays.copyOf(sortedPoints, len);
            Arrays.sort(otherPoints, pComparator);
            int equalSlopePointCount = 0;
            for (int j = 1; j < len - 1; j++) {
                if (p.slopeTo(otherPoints[j]) == p.slopeTo(otherPoints[j + 1])) {
                    equalSlopePoints[equalSlopePointCount++] = otherPoints[j];
                } else {
                    if (equalSlopePointCount >= 2) {
                        equalSlopePoints[equalSlopePointCount++] = otherPoints[j];
                        equalSlopePoints[equalSlopePointCount++] = p;
                        Arrays.sort(equalSlopePoints, 0, equalSlopePointCount);
                        if (equalSlopePoints[0] == p) {
                            segments.add(new LineSegment(p, equalSlopePoints[equalSlopePointCount - 1]));
                        }
                    }
                    equalSlopePointCount = 0;
                }
            }
            if (equalSlopePointCount >= 2) {
                equalSlopePoints[equalSlopePointCount++] = otherPoints[len - 1];
                equalSlopePoints[equalSlopePointCount++] = p;
                Arrays.sort(equalSlopePoints, 0, equalSlopePointCount);
                if (equalSlopePoints[0] == p) {
                    segments.add(new LineSegment(p, equalSlopePoints[equalSlopePointCount - 1]));
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}