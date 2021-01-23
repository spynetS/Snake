package se.stensatter.alfred;

public class GameObject {

    public Vector2 posistion= new Vector2();
    public Rectangle rectangle;
    public GameObject(){}
    public Vector2 dir=new Vector2(0,0);
    public GameObject(Vector2 posistion){
        this.posistion = posistion;
    }


}
