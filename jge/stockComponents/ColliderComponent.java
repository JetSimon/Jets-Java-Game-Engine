package jge.stockComponents;
import jge.Component;
import jge.Entity;
import java.util.ArrayList;

//Base collider component. Uses box collision. Add this to an entity to support collision detection out of box.
//Only returns if touching, does not yet say WHAT touching. Soon this will change!
public class ColliderComponent extends Component 
{
    public ArrayList<Entity> touching = new ArrayList<Entity>();

    //On start add to pool of colliders so game knows
    public void start()
    {
        jge.colliders.add(entity);
    }

    public void preupdate()
    {
        touching = new ArrayList<Entity>(); //This is probably bad to do like this so I'll replace this later
        for (Entity col : jge.colliders) 
        {
            //Don't touch yourself!
            if(col == entity)
                continue;

            if(getTouching(col))
            {
                touching.add(col);
            }
        }
    }

    public boolean touchingAnything()
    {
        return touching.size() > 0;
    }

    public boolean touchingTag(String tag)
    {
        for (Entity e : touching)
        {
            if(e.tag.equals(tag))
            {
                return true;
            }
        }

        return false;
    }

    //Basic bounding box detection code
    boolean getTouching(Entity b)
    {
        Entity a = entity;
        return (Math.abs((a.x + a.width/2) - (b.x + b.width/2)) * 2 < (a.width + b.width)) &&
         (Math.abs((a.y + a.height/2) - (b.y + b.height/2)) * 2 < (a.height + b.height));
    }
    
}