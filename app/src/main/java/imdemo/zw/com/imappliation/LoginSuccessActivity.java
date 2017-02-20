package imdemo.zw.com.imappliation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

import imdemo.zw.com.imappliation.fragment.FragmentOne;
import imdemo.zw.com.imappliation.fragment.FragmentThree;
import imdemo.zw.com.imappliation.fragment.FragmentTwo;
import imdemo.zw.com.imappliation.radio.GradualRadioGroup;

public class LoginSuccessActivity extends AppCompatActivity {
    private final String TAG="LoginSuccessActivity";
    private ViewPager viewPager;
    private GradualRadioGroup gradualRadioGroup;
    private FragmentOne mFragmentOne;
    private FragmentTwo mFragmentTwo;
    private FragmentThree mFragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        InitView();
        InitData();
    }

    private void InitData(){
        List<Fragment> list = new ArrayList<>();
        mFragmentOne=new FragmentOne();
        mFragmentTwo=new FragmentTwo();
        mFragmentThree=new FragmentThree();
        list.add(mFragmentOne);
        list.add(mFragmentTwo);
        list.add(mFragmentThree);

        viewPager.setAdapter(new DemoPagerAdapter(getSupportFragmentManager(), list));
        gradualRadioGroup.setViewPager(viewPager);
    }

    private void InitView(){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        gradualRadioGroup = (GradualRadioGroup) findViewById(R.id.radiobar);
    }

    class DemoPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> mData;

        public DemoPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public DemoPagerAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            mData = data;
        }

        @Override
        public Fragment getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }
    }
}
