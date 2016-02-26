package com.ldp.zydictionary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

import com.ldp.zydictionary.fragment.TabFragment;
import com.ldp.zydictionary.fragment.YCFragment;
import com.ldp.zydictionary.view.ChangeColorIconWithText;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static String tag = "Rxjava";
    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();

    private String[] mTitles = new String[]{
         "Second Fragement","Third Fragement","Fourth Fragement"
    };

    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOverflowButtonAlways();

        initView();

        initDatas();

        mViewPager.setAdapter(mAdapter);

        initEvent();

        practiceRxjava();
    }

    //观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onNext(String s) {
            Log.d(tag, "Item: " + s);
        }

        @Override
        public void onCompleted() {
            Log.d(tag, "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.d(tag, "Error!");
        }
    };
//    被观察者
//    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
//        @Override
//        public void call(Subscriber<? super String> subscriber) {
//            subscriber.onNext("Hello");
//            subscriber.onNext("Hi");
//            subscriber.onNext("Aloha");
//            subscriber.onCompleted();
//        }
//    });

//    //等效以上写法
//    Observable observable = Observable.just("Hello", "Hi", "Aloha");

    //等效以上写法
    String[] words = {"Hello", "Hi", "Aloha"};
    Observable observable = Observable.from(words);

    /**
     * 练习Rxjava
     */
    private void practiceRxjava() {
//        1.组合写法：
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("hello");
//            }
//        }).subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d("rx", s);
//            }
//        });

//        2.分离写法：
        observable.subscribe(observer);
    }

    /**
     * 初始化所有事件
     */
    private void initEvent()
    {
        mViewPager.setOnPageChangeListener(this);
    }


    private void initView()
    {
        mViewPager = (ViewPager)findViewById(R.id.id_viewpager);

        ChangeColorIconWithText one = (ChangeColorIconWithText)findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText)findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText)findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);
        ChangeColorIconWithText four = (ChangeColorIconWithText)findViewById(R.id.id_indicator_four);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }

    private void initDatas() {
        YCFragment ycFragment = new YCFragment();
        Bundle bundle = new Bundle();
        bundle.putString(YCFragment.TITLE,"药材页面");
        ycFragment.setArguments(bundle);
        mTabs.add(ycFragment);

        for(String title:mTitles){
            TabFragment tabFragment = new TabFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putString(TabFragment.TITLE,title);
            tabFragment.setArguments(bundle1);

            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mTabs.get(i);
            }

            @Override
            public int getCount() {
                return mTabs.size();
            }
        };
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    //如果ouverflowbutton没显示，可以主动的反射显示出来
    private void setOverflowButtonAlways()
    {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 设置menu显示icon
//     * @param featureId
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if(featureId != Window.FEATURE_ACTION_BAR && menu!=null)
//        {
//            if(menu.getClass().getSimpleName().equals("MenuBuilder"))
//            {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu,true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return super.onMenuOpened(featureId, menu);
//    }

    @Override
    public void onClick(View v)
    {
        resetOtherTabs();

        switch (v.getId())
        {
            case R.id.id_indicator_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1,false);
                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2,false);
                break;
            case R.id.id_indicator_four:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3,false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs()
    {
        for(int i=0;i<mTabIndicators.size();i++)
        {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
//        Log.e("TAG","postion="+position+",positionOffset="+positionOffset);

        if(positionOffset>0)
        {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position+1);
            left.setIconAlpha(1-positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
