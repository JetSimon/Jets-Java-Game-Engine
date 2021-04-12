import java.awt.Color;

import jge.*;
import jge.stockComponents.*;

public class ExampleGame
{
    final static int FPS = 24; //This is the best frame rate.
    public static void main(String[] args) throws InterruptedException 
    {
        JGE game = new JGE("Squid Invasion", 200, 200, Color.blue); //Create game with name Test, size of 200 x 200

        for(int j = 0; j < 2; j++) //Create a row of squid
        {
            for(int i = 0; i < 5; i++) //Fill row with squid! Ahhh! 🦑
            {
                Entity squid = new Entity(12 + 36*i,12 + 36 * j, 32, 32, "ExampleGame_Resources/squid.png");
                squid.addComponent(new ColliderComponent());
                squid.addComponent(new SquidComponent());
                squid.tag = "Enemy";
                game.AddEntity(squid);
            }
        }

        Entity player = new Entity(game.getScreenWidth() / 2 - 16,162,32,32, "ExampleGame_Resources/player.png"); //Create player entity
        player.addComponent(new PlayerComponent()); //Make player controllable with WASD
        player.addComponent(new ColliderComponent()); //Make player collide with stuff
        player.tag = "Player";
        
        game.AddEntity(player);
        
        game.start();

        //Game loop
        while(game.isRunning())
        {   
            Thread.sleep(1000/FPS); //Really shitty way to update. Need to use better way when I learn how!
            game.gameLoop();
        }    

    }
}

class SquidComponent extends Component
{
    ColliderComponent c;
    int movementMultiplier = 1; 
    
    public void start()
    {
        c = (ColliderComponent)entity.getComponent(ColliderComponent.class); //And then find the components and assign to use!
    }

    public void update()
    {
        movementMultiplier *= 1.5;
        entity.y += 0.05 + 0.05 * movementMultiplier;

        if(c.touchingTag("Bullet"))
        {
            for(Entity e : c.touching)
            {
                if(e.tag.equals("Bullet"))
                    e.destroy();
            }

            entity.destroy();
        }
    }
}

class BulletComponent extends Component
{
    public void update()
    {
        entity.y -= 2;
        if(entity.y < -2)
            entity.destroy();
    }
}

//Kind of funny that I just made a new controller for an example instead of using the built in one. Haha,,,
class PlayerComponent extends Component
{
    double velocity = 0;
    int speed = 2;

    int bulletTimer = 10;

    public void update()
    {
        bulletTimer--;
        
        //Controls
        velocity = 0;

        if(jge.isKeyDown(65) && entity.x > 16)
        {
            velocity = -speed;
        }

        if(jge.isKeyDown(68) && entity.x < jge.getWidth() - 16)
        {
            velocity = speed;
        }

        entity.x += velocity;

        //Shooting controls!
        if(jge.isKeyDown(32) && bulletTimer <= 0)
        {
            bulletTimer = 10;
            Entity bullet = new Entity(entity.x+15, entity.y-10, 2, 2, "ExampleGame_Resources/bullet.png");
            bullet.tag = "Bullet";
            bullet.addComponent(new BulletComponent());
            bullet.addComponent(new ColliderComponent());
            System.out.println("Pew pew");
            jge.AddEntity(bullet);
        }
    }
}