package jge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class JGE extends JPanel implements KeyListener
{
    public String title;

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public ArrayList<Entity> colliders = new ArrayList<Entity>();

    ArrayList<String> keysDown = new ArrayList<String>();

    public JGE(String title, int width, int height)
    {
        this(title, width, height, Color.WHITE);
    }

    public JGE(String title, int width, int height, Color background)
    {
        this.title = title;

        addKeyListener(this);
        setFocusable(true);

        setPreferredSize( new Dimension(width, height) );
        setBackground(background); 
    }

    public void start()
    {
        JFrame frame = new JFrame( this.title );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add( this );
        frame.pack();
        frame.setVisible( true );

        for (Entity entity : entities) 
        {
            for (Component c : entity.components)
            {
                c.jge = this;
                c.start();
            }    
        }
    }

    public void updateEntities()
    {
        for (Entity entity : entities) 
        {
            for (Component c : entity.components)
            {
                c.update();
            }    
        }

        this.repaint();
    }

    public void paintComponent ( Graphics gr )
    { 
        super.paintComponent( gr );

        for (Entity entity : entities) 
        {
            gr.setColor( entity.color );
            gr.fillRect((int)entity.x, (int)entity.y, entity.width, entity.height);
        }
    }

    public void AddEntity(Entity e)
    {
        this.entities.add(e);
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

    public boolean isKeyDown(String k)
    {
        return keysDown.contains(k);
    }

}