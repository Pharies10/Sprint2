/**
 * 
 */
package software_masters.planner_networking;

import java.util.ArrayList;
import javafx.scene.control.TreeItem;
 

import java.util.ArrayList;
import javafx.scene.control.TreeItem;


/**
 * @author pharies
 *
 */
public class treeHelper
{
	public treeHelper()
	{
		
		
	}
	/**
	 * recursive method 
	 * takes a plan and converts it to a treeitem / treeview
	 * 
	 */
	public TreeItem makeTree(Node node) 
	{
		TreeItem curr = new TreeItem(node);
		ArrayList<TreeItem> children = new ArrayList<TreeItem>();
		
		for(int i = 0; i < node.getChildren().size(); i++)
		{
			TreeItem child = makeTree(node.getChildren().get(i));
			
			children.add(child);
			
			
		}
		
		curr.getChildren().addAll(children);
		
		
		return curr;

	}
}
