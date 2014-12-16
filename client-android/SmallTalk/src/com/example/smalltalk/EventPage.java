package com.example.smalltalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EventPage extends Activity {
	TextView text,description,date;
	ImageView iv;
	Button register;
	String possibleEmail;
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		getActionBar().setTitle("Event Details");
	     DisplayImageOptions options = new DisplayImageOptions.Builder()
	        .cacheInMemory(true) // default
	        .cacheOnDisk(true)
	        .build();

	     getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00a1e2));
	    Bundle extras=getIntent().getExtras();
	    String title,desc,url,day;
	    title=extras.getString("title");
	    desc=extras.getString("desc");
	    url=extras.getString("url");
	    day=extras.getString("date");
	    id=extras.getInt("id");
		iv=(ImageView)findViewById(R.id.imageView1);
		text = (TextView)findViewById(R.id.textView1);
		description = (TextView)findViewById(R.id.textView2);
		date = (TextView)findViewById(R.id.textView3);
		register = (Button)findViewById(R.id.imageButton1);
		register.setBackgroundColor(Color.rgb(0x00, 0xa1, 0xe2));
		ImageLoader.getInstance().displayImage(url, iv, options);
		text.setText(title);
		description.setText(desc);
		String dayArr = day.replace("UTC", "");
		date.setText("Date:"+dayArr);
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; 
		Account[] accounts = AccountManager.get(getBaseContext()).getAccounts();
		for (Account account : accounts) {
		if (emailPattern.matcher(account.name).matches()) {
			possibleEmail = account.name;
			}
		}
		System.out.println(possibleEmail);
		String resArr="1,2,3;lfsfjk,two,title;2014-07-09 16:09:00 UTC,2014-07-10 16:28:00 UTC,2014-07-11 18:44:00 UTC;dugfugfu,sfghskgjb,description;adfbksjfbkj,sfgsgjb,www.example.com";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); 
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_page, menu);
		return true;
	}
	public void regClick(View v){
    	Log.e("test", "http://smaltalk.herokuapp.com/match?eventid="+(id+1)+"&email="+possibleEmail);
		 try {
			 	
	        	String resArr = getResponseText("http://smaltalk.herokuapp.com/match?eventid="+(id+1)+"&email="+possibleEmail);
	        	Log.e("test", resArr);
	        	String[] res=resArr.split(";");	        	
	        	final String[] emails=res[0].split(",");
	        	final String[] names=res[1].split(",");
	        	final String[] images=res[2].split(",");
	        	final String[] topics=res[3].split("#");//{"test1,test2,test3,test4,test5","test1,test2,test3","test1,test2,test3","test1,test2,test3"};
	        	Log.e("test", "HelloKitty");
	        	Log.e("test", "HelloKitty");
	        	Intent i=new Intent(getApplicationContext(),People.class);
				i.putExtra("names", names);
				i.putExtra("emails", emails);
				i.putExtra("images", images);
				i.putExtra("topics",topics);
				startActivity(i);
	        	//Log.e("test",getResponseText("http://teqip-pesit.herokuapp.com/events/2.json"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
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


}
