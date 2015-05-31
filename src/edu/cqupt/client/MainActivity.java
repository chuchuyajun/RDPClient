package edu.cqupt.client;

import java.lang.reflect.Method;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity{

	 private String TAG = "Event Inject";	
	 
	 private static final int VolumeDown_ID = Menu.FIRST;
	 private static final int Switch_ID     = Menu.FIRST + 1;
	 private static final int VolumeUp_ID   = Menu.FIRST + 2;
	 private static final int Menu_ID       = Menu.FIRST + 3;
	 private static final int Home_ID       = Menu.FIRST + 4;
	 private static final int Back_ID       = Menu.FIRST + 5;
	   	
	 protected void onCreate(Bundle savedInstanceState) 
	   {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.client); // inflate the layout
	         
      
	      // get reference to the DoodleView
	     
	   } // end method onCreate
  
		
	

	 public boolean onOptionsItemSelected(MenuItem item) 
	   {
	      // switch based on the MenuItem id
	      switch (item.getItemId()) 
	      {
	      	 case VolumeDown_ID:
	      
	            return true; // consume the menu event
	         case Switch_ID:
	        //	 cmd.EventInject(cmd.getSwitchCmd());
	            return true; // consume the menu event
	         case VolumeUp_ID:
	        
	            return true; // consume the menu event
	         case Menu_ID:
	        	
	            return true; // consume the menu event
	         case Home_ID:
	        	
	            return true; // consume the menu event
	         case Back_ID:
	        	
	            return true; // consume the menu event	        
	           
	      } // end switch
	      
	      return super.onOptionsItemSelected(item); // call super's method
	   } // end method onOptio

	 @Override
	   public boolean onCreateOptionsMenu(Menu menu) 
	   {
	      super.onCreateOptionsMenu(menu); // call super's method
	      
	      setIconEnable(menu, true); 
	      // add options to menu
	      menu.add(Menu.NONE, VolumeDown_ID, Menu.NONE,R.string.volumeDown).setIcon(R.drawable.down);
	      menu.add(Menu.NONE, Switch_ID, Menu.NONE,R.string.Switch).setIcon(R.drawable.sw);
	      menu.add(Menu.NONE, VolumeUp_ID, Menu.NONE,R.string.volumeUp).setIcon(R.drawable.up);
	      menu.add(Menu.NONE, Menu_ID, Menu.NONE,R.string.menu).setIcon(R.drawable.menu);
	      menu.add(Menu.NONE, Home_ID, Menu.NONE,R.string.home).setIcon(R.drawable.home);
	      menu.add(Menu.NONE, Back_ID, Menu.NONE,R.string.back).setIcon(R.drawable.back);


	      return true; // options menu creation was handled
	   } // end onCreateOptionsMenu
                
	
	 private void setIconEnable(Menu menu, boolean enable)  
	    {  
	        try   
	        {  
	            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");  
	            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);  
	            m.setAccessible(true);  
	              
	            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)  
	            m.invoke(menu, enable);  
	              
	        } catch (Exception e)   
	        {  
	            e.printStackTrace();  
	        }  
	    } 

}
