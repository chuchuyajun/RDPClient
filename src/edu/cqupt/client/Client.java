package edu.cqupt.client;

import edu.cqupt.client.RemoteView.onRemoteViewClickCallback;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

public class Client extends Activity{
	

    private PopupWindow pop;
	private View layout;
	DisplayMetrics metrics;
	int width;
	int height; 
	
	RemoteView remoteview;
	SocketThread socketThread;
  
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remoteview);
        
        remoteview = (RemoteView)findViewById(R.id.remoteView);
        		
    	metrics = getResources().getDisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		
		String data = width+","+height;
		
		SocketThread.onBitmapReceiveCallback ReceivetCallback = new SocketThread.onBitmapReceiveCallback(){

			@Override
			public void onReceived(Bitmap bitmap) {
				// TODO Auto-generated method stub
				remoteview.addBitmap(bitmap);
			}
			
		};
		
		onRemoteViewClickCallback onRemoteViewClickCallback = new RemoteView.onRemoteViewClickCallback(){

			@Override
			public void onViewClickCallback(float x, float y) {
				// TODO Auto-generated method stub
				String cmd = x / width + "," + y / height;
				socketThread.send(cmd);
			}
			
		};
           
	   socketThread = new SocketThread(ReceivetCallback);
	   socketThread.start();
        
       new UdpClient(data).start();
      
	}
	
	
	public void homeClick()
	{
		Toast.makeText(Client.this, "单击定制菜单.", Toast.LENGTH_LONG).show();
		pop.dismiss();
	};
	
	public void showMenu(){
		layout = getLayoutInflater().inflate(R.layout.menu_layout, null);
		pop = new PopupWindow(layout, getWindowManager().getDefaultDisplay().getWidth(),500);
		pop.showAtLocation(layout, Gravity.BOTTOM, 0, 0);	
			
		}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_MENU:
				layout = getLayoutInflater().inflate(R.layout.menu_layout, null);
				pop = new PopupWindow(layout, getWindowManager().getDefaultDisplay().getWidth(),500);
				pop.showAtLocation(layout, Gravity.BOTTOM, 0, 0);				
				return false;
			case KeyEvent.KEYCODE_BACK:	
				pop.dismiss();
				return false;
		}

		return super.onKeyDown(keyCode, event);
	}


}
