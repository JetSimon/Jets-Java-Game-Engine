package jge.stockComponents;
import jge.Component;
import jge.Entity;

//Base collider component. Uses box collision. Add this to an entity to support collision detection out of box.
//Only returns if touching, does not yet say WHAT touching. Soon this will change!
public class ColliderComponent extends Component 
{
    public boolean isTouching = false;

    //On start add to pool of colliders so game knows
    public void start()
    {
        jge.colliders.add(entity);
    }

    public void update()
    {
        for (Entity col : jge.colliders) 
        {
            //Don't touch yourself!
            if(col == entity)
                return;

            isTouching = getTouching(col);
        }
    }

    //Basic bounding box detection code
    boolean getTouching(Entity b)
    {
        Entity a = entity;
        return (Math.abs((a.x + a.width/2) - (b.x + b.width/2)) * 2 < (a.width + b.width)) &&
         (Math.abs((a.y + a.height/2) - (b.y + b.height/2)) * 2 < (a.height + b.height));
    }
    
}