
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint extends JPanel {
	//arrays of rectangles used to be save
	private ArrayList<Rectangle>rectangles = new ArrayList<Rectangle>();
	//arrays of circles to be drawed
	private ArrayList<Circle>circles = new ArrayList<Circle>();
	//arrays of lines to be drawed
	private ArrayList<Line>lines = new ArrayList<Line>();
	private static Graphics gc;
	int indexOfSelEl;
    private Point strtPoint; //start point of drawing
    private Point endPoint; // end point of drawing
    public static int counter = 6; //counter used to change colors
    private Color[] colors = {Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW,Color.ORANGE,Color.BLACK};
    private Color currentColor = Color.BLACK;
    private boolean flagdraw;
    private boolean flagsMovedShape[] = {false,false,false};
    private boolean shapes[] = {false,false,false};
    Paint() {
        setLayout(new BorderLayout());
        JPanel pnlButtons = new JPanel();
        JPanel pnlDraw = new JPanel();
        pnlButtons.setLayout(new GridLayout(8, 1));

        JButton btnRectangle = new JButton("Rectangle");
        btnRectangle.setSize(15, 15);
        pnlButtons.add(btnRectangle);

        JButton btnCircle = new JButton("Circle");
        btnCircle.setSize(15, 15);
        pnlButtons.add(btnCircle);

        JButton btnLine = new JButton("Line");
        btnLine.setSize(15, 15);
        pnlButtons.add(btnLine);

        JButton btnMove = new JButton("Move");
        btnMove.setSize(15, 15);
        pnlButtons.add(btnMove);
        
        JButton btnColor = new JButton("Change Color");
        btnColor.setSize(15,15);
        pnlButtons.add(btnColor);
        
        JButton btnOpen= new JButton("Open");
        btnOpen.setSize(15, 15);
        pnlButtons.add(btnOpen);
        
        JButton btnSave= new JButton("Save");
        btnSave.setSize(15, 15);
        pnlButtons.add(btnSave);

        JButton btnExit = new JButton("Exit");
        btnExit.setSize(15, 15);
        pnlButtons.add(btnExit);

        add(pnlButtons, BorderLayout.EAST);
        
        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });

        btnRectangle.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
            	flagdraw = true;
                shapes[0] = true;
                shapes[1] = false;
                shapes[2] = false;
            }
        });

        btnCircle.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
            	flagdraw = true;
                shapes[0] = false;
                shapes[1] = true;
                shapes[2] = false;
            }
        });

        btnLine.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
            	flagdraw = true;
                shapes[0] = false;
                shapes[1] = false;
                shapes[2] = true;
            }
        });
        btnMove.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
            	flagdraw = false;
            }
            
        });

        pnlDraw.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
               strtPoint = me.getPoint();
               if(flagdraw){
               gc = getGraphics(); 
               } else {
            	   for(int i=0;i<3;i++){
            		   flagsMovedShape[i]=false;
            	   }
            	   int checkPointX = me.getX();
            	   int checkPointY = me.getY();
            	   int maxSize = maxSize(rectangles.size(), circles.size(), lines.size());
            	   for(int i=0;i<maxSize;i++){
            		   if(circles.size()>0 && isOnCircle(circles.get(i%circles.size()), checkPointX, checkPointY)){
            			   indexOfSelEl = i;
            			   flagsMovedShape[0]=true;
            			   flagsMovedShape[1]=false;
            			   flagsMovedShape[2]=false;
            			   break;
            		   } else if(lines.size()>0 && isOnLine(lines.get(i%lines.size()), checkPointX, checkPointY)){
            			   indexOfSelEl = i;
            			   flagsMovedShape[0]=false;
            			   flagsMovedShape[1]=true;
            			   flagsMovedShape[2]=false;
            			   break;
            		   } else if(rectangles.size()>0 && isInRectangle(rectangles.get(i%rectangles.size()), checkPointX, checkPointY)){
            			   indexOfSelEl = i;
            			   flagsMovedShape[0]=false;
            			   flagsMovedShape[1]=false;
            			   flagsMovedShape[2]=true;
            			   break;
            		   }
            	   }
               }
            }

            public void mouseReleased(MouseEvent me) {
            	indexOfSelEl = -1;
               endPoint = me.getPoint();
               if(flagdraw==true){
            	   gc.setColor(currentColor);
               if(shapes[0]==true){
            	   int length = endPoint.x-strtPoint.x;
            	   int width = endPoint.y -strtPoint.y ;
            	   rectangles.add(new Rectangle(strtPoint.x,strtPoint.y,width,length));
            	   gc.drawRect(rectangles.get(rectangles.size()-1).getX(),
            			   rectangles.get(rectangles.size()-1).getY(),
            			   rectangles.get(rectangles.size()-1).getWidth(),
            			   rectangles.get(rectangles.size()-1).getLength());
            	   System.out.println(isInRectangle(rectangles.get(rectangles.size()-1),
            			  100, 100));
            	  
               }else if(shapes[1]){
            	   int radius = (int) Math.sqrt(Math.pow(endPoint.x-strtPoint.x, 2)
                      		+Math.pow(endPoint.y - strtPoint.y, 2));
            	   circles.add(new Circle(strtPoint.x,strtPoint.y,radius));
                 
                 gc.drawOval(circles.get(circles.size()-1).getX(),
                		 circles.get(circles.size()-1).getY(),
                		 circles.get(circles.size()-1).getRadius(),
                		 circles.get(circles.size()-1).getRadius() );
               } else if(shapes[2]==true){
                 lines.add(new Line(strtPoint.x,strtPoint.y,endPoint.x,endPoint.y));
                 gc.drawLine(lines.get(lines.size()-1).getStartX(),
                		 lines.get(lines.size()-1).getStartY(),
                		 lines.get(lines.size()-1).getEndX(),
                		 lines.get(lines.size()-1).getEndY());
             }
               gc.dispose(); 
            } else {
            	
            }
            }
            
        });
        pnlDraw.addMouseMotionListener(new MouseAdapter() {
          public void mouseDragged(MouseEvent me){
        	  if(!flagdraw && indexOfSelEl!=-1){
        	if(flagsMovedShape[2]){
        		int tempX = me.getX();
        		int tempY = me.getY();
        		rectangles.get(indexOfSelEl).setLocation(tempX, tempY);
        		gc = getGraphics(); 
            	gc.clearRect(0, 0, 370,400);
            	gc.dispose();
            	redraw();
        	} else if(flagsMovedShape[0]){
        		circles.get(indexOfSelEl).setLocation(me.getX(), me.getY());
        		gc = getGraphics(); 
            	gc.clearRect(0, 0, 370,400);
            	gc.dispose();
            	redraw();
        	} else if(flagsMovedShape[1]){
        		lines.get(indexOfSelEl).setLocation(me.getX(), me.getY());
        		gc = getGraphics(); 
            	gc.clearRect(0, 0, 370,400);
            	gc.dispose();
            	redraw();
        	}
        	  }
        }
		});
        //this method change the color of drawings
         btnColor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                currentColor = colors[counter%6];
                counter++;
            }
        });
        add(pnlDraw);
        pnlDraw.setSize(365, 400);
        setVisible(true);
        setSize(250, 250);
    }
   //this method check if point belongs to a point
    public boolean isOnLine(Line check,int pointX,int pointY){
    		int lengtLine =(int) Math.sqrt(Math.pow(check.getEndX()-check.getStartX(),2)+
    				Math.pow(check.getEndY()-check.getStartY(),2));
    		int firstLength = (int) Math.sqrt(Math.pow(pointX-check.getStartX(), 2)+
    				Math.pow(pointY-check.getStartY(), 2) );
    		int secondLength = (int) Math.sqrt(Math.pow(check.getEndX()-pointX, 2)+
    				Math.pow(check.getEndY()-pointY, 2));
    	return (firstLength+secondLength)==lengtLine;
    }
    //this method check if point belongs to a circle
    public boolean isOnCircle(Circle check,int x,int y){
    	int temp =(x-check.getX())*(x-check.getX()) + (y-check.getY())*(y-check.getY());
    	int radiusOnSecond = check.getRadius()*check.getRadius();		
    	return temp<=radiusOnSecond;
    }
  //this method check if point belongs to a rectangle
    public boolean isInRectangle(Rectangle check,int x,int y){
    	int x1 = check.getX()+check.getWidth();
    	int y1 = check.getY()+check.getLength();
    	if(x>=check.getX() && x<=x1 && y>=check.getY() && y<=y1){
    		return true;
    	}else {
    	return false;
    	}
    }
    // the method redraw elements after moving
    public  void redraw(){
    	int maxSize = maxSize(rectangles.size(), circles.size(), lines.size());
    	for(int i=0;i<maxSize;i++){
    		if(rectangles.size()>0){
    		gc = getGraphics();
    		gc.drawRect(rectangles.get(i%rectangles.size()).getX(),
     			   rectangles.get(i%rectangles.size()).getY(),
     			   rectangles.get(i%rectangles.size()).getWidth(),
     			   rectangles.get(i%rectangles.size()).getLength());
    		 gc.dispose(); 
    		}
    		 if(circles.size()>0){
    			 gc = getGraphics();
    		gc.drawOval(circles.get(i%circles.size()).getX(),
    				circles.get(i%circles.size()).getY(),
    				circles.get(i%circles.size()).getRadius(),
    				circles.get(i%circles.size()).getRadius());
    		gc.dispose(); 
    		 }
    		 if(lines.size()>0){
    		gc = getGraphics();
    		 gc.drawLine(lines.get(i%lines.size()).getStartX(),
            		 lines.get(i%lines.size()).getStartY(),
            		 lines.get(i%lines.size()).getEndX(),
            		 lines.get(i%lines.size()).getEndY());
    		 gc.dispose(); 
    		 }
    	
    }
    }
    //this method returns the max length from arrays
    public int maxSize(int a,int b,int c){
    	if(a>=b && a>=c){
    		return a;
    	} else if(b>=a && b>=c){
    		return b;
    	} else {
    		return c;
    	}
    }
    public static void main(String[] args) {
        JFrame panelTest = new JFrame("Paint");
        Paint test = new Paint();
        panelTest.add(test, BorderLayout.CENTER);
        panelTest.setSize(500, 400);
        panelTest.setVisible(true);
        panelTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelTest.setResizable(false);

    }
}
