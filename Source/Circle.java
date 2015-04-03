
public class Circle {

	private int centerX;
	private int centerY;
	private int radius;
	
	public Circle(int centerX, int centerY,int radius){
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	public Circle(){
		this(0, 0, 0);
	}
	
	public int getX(){
		return this.centerX;
	}
	public int getY(){
		return this.centerY;
	}
	public int getRadius(){
		return this.radius;
	}
	public void setLocation(int x,int y){
		this.centerX = x;
		this.centerY = y;
	}

}
