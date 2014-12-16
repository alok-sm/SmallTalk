package com.example.smalltalk;

import java.net.URI;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;


public class customSimpleAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final String[] title;
  private final String[] desc;
  private final String[] url;
  public customSimpleAdapter(Context context, String[] title1,String[] desc,String[] url) {
    super(context, R.layout.listview_layout, title1);
    this.context = context;
    this.title = title1;
    this.desc=desc;
    this.url=url;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    DisplayImageOptions options = new DisplayImageOptions.Builder()
    .cacheInMemory(true) // default
    .cacheOnDisk(true)
    .build();
    View rowView = inflater.inflate(R.layout.listview_layout, parent, false);
    TextView t1 = (TextView) rowView.findViewById(R.id.textView1);
    TextView t2 = (TextView) rowView.findViewById(R.id.textView2);
    ImageView iv = (ImageView) rowView.findViewById(R.id.imageView1);
    t1.setText(title[position]);
    t2.setText(desc[position].replace("UTC", ""));
	ImageLoader.getInstance().displayImage(url[position], iv,options);
    // Change the icon for Windows and iPhone
    return rowView;
  }
}