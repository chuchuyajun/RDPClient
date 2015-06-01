package edu.cqupt.client;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RemoteView extends View 
{
	  
	 private Bitmap bitmap; 
	 
	 onRemoteViewClickCallback viewClickCallback;
	
	   public static interface onRemoteViewClickCallback {
	        public void onViewClickCallback(float x,float y);
	    }   
	   
	   public void addCallBack(onRemoteViewClickCallback viewClickCallback){
		   this.viewClickCallback = viewClickCallback;
	   }
	   
	   
	   public void addBitmap(Bitmap bitmap){
		   this.bitmap = bitmap;
		   invalidate(); 
	   }

	   public RemoteView(Context context, AttributeSet attrs) 
	   {
	      super(context, attrs);   
	   } 

	   @Override
	   public void onSizeChanged(int w, int h, int oldW, int oldH)
	   {
	      bitmap = Bitmap.createBitmap(getWidth(), getHeight(),Bitmap.Config.ARGB_8888);
	      bitmap.eraseColor(Color.WHITE); // erase the BitMap with white
	   } 
	   
	
	   @Override
	   protected void onDraw(Canvas canvas) 
	   {
	      canvas.drawBitmap(bitmap, 0, 0, null);	    
	   } 
	   @Override
	   public boolean onTouchEvent(MotionEvent event) 
	   {	   
	      viewClickCallback.onViewClickCallback(event.getX() ,event.getY() );	
	      return true; 
	   } 	  
	  
} 
