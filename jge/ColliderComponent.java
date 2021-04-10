package jge;

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