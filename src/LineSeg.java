import java.lang.Math;

public class LineSeg extends AbstractElement implements CollisionDetector{
    
    private Point begin;
    private Point end;
    private static int numberOfInstances;

    public LineSeg()
    {
        begin = new Point();
        end = new Point();
        numberOfInstances += 1;
    }

    public LineSeg(Point begin, Point end)
    {
        this.begin = begin;
        this.end = end;
        numberOfInstances += 1;
    }

    public Point getBegin()
    {
        return begin;
    }

    public Point getEnd()
    {
        return end;
    }

    public int getNumOfInstances() 
    {
        return numberOfInstances;
    }

    public boolean intersect(Point p)
    {
        //to avoid redundency, create a new run an intersect on the given point
        LineSeg l = new LineSeg(begin, end);
        return p.intersect(l);
    }

    public boolean intersect(LineSeg l)
    {
        //assign x and y values of each point
        float beginX1 = begin.getX();
        float beginY1 = begin.getY();
        float endX1 = end.getX();
        float endY1 = end.getY();
        float beginX2 = (l.getBegin()).getX();
        float beginY2 = (l.getBegin()).getY();
        float endX2 = (l.getEnd()).getX();
        float endY2 = (l.getEnd()).getY();

        //get the slopes and offsets of each line segment
        float slope1 = (beginY1 - endY1) / (beginX1 - beginY1);
        float slope2 = (beginY2 - endY2) / (beginX2 - beginY2);
        float offset1 = beginY1 - (beginX1 * slope1);
        float offset2 = beginY2 - (beginX2 * slope2);

        //check if they're parallel and then check if they overlap
        if (slope1 == slope2) {
            if (offset1 == offset2 && (l.intersect(begin) || l.intersect(end)))
                {return true;}
            return false; }
        
        //calculate intersection point
        float intX = (offset2 - offset1) / (slope2 - slope1);
        float intY = (offset1 * slope2 - slope2 * offset1) / (slope2 - slope1);

        //check if intersection is within bounds
        if (!((intX < beginX1 && intX > endX1) || (intX > beginX1 && intX < endX1))) {return false;}
        if (!((intX < beginX2 && intX > endX2) || (intX > beginX2 && intX < endX2))) {return false;}
        if (!((intY < beginY1 && intY > endY1) || (intY > beginY1 && intY < endY1))) {return false;}
        if (!((intY < beginY2 && intY > endY2) || (intY > beginY2 && intY < endY2))) {return false;}
        return true;
    }

    public boolean intersect(Rectangle r)
    {
        //check if either point is within the rect
        if (r.intersect(begin) || r.intersect(end))
            {return true;}
        
        boolean bool = false;
        float lineX = begin.getX();
        float lineY = begin.getY();
        float left = r.getLeft();
        float right = r.getRight();
        float top = r.getTop();
        float bottom = r.getBottom();

        //find which side of the rect one of the points is on, and then see if it intersects with the 1 or 2 sides it can see
        if (lineX <= left) {
            Point p1 = new Point(left, top);
            Point p2 = new Point(left, bottom);
            LineSeg l = new LineSeg(p1, p2);
            LineSeg l2 = new LineSeg(begin, end);
            bool = bool || l.intersect(l2); }
        if (lineX >= right) {
            Point p1 = new Point(right, top);
            Point p2 = new Point(right, bottom);
            LineSeg l = new LineSeg(p1, p2);
            LineSeg l2 = new LineSeg(begin, end);
            bool = bool || l.intersect(l2); }
        if (lineY <= bottom) {
            Point p1 = new Point(left, bottom);
            Point p2 = new Point(right, bottom);
            LineSeg l = new LineSeg(p1, p2);
            LineSeg l2 = new LineSeg(begin, end);
            bool = bool || l.intersect(l2); }
        if (lineY >= top) {
            Point p1 = new Point(left, top);
            Point p2 = new Point(right, top);
            LineSeg l = new LineSeg(p1, p2);
            LineSeg l2 = new LineSeg(begin, end);
            bool = bool || l.intersect(l2); }
        
        return bool;
    }

    public boolean intersect(Circle c)
    {
        //slope of line segment
        float slope = (begin.getY() - end.getY()) / (begin.getX() - end.getX());
        float circleX = (c.getCenter()).getX();
        float circleY = (c.getCenter()).getY();
        //use the slope as an angle and the hypotenuse (radius of circle) to create a new line segment
        //perpendicular to the old one and spanning the diameter of the circle
        double newYD = Math.sin((Math.PI / 2) * (- 1 / slope)) * c.getRadius();
        float newY = (float)newYD;
        double newXD = Math.sqrt((c.getRadius() * c.getRadius()) - (newY * newY));
        float newX = (float)newXD;
        //create the new line segment and compare
        Point p1 = new Point((circleX + newX), (circleY + newY));
        Point p2 = new Point((circleX - newX), (circleY - newY));
        LineSeg l = new LineSeg(p1, p2);
        LineSeg l2 = new LineSeg(begin, end);
        
        if (l.intersect(l2))
            {return true;}
        //check if endpoints intersect
        return (c.intersect(begin) || c.intersect(end));
    }

}