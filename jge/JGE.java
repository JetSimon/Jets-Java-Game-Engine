package jge;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class JGE extends JPanel implements KeyListener
{
    JFrame frame;
    boolean running = false;

    public String title; //Title that will appear in window

    public ArrayList<Entity> entities = new ArrayList<Entity>(); //List of entities in game
    public ArrayList<Entity> colliders = new ArrayList<Entity>(); //List of entities in game that have colliders
    public ArrayList<Entity> toRemove = new ArrayList<Entity>();
    public ArrayList<Entity> toAdd = new ArrayList<Entity>();
    int deltaAdd = 0;
    int deltaRemove = 0;

    ArrayList<String> keysDown = new ArrayList<String>(); //List of keys down in string format. NOT keycode!

    //Mouse stuff
    public int mouseX = 0;
    public int mouseY = 0;
    
    //Screen stuff in case you need it
    private int screenWidth, screenHeight;

    public boolean mousePressed = false; //When mouse is down, use to check

    //Default constructor with no background colour
    public JGE(String title, int width, int height)
    {
        this(title, width, height, Color.WHITE);
    }

    public JGE(String title, int width, int height, Color background)
    {
        this.title = title;
        this.screenWidth = width;
        this.screenHeight = height;

        //Make mouse input work
        addMouseInput(this);

        //Make keyboard controls work
        addKeyListener(this);
        setFocusable(true);

        //Basic window setup
        setPreferredSize( new Dimension(width, height) );
        setBackground(background); 
    }

    //This method should only be called once to start your game!
    public void start()
    {
        running = true;
        frame = new JFrame( this.title );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add( this );
        frame.pack();
        frame.setVisible( true );

        for (Entity entity : entities) 
        {
            AwakenEntity(entity); 
        }
    }

    public void gameLoop()
    {
        updateEntities();
        addAndRemoveEntities();
    }

    //Called every frame
    void updateEntities()
    {
        for (Entity entity : entities) 
        {
            for (Component c : entity.components)
            {
                c.preupdate();
            }    
        }

        for (Entity entity : entities) 
        {
            for (Component c : entity.components)
            {
                c.update();
            }    
        }
        this.repaint();
    }

    void addAndRemoveEntities()
    {
        for (Entity entity : toAdd)
        {
            entities.add(entity);
            AwakenEntity(entity);
        }

        for (Entity entity : toRemove)
        {
            if(colliders.contains(entity))
                colliders.remove(entity);
            if(entities.contains(entity))
                entities.remove(entity);
        }

        toAdd = new ArrayList<Entity>();
        toRemove = new ArrayList<Entity>();
    }

    //Called during updateEntities
    public void paintComponent ( Graphics gr )
    { 
        super.paintComponent( gr );
        for (Entity entity : entities) 
        {
            gr.drawImage(entity.image, (int)entity.x, (int)entity.y, null);
        }
    }

    public void AddEntity(Entity e)
    {
        if(!running)
        {
            this.entities.add(e);
        }
        else
        {
            toAdd.add(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        String k = e.getKeyText(e.getKeyCode()); //This sucks replace later
        if(!keysDown.contains(k))
            keysDown.add(k);
    }

    public void keyReleased(KeyEvent e) {
        String k = e.getKeyText(e.getKeyCode()); //Ditto
        keysDown.remove(k);
        //System.out.println(keysDown.toString());
    }
    
    
    public void keyTyped(KeyEvent e) {
        //I had to put this here??...
    }

    //Check if keysDown input array has key k down in
    public boolean isKeyDown(String k)
    {
        return keysDown.contains(k);
    }

    public void addMouseInput(JGE j)
    {
        //Make mouse controls work
        j.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) 
            {
                j.mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) 
            {
                j.mousePressed = false;
            }
         });

        j.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                j.mouseX = e.getX();
                j.mouseY = e.getY();
            }
        });
    }

    public int getScreenWidth()
    {
        return this.screenWidth;
    }

    public int getScreenHeight()
    {
        return this.screenHeight;
    }

    public ArrayList<Entity> getAllWithTag(String tag)
    {
        ArrayList<Entity> output = new ArrayList<Entity>();

        for(Entity entity : entities)
        {
            if(entity.tag.equals(tag))
            {
                output.add(entity);
            }
        }

        return output;
    }

    void AwakenEntity(Entity entity)
    {
        entity.jge = this;
        //Components are called in order they are added in entity
        for (Component c : entity.components)
        {
            c.jge = this;
            c.start();
        }   
    }

    public boolean isRunning()
    {
        return running;
    }

    public void exit()
    {
        running = false;
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}