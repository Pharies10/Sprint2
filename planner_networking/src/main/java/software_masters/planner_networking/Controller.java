/**
 * 
 */
package software_masters.planner_networking;

import java.rmi.RemoteException;

/**
 * @author pharies
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


	public void addBranch(Node node) throws RemoteException, IllegalArgumentException
	{
		System.out.println("hello");
		Plan plan = client.getCurrPlanFile().getPlan();
		plan.addNode(node);
		
		client.getCurrPlanFile().setPlan(plan);
		
		view.setTree();
		
	}
	
	
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
