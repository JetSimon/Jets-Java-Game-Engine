package jge.stockComponents;
import jge.Component;

//Very basic base character controller. Supports collision using ColliderComponent. Is not physically accurate!
public class ControllerComponent extends Component
{
    double max_ax = 50;
    double max_ay = 50;

    double ax = 0;
    double ay = 0;

    //You can declare components to reference like this so you only have to find them once!
    ColliderComponent c;

    public void start()
    {
        c = (ColliderComponent)entity.getComponent(ColliderComponent.class); //And then find the components and assign to use!
    }

    public void update()
    {
        //If object has a collider and it is touching something, you cannot move!!!
        if(c != null)
        {
            if(c.touchingAnything())
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
