package mountain;

import fractal.*;

public class Mountain extends Fractal
{
    private Point p1;
    private Point p2;
    private Point p3;

    public Mountain(Point p1, Point p2, Point p3)
    {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        
    }

    @Override
    public String getTitle()
    {
        return "Hej";
    }

    @Override
    public void draw(TurtleGraphics g)
    {
        fractalLine(g, order, p1, p2, p3);

    }

    private void fractalLine(TurtleGraphics g, int order, Point a, Point b, Point c)
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

            fractalLine(g, order-1, midPoint(a,b), midPoint(a,c), midPoint(b,c));
            fractalLine(g, order-1, a, midPoint(a,c), midPoint(b,c));
            fractalLine(g, order-1, midPoint(a,b), b, midPoint(b,c));
            fractalLine(g, order-1, midPoint(a,b), midPoint(a,c), c);
        }
    }

    private Point midPoint (Point p1, Point p2) 
    {
        return new Point ((p1.getX() + p2.getX() /2) , (p1.getY() + p2.getY() /2)) ;
    }
}
