/**
 * 
 */
package software_masters.planner_networking;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//import ButtonFXtest.Test_fx.Node;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * 
 * Client and Controller and initialized in start
 * will need to be changed upon addition of new scenes
 * to change text you have to press enter
 * see manual test document for further instructions
 * 
 * 
 * 
 */
public class editView extends Application 
{
	
	String info;
	
	TextField text;
	//Layer 1 right side
	VBox btnHolder;
	Button addBtn;
	Button removeBtn;
	Button saveBtn;
	
	//Layer 1 top bar
	BorderPane topBar;
	Button home;
	Button logOut;
	
	// add buttons for homepage and logout
	
	//Layer 1 left side
	VBox tree;
	TreeView<TreeItem> treeView;
	//Layer 1 Center bar
	BorderPane data;
	// add text boxes for data ... online research
	
	// Layer 0
	BorderPane backing;
	

	
	Stage stage;
	
	
	public Client client;
	public Controller c;
	
    public editView(Controller c)
    {
    	this.c = c;
       
   	}

    public editView()
    {
    
       
   	}
	
    public static void main( String[] args )
    {
        launch(args);
    }
    /**
     * builds and sets actions to buttons that go on right side of Gui
     * 
     * @return vbox on  right side 
     */
    private VBox buildRight()
    {
    	
    	
    	//set add action
    	addBtn = new Button("Add Branch");
    	addBtn.setOnAction(e -> {
			try
			{
				addBranch();
			} catch (RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    	
    	
    	// set remove action
    	removeBtn = new Button("Remove Branch");
    	removeBtn.setOnAction(e -> {
			try
			{
				removeBranch();
			} catch (RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1)
			{
				// TODO Auto-generated catch block
				System.out.println("cannont remove node");
			}
		});
    	
    	// set save action
    	saveBtn = new Button("Save Plan");
    	saveBtn.setOnAction(e -> {
			try
			{
				update();
			} catch (IllegalArgumentException | RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    	btnHolder = new VBox(addBtn, removeBtn, saveBtn);
    	
    	return btnHolder;
    }
    

    
    /**
     * sets the tops buttons
     * since no need for logout and Home the buttons are not set for action
     * needs to be added to upon further development
     * @return
     */
    private BorderPane buildTop()
    {
    	
    	home = new Button("Home");
    	logOut = new Button("Logout");
    	topBar = new BorderPane();
    	
    	topBar.setLeft(logOut);
    	topBar.setCenter(home);
    	
    	//
    	
    	return topBar;
    }
    
    
    /**
     * 
     * sets the tree and the text box to the latest version of tree
     * 
     * 
     * @param plan
     * @return
     * @throws RemoteException
     */
    
    
    public void setTree() throws RemoteException
    {
    	
    	Plan plan = c.getPlan();
		
		
    	treeHelper helper = new treeHelper();
        treeView = new TreeView();
        TreeItem rootItem = helper.makeTree(plan.getRoot());
        // Set the Root Node

        treeView.setRoot(rootItem);
        
         
        tree = new VBox();
        // Add the TreeView to the VBox
        tree.getChildren().add(treeView); 
    	backing.setLeft(tree);
    	
		text = new TextField();

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() 
		{
			   @SuppressWarnings("unchecked")
			public void changed(ObservableValue observable, Object oldValue,
		                Object newValue) {

				   text.setPromptText(((TreeItem<Node>) newValue).getValue().getData());
				   System.out.println(text.getText());
				   text.setOnAction(e -> save(treeView.getSelectionModel().getSelectedItem()));        
					       
					    }
			
				
		            
		            //System.out.println("textfield changed from  to " +  ((TreeItem<Node>) newValue).getValue().getData()));

		});
		
		backing.setCenter(text);
    	
    }


   
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		
		stage = primaryStage;
        c = new Controller();
        client = new Client(null);
        c.setClient(client);
        c.setView(this);
    	PlanFile file = new PlanFile();
    	Plan test = new VMOSA();

    	file.setPlan(test);
    	client.setCurrPlanFile(file);
    	
		
		btnHolder = buildRight();
		topBar = buildTop();
		data = new BorderPane();
		backing = new BorderPane();
		
		setTree();
        
	  	

		
		
        backing.setLeft(tree);
        backing.setCenter(text);
  
        backing.setRight(btnHolder);
        backing.setTop(topBar);
        Scene scene = new Scene(backing,900,400);
        
        
		// Finalize and show the stage
		stage.setScene(scene);
		stage.setTitle("Edit view");
		stage.show();

		
	}
	
	/**
	 * action called when add branch
	 * sends it to controller to handle
	 * 
	 * @throws RemoteException
	 */
	private void addBranch() throws RemoteException
	{
		TreeItem parent = treeView.getSelectionModel().getSelectedItem();
		Node node = (Node) parent.getValue();
		
		c.addBranch(node);
		
		
		
		
	}
	
	/**
	 * action called when remove branch
	 * sends it to controller to handle
	 * 
	 * @throws RemoteException
	 */
	private void removeBranch() throws RemoteException, IllegalArgumentException
	{
		TreeItem parent = treeView.getSelectionModel().getSelectedItem();
		Node node = (Node) parent.getValue();
		
		c.removeBranch(node);
		
		
	}
	
	
	
	
	/**
	 * 
	 * action called when save plan
	 * update plan 
	 * 
	 * @throws IllegalArgumentException
	 * @throws RemoteException
	 */
	private void update() throws IllegalArgumentException, RemoteException
	{
		c.update();
		
		
		
		
	}

	/**
	 * @return the c
	 */
	public Controller getC()
	{
		return c;
	}


	/**
	 * @param c the c to set
	 */
	public void setC(Controller c)
	{
		this.c = c;
	}
	
	/**
	 * action called when text changes and return key pressed
	 * sets node data
	 * 
	 * @param item
	 */
	private void save(TreeItem item)
	{
		
		Node node = (Node) item.getValue();
		node.setData(text.getText());
		try
		{
			this.setTree();
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}

