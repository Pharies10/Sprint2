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
	
	//Layer 1 Center bar
	BorderPane data;
	// add text boxes for data ... online research
	
	// Layer 0
	BorderPane backing;
	
	Plan plan;
	
	Stage stage;
	
    public static void main( String[] args )
    {
        launch(args);
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
    	saveBtn = new Button("Save Plan");
    	btnHolder = new VBox(addBtn, removeBtn, saveBtn);
    	
    	return btnHolder;
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
    
    
    public void setTree(Plan plan) throws RemoteException
    {
    	

		
		
		
		
    	treeHelper helper = new treeHelper();
        TreeView treeView = new TreeView();
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
        plan = new VMOSA();

		
		btnHolder = buildRight();
		topBar = buildTop();
		data = new BorderPane();
		backing = new BorderPane();
		
		setTree(plan);
        
        
        
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
		
		Node rootNode = plan.getRoot();
		Node missionNode = rootNode.getChildren().get(0);
		Node objNode = missionNode.getChildren().get(0);
		plan.addNode(objNode);
		
		setTree(plan);
		
		
		
		
		
	}
}

