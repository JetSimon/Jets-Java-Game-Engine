import jge.*;
import jge.stockComponents.*;

public class TestGame
{
    final static int FPS = 24; //This is the best frame rate.
    public static void main(String[] args) throws InterruptedException 
    {
        JGE game = new JGE("Test", 200, 200); //Create game with name Test, size of 200 x 200

        Entity player = new Entity(100,100,32,32); //Create player entity
        Entity wall = new Entity(50,50, 32, 32); //Create wall entity

        wall.addComponent(new ColliderComponent()); //Make wall collide with stuff 

        player.addComponent(new ControllerComponent()); //Make player controllable with WASD
        player.addComponent(new ColliderComponent()); //Make player collide with stuff

        //Actually put wall + player in game
        game.AddEntity(wall);
        game.AddEntity(player);

        game.start();

        //Game loop
        while(true)
        {   
            Thread.sleep(1000/FPS); //Really shitty way to update. Need to use better way when I learn how!
            game.updateEntities();
        }    

    }
}