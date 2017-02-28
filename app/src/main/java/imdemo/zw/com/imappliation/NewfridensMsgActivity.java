package imdemo.zw.com.imappliation;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import imdemo.zw.com.imappliation.adapter.DemolistAdapter;
import imdemo.zw.com.imappliation.model.FriendMsgBean;
import imdemo.zw.com.imappliation.util.Dateutil;
import imdemo.zw.com.imappliation.view.ListItemDecoration;

public class NewfridensMsgActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG="NewfridensMsgActivity";
    private List<FriendMsgBean> frilist=new ArrayList<FriendMsgBean>();
    private RecyclerView msglist;
    private TextView title_center;
    private ImageView title_left;
    private DemolistAdapter adapter;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfridens_msg);

        initView();
        getmsg();
    }

    private void initView() {
        title_center= (TextView) findViewById(R.id.title_center);
        title_center.setText("消息");
        title_left= (ImageView) findViewById(R.id.title_left);
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);

        msglist= (RecyclerView) findViewById(R.id.msglist);
        adapter=new DemolistAdapter(NewfridensMsgActivity.this,frilist);
        msglist.setAdapter(adapter);

        msglist.setLayoutManager(new LinearLayoutManager(this));
        // 设置item分
        msglist.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        // 设置item动画
        msglist.setItemAnimator(new DefaultItemAnimator());
        msglist.setHasFixedSize(true);


    }

    public void getmsg(){
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {

            @Override
            public void onContactAgreed(String username) {
                //好友请求被同意
                addfriToList(username,1);

            }

            @Override
            public void onContactRefused(String username) {
                //好友请求被拒绝
                addfriToList(username,2);
            }

            @Override
            public void onContactInvited(String username, String reason) {
                //收到好友邀请
                addfriToList(username,3);
            }

            @Override
            public void onContactDeleted(String username) {
                //被删除时回调此方法
                addfriToList(username,4);
            }


            @Override
            public void onContactAdded(String username) {
                //增加了联系人时回调此方法
                addfriToList(username,5);
            }
        });
    }

    private void addfriToList(String username,int type) {
        FriendMsgBean fribean=new FriendMsgBean();
        fribean.setUserDate(Dateutil.friendlyFormat(Dateutil.getDate()));
        fribean.setUserName(username);
        fribean.setType(type);
        frilist.add(fribean);

        Message message=new Message();
        message.what=0;
        handler.sendMessage(message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_left:
                finish();
                break;
        }
    }
}
