public class Circle extends AbstractElement implements CollisionDetector {
    
    private Point center;
    private float radius;
    private static int numberOfInstances;

    public Circle()
    {
        center = new Point();
        radius = 0;
        numberOfInstances += 1;
    }

    public Circle(Point center, float radius)
    {
        this.center = center;
        this.radius = radius;
        numberOfInstances += 1;
    }

    public Point getCenter()
    {
        return center;
    }

    public float getRadius()
    {
        return radius;
    }

    public static int getNumOfInstances() 
    {
        return numberOfInstances;
    }

    public boolean intersect(Point p)
    {
        Circle c = new Circle(center, radius);
        return p.intersect(c);
    }

    public boolean intersect(LineSeg l)
    {
        Circle c = new Circle(center, radius);
        return l.intersect(c);
    }

    public boolean intersect(Rectangle r)
    {
        Circle c = new Circle(center, radius);
        return r.intersect(c);
    }

    public boolean intersect(Circle c)
    {
        float circleX = (c.getCenter()).getX();
        float circleY = (c.getCenter()).getY();
        //check if the distance between centers is larger than both radius
        if (Math.sqrt((center.getX() - circleX) * (center.getX() - circleX) + (center.getY() - circleY) * (center.getY() - circleY)) < c.getRadius() + radius)
            {return true;}
        return false;
    }

}
