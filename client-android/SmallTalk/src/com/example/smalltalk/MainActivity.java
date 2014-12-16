package com.example.smalltalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	     getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00a1e2));
	     ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
	     .build();
	     ImageLoader.getInstance().init(config);
        //final String[] title={"Event 1","Event 2","Event 3"};
		//String[] desc={"21st july 9PM","22nd july 10pm","23 may 11AM"}, url={"http://careers.queensu.ca/events/events.jpg","http://careers.queensu.ca/events/events.jpg","http://careers.queensu.ca/events/events.jpg"};     
        ListView v = (ListView) findViewById(R.id.listView1);
        Log.e("test", "hello1");
        String resArr="1,2,3;lfsfjk,two,title;2014-07-09 16:09:00 UTC,2014-07-10 16:28:00 UTC,2014-07-11 18:44:00 UTC;dugfugfu,sfghskgjb,description;adfbksjfbkj,sfgsgjb,www.example.com";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
        try {
        	resArr = getResponseText("http://smaltalk.herokuapp.com/events.json");
			//Log.e("test",getResponseText("http://teqip-pesit.herokuapp.com/events/2.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String[] res=resArr.split(";");
        int[] ids=new int[res[0].split(",").length];
        for(int i=0;i<res[0].split(",").length;i++)
        {
        	ids[i]=Integer.parseInt(res[0].split(",")[i]);
        	Log.e("test", ""+ids[i]);
        }
        final String[] title=res[1].split(",");
        final String[] desc=res[2].split(",");
        final String[] desc2=res[3].split(",");
        final String[] url=res[4].split(",");
        customSimpleAdapter s1=new customSimpleAdapter(getApplicationContext(), title, desc, url);
        v.setAdapter(s1);
        v.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.e("test",""+(arg2+1));
				Intent i=new Intent(getApplicationContext(),EventPage.class);
				i.putExtra("title", title[arg2]);
				i.putExtra("date", desc[arg2]);
				i.putExtra("url", url[arg2]);
				i.putExtra("desc",desc2[arg2]);
				i.putExtra("id",""+(arg2+1));
				startActivity(i);
				// TODO Auto-generated method stub	
			}
        });
        Log.e("test", "hello2");

	}

	private String getResponseText(String stringUrl) throws IOException
	{
	    StringBuilder response  = new StringBuilder();

	    URL url = new URL(stringUrl);
	    HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
	    if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK)
	    {
	        BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()),8192);
	        String strLine = null;
	        while ((strLine = input.readLine()) != null)
	        {
	            response.append(strLine);
	        }
	        input.close();
	    }
	    return response.toString();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
