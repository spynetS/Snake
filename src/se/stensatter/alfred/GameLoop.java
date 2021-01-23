package se.stensatter.alfred;

import jdk.jfr.events.ExceptionThrownEvent;
import org.w3c.dom.events.EventException;

import java.lang.Thread.*;

public class GameLoop {

    public boolean isGameRunning=true;
    DisplayGraphics g;
    public long speed;
    public GameLoop(DisplayGraphics g)
    {
        this.g = g;

        Thread thread = new Thread(this::run);
        thread.start();
    }
    public void run()
    {
        while (isGameRunning)
        {
            try {Thread.sleep(speed); }
            catch(InterruptedException e){}
            try{g.Update(); }
            catch (Exception e){ System.out.println(e);}
           // g.Render();

        }
    }
}
