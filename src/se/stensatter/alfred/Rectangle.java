package se.stensatter.alfred;

import java.awt.*;

public class Rectangle extends Canvas {

    public Vector2 size;
    public Transform transform;

    @Override
    public void paint(Graphics g) {
        g.drawRect(12,12,12,12);
    }


}
