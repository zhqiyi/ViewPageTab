package com.example.viewpagertab;

import java.util.Locale;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnDrawListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{

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
	private TextView tab1,tab2,tab3;

	private TextView tabLine;
	private float tabHeight,tabWidth;
	private int tabTotale = 3;
	private int scorllState = 0 ;
	private int screenWidth;
	float temp = 0;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();


		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		tabLine = (TextView) findViewById(R.id.tab_line);
		tab1 = (TextView) findViewById(R.id.tab1);
		tab2 = (TextView) findViewById(R.id.tab2);
		tab3 = (TextView) findViewById(R.id.tab3);
		
		tab1.setOnClickListener(this);
		tab2.setOnClickListener(this);
		tab3.setOnClickListener(this);
		

		tab1.getViewTreeObserver().addOnDrawListener(new OnDrawListener() {

			@Override
			public void onDraw() {
				// TODO Auto-generated method stub
				tabHeight = tab1.getHeight();

			}
		});



		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
		.setOnPageChangeListener(new  OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				/**
				 * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。
				 * 其中三个参数的含义分别为：
				 * position: 当前页面，及你点击滑动的页面。
				 * positionOffset: 当前页面偏移的百分比。
				 * positionOffsetPixels: 当前页面偏移的像素位置。
				 */
				System.out.println("左滑为当前页面，右滑为目标页面: " + position);
				System.out.println("当前页面偏移的百分比: " + positionOffset);
				System.out.println("当前页面偏移的像素位置: " + positionOffsetPixels);
				System.out.println("----------------------------------------");

				if (positionOffsetPixels != 0 && position == 0) {
					tabLine.setTranslationX((float)positionOffsetPixels / 3);
					temp = tabLine.getX();
					System.out.println("temp: " + temp);
				}else if (positionOffsetPixels != 0 && position == 1) {
					tabLine.setTranslationX((float)positionOffsetPixels / 3 + temp);
				}

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				/**
				 * arg0 == 1表示手指按下正在滑动
				 * arg0 == 2表示手指抬起正在滑动
				 * arg0 == 0表示什么都没做
				 * 当页面开始滑动的时候，三种状态的变化顺序为（1，2，0）
				 */
				scorllState = state;
			}
		}); 



		// For each of the sections in the app, add a tab to the action bar.

	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setTabLine();
	}

	/**
	 * 设置TabLine
	 */
	public void setTabLine() {
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenWidth = metric.widthPixels;     // 屏幕宽度（像素）
		MarginLayoutParams layoutParams = new MarginLayoutParams(screenWidth / tabTotale, 10);
		layoutParams.topMargin = DensityUtil.dip2px(getApplicationContext(), 50);
		RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(layoutParams);
		tabLine.setLayoutParams(layoutParams2);
		tabWidth = screenWidth / tabTotale;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	public void startAnimation(View view,int offset){
		ValueAnimator animator = ObjectAnimator.ofFloat(view, "translationX", view.getX() , tabWidth * offset);
		animator.setDuration(300);
		animator.start();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tab1:
			mViewPager.setCurrentItem(0, true);
			
			break;
		case R.id.tab2:
			mViewPager.setCurrentItem(1, true);
			break;
		case R.id.tab3:
			mViewPager.setCurrentItem(2, true);
			break;
		default:
			break;
		}
	}

}
