package jge;

//Basic component layout. Start is called on game start update is called every "frame".

public class Component
{
    public Entity entity; //Entity component belongs to
    public JGE jge; //Game currently running. Contains collider info, list of entities, and input info

    public void start()
    {
        //Override me
    }

    public void preupdate()
    {
        //Override me if you want to do stuff before the update
    }

    public void update()
    {
        //Override me harder
    }    
}
