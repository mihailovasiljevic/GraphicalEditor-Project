package maika.view.painters;

import maika.model.elements.DiagramElement;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.awt.Font;

@SuppressWarnings("serial")
public class ElementPainter implements Serializable{
	
	protected Shape shape;
	protected DiagramElement element;

	public ElementPainter(DiagramElement element) {
		super();
		this.element = element;
	}
	
	
	public void paint(Graphics2D g, DiagramElement element){
		
		
		AffineTransform oldTranform=g.getTransform();
		//uzimamo element kome painter pripada
		


		
		g.translate(element.getPosition().getX(), element.getPosition().getY());
		g.rotate(element.getRotation(), element.getSize().getWidth()/2, element.getSize().getHeight()/2);
		g.scale(element.getScale(), element.getScale());
		
		
		g.setPaint(element.getStrokeColor());
		g.setStroke(element.getStroke());
		g.draw(getShape());

		g.setPaint(element.getPaint());
		g.fill(getShape());	
		g.setPaint(Color.BLUE);
		
		g.setFont(new Font("Arial",Font.BOLD,12));
		g.setPaint(element.getStrokeColor());
		g.drawString(element.getName(),10,(int)element.getSize().getHeight()+20);
		g.setTransform(oldTranform);
}
		
		
	public boolean elementAt(DiagramElement element, Point pos){
		return getShape().contains(pos);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}


	public boolean isElementAt(Point point) {
		// TODO Auto-generated method stub
		Rectangle2D rect=new Rectangle2D.Double();
		rect.setRect(element.getPosition().getX(), element.getPosition().getY(),
				element.getSize().getWidth(), element.getSize().getHeight());
		return rect.contains(point);
	}

	
	
}
