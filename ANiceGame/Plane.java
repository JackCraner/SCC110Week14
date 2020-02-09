public class Plane
{
    private int numParts = 6;
    private int planeSpeed;
    Rectangle parts[] = new Rectangle[numParts];

    public Plane(int numberParts, int xValue, int yValue, int size,int speed, String col)
    {
        int offset = (10*size);
        parts[0] = new Rectangle(xValue,yValue,offset,offset,col);
        parts[1] = new Rectangle(xValue,yValue+offset,offset,offset,col);
        parts[2] = new Rectangle(xValue,yValue+(2*offset),offset,offset,col);
        parts[3] = new Rectangle(xValue+offset,yValue+(2*offset),offset,offset,col);
        parts[4] = new Rectangle(xValue-offset,yValue+(2*offset),offset,offset,col);
        parts[5] = new Rectangle(xValue,yValue+(3*offset),offset,offset,col);
        planeSpeed = speed;
    }
    public int getSpeed()
    {
        return planeSpeed;
    }
    public void setSpeed(int speed)
    {
        planeSpeed = speed;
    }
    public int getNumPart()
    {
        return numParts;
    }
    public Rectangle returnParts(int partNum)
    {
        return parts[partNum];

    }
    
}