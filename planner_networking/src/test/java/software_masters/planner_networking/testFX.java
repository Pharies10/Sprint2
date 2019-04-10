/**
 * 
 */
package software_masters.planner_networking;

import java.rmi.RemoteException;

import javafx.application.Application;

/**
 * @author pharies
 *
 */
public class testFX
{

	/**
	 * @throws Exception 
	 * 
	 */
	public testFX() throws Exception
	{
		Plan test = new VMOSA();
    	Client client = new Client(null);
    	PlanFile file = new PlanFile();
    	file.setPlan(test);
    	client.setCurrPlanFile(file);
    	
    	
    	Controller c = new Controller(client);
	
	

	
 
	
		
	}

}
