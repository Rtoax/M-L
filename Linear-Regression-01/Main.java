/** 
 * Linear Regression 
 *    solution:y[] = a*x[] + b 
 * 
 *        2018.04: rongtao 
 *
 * Copyright (C) RongTao, All right reserve.
 */  
import javax.swing.*;  
import java.awt.*;  
  
/** 
 *  JFrame : main window 
 * 
 */  
class myJFrame extends JFrame  
{  
    private Toolkit toolkit;   
      
    myJFrame(String title, int width, int height)  
    {  
        this.setTitle(title);  
        this.setSize(width, height);  
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
          
        toolkit = getToolkit();    
        Dimension size = toolkit.getScreenSize();    
        setLocation((size.width/2 - getWidth())/2, (size.height - getWidth())/2);   
    }  
}  
/** 
 *  JPanel: for drawing 
 * 
 */  
class myJPanel extends JPanel  
{  
    private int lineColor, lineWidth;  
    private int pointColor, pointWidth;  
    private int width, height, numpoint;  
    private float x[], y[], minx=99999, miny=99999, maxx=0, maxy=0;  
    private float slope, intercept;  
    private float radiox, radioy;  
    private int bndr = 20;  
      
    myJPanel(int lc, int lw, float x[], float y[], int pc, int pw,   
             float slope, float intercept)/*xielv, jiejv*/  
    {  
        this.lineColor = lc;  
        this.lineWidth = lw;  
        this.pointColor = pc;  
        this.pointWidth = pw;  
        this.x = x;  
        this.y = y;  
        this.slope = slope;  
        this.intercept = intercept;  
        this.numpoint = x.length<y.length?x.length:y.length;  
        getArea();  
    }  
    /** 
     *  painting 
     * 
     */  
    public void paintComponent(Graphics g)   
    {  
        super.paintComponent(g);  
          
        Graphics2D g2d = (Graphics2D) g;    
    
        Dimension size = getSize();    
        Insets insets = getInsets();   
          
        /* linear regression line */  
        g2d.setColor(getLineColor(lineColor));    
        g2d.setStroke(getLineWidth(lineWidth));   
        g2d.drawLine((int)((x[0]-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                     getHeight()-bndr-  
                        (int)((x[0]*slope+intercept-miny)*1.0f  
                                /(maxy-miny)*(getHeight()-bndr*2)),  
                     (int)((x[numpoint-1]-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                     getHeight()-bndr-  
                        (int)((x[numpoint-1]*slope+intercept-miny)*1.0f  
                                /(maxy-miny)*(getHeight()-bndr*2))  
                     );   
          
        /* Axis */  
        g2d.setColor(getLineColor(4));    
        g2d.setStroke(getLineWidth(1));   
        /* Vertical axis */  
        g2d.drawLine((int)((0-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                     getHeight()-bndr- (int)((miny-miny)*1.0f/(maxy-miny)*(getHeight()-bndr*2)),  
                     (int)((0-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                     getHeight()-bndr- (int)((maxy-miny)*1.0f/(maxy-miny)*(getHeight()-bndr*2))  
                     );  
        /* Horizontal axis */  
        g2d.drawLine((int)((0)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                     getHeight()-bndr- (int)((0-miny)*1.0f/(maxy-miny)*(getHeight()-bndr*2)),  
                     (int)((maxx-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                     getHeight()-bndr- (int)((0-miny)*1.0f/(maxy-miny)*(getHeight()-bndr*2))  
                     );  
          
        /* point */  
        g2d.setColor(getLineColor(pointColor));    
        g2d.setStroke(getLineWidth(pointWidth));  
        for(int i = 0; i < numpoint; i++)  
        {  
            g2d.drawLine((int)((x[i]-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                         getHeight()-bndr-(int)((y[i]-miny)*1.0f/(maxy-miny)*(getHeight()-bndr*2)),  
                         (int)((x[i]-minx)*1.0f/(maxx-minx)*(getWidth()-bndr*2))+bndr,  
                         getHeight()-bndr-(int)((y[i]-miny)*1.0f/(maxy-miny)*(getHeight()-bndr*2)));  
        }  
          
    }  
    /* get raypath line width */    
    public BasicStroke getLineWidth(int dim)  
    {    
        BasicStroke tmp = new BasicStroke(dim);    
        return tmp;    
    }    
    /* get raypath line color */    
    public Color getLineColor(int dim)  
    {   
        if(dim==1)return Color.RED;    
        else if(dim==2)return Color.BLUE;    
        else if(dim==3)return Color.GREEN;    
        else if(dim==4)return Color.BLACK;    
        else if(dim==5)return Color.GRAY;    
        else if(dim==6)return Color.YELLOW;    
        else if(dim==7)return Color.PINK;    
        else if(dim==8)return Color.CYAN;    
        else if(dim==9)return Color.MAGENTA;    
        else if(dim==10)return Color.ORANGE;    
        else return Color.BLACK;    
    }   
    /* get drawing area */  
    void getArea()  
    {  
        for(int i=0;i<numpoint;i++)  
        {  
            if(minx > x[i])  
                minx = x[i];  
            if(miny > y[i])  
                miny = y[i];  
            if(maxx < x[i])  
                maxx = x[i];  
            if(maxy < y[i])  
                maxy = y[i];  
        }  
    }  
}  
/** 
 *  linear regression calculation 
 * 
 */  
class linearRegression  
{  
    private float x[], y[];  
    private int numpoint=0;  
    linearRegression(float x[], float y[])  
    {  
        this.x = x;  
        this.y = y;  
        this.numpoint = x.length<y.length?x.length:y.length;  
    }  
    float[] normalEquation()  
    {  
        /*line slope, line intercept, squared error*/  
        float solution[] = new float[3];  
        float SumX=0,SumY=0,SumXY=0,SumXX=0,SumYY=0,SumXAndSumY,SumXAndSumX,SumYAndSumY;  
          
        for(int i=0;i< numpoint;i++)   
        {  
            SumX=SumX+x[i];        
            SumY=SumY+y[i];     
            SumXY=SumXY+x[i]*y[i];  
            SumXX=SumXX+x[i]*x[i];  
            SumYY=SumYY+y[i]*y[i];   
        }  
          
        SumXAndSumY=SumX*SumY;    
        SumXAndSumX=SumX*SumX;     
        SumYAndSumY=SumY*SumY;     
          
        if((numpoint*SumXX-SumXAndSumX)>0)  
        {  
            /* line slope */  
            solution[0] = (numpoint*SumXY-SumXAndSumY)/(numpoint*SumXX-SumXAndSumX);    
            /* line intercept */  
            solution[1] = (SumY-solution[0]*SumX)/numpoint;  //intercept  
        }  
          
        if((numpoint*SumXX-SumXAndSumX)*(numpoint*SumYY-SumYAndSumY)>0)  
            /* squared error */  
            solution[2]=(numpoint*SumXY-SumXAndSumY)*(numpoint*SumXY-SumXAndSumY)  
                    /((numpoint*SumXX-SumXAndSumX)*(numpoint*SumYY-SumYAndSumY));//R^2  
          
        System.out.println("squared error: "+solution[2]);  
        return solution;  
    }  
      
}  
  
/** 
 *  Main class 
 *  
 */  
public class Main  
{  
    static public void main(String[]args)  
    {  
        float x[] = {1,3,2,5,4,7,6,9,8};  
        float y[] = {-3,-1,-2,0,1,-3,2,4,3};  
          
        myJFrame jf = new myJFrame("Linear Regression",200,200);  
          
        /*line slope, line intercept, squared error*/  
        float solution[] = new linearRegression(x,y).normalEquation();  
          
        /* lineColor, lineWidth, X, Y, pointColor, pointWidth, slope, intercept*/  
        jf.add(new myJPanel(3,2,x,y,2,6,solution[0],solution[1]));  
          
        System.out.println("y = "+solution[0]+"*x "+solution[1]);  
          
        jf.setVisible(true);  
    }  
}  
