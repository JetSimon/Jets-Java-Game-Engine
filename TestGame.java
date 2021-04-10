import jge.*;
import jge.stockComponents.*;

public class TestGame
{
    final static int FPS = 24;
    public static void main(String[] args) throws InterruptedException 
    {
        JGE game = new JGE("Test", 200, 200);

        Entity player = new Entity(100,100,20,20);

        player.addComponent(new ControllerComponent());
        player.addComponent(new ColliderComponent());

        game.AddEntity(player);

        game.start();

        while(true)
        {   
            Thread.sleep(1000/FPS);
            game.updateEntities();
        }    

    }
}