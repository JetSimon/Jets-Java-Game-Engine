package jge;

public class ControllerComponent extends Component
{
    double max_ax = 50;
    double max_ay = 50;

    double ax = 0;
    double ay = 0;


    public void update()
    {
        if(entity.getComponent(new ColliderComponent()))
            return;

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

        ax = Math.min(ax, max_ax);
        ay = Math.min(ay, max_ay);

        entity.x += ax;
        entity.y += ay;
    }
}
