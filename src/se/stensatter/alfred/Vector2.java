package se.stensatter.alfred;

public class Vector2 {
    double x;
    double y;

    public Vector2(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2 add(Vector2 vector2)
    {
        return new Vector2(this.x + vector2.x,this.y+ vector2.y);
    }
    public void setVector(double x,double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector2()
    {
        x=1;
        y=1;
    }

    public boolean Equals(Vector2 vector2) {
        if (this.x == vector2.x && this.y == vector2.y)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return x+","+y;
    }
}
