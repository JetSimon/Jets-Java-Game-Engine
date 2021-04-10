package jge;
import java.awt.Color;
import java.util.ArrayList;

/*
    TODO:
    1. CHANGE COLLIDER TO POOLING SYSTEM SO YOU CAN CHECK SPECIFIC OBJECT?
*/

public class Entity 
{
    public double x, y; //position
    public int width, height; //width height
    public String tag; //To use to determine what kind of entity you're colliding with and stuff
    Color color = Color.RED; //temp, used until image support is added
    ArrayList<Component> components = new ArrayList<Component>(); //List of all components attached to this entity

    public void addComponent(Component c)
    {
        c.entity = this;
        components.add(c);
    }

    public Component getComponent(Class<?> looking)
    {   
        for (Component component : components) 
        {
            //System.out.println(component.getClass().getSimpleName());
            if(looking.isInstance(component))
            {
                return component;
            }   
        }

        System.out.println("ERROR: NO COMPONENT FOUND WITH NAME " + looking.getClass().getSimpleName());
        return null;
    }

    public Entity(double x, double y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
