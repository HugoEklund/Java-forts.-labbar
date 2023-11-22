package mountain;

import java.util.HashMap;
import fractal.*;

public class Mountain extends Fractal
{
    private Point p1, p2, p3;
    private double dev;
    HashMap<Side, Point> sideMap;

    public Mountain(Point p1, Point p2, Point p3, double dev)
    {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.dev = dev;
        this.sideMap = new HashMap<>();
    }

    @Override
    public String getTitle()
    {
        return "Mountain";
    }

    @Override
    public void draw(TurtleGraphics g)
    {
        fractalLine(g, order, p1, p2, p3, dev);
    }

    private void fractalLine(TurtleGraphics g, int order, Point a, Point b, Point c, double dev)
    {
        if (order == 0) 
		{
			g.moveTo(a.getX(), a.getY());
			g.forwardTo(b.getX(), b.getY());
            g.forwardTo(c.getX(), c.getY());
            g.forwardTo(a.getX(), a.getY());
		} 

        else
        {
            g.moveTo(a.getX(), a.getY());

            fractalLine(g, order-1, a, midPoint(a,b, dev), midPoint(a,c, dev), dev/2);
            fractalLine(g, order-1, midPoint(a,b, dev), b, midPoint(b,c, dev), dev/2);
            fractalLine(g, order-1, midPoint(b,c, dev), c, midPoint(a,c, dev), dev/2);
            fractalLine(g, order-1, midPoint(a,b, dev), midPoint(b,c, dev), midPoint(a, c, dev), dev/2);
        }
    }

    private Point midPoint (Point p1, Point p2, double dev)
    {
        Side aSide = new Side(p1, p2);

        if (sideMap.containsKey(aSide))
        {
            Point tempPoint = sideMap.get(aSide);
            sideMap.remove(tempPoint);
            return tempPoint;
        }
        else
        {
            Point midPoint = new Point ((p1.getX() + p2.getX())/2 , (p1.getY() + p2.getY())/2 + (int)RandomUtilities.randFunc(dev));
            sideMap.put(aSide, midPoint);
            return midPoint;
        }
    }
}
