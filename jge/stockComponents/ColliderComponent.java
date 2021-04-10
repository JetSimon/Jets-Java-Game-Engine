package jge.stockComponents;
import jge.Component;
import jge.Entity;

public class ColliderComponent extends Component 
{
    public boolean isTouching = false;

    public void start()
    {
        jge.colliders.add(entity);
    }

    public void update()
    {
        for (Entity col : jge.colliders) 
        {

        }
    }
    
}