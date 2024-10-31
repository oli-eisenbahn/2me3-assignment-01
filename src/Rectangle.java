public class Rectangle extends AbstractElement implements CollisionDetector {
    
    private float left;
    private float right;
    private float top;
    private float bottom;
    private static int numberOfInstances;

    public Rectangle()
    {
        left = 0;
        right = 0;
        top = 0;
        bottom = 0;
        numberOfInstances += 1;
    }

    public Rectangle(float left, float right, float top, float bottom)
    {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        numberOfInstances += 1;
    }

    public float getLeft()
    {
        return left;
    }

    public float getRight()
    {
        return right;
    }

    public float getTop()
    {
        return top;
    }

    public float getBottom()
    {
        return bottom;
    }

    public int getNumOfInstances() 
    {
        return numberOfInstances;
    }

    public boolean intersect(Point p)
    {
        Rectangle r = new Rectangle(left, right, top, bottom);
        return p.intersect(r);
    }

    public boolean intersect(LineSeg l)
    {
        Rectangle r = new Rectangle(left, right, top, bottom);
        return l.intersect(r);
    }

    public boolean intersect(Rectangle r)
    {
        //check if either edge is within the range of the other rectangle, or if edges are on either side
        boolean xWithin = (r.getLeft() < left && left < r.getRight()) || (r.getLeft() < right && right < r.getRight()) || (r.getLeft() > left && right > r.getRight());
        boolean yWithin = (r.getBottom() < bottom && bottom < r.getTop()) || (r.getBottom() < top && top < r.getTop()) || (r.getBottom() > bottom && top > r.getTop());
        if (xWithin && yWithin)
            {return true;}
        return false;
    }

    public boolean intersect(Circle c)
    {
        float circleX = (c.getCenter()).getX();
        float circleY = (c.getCenter()).getY();

        //check if the circle's center falls within the rectangle
        if (circleX > right && circleX < left && circleY > bottom && circleY < top)
            {return true;}

        Point begin;
        Point end;
        LineSeg l;
        if (circleX <= right) {
            begin = new Point(right, bottom);
            end = new Point(right, top);
            l = new LineSeg(begin, end);
            return l.intersect(c); }
        if (circleX >= left) {
            begin = new Point(left, bottom);
            end = new Point(left, top);
            l = new LineSeg(begin, end);
            return l.intersect(c); }
        if (circleY <= bottom) {
            begin = new Point(left, bottom);
            end = new Point(right, bottom);
            l = new LineSeg(begin, end);
            return l.intersect(c); }
        if (circleY >= top) {
            begin = new Point(left, top);
            end = new Point(right, top);
            l = new LineSeg(begin, end);
            return l.intersect(c); }
        return false;
    }

}
