package maika.states;

import java.io.Serializable;

import maika.view.CascadeDiagramView;

@SuppressWarnings("serial")
public class StateManager implements Serializable{
	private State currentState;
	
	RectangleState rectangleState;
	SelectState selectState;
	MoveState moveState;
	ResizeState resizeState;
	ElipseState elipseState;
	TriangleState triangleState;
	StarState starState;
	
	public StateManager(CascadeDiagramView tmpDiag){
		rectangleState = new RectangleState(tmpDiag);
		selectState = new SelectState(tmpDiag); 
		moveState = new MoveState(tmpDiag);
		resizeState = new ResizeState(tmpDiag);
		elipseState = new ElipseState(tmpDiag);
		triangleState = new TriangleState(tmpDiag);
		starState = new StarState(tmpDiag);
		currentState = selectState;
	}
	
	public void setRectangleState(){ currentState = rectangleState; }
	public void setSelectState(){ currentState = selectState; }
	public void setMoveState(){currentState=moveState;}
	public void setResizeState(){currentState=resizeState;}
	public void setElipseState(){currentState=elipseState;}
	public void setTriangleState(){currentState=triangleState;}
	public void setStarState(){currentState=starState;}
	
	public State getCurrentState() {
		return currentState;
	}
}
