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


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class editView extends Application 
{
	
	
	
	
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
	TreeView treeView;
	//Layer 1 Center bar
	BorderPane data;
	// add text boxes for data ... online research
	
	// Layer 0
	BorderPane backing;
	
	Plan plan;
	
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
	
    /**
     * 
     * 
     * @return vbox on  right side 
     */
    private VBox buildRight()
    {

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
    	removeBtn = new Button("Remove Branch");
    	removeBtn.setOnAction(e -> removeBranch());
    	saveBtn = new Button("Save Plan");
    	btnHolder = new VBox(addBtn, removeBtn, saveBtn);
    	
    	return btnHolder;
    }
    
    
    private VBox buildLeft()
    {

    	Button treeBtn = new Button("Add tree");
    	treeBtn.setOnAction(e -> {
			try
			{
				this.setTree();
			} catch (RemoteException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


    	tree = new VBox(treeBtn);
    	
    	return tree;
    }
    
    
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
     * Controller will use setTree to 
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
		//VBox treeBtn = buildLeft();
		setTree();
        
        backing.setLeft(tree);
        backing.setCenter(data);
        backing.setRight(btnHolder);
        backing.setTop(topBar);
        Scene scene = new Scene(backing,900,400);
        
        
		// Finalize and show the stage
		stage.setScene(scene);
		stage.setTitle("Edit view");
		stage.show();

		
	}
	
	
	private void addBranch() throws RemoteException
	{
		TreeItem parent = treeView.getSelectedItem();
		
		
		c.addBranch(null);
		
		setTree();

		
	}
	
	private void removeBranch()
	{
		
		
		
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
	
	
}

