package com.example.smalltalk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

@SuppressLint("ValidFragment")
public class Frag_activity extends Fragment {
    String name,email,url;
    String topics[]={" "};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View rootView = inflater.inflate(R.layout.frag,
				container, false);
    	ImageView iv=(ImageView) rootView.findViewById(R.id.imageView1);
    	TextView nameField=(TextView) rootView.findViewById(R.id.textView1);
    	TextView topicsField=(TextView) rootView.findViewById(R.id.textView2);
    	nameField.setText(name);
	     DisplayImageOptions options = new DisplayImageOptions.Builder()
	        .cacheInMemory(true) // default
	        .cacheOnDisk(true)
	        .build();
    	String fullTopic="";
    	for(int i=0;i<topics.length;i++)
    		fullTopic+=topics[i]+"\n";
    	topicsField.setText(fullTopic);
    	ImageLoader.getInstance().displayImage(url, iv,options);
    	iv.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setData(Uri.parse("mailto:"));
				emailIntent.setType("text/plain");
				emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{email});
				startActivity(emailIntent);
				return true;
			}
		});
    	iv.setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	// TODO Auto-generated method stub
    	    	
    	    	Toast.makeText(getActivity().getApplicationContext(), "Long Press To Connect.", Toast.LENGTH_SHORT).show();
    	    }
    	});
    	return rootView;
    }
    public Frag_activity(String name,String email,String topics[],String url){
    	this.name=name;
    	this.email=email;
    	this.topics=topics;
    	this.url=url;
    }
}