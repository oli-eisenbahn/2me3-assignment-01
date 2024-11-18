public class TestElements {
    public static void main(String[] args) {
		testStaticElements();
        testCollisions();
    }

    static void testStaticElements(){
        
        // Uncomment the following code when you want to test your classes
        Point p1 = new Point(1,1);
		Point p2 = new Point(4,3);
		LineSeg l1 = new LineSeg(new Point(2,2), new Point(5,3));
		LineSeg l2 = new LineSeg(p1, p2);
		Circle c1 = new Circle(p1, 3);
		Circle c2 = new Circle(p2, 5);
		Rectangle r1 = new Rectangle(3,5,3,0);
		Rectangle r2 = new Rectangle(1,7,6,3);
		
		if (Point.getNumOfInstances()!= 4){
			System.out.println("Point.NumberOfInsances error");

		}
		if (LineSeg.getNumOfInstances()!= 2){
			System.out.println("LineSeg.NumberOfInsances error");
		}
		if (Circle.getNumOfInstances()!= 2){
		 	System.out.println("Circle.NumberOfInsances error");
		}
		if (Rectangle.getNumOfInstances()!= 2){
		 	System.out.println("Rectangle.NumberOfInsances error");
		}
		if (AbstractElement.getNumOfInstances()!=10){
		 	System.out.println("AbstractElement.NumberOfInsances error");
			 System.out.println(AbstractElement.getNumOfInstances());
		}
    }

    // Write your own test cases for testing intersections
    static void testCollisions(){
		Point p1 = new Point(2,2);
		Point p2 = new Point(3,2);
		LineSeg l1 = new LineSeg(new Point(0,0), new Point(3,3));
		LineSeg l2 = new LineSeg(p1, p2);
		LineSeg l3 = new LineSeg(new Point(0,5), new Point(3,0));
		Circle c1 = new Circle(p1, 1);
		Circle c2 = new Circle(p2, 5);
		Rectangle r1 = new Rectangle(0,4,5,1);
		Rectangle r2 = new Rectangle(1,7,6,3);
		System.out.println(l1.intersect(c1));
    }
}