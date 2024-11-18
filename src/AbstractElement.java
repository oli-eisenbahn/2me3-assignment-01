public abstract class AbstractElement {
    
    private static int numberOfInstances = 0;

    public AbstractElement() {
        numberOfInstances++;
    }

    public static int getNumOfInstances() 
    {
        return numberOfInstances;
    }

}