package maika.view;

import maika.commands.CommandManager;
import maika.event.UpdateEvent;
import maika.event.UpdateListener;


import java.awt.Adjustable;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import maika.application.Application;
import maika.model.elements.DiagramElement;
import maika.model.workspace.Diagram;
import maika.model.workspace.Workspace;
import maika.states.StateManager;
import maika.view.painters.ElementPainter;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.tree.TreePath;



@SuppressWarnings("serial")
public class CascadeDiagramView extends JInternalFrame implements AdjustmentListener, UpdateListener {
	
	public static int openFrameCount = 0;
	
	// služe nam za određivanje pozicije unutrašnjeg prozora
	static final int xOffset = 30, yOffset = 30;
	
	private Diagram diagram;
	//framework nam predstavlja radnu povrsinu za dijagram
 
	private JPanel framework;
	private JScrollBar scrollVertical;
	private JScrollBar scrollHorizontal;	
	
	private StateManager stateManager=new StateManager(this);
	private CommandManager commandManager=new CommandManager();

	
	//inicijalna pozicija skrol bara, vazna kod pomeranja skrol bara
	private int initialHorizontal=1490;
	private int initialVertical=1490;
	
	private Point2D lastPosition=null;
	
	//ukupna „količina” transliranja (pomeranja) po X i Y osi
	double translateX = 0;
	double translateY = 0;
	//ukupna “količina” skaliranja
	double scaling = 1;
	//faktor transliranja,
	//količina transliranja=broj rotacija točkića*faktor transliranja
	final static double translateFactor = 10;
	//faktor skaliranja,
	//količina skaliranja=broj rotacija točkića*faktor skaliranja
	final static double scalingFactor = 1.2;
	//objekat transformacije na kome se vrše pripreme transformacija
	private AffineTransform transformation = new AffineTransform();
	
	public enum Handle {
		North, South, East, West, SouthEast, SouthWest, NorthEast, NorthWest
	}
	public enum Direction{
		Up, Down, Left, Right
	}
	static final int handleSize = 7;
	
	public static final int RECTANGLE=1;
	public static final int ELIPSE=2;
	public static final int TRIANGLE=3;
	public static final int STAR=4;	
	
	public void startRectangleState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setRectangleState();

	}	
	
	public void startElipseState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setElipseState();
	}	
	
	public void startTriangleState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setTriangleState();
	}	
	public void startStarState() {
		diagram.getSelectionModel().removeAllFromSelectionList();
		stateManager.setStarState();
	}		
    public void startSelectState() {
    	stateManager.setSelectState();

	}
    public void startMoveState(){
    	stateManager.setMoveState();

    }
    
    public void startResizeState(){
    	stateManager.setResizeState();

    }
    
	public StateManager getStateManager() {
		return stateManager;
	}
	
	
	
	public CascadeDiagramView() {
		super("" ,
		          true, //resizable
		          true, //closable
		          true, //maximizable
		          true);//iconifiable
		++openFrameCount;
		setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		setIconifiable(true);
		setClosable(true);
		
		
		setSize(650,500);
	    setVisible(true);
		
	    framework=new Framework();
	    framework.setCursor(new Cursor(Cursor.HAND_CURSOR));
		framework.setBackground(Color.WHITE);
		getContentPane().add(framework,BorderLayout.CENTER);
		
		//postavljanje horizontalnog i vertikalnog skrol bara----------------------------
		scrollHorizontal=new JScrollBar(JScrollBar.HORIZONTAL, initialHorizontal, 20, 0, 3000);
		scrollVertical=new JScrollBar(JScrollBar.VERTICAL, initialVertical, 20, 0, 3000);

		
		scrollHorizontal.addAdjustmentListener(this);
		scrollVertical.addAdjustmentListener(this);
		
		this.add(scrollHorizontal,BorderLayout.SOUTH);
		this.add(scrollVertical,BorderLayout.EAST);
		//--------------------------------------------------------------------------
		
		DiagramController c=new DiagramController();
		framework.addMouseListener(c);
		framework.addMouseMotionListener(c);
		framework.addMouseWheelListener(c);
 	    
 	   addInternalFrameListener(new InternalFrameListener() {
			
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				
			}
			
			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameDeactivated(InternalFrameEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				
			}
			
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
			}
			
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
			    
				if(Application.getInstance().isSelectedFromTree())
					return;
		
				Object[] pomAL = new Object[3];
				pomAL[0] = ((Workspace)Application.getInstance().getWorkspaceModel().getRoot());
				if(diagram.getParent() != null)
					pomAL[1] = (diagram.getParent());
				pomAL[2] = (getDiagram());
				TreePath path = new TreePath(pomAL);

				Application.getInstance().getWorkspaceTree().setSelectionPath(path);
				SwingUtilities.updateComponentTreeUI(Application.getInstance().getWorkspaceTree());		  
			}
		});
 	    
 	   
 	   Application.getInstance().getDiagramViews().add(this);
 	    
 	    
	}
	
	public void setDiagram(Diagram diagram){
		this.diagram = diagram; 
		this.setName(diagram.getName());
		this.diagram.getDiagramModel().addUpdateListener(this);
		this.diagram.getSelectionModel().addUpdateListener(this);
		setTitle(diagram.getName());
	}


	public Diagram getDiagram() {
		return diagram;
	}

	
	
	/*
	 prosledjujemo izvsravanje aplikacije u zavisnosti od 
	 aktivnog stanje klasi koja 
	 obradjuje trenutno aktivno stanje
	 */
	private class DiagramController extends MouseAdapter implements MouseMotionListener{

		public void mousePressed(MouseEvent e) {
			   lastPosition=e.getPoint();
			   transformToUserSpace(lastPosition);
			
			   getStateManager().getCurrentState().mousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			
			   getStateManager().getCurrentState().mouseReleased(e);
		}
		
		public void mouseDragged(MouseEvent e ){
			
			   getStateManager().getCurrentState().mouseDragged(e);
		}
		public void mouseMoved(MouseEvent e) {
			
			getStateManager().getCurrentState().mouseMoved(e);
		}
		
		/*
		 * Transliranje i sklarianje se vrsi u metodi mouse wheel moved
		 */
		
		public void mouseWheelMoved(MouseWheelEvent e) {
			
			if((e.getModifiers()&MouseWheelEvent.CTRL_MASK) != 0){ // Ako pritisnut Ctrl
				// Radimo zoom u tački (diskretni zoom)
				// Prvo je potrebno da odredimo novo skaliranje 
				double newScaling = scaling;
				if(e.getWheelRotation()>0)
					
					newScaling *= (double)e.getWheelRotation()*scalingFactor;
				
				else{
					
					if (e.getWheelRotation()!=0){
						
						newScaling /= -(double)e.getWheelRotation()*scalingFactor;
						
					}
					
					
				}
				// Zatim je potrebno da skaliranje održimo u intervalu [0.2, 5]
				if(newScaling < 0.2){
					newScaling = 0.2;
					
				}else if(newScaling > 5){
					newScaling = 5;
				}
				
				
				/* newScaling je novi parametar skaliranja (članovi m00 i m11 transformacione matrice)
				 * Prilikom skaliranja dolazi do pomeranja userspace koordinata na kojima se nalazi pokazivač miša.
				 * Da bi dobili ispravan Point zoom moramo izvršiti translaciju tako da poništimo "smicanje" usled skaliranja 
				 * tj. moramo postići da se userspace koordinate miša ne promene.
				 */
				
				Point2D oldPosition = e.getPoint();
				transformToUserSpace(oldPosition);
				
				scaling = newScaling;
				setupTransformation();

				
				Point2D newPosition = e.getPoint();
				transformToUserSpace(newPosition);
				
				translateX +=  newPosition.getX() - oldPosition.getX();
				translateY += newPosition.getY() - oldPosition.getY();
	
				//setupTransformation();
				
				

			}else if((e.getModifiers()&MouseWheelEvent.SHIFT_MASK) != 0){  // Ako je pritisnut Shift
				
					translateX += (double)e.getWheelRotation() * translateFactor/scaling;
					
			}else{ // u ostalim slučajevima vršimo skrolovanje po Y osi
				
					translateY += (double)e.getWheelRotation() * translateFactor/scaling;
				

				
			}
			
			setupTransformation();
			repaint();
		}
		
	}
	
	/*
	Da bi odredili, za zadate fizičke koordinate odredili,
		logičku poziciju moramo koristiti mapiranje tačke kroz 
		inverznu matricu transformacije.
 */

	public void transformToUserSpace(Point2D deviceSpace){
		try {
			transformation.inverseTransform(deviceSpace, deviceSpace);
		} catch (NoninvertibleTransformException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePerformed(UpdateEvent e) {
		repaint();
		
	}
	
	
	
	private void setupTransformation() {
		
		transformation.setToIdentity();
		// Zumiranje
		
		transformation.scale(scaling, scaling);
		// Skrolovanje
		transformation.translate(translateX, translateY);
		
	}
	
	private class Framework extends JPanel{
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.LIGHT_GRAY);
			Graphics2D g2 = (Graphics2D) g;
 		    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			
			g2.transform(transformation);
			//g2.setTransform(transformation);
			
			Iterator<DiagramElement> it = diagram.getDiagramModel().getElementsIterator();
			while(it.hasNext()){
				DiagramElement d = (DiagramElement) it.next();
				ElementPainter paint =  (ElementPainter) d.getElementPainter();
				paint.paint(g2, d);
				
			}
			

			
			paintSelectionHandles(g2);
			
		}

	}
	
	public void paintSelectionHandles(Graphics2D g2) {
		Iterator<DiagramElement> it = diagram.getSelectionModel().getSelectionListIterator();
		while(it.hasNext()){
			DiagramElement element =  it.next();

				// Iscrtavanje pravougaonika sa isprekidanom linijom
				g2.setStroke(new BasicStroke((float)1, BasicStroke.CAP_SQUARE, 
						BasicStroke.JOIN_BEVEL, 1f, new float[]{3f, 6f}, 0 ));
				g2.setPaint(Color.BLACK);
			
				//g2.drawRect((int)device.getPosition().getX(), (int)device.getPosition().getY(),
					//	(int)device.getSize().getWidth(), (int)device.getSize().getHeight());
			
				if (element.getRotation() == 0 || element.getRotation() == Math.PI || element.getRotation() == -Math.PI){
					g2.drawRect((int)element.getPosition().getX(), (int)element.getPosition().getY(),
							(int)element.getSize().getWidth(), (int)element.getSize().getHeight());
					//	Iscrtavanje hendlova
					for(Handle e: Handle.values()){
						paintSelectionHandle(g2, getHandlePoint(element.getPosition(), element.getSize(), e));
					}
				}
				else {
					double razlika = (element.getSize().getWidth()-element.getSize().getHeight())/2;
					g2.drawRect((int)(element.getPosition().getX() + razlika), (int)(element.getPosition().getY() - razlika),
							(int)(element.getSize().getHeight()), (int)(element.getSize().getWidth()));
					
					//uzimam novu poziciju i velicinu da bi iscrtao selekciju i hendlove
					int x = (int)(element.getPosition().getX()+razlika);
					int y = (int)(element.getPosition().getY()-razlika);
					double h = element.getSize().getWidth();
					double w = element.getSize().getHeight();
					element.getSize().setSize(w, h);
					Point2D p = (Point2D)element.getPosition().clone();
					p.setLocation(x, y);
					Dimension2D dim = (Dimension2D)element.getSize().clone();
					dim.setSize(w, h);
					// 	Iscrtavanje hendlova
					for(Handle e: Handle.values()){
						paintSelectionHandle(g2, getHandlePoint(p, dim, e));
					}
				}
				
				// 	Iscrtavanje hendlova
//				for(Handle e: Handle.values()){
//					paintSelectionHandle(g2, getHandlePoint(device.getPosition(), device.getSize(), e));
//					System.out.println("paintSelectionHandles 2");
//				}
			
			


		}
	}	
	
	
	private void paintSelectionHandle(Graphics2D g2, Point2D position){
		double size = handleSize;
		g2.fill(new Rectangle2D.Double(position.getX()-size/2, position.getY()-size/2, 
				size, size));	
		repaint();
	}
	
	
	private Point2D getHandlePoint(Point2D topLeft, Dimension2D size, Handle handlePosition ){
	double x=0, y=0;
		
		// Doređivanje y koordinate
		
		// Ako su gornji hendlovi
		if(handlePosition == Handle.NorthWest || handlePosition == Handle.North || handlePosition == Handle.NorthEast){
			y = topLeft.getY();
		}
		// Ako su centralni po y osi
		if(handlePosition == Handle.East || handlePosition == Handle.West){
			y = topLeft.getY()+size.getHeight()/2;
		}
		//Ako su donji
		if(handlePosition == Handle.SouthWest || handlePosition == Handle.South || handlePosition == Handle.SouthEast){
			y = topLeft.getY() + size.getHeight();
		}

		// Određivanje x koordinate
		
		// Ako su levi
		if(handlePosition == Handle.NorthWest || handlePosition == Handle.West || handlePosition == Handle.SouthWest){
			x = topLeft.getX();
		}
		// ako su centralni po x osi
		if(handlePosition == Handle.North || handlePosition == Handle.South){
			x = topLeft.getX() + size.getWidth()/2;
		}
		// ako su desni
		if(handlePosition == Handle.NorthEast || handlePosition == Handle.East || handlePosition == Handle.SouthEast){
			x = topLeft.getX() + size.getWidth();
		}
		
		return new Point2D.Double(x,y);
	}
	
	/**
	 * Na osnovu hendla iznad koga se nalazi postavlja kursor
	 */
	public void setMouseCursor(Point2D point){
		Handle handle = getDeviceAndHandleForPoint(point);

		if(handle != null){
			Cursor cursor = null;
			
			switch(handle){
			case North: cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);break;
			case South: cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);break;
			case East: cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);break;
			case West: cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);break;
			case SouthEast: cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);break;
			case NorthWest: cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);break;
			case SouthWest: cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);break;
			case NorthEast: cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);break;
			}
			framework.setCursor(cursor);
		}
		else
			framework.setCursor(Cursor.getDefaultCursor());

	}
	
	/**
	 * Određuje handl i uređaj koji se nalazi na zadatoj lokaciji 
	 * @param point Ulazni parametar koji određuje lokaciju
	 * @return Hendl za zadatu poziciju. Ukoliko je null tada je device nedefinisan.
	 */
	public Handle getDeviceAndHandleForPoint(Point2D point){
		DiagramElement element;
		
		Iterator<DiagramElement> it = diagram.getSelectionModel().getSelectionListIterator();
		while(it.hasNext()){
			element = it.next();
			return getHandleForPoint(element, point);
		}
		return null;
	}
	
	/**
	 * Za zadatu tačku i uređaj vraća hendl.
	 * @param device
	 * @param point
	 * @return Hendl ukoliko je "pogođen", u suprotnom vraća null
	 */
	private Handle getHandleForPoint(DiagramElement element, Point2D point){
		for(Handle h: Handle.values()){
			if(isPointInHandle(element, point, h)){
				return h;
			}
		}
		return null;
	}
	
	/**
	 * Za zadati uređaj, tačku i hendl određuje da li je tačka unutar hendla
	 * @param device
	 * @param point
	 * @param handle
	 * @return
	 */
	private boolean isPointInHandle(DiagramElement element, Point2D point, Handle handle){
		
			
			Point2D handleCenter = getHandlePoint(element.getPosition(), element.getSize(), handle);
			return ( (Math.abs(point.getX()-handleCenter.getX())<=(double)handleSize/2) && 
					(Math.abs(point.getY()-handleCenter.getY())<=(double)handleSize/2) );

	}
	

	
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
	// TODO Auto-generated method stub
		
		//Nakon određivanja vrednosti translateX i translateY koje 
		//zavise potrebno je setovati novu transformaciju
		//uzeti u obzir koji je skrol bar pomeran
		//i u zavisnosti od prethodne pozicije datog skrol bara
		//i trenutnog skaliranja izvrsiti transformaciju translacije
		//nove 
		if(e.getAdjustable().getOrientation()==Adjustable.HORIZONTAL){
			if(initialHorizontal<e.getValue()){
				translateX+=(double)((e.getValue()-initialHorizontal)*(-translateFactor))/transformation.getScaleX();
				//transformation.translate((double)((e.getValue()-initialHorizontal)*(-translateFactor))/transformation.getScaleX(), 0);

			}
			else{
				translateX+=(double)((e.getValue()-initialHorizontal)*(-translateFactor))/transformation.getScaleX();
				//translateX+=(double)((initialHorizontal-e.getValue())*(-translateFactor))/transformation.getScaleX();
				//transformation.translate((double)((initialHorizontal-e.getValue())*(translateFactor))/transformation.getScaleX(), 0);			
			}
			initialHorizontal=e.getValue();
			
		}
		else{
			if(initialVertical<e.getValue()){			
				translateY+=(double)((e.getValue()-initialVertical)*(-translateFactor))/transformation.getScaleX();
				//transformation.translate(0,(double)((e.getValue()-initialVertical)*(-translateFactor))/transformation.getScaleX());				
			}
			else{
				translateY+=(double)((e.getValue()-initialVertical)*(-translateFactor))/transformation.getScaleX();
				//translateY+=(double)((initialVertical-e.getValue())*(-translateFactor))/transformation.getScaleX();
				//transformation.translate(0,(double)((initialVertical-e.getValue())*(translateFactor))/transformation.getScaleX());			
			}
			initialVertical=e.getValue();
		}
		setupTransformation();
		repaint();
	}


	public JPanel getFramework() {
		return framework;
	}
	
	public Point2D getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point2D lastPosition) {
		this.lastPosition = lastPosition;
	}
	
	public void autoScroll(Direction direction){
		
		
		switch(direction){
			case Left:{
				int valueScroll=scrollHorizontal.getValue();
				if(valueScroll>=scrollHorizontal.getMinimum()){				
					scrollHorizontal.setValue(scrollHorizontal.getValue()-2);
					valueScroll=scrollHorizontal.getValue();
					initialHorizontal=valueScroll;
				}
				break;
			}
			case Up:{
				int valueScroll=scrollVertical.getValue();
				if(valueScroll<=scrollVertical.getMaximum()){				
					scrollVertical.setValue(scrollVertical.getValue()+2);
					valueScroll=scrollVertical.getValue();
					initialVertical=valueScroll;
				}
				break;
			}
			case Right:{
				int valueScroll=scrollHorizontal.getValue();
				if(valueScroll<=scrollHorizontal.getMaximum()){				
					scrollHorizontal.setValue(scrollHorizontal.getValue()+2);
					valueScroll=scrollHorizontal.getValue();
					initialHorizontal=valueScroll;
				}
				break;				
			}
			case Down:{
				int valueScroll=scrollVertical.getValue();
				if(valueScroll<=scrollVertical.getMaximum()){				
					scrollVertical.setValue(scrollVertical.getValue()-2);
					valueScroll=scrollVertical.getValue();
					initialVertical=valueScroll;
				}
				break;
				
			}
			
		}	
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}
	
	
	/* newScaling je novi parametar skaliranja (clanovi m00 i m11 transformacione matrice)
	 * Prilikom skaliranja dolazi do pomeranja userspace koordinata na kojima se nalazi pokazivac misa.
	 * Da bi dobili ispravan Point zoom moramo izvrsiti translaciju tako da ponistimo "smicanje" usled skaliranja 
	 * tj. moramo postici da se userspace koordinate misa ne promene.
	 */
	public void centerZoom(boolean direction) {
		double newScaling = transformation.getScaleX();
		
		if (direction)
			if (newScaling > 4.9)
				return;
		else
			if (newScaling < 0.3)
				return;
		
		if (direction)
			newScaling *= scalingFactor;
		else
			newScaling /= scalingFactor;
		
		if(newScaling < 0.2)
			newScaling = 0.2;
		if(newScaling > 5)
			newScaling = 5;
		
					
		Point2D oldPosition = new Point(getWidth()/2, getHeight()/2); //zapamtimo koordinate misa
		transformToUserSpace(oldPosition);
		
		transformation.setToScale(newScaling, newScaling); //izvrsimo zum

		Point2D newPosition = new Point(getWidth()/2, getHeight()/2); //izracunamo nove koordinate
		transformToUserSpace(newPosition);
				
		transformation.translate(newPosition.getX() - oldPosition.getX(), newPosition.getY() - oldPosition.getY());
		//razlika nove i stare pozicije sprecava pomeranje misa sa starih koordinata
		repaint();
	}
	
	/*
	 * Vraca element ciji je handle uhvacen
	 */
	public DiagramElement getHandlesElement(Point2D point){
		DiagramElement element;
		Handle handle = null;
		
		Iterator<DiagramElement> it = diagram.getSelectionModel().getSelectionListIterator();
		while(it.hasNext()){
			element = it.next();
			handle = getHandleForPoint(element, point);
			if (handle != null)
				return element;
		}
		return null;
	}
	

}	
	
	


