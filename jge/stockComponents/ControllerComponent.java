package jge.stockComponents;
import jge.Component;

public class ControllerComponent extends Component
{
    double max_ax = 50;
    double max_ay = 50;

    double ax = 0;
    double ay = 0;


    public void update()
    {
        //If object has a collider and it is touching something, you cannot move!!!
        if(entity.getComponent(ColliderComponent.class) != null)
        {
            ColliderComponent c = (ColliderComponent)entity.getComponent(ColliderComponent.class);
            if(c.isTouching)
            {
                ax *= -1.5;
                ay *= -1.5;
            }
        }

        ay *= 0.9;
        ax *= 0.9;

        if(jge.isKeyDown("W"))
        {
            ay -= 1;
        }

        if(jge.isKeyDown("S"))
        {
            ay += 1;
        }

        if(jge.isKeyDown("A"))
        {
            ax -= 1;
        }

        if(jge.isKeyDown("D"))
        {
            ax += 1;
        }

        entity.x += ax;
        entity.y += ay;

        ax = Math.min(ax, max_ax);
        ay = Math.min(ay, max_ay);
    }
}
