import java.util.Random;
public class NiceGameDriver
{

    public static void main(String[] args)
    { 
        int gameTime = 0;
        int gameScore =0;

        int numberBalls = 1;
        Random rand = new Random(); 
        Ball footballs[] = new Ball[numberBalls];


        GameArena map = new GameArena(1000,1000);
        Plane player = new Plane(5,400,400,1,5,"RED");
        drawObjects(player,map);

        for (int i = 0; i< numberBalls; i++) 
        {
        int rand_int1 = rand.nextInt(50);
        int rand_int2 = (rand.nextInt(99));
        footballs[i] = new Ball(500,500,20,"MAGENTA",rand_int2,rand_int1);
        map.addBall(footballs[i]);
        }


        while (true)
        {
            
            try 
            {
                Thread.sleep(50);
            } 
            catch (InterruptedException ie) 
            {
                Thread.currentThread().interrupt();
            }
            gameTime +=1;
            if (gameTime%20 == 0)
            {
                gameScore+=1;
                System.out.println(gameScore);
            }
            if (map.upPressed())
            {
                moveVertial(player,map,-player.getSpeed());
                
            }
            if (map.downPressed())
            {
                moveVertial(player,map,player.getSpeed());
                
            }
            if (map.leftPressed())
            {
                moveHorizonal(player,map,-player.getSpeed());
                
            }
            if (map.rightPressed())
            {
                moveHorizonal(player,map,player.getSpeed());
                
            }
            if (map.spacePressed())
            {
                player.setSpeed(15);
            }else{
                player.setSpeed(5);
            }

            for (int i = 0; i< numberBalls; i++) 
            {
                ballMovement(footballs[i],map);
                checkCollision(player,map,footballs[i]);
            }


        }
    
    }
    public static void drawObjects(Plane plane1, GameArena mapArea)
    {
        for (int i = 0; i<plane1.getNumPart();i++)
        {
        mapArea.addRectangle(plane1.returnParts(i));
        }

    }
    public static void moveVertial(Plane plane1, GameArena mapArea, int direction)
    {
        boolean movementAllowed = true;
        for (int i = 0; i<plane1.getNumPart();i++)
        {
            if (plane1.returnParts(i).getYPosition()+direction > (0.75*mapArea.getArenaHeight())||plane1.returnParts(i).getYPosition()+direction <(0.25*mapArea.getArenaHeight()))
            {   
                movementAllowed = false;
            }
        }
        if (movementAllowed)
        {
            for (int i = 0; i<plane1.getNumPart();i++)
            {
                plane1.returnParts(i).setYPosition(plane1.returnParts(i).getYPosition()+direction);
            }
        }
    }
    public static void moveHorizonal(Plane plane1,GameArena mapArea, int direction)
    {
        boolean movementAllowed = true;
        for (int i = 0; i<plane1.getNumPart();i++)
        {
            if ((plane1.returnParts(i).getXPosition()+direction > (0.75*mapArea.getArenaWidth()))||(plane1.returnParts(i).getXPosition()+direction < (0.25* mapArea.getArenaWidth())))
            {
                movementAllowed = false;
            }
        }
        if (movementAllowed)
        {
            for (int i = 0; i<plane1.getNumPart();i++)
            {
                plane1.returnParts(i).setXPosition(plane1.returnParts(i).getXPosition()+direction);
            }
        }
    }
    public static void ballMovement(Ball ballObject, GameArena map)
    {
            Random rand = new Random(); 
            double pathAngleSplit = ((double)(ballObject.getPathAngle() / (double)(100)));
            double movementValueX = pathAngleSplit * ballObject.getPathSpeed();
            double movementValueY = (1 - pathAngleSplit) * ballObject.getPathSpeed();
            if ((ballObject.getXPosition() + movementValueX > (0.75*map.getArenaWidth()))||(ballObject.getXPosition() + movementValueX) <(0.25*map.getArenaWidth())) {
                ballObject.setPathSpeed(-(ballObject.getPathSpeed()));
                int rand_int2 = (rand.nextInt(100));
                ballObject.setPathAngle(rand_int2);
            }
            
            ballObject.setXPosition(ballObject.getXPosition() + movementValueX);
            
            if ((ballObject.getYPosition() + movementValueY > (0.75*map.getArenaHeight()))||(ballObject.getYPosition() + movementValueY) <(0.25*map.getArenaHeight())) {
                ballObject.setPathSpeed(-(ballObject.getPathSpeed()));
                int rand_int2 = (rand.nextInt(99));
                ballObject.setPathAngle(rand_int2);
            }
            
            ballObject.setYPosition(ballObject.getYPosition() + movementValueY);

    }
    public static void checkCollision(Plane plane1, GameArena map, Ball enemy)
    {
        int counter = 0;
        System.out.println(plane1.returnParts(0).getXPosition()+ " " + (int)enemy.getXPosition());
         for (int i = 0; i<plane1.getNumPart();i++)
        {
           counter += counter;
            if ((((plane1.returnParts(i).getXPosition() - enemy.getPathSpeed()) <= (int)enemy.getXPosition()) && ((int)enemy.getXPosition() <= (plane1.returnParts(i).getXPosition() - enemy.getPathSpeed()))) && (((plane1.returnParts(i).getYPosition() - enemy.getPathSpeed()) <= (int)enemy.getYPosition()) && ((int)enemy.getYPosition() <= (plane1.returnParts(i).getYPosition() - enemy.getPathSpeed()))))
            {
                System.out.println("hello");
                System.out.println(plane1.returnParts(i).getXPosition()+ " " + (int)enemy.getXPosition());
                plane1.returnParts(i).setColour("RED");
            }
        }


    }




}