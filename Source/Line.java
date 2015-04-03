
public class Line {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	
	public Line(int startX,int startY,int endX,int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX =endX;
		this.endY = endY;
	}
	public Line(){
		this(0, 0, 0, 0);
	}
	
	public int getStartX(){
		return this.startX;
	}
	
	public int getStartY(){
		return this.startY;
	}
	public int getEndX(){
		return this.endX;
	}
	public int getEndY(){
		return this.endY;
	}
	public void setLocation(int x,int y){
		this.startX = x;
		this.startY = y;
	}
}
