package com.hit.wangoceantao.algorithm;

/**
 * @Description: ${TODO}
 * <p>
 * Created by wanghaitao on 2017/8/21 17:38.
 * <p>
 * Email：wanghaitao01@hecom.cn
 */

public class LineSegmentUtils {
    // Given three colinear points p, q, r, the function checks if point q lies on line segment 'pr'
    static boolean onSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;
        return false;
    }


    /**
     * To find orientation of ordered triplet (p, q, r).
     * The function returns following values
     *
     * @param p
     * @param q
     * @param r
     * @return 0 --> p, q and r are colinear, 1 --> 顺时针方向, 2 --> 逆时钟方向
     */
    static int orientation(Point p, Point q, Point r) {
        // See http://www.geeksforgeeks.org/orientation-3-ordered-points/  for details of below formula.
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;  // colinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1' and 'p2q2' intersect.
    public static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {

        // Find the four orientations needed for general and special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        // General case
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        // p1, q1 and p2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }


    public static void main(String[] args) {
        Point p1 = new Point(1, 1), q1 = new Point(10, 1);
        Point p2 = new Point(1, 2), q2 = new Point(10, 2);

        System.out.println(doIntersect(p1, q1, p2, q2) ?"Yes\n" :  "No\n");

        p1 = new Point(10, 0);
        q1 = new Point(0, 10);
        p2 = new Point(0, 0);
        q2 = new Point(10, 10);

        System.out.println(doIntersect(p1, q1, p2, q2) ?"Yes\n" :  "No\n");
        p1 = new Point(-5, -5);
        q1 = new Point(0, 0);
        p2 = new Point(1, 1);
        q2 = new Point(10, 100);
        System.out.println(doIntersect(p1, q1, p2, q2) ?"Yes\n" :  "No\n");

         p1=new Point(100,100);
         q1=new Point(200,100);
         p2=new Point(50,150);
         q2=new Point(150,150);
        System.out.println(doIntersect(p1, q1, p2, q2) ?"Yes\n" :  "No\n");
    }
}
