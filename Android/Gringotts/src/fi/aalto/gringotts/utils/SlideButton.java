package fi.aalto.gringotts.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Slide to unlock,pay,etc
 * 
 * @author nguyenh5
 * @see http 
 *      ://stackoverflow.com/questions/14910226/how-to-make-slide-to-unlock-
 *      button -in-android
 */
public class SlideButton extends SeekBar {

	private Drawable thumb;
	private SlideButtonListener listener;

	public SlideButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setThumb(Drawable thumb) {
		super.setThumb(thumb);
		this.thumb = thumb;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (thumb.getBounds().contains((int) event.getX(),
					(int) event.getY())) {
				super.onTouchEvent(event);
			} else
				return false;
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getProgress() > 70)
				handleSlide();

			setProgress(0);
		} else
			super.onTouchEvent(event);

		return true;
	}

	private void handleSlide() {
		if (listener != null)
			listener.handleSlide();
	}

	public void setSlideButtonListener(SlideButtonListener listener) {
		this.listener = listener;
	}
}