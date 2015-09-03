package maika.actions;

public class ActionManager {
	private NewProjectAction newProjectAction;
	private NewDiagramAction newDiagramAction;
	private OpenProjectAction openProjectAction;
	private OpenDiagramAction openDiagramAction;
	private SaveProjectAction saveProjectAction;
	private SaveDiagramAction saveDiagramAction;	
	private CloseProjectAction closeProjectAction;
	private CloseDiagramAction closeDiagramAction;		
	private PrintAction printAction;
	private ImportAction importAction;
	private ExportAction exportAction;
	
	private HorizontalWindowsAction horizontalWindowsAction;
	private VerticalWindowsAction verticalWindowsAction;
	private CascadeWindowsAction cascadeWindowsAction;
	private PreviousWindowAction previousWindowAction;
	private NextWindowAction nextWindowAction;
	
	private SelectGTAction selectGTAction;
	private TriangleGTAction triangleGTAction;
	private CircleGTAction circleGTAction;
	private RectangleGTAction rectangleGTAction;
	
	private RotateRightAction rotateRightAction;
	private RotateLeftAction rotateLeftAction;
	
	private UndoAction undoAction;
	private RedoAction redoAction;
	
	private StarGTAction starGTAction;
	
	private AboutAction aboutAction;
	
	private DeleteSelectedElements deleteSelectedElements;
	private ZoomInAction zoomInAction;
	private ZoomOutAction zoomOutAction;
	public ActionManager(){
		
		newProjectAction = new NewProjectAction();
		newDiagramAction = new NewDiagramAction();
		openProjectAction = new OpenProjectAction();
		openDiagramAction = new OpenDiagramAction();
		saveProjectAction = new SaveProjectAction();
		saveDiagramAction = new SaveDiagramAction();	
		closeProjectAction = new CloseProjectAction();
		closeDiagramAction = new CloseDiagramAction();
		printAction = new PrintAction();
		importAction = new ImportAction();
		exportAction = new ExportAction();
		
		horizontalWindowsAction = new HorizontalWindowsAction();
		verticalWindowsAction = new VerticalWindowsAction();
		cascadeWindowsAction = new CascadeWindowsAction();
		previousWindowAction = new PreviousWindowAction();
		nextWindowAction = new NextWindowAction();
		
		selectGTAction = new SelectGTAction();
		triangleGTAction = new TriangleGTAction();
		circleGTAction = new CircleGTAction();
		rectangleGTAction = new RectangleGTAction();
		
		rotateRightAction = new RotateRightAction();
		rotateLeftAction = new RotateLeftAction();
		
		aboutAction = new AboutAction();
		
		undoAction = new UndoAction();
		redoAction = new RedoAction();
		starGTAction = new StarGTAction();
		deleteSelectedElements = new DeleteSelectedElements();
		zoomInAction = new ZoomInAction();
		zoomOutAction = new ZoomOutAction();
		
	}


	public ZoomInAction getZoomInAction() {
		return zoomInAction;
	}


	public void setZoomInAction(ZoomInAction zoomInAction) {
		this.zoomInAction = zoomInAction;
	}


	public ZoomOutAction getZoomOutAction() {
		return zoomOutAction;
	}


	public void setZoomOutAction(ZoomOutAction zoomOutAction) {
		this.zoomOutAction = zoomOutAction;
	}


	public DeleteSelectedElements getDeleteSelectedElements() {
		return deleteSelectedElements;
	}


	public void setDeleteSelectedElements(
			DeleteSelectedElements deleteSelectedElements) {
		this.deleteSelectedElements = deleteSelectedElements;
	}


	public StarGTAction getStarGTAction() {
		return starGTAction;
	}

	public void setStarGTAction(StarGTAction starGTAction) {
		this.starGTAction = starGTAction;
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}

	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}

	public RotateRightAction getRotateRightAction() {
		return rotateRightAction;
	}

	public void setRotateRightAction(RotateRightAction rotateRightAction) {
		this.rotateRightAction = rotateRightAction;
	}

	public RotateLeftAction getRotateLeftAction() {
		return rotateLeftAction;
	}

	public void setRotateLeftAction(RotateLeftAction rotateLeftAction) {
		this.rotateLeftAction = rotateLeftAction;
	}

	public AboutAction getAboutAction() {
		return aboutAction;
	}

	public SelectGTAction getSelectGTAction() {
		return selectGTAction;
	}

	public TriangleGTAction getTriangleGTAction() {
		return triangleGTAction;
	}

	public CircleGTAction getCircleGTAction() {
		return circleGTAction;
	}

	public RectangleGTAction getRectangleGTAction() {
		return rectangleGTAction;
	}

	public PreviousWindowAction getPreviousWindowAction() {
		return previousWindowAction;
	}

	public NextWindowAction getNextWindowAction() {
		return nextWindowAction;
	}

	public HorizontalWindowsAction getHorizontalWindowsAction() {
		return horizontalWindowsAction;
	}

	public VerticalWindowsAction getVerticalWindowsAction() {
		return verticalWindowsAction;
	}

	public CascadeWindowsAction getCascadeWindowsAction() {
		return cascadeWindowsAction;
	}

	public PrintAction getPrintAction() {
		return printAction;
	}

	public ImportAction getImportAction() {
		return importAction;
	}

	public ExportAction getExportAction() {
		return exportAction;
	}

	public CloseProjectAction getCloseProjectAction() {
		return closeProjectAction;
	}

	public CloseDiagramAction getCloseDiagramAction() {
		return closeDiagramAction;
	}

	public SaveProjectAction getSaveProjectAction() {
		return saveProjectAction;
	}

	public SaveDiagramAction getSaveDiagramAction() {
		return saveDiagramAction;
	}

	public OpenDiagramAction getOpenDiagramAction() {
		return openDiagramAction;
	}

	public NewProjectAction getNewProjectAction() {
		return newProjectAction;
	}

	public NewDiagramAction getNewDiagramAction() {
		return newDiagramAction;
	}

	public OpenProjectAction getOpenProjectAction() {
		return openProjectAction;
	}
	
	
	
}
