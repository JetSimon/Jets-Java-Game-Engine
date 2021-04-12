package jge;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Entity 
{
    public double x, y; //position
    public int width, height; //width height
    public String tag, imagePath; //To use to determine what kind of entity you're colliding with and stuff

    BufferedImage image;

    ArrayList<Component> components = new ArrayList<Component>(); //List of all components attached to this entity

    public void addComponent(Component c)
    {
        c.entity = this;
        components.add(c);
    }

    public Component getComponent(Class<?> looking)
    { 
        //Will return first instance of component on entity!  
        for (Component component : components) 
        {
            if(looking.isInstance(component))
            {
                return component;
            }   
        }

        System.out.println("ERROR: NO COMPONENT FOUND WITH NAME " + looking.getClass().getSimpleName());
        return null;
    }

    //In the future we will make this have an image and bounding box model instead. I don't know why I am typing we, it's just me.
    public Entity(double x, double y, int width, int height, String imagePath)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagePath = imagePath;

        try
        {
            this.image = ImageIO.read(new File(this.imagePath));
        }
        catch(IOException ex)
        {
            System.out.println("ERROR LOADING IMAGE FOR SPRITE");
        }
        
    }

    public Entity(double x, double y, int width, int height)
    {
        //Change this to be non-os dependant
        this(x,y,width,height,"default_resources/default_sprite.png");
    }
}
