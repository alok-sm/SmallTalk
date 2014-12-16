package com.example.smalltalk;

import java.util.Locale;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import java.util.Arrays;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class People extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
    String[] names,emails,urls,topicsFull;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people);
		getActionBar().setBackgroundDrawable(new ColorDrawable(0xff00a1e2));
		getActionBar().setTitle("People You Should Meet");
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());
	     ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
	        .memoryCacheSize(2 * 1024 * 1024)   
	     .diskCacheSize(50 * 1024 * 1024)
	        .diskCacheFileCount(100)
	     .build();
	     ImageLoader.getInstance().init(config);
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		  
		    Bundle extras=getIntent().getExtras();
		    names=extras.getStringArray("names");
		    emails=extras.getStringArray("emails");
		    urls=extras.getStringArray("images");
		    topicsFull=extras.getStringArray("topics");
		    Log.e("test",Arrays.toString(topicsFull));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			return new Frag_activity(names[position],emails[position],topicsFull[position].split(","),urls[position]);
		}

		@Override
		public int getCount() {
		    Bundle extras=getIntent().getExtras();
			String[] names2=extras.getStringArray("names");
			// Show 3 total pages.
			return names2.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
		    Bundle extras=getIntent().getExtras();
			String[] names2=extras.getStringArray("names");

			return names2[position];
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */


}
