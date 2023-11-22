package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication 
{
	public static void main(String[] args) 
	{
		Point a = new Point(100, 500);
		Point b = new Point(350, 100);
		Point c = new Point(500, 500);

		Fractal[] fractals = new Fractal[1];
		fractals[0] = new Mountain(a, b, c, 30);

	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
