package maika.model.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import maika.view.painters.StarPainter;

@SuppressWarnings("serial")
public class StarElement extends DiagramElement{
	public StarElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color strokeColor) {
		super(position, size, stroke, paint,strokeColor);
		elementPainter = new StarPainter(this);
	}

	
	public StarElement(StarElement star){
		super(star);
		setName("star");
		elementPainter=new StarPainter(this);
	}
	
	
	public static DiagramElement createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.WHITE;
        StarElement starElement=new StarElement(position, 
	    		                       new Dimension(80,70),
	    		                      new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ),
             	                      fill,
	    		                      Color.BLACK);
        starElement.setName("Star" + elemNo);
		return starElement;
	}
	
	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new StarElement(this);
	}
}
