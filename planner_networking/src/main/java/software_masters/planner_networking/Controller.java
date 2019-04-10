/**
 * 
 */
package software_masters.planner_networking;

import java.rmi.RemoteException;

import javafx.scene.control.TreeItem;

/**
 * @author pharies
 *
 * controller between the view (editView) and model (client)
 * initialized in the view
 *
 */
public class Controller
{
	
	
	Client client;
	editView view;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public Controller(Client client) throws Exception
	{

		
		
		
		
		
	}
	
	
	public Controller()
	{
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 
	 * Takes tree item
	 * extracts node
	 * add nodes (and children)
	 * sets it to the clients plan
	 * resets the view in the View
	 * 
	 * @param node
	 * @throws RemoteException
	 * @throws IllegalArgumentException
	 */
	public void addBranch(Node node) throws RemoteException, IllegalArgumentException
	{
		
		Plan plan = client.getCurrPlanFile().getPlan();
		plan.addNode(node);
		
		client.getCurrPlanFile().setPlan(plan);
		
		view.setTree();
		
	}
	
	/**
	 * 
	 * Takes tree item
	 * extracts node
	 * removes node
	 * sets it to the clients plan
	 * resets the view in the View
	 * 
	 * @param node
	 * @throws RemoteException
	 * @throws IllegalArgumentException
	 */
	public void removeBranch(Node node) throws RemoteException, IllegalArgumentException
	{
		System.out.println("hello");
		Plan plan = client.getCurrPlanFile().getPlan();
		plan.removeNode(node);
		
		client.getCurrPlanFile().setPlan(plan);
		
		view.setTree();
		
	}
	
	
	/**
	 * 
	 * gets current plan from the client
	 * 
	 * @return
	 */
	public Plan getPlan()
	{
		Plan plan = client.getCurrPlanFile().getPlan();
		return plan;
		
		
	}


	/**
	 * @return the client
	 */
	public Client getClient()
	{
		return client;
	}


	/**
	 * @param client the client to set
	 */
	public void setClient(Client client)
	{
		this.client = client;
	}


	/**
	 * @return the view
	 */
	public editView getView()
	{
		return view;
	}


	/**
	 * @param view the view to set
	 */
	public void setView(editView view)
	{
		this.view = view;
	}


}
