package se.stensatter.alfred;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.java2d.windows.GDIRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class DisplayGraphics extends JPanel {

    public int pixelSize=50;
    public ArrayList<GameObject> snake = new ArrayList<GameObject>();
    GameLoop gameLoop;
    GameObject apple;
    Random ran = new Random();
    Vector2 directionn = new Vector2(0,0);
    MainSwing mainSwing;
    public long speed;
    void Start()
    {
        gameLoop = new GameLoop(this);
        gameLoop.speed = speed;
        requestFocus();
    }
    public DisplayGraphics()
    {
        snake.add(new GameObject(new Vector2(1,1)));
        apple = new GameObject();
        snake.get(0).dir.setVector(1,0);
        setFocusable(true);
        requestFocus();
        this.setBackground(new Color(12,35,24));
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if(e.getKeyChar()=='d'&&snake.get(0).dir.x!=-1)
                {
                    snake.get(0).dir.setVector(1,0);
                }
                if(e.getKeyChar()=='a'&&snake.get(0).dir.x!=1)
                {
                    snake.get(0).dir.setVector(-1,0);

                }
                if(e.getKeyChar()=='w'&&snake.get(0).dir.y!=1)
                {
                    snake.get(0).dir.setVector(0,-1);

                }
                if(e.getKeyChar()=='s'&&directionn.y!=-1)
                {
                    snake.get(0).dir.setVector(0,1);
                }
            }
        });
    }
    private void addApple()
    {
        try {Thread.sleep(50); }
        catch(InterruptedException e){}
        Vector2 applepos = new Vector2(ran.nextInt((getWidth()/pixelSize)-1),ran.nextInt((getHeight()/pixelSize)-1));
        for(int i = 0;i<snake.size();i++)
        {
            if(!snake.get(0).posistion.Equals(applepos))
            {
                apple.posistion.setVector(applepos.x,applepos.y);
                System.out.println(pixelSize+"WIDTH "+applepos.x+" HIGHT "+applepos.y);

            }
        }
    }
    private void addTail()
    {
        if(snake.size()==0)
        {
            snake.add(new GameObject(new Vector2(1,1)));

        }
        else{
            snake.add(new GameObject(new Vector2(snake.get(snake.size()-1).posistion.x, snake.get(snake.size()-1).posistion.y)));
        }
    }
    public void Render()
    {
        validate();
        repaint();
    }
    void paintLines(Graphics g)
    {
        for(int i = 0; i<=getWidth();i=i+pixelSize)
        {
            g.drawLine(i,0,i,getWidth());
        }
        for(int i = 0; i<=getHeight();i=i+pixelSize)
        {
            g.drawLine(0,i,getWidth(),i);
        }
    }
    public void Die()
    {
        gameLoop.isGameRunning =false;
        System.out.println("Dead");
        JButton button = new JButton("RESTART");
        button.setBackground(new Color(255,255,255));
        add(button);
        JButton quit = new JButton("SETTINGS");
        quit.setBackground(new Color(255,255,255));
        add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(button);
                remove(quit);
                validate();
                repaint();
                snake.clear();
                mainSwing.NewGame();
                revalidate();            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop.isGameRunning = true;
                remove(button);
                remove(quit);
                validate();
                repaint();
                snake.clear();
                addTail();
                Start();
                revalidate();
            }
        });

    }
    public void Update()
    {
        System.out.println(snake.get(0).posistion);
        for(int i = snake.size()-1; i>=0;i--)
        {
            if(snake.get(i).posistion.x*pixelSize >getWidth())
            {
                snake.get(0).posistion.x = 0;
            }
            if(snake.get(i).posistion.x*pixelSize <0)
            {
               snake.get(0).posistion.x = getWidth()/pixelSize;
            }
            if(snake.get(i).posistion.y*pixelSize <0)
            {
                snake.get(0).posistion.y = getHeight()/pixelSize;
            }
            if(snake.get(i).posistion.y*pixelSize >getHeight())
            {
                snake.get(0).posistion.y = 0;
            }
            if(i ==0)
            {
                snake.get(0).posistion=snake.get(0).posistion.add(snake.get(0).dir);
                directionn = snake.get(0).posistion;
            }
            else
            {
               snake.get(i).posistion = snake.get(i-1).posistion;
            }
        }
        for(int i = 1; i<snake.size();i++)
        {
            if(snake.get(0).posistion.Equals(snake.get(i).posistion))
            {
                Die();
            }
        }
        if(snake.get(0).posistion.Equals(apple.posistion))
        {
            addApple();
            addTail();

        }
        Render();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintLines(g);
        Color color = new Color(10,255,10);
        for(int i = 0; i<snake.size();i++)
        {
            g.setColor(color);
            g.drawRect((int)snake.get(i).posistion.x*pixelSize,(int)snake.get(i).posistion.y*pixelSize,pixelSize,pixelSize);
            g.fillRect((int)snake.get(i).posistion.x*pixelSize,(int)snake.get(i).posistion.y*pixelSize,pixelSize-2,pixelSize-2);
        }
        g.setColor(new Color(255,0,0));
        g.fillRect((int)apple.posistion.x*pixelSize,(int)apple.posistion.y*pixelSize,pixelSize,pixelSize);
    }
}
