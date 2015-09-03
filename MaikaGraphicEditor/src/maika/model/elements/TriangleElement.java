package maika.model.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import maika.view.painters.TrianglePainter;

@SuppressWarnings("serial")
public class TriangleElement extends DiagramElement{
	
	public TriangleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color strokeColor) {
		super(position, size, stroke, paint,strokeColor);
		elementPainter = new TrianglePainter(this);
	}

	
	public TriangleElement(TriangleElement triangle){
		super(triangle);
		setName("triangle");
		elementPainter=new TrianglePainter(this);
	}
	
	
	public static DiagramElement createDefault(Point2D pos, int elemNo){
		Point2D position = (Point2D) pos.clone();
		
        Paint fill = Color.WHITE;
        TriangleElement triangleElement=new TriangleElement(position, 
	    		                       new Dimension(80, 50),
	    		                      new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ),
             	                      fill,
	    		                      Color.BLACK);
        triangleElement.setName("Triangle" + elemNo);
		return triangleElement;
	}
	
	@Override
	public DiagramElement clone() {
		// TODO Auto-generated method stub
		return new TriangleElement(this);
	}
}
