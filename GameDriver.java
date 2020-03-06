import java.util.Random; 
public class GameDriver
{
    
    public static void main(String[] args){ 
        int numberBalls = 100;
        Random rand = new Random(); 
        Ball footballs[] = new Ball[numberBalls];
        
        for (int i = 0; i< numberBalls; i++) 
        {
        int rand_int1 = rand.nextInt(50);
        int rand_int2 = (rand.nextInt(99));
        footballs[i] = new Ball(200,200,20,"MAGENTA",rand_int2,rand_int1);
        map.addBall(footballs[i]);
        }
       
       
    
        while (true) {
            try 
            {
                Thread.sleep(50);
            } 
            catch (InterruptedException ie) 
            {
                Thread.currentThread().interrupt();
            }
            for (int i = 0; i< numberBalls; i++) 
            {
            double pathAngleSplit = ((double)(footballs[i].getPathAngle() / (double)(100)));
            double movementValueX = pathAngleSplit * footballs[i].getPathSpeed();
            double movementValueY = (1 - pathAngleSplit) * footballs[i].getPathSpeed();
            if ((footballs[i].getXPosition() + movementValueX > map.getArenaWidth())||(footballs[i].getXPosition() + movementValueX) <0) {
                footballs[i].setPathSpeed(-(footballs[i].getPathSpeed()));
                int rand_int2 = (rand.nextInt(100));
                footballs[i].setPathAngle(rand_int2);
            }
            
            footballs[i].setXPosition(footballs[i].getXPosition() + movementValueX);
            
            if ((footballs[i].getYPosition() + movementValueY > map.getArenaHeight())||(footballs[i].getYPosition() + movementValueY) <0) {
                footballs[i].setPathSpeed(-(footballs[i].getPathSpeed()));
                int rand_int2 = (rand.nextInt(99));
                footballs[i].setPathAngle(rand_int2);
            }
            
            footballs[i].setYPosition(footballs[i].getYPosition() + movementValueY);
            }
        }
        

    }
}



