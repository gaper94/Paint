
public class Rectangle {

	private int pointX;
	private int pointY;
	private int length;
	private int width;
	
	public Rectangle(int pointX,int pointY,int length,int width){
		this.pointX = pointX;
		this.pointY = pointY;
		this.length = length;
		this.width = width;
	}
	public Rectangle() {
		pointX =0;
		pointY= 0;
		this.length = 0;
		this.width = 0;
	}
	
	public void setLocation(int x,int y){
		pointX = x;
		pointY= y;
	}
	public int getX(){
		return pointX;
	}
	public int getY(){
		return pointY;
	}
	public int getLength(){
		return length;
	}
	public int getWidth(){
		return width;
	}
	
}
