package maika.model.elements;

import maika.serialization.SerializableStrokeAdapter;
import maika.view.painters.ElementPainter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class DiagramElement implements Serializable{
	protected Paint paint;
	protected SerializableStrokeAdapter stroke;
	protected Color strokeColor;
	
	protected String name = "";
	protected String description = "";
	
	protected Dimension size;
	protected Point2D position;
	
	
	protected double scale;
	protected double rotation;
	
	
	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	protected ElementPainter elementPainter;
	
	public DiagramElement(Point2D position, Dimension size, Stroke stroke, Paint paint){
		setStroke(stroke);
		this.paint = paint;
		this.size = size;
		position.setLocation(position.getX()-size.getWidth()/2,position.getY()-size.getHeight()/2);
		this.position = position;
		this.scale=1;
		this.rotation=0;
		
	}
	
	public DiagramElement(Point2D position, Dimension size, Stroke stroke, Paint paint,Color strokeColor){
		setStroke(stroke);
		this.paint = paint;
		this.size = size;
		position.setLocation(position.getX()-size.getWidth()/2,position.getY()-size.getHeight()/2);
		this.position = position;
		this.scale=1;
		this.rotation=0;
		this.strokeColor = strokeColor;
		
	}
	
	public DiagramElement(DiagramElement element){
		this.stroke = element.stroke;
		this.paint = element.paint;
		this.size = element.size;
		position.setLocation(position.getX()-size.getWidth()/2,position.getY()-size.getHeight()/2);
		this.position = element.position;
		this.scale=1;
		this.rotation=0;	
	}
	
	public DiagramElement(Stroke stroke, Paint paint,Color  strokeColor){
		setStroke(stroke);
		this.paint = paint;
		this.strokeColor=strokeColor;
	}
	
	
	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = new SerializableStrokeAdapter(stroke);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	//uracunata i kolicina skaliranja
	public Dimension getSize() {
		Dimension tempSize = new Dimension();
		tempSize.setSize(size.getWidth()*getScale(),
		size.getHeight()*getScale());
		return tempSize;
	}
	public void setSize(Dimension size) {
		this.size = size;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public ElementPainter getElementPainter() {
		return elementPainter;
	}

	public void setElementPainter(ElementPainter elementPainter) {
		this.elementPainter = elementPainter;
	}
	//vraca pocetnu velicinu
	public Dimension getInitSize(){
		return size;
	}

}
