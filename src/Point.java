import java.lang.Math;

public class Point extends AbstractElement implements CollisionDetector{
    
    private float x;
    private float y;
    private static int numberOfInstances;

    public Point()
    {
        x = 0;
        y = 0;
        numberOfInstances += 1;
    }

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
        numberOfInstances += 1;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public int getNumOfInstances()
    {
        return numberOfInstances;
    }

    public boolean intersect(Point p)
    {
        if (p.getX() == x && p.getY() == y)
            {return true;}
        return false;
    }

    public boolean intersect(LineSeg l)
    {
        float lineXB = (l.getBegin()).getX();
        float lineXE = (l.getEnd()).getX();
        float lineYB = (l.getBegin()).getY();
        float lineYE = (l.getEnd()).getY();
        //check if x and y fall in range of line segment
        if ((lineXB <= x && x <= lineXE) && (lineYB <= y && y <= lineYE)) {
            //check if distance between the two points is the same for the x and y, which would mean it would be on the line segment
            if (((x - lineXE) / (lineXB - lineXE)) == ((y - lineYE) / (lineYB - lineYE)))
                {return true;} }
        return false;
    }

    public boolean intersect(Rectangle r)
    {
        if ((r.getLeft() <= x && x <= r.getRight()) && (r.getBottom() <= y && y <= r.getTop()))
            {return true;}
        return false;
    }

    public boolean intersect(Circle c)
    {
        float centerX = (c.getCenter()).getX();
        float centerY = (c.getCenter()).getY();
        if (Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)) <= c.getRadius())
            {return true;}
        return false;
    }

}