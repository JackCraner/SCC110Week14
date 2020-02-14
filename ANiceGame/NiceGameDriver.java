import java.util.Random;
import java.lang.Math; 
import java.util.List;
import java.util.ArrayList;
public class NiceGameDriver
{

    public static void main(String[] args)
    { 
        int gameTime = 0;
        int gameScore =0;               

        int maxNumberBalls = 40;
        int numberBalls = 4;
        
        List<Ball> footballs = new ArrayList<>();


        GameArena map = new GameArena(1000,1000);
        Plane player = new Plane(5,map.getArenaWidth()/3,map.getArenaHeight()/3,1,5,"GREEN");
        drawObjects(player,map);

        for (int i = 0; i< numberBalls; i++)            //initializes the balls via their constructors
        {
            addBall(i, footballs, map);
        }
        drawBoundaries(map);

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
            if (gameTime%10 == 0)
            {
                gameScore+=1;
                addBall(numberBalls, footballs, map);
                numberBalls +=1;
                
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
                ballMovement(footballs.get(i),map);
                checkCollision(player,map,footballs.get(i),gameScore);
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
            if ((ballObject.getXPosition() + movementValueX > (0.75*map.getArenaWidth()))) {
                ballObject.setXPosition(ballObject.getXPosition()-ballObject.getSize());
                ballObject.setPathSpeed(-(ballObject.getPathSpeed()));
                int rand_int2 = (rand.nextInt(140) + 40);
                ballObject.setPathAngle(rand_int2);
           
            }
            if ((ballObject.getXPosition() + movementValueX) <(0.25*map.getArenaWidth()))
            {
                ballObject.setXPosition(ballObject.getXPosition()+ballObject.getSize());
                ballObject.setPathSpeed(-(ballObject.getPathSpeed()));
                int rand_int2 = (rand.nextInt(170) + 10);
                ballObject.setPathAngle(rand_int2);
            }
            
            ballObject.setXPosition(ballObject.getXPosition() + movementValueX);
            
            if ((ballObject.getYPosition() + movementValueY > (0.75*map.getArenaHeight()))) 
            {
                ballObject.setYPosition(ballObject.getYPosition()-ballObject.getSize());
                ballObject.setPathSpeed(-(ballObject.getPathSpeed()));
                int rand_int2 = (rand.nextInt(170)+10);
                ballObject.setPathAngle(rand_int2);
               
            }
            if ((ballObject.getYPosition() + movementValueY) <(0.25*map.getArenaHeight()))
            {
                ballObject.setYPosition(ballObject.getYPosition()+ballObject.getSize());
                ballObject.setPathSpeed(-(ballObject.getPathSpeed()));
                int rand_int2 = (rand.nextInt(140)+40);
                ballObject.setPathAngle(rand_int2);
            }
          /*  
            if ((ballObject.getYPosition() + movementValueY > (0.78*map.getArenaHeight()))||(ballObject.getYPosition() + movementValueY) <(0.23*map.getArenaHeight())) {
                map.removeBall(ballObject);
            }
            if ((ballObject.getXPosition() + movementValueX > (0.78*map.getArenaWidth()))||(ballObject.getXPosition() + movementValueX) <(0.23*map.getArenaWidth())) {
                map.removeBall(ballObject);
            }
            */
            ballObject.setYPosition(ballObject.getYPosition() + movementValueY);
            
    }
    public static void checkCollision(Plane plane1, GameArena map, Ball enemy, int gameScore)
    {
       
       
         for (int i = 0; i<plane1.getNumPart();i++)
        {
           
            
            if ((((plane1.returnParts(i).getXPosition() - Math.abs(enemy.getPathSpeed())) <= (int)enemy.getXPosition()) && ((int)enemy.getXPosition() <= (plane1.returnParts(i).getXPosition() + Math.abs(enemy.getPathSpeed())))) && (((plane1.returnParts(i).getYPosition() - Math.abs(enemy.getPathSpeed())) <= (int)enemy.getYPosition()) && ((int)enemy.getYPosition() <= (plane1.returnParts(i).getYPosition() + Math.abs(enemy.getPathSpeed())))))
            {
                
                plane1.returnParts(i).setColour("RED");
                if (!(plane1.checkIfAlive()))
                {
                    System.out.println("You Lose, you score was: " + gameScore);
                    System.exit(0);
                }

            }
        }


    }
    public static void addBall(int ballNum, List<Ball> listOfBalls ,GameArena map)
    {
        Random rand = new Random(); 
        int rand_int1 = rand.nextInt(20);
        int rand_int2 = (rand.nextInt(99));
        Ball newitem = new Ball(map.getArenaWidth()/2,map.getArenaHeight()/2,20,"MAGENTA",rand_int2, rand_int1);
        listOfBalls.add(newitem);
        map.addBall(listOfBalls.get(ballNum));
        

    }
    public static void drawBoundaries(GameArena map)
    {
        Rectangle walls[] = new Rectangle[4];
        int wallWidth = 5;
        walls[0] = new Rectangle((map.getArenaHeight()*0.25)-wallWidth,(map.getArenaWidth()*0.25)-wallWidth,wallWidth,(map.getArenaHeight()/2) + wallWidth *4,"RED");
        walls[1] = new Rectangle((map.getArenaHeight()*0.25)-wallWidth,(map.getArenaWidth()*0.25)-wallWidth,(map.getArenaWidth()/2)+ (wallWidth *4),wallWidth,"RED");
        walls[2] = new Rectangle((map.getArenaHeight()*0.25)-wallWidth,(map.getArenaWidth()*0.75)+(2*wallWidth),(map.getArenaWidth()/2)+ (wallWidth *4),wallWidth,"RED");
        walls[3] = new Rectangle((map.getArenaHeight()*0.75)+(2*wallWidth),(map.getArenaWidth()*0.25) - wallWidth,wallWidth,(map.getArenaHeight()/2)+ (wallWidth *4),"RED");
        
        
        for (int i = 0; i<4;i++)
        {
           map.addRectangle(walls[i]);
        }

    }




}