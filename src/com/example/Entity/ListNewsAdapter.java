package com.example.Entity;

import java.util.List;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.boccaccio.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListNewsAdapter extends BaseAdapter {
	protected ENews[] listNews;
	protected Context context;
	AQuery _aq;

	public ListNewsAdapter(Context context, ENews[] listNews) {
		this.context = context;
		this.listNews = listNews;
	}

	@Override
	public int getCount() {
		return this.listNews.length;
	}

	@Override
	public ENews getItem(int arg0) {
		return this.listNews[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int positon, View convertView, ViewGroup parent) {
		ENews data = getItem(positon);
		final ViewHolder holder;
		_aq = new AQuery(context);

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.news_list_item, parent,
					false);
			holder.image = (ImageView) convertView
					.findViewById(R.id.news_list_item_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.news_list_item_title);
			holder.content = (TextView) convertView
					.findViewById(R.id.news_list_item_content);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		//int loader = R.drawable.ic_launcher;

		String url = data.getResourceImg();
		Log.e("Log image", url);

		_aq.ajax(url, Bitmap.class, new AjaxCallback<Bitmap>() {

			@Override
			public void callback(String url, Bitmap object, AjaxStatus status) {

				holder.image.setImageBitmap(object);

			}
		});

		// ImageLoader imgLoader = new ImageLoader(context);

		// whenever you want to load an image from url
		// call DisplayImage function
		// url - image url to load
		// loader - loader image, will be displayed before getting image
		// image - ImageView
		// imgLoader.DisplayImage(data.getResourceImg(), loader, holder.image);
		// holder.image.setImageBitmap(this.downloadImage(data.getResourceImg()));
		holder.title.setText(data.getTitle());
		holder.content.setText(data.getDate());
		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView content;
		ImageView image;
	}

}
