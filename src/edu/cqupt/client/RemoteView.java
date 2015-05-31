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
	  

	   private Bitmap bitmap; // drawing area for display or saving
	   
	   public void addBitmap(Bitmap bitmap){
		   this.bitmap = bitmap;
		   invalidate(); 
	   }

	   // DoodleView constructor initializes the DoodleView
	   public RemoteView(Context context, AttributeSet attrs) 
	   {
	      super(context, attrs); // pass context to View's constructor     
	   } // end DoodleView constructor

	   // Method onSizeChanged creates BitMap and Canvas after app displays
	   @Override
	   public void onSizeChanged(int w, int h, int oldW, int oldH)
	   {
	      bitmap = Bitmap.createBitmap(getWidth(), getHeight(),Bitmap.Config.ARGB_8888);
	      bitmap.eraseColor(Color.WHITE); // erase the BitMap with white
	   } // end method onSizeChanged
	   
	   // clear the painting
	
	   @Override
	   protected void onDraw(Canvas canvas) 
	   {
	      // draw the background screen
	      canvas.drawBitmap(bitmap, 0, 0, null);
	    
	   } // end method onDraw

	   // handle touch event
	   @Override
	   public boolean onTouchEvent(MotionEvent event) 
	   {
	      // get the event type and the ID of the pointer that caused the event
	      int action = event.getActionMasked(); // event type 
	      int actionIndex = event.getActionIndex(); // pointer (i.e., finger)
  
	
	      return true; 
	   } 

	   

	  
	  
} 
