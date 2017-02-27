package imdemo.zw.com.imappliation.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import imdemo.zw.com.imappliation.NewfridensMsgActivity;
import imdemo.zw.com.imappliation.R;
import imdemo.zw.com.imappliation.adapter.SortAdapter;
import imdemo.zw.com.imappliation.bean.SortModel;
import imdemo.zw.com.imappliation.util.CharacterParser;
import imdemo.zw.com.imappliation.AddFridendActivity;
import imdemo.zw.com.imappliation.widget.ClearEditText;
import imdemo.zw.com.imappliation.widget.PinyinComparator;
import imdemo.zw.com.imappliation.widget.SideBar;

/**
 * Created by admin on 2017/2/16.
 */

public class FragmentTwo extends Fragment implements View.OnClickListener {
    private final String TAG="FragmentTwo";
    private View view;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    //汉字转换成拼音的类
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    //根据拼音来排列ListView里面的数据类
    private PinyinComparator pinyinComparator;
    private List<String> mfri=new ArrayList<String>();
    private TextView title_center;
    private ImageView title_right;

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    filledData(mfri);
                    break;
                case 1:
                    goColl();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        initViews();
        return view;
    }

    private void initViews() {
        sortListView = (ListView) view.findViewById(R.id.country_lvcountry);
        title_right= (ImageView) view.findViewById(R.id.title_right);
        title_center= (TextView) view.findViewById(R.id.title_center);
        title_center.setText("我的好友");
        title_right.setVisibility(View.VISIBLE);
        title_right.setImageResource(R.mipmap.add_fridends);
        title_right.setOnClickListener(this);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.fridendslisthead_layout, null);
        headerView.setPadding(0,10, 0, 10);
        sortListView.addHeaderView(headerView);
        RelativeLayout nfriRe= (RelativeLayout) headerView.findViewById(R.id.nfriRe);
        nfriRe.setOnClickListener(this);

        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        dialog = (TextView) view.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }
            }
        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                Toast.makeText(getActivity(),position+"" , Toast.LENGTH_SHORT).show();
            }
        });

        //获取好友
        mfri=getFriends();
    }

    private void goColl() {
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortAdapter(getActivity(), SourceDateList);
        sortListView.setAdapter(adapter);
        mClearEditText = (ClearEditText)view.findViewById(R.id.filter_edit);
        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private List getFriends(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mfri= EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Message msg = new Message();
                    msg.what=0;
                    mHandler.sendMessage(msg);

                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return mfri;
    }

    /**
     * 为ListView填充数据
     * @return
     */
    private void filledData(List mlist){
        List<SortModel> mSortList = new ArrayList<SortModel>();
        for(int i=0; i<mlist.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(mfri.get(i));
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(mlist.get(i).toString());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
            System.out.println("aaaa"+mSortList.size());
        }
        SourceDateList=mSortList;
        Message msg = new Message();
        msg.what=1;
        mHandler.sendMessage(msg);
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     * @param filterStr
     */
    private void filterData(String filterStr){
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList = SourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel : SourceDateList){
                String name = sortModel.getName();
                if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nfriRe:
                startActivity(new Intent(getActivity(), NewfridensMsgActivity.class));
                break;
            case R.id.title_right:
                startActivity(new Intent(getActivity(), AddFridendActivity.class));
                break;
        }
    }
}
