package fi.aalto.gringotts.adapters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.example.android.displayingbitmaps.util.ImageFetcher;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fi.aalto.gringotts.R;
import fi.aalto.gringotts.entities.*;

public class EventListAdapter extends ArrayAdapter<Event> {
	public static int RESOURCE_ID = R.layout.list_event_row;
	public static int THUMB_SIZE = 90;
	
	private List<Event> mEvents;
	private LayoutInflater mInflater;
	private ImageFetcher mImageFetcher;

	public EventListAdapter(FragmentActivity context, List<Event> _events) {
		super(context, RESOURCE_ID, _events);
		
		mEvents = _events;
		mInflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		mImageFetcher = ImageFetcher.createImageFetcher(context, THUMB_SIZE);
	}
	
	@Override
	public View getView (int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(RESOURCE_ID, parent, false);
		}
		TextView eventNameView = (TextView) convertView.findViewById(R.id.event_name);
		eventNameView.setText(mEvents.get(position).name);
		
		ImageView iconView = (ImageView) convertView.findViewById(R.id.event_icon);
		mImageFetcher.loadImage(mEvents.get(position).url, iconView);
		 
		return convertView;
	}
	
//	private Bitmap loadImage(String urlStr) {
//		try {
//			URL url = new URL(urlStr);
//			Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//			return bmp;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	@Override
    public void clear() {
        super.clear();
        mImageFetcher.closeCache();
    }

}