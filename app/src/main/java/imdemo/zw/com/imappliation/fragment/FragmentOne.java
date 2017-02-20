package imdemo.zw.com.imappliation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.adapter.message.EMAMessage;

import java.util.List;

import imdemo.zw.com.imappliation.R;
import imdemo.zw.com.imappliation.util.MyToast;

/**
 * Created by admin on 2017/2/16.
 */

public class FragmentOne extends Fragment {
    private View view;
    private TextView imess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);

        initView();

        EMClient.getInstance().chatManager().addMessageListener(msgListener);

        return view;
    }

    public void initView(){
        imess= (TextView) view.findViewById(R.id.imess);
    }


    EMMessageListener msgListener=new EMMessageListener() {
        //收到消息
        @Override
        public void onMessageReceived(List<EMMessage> messages) {
          for (int i=0;i<messages.size();i++){
              System.out.println(messages.get(i));
              MyToast.show(getActivity(),messages.get(i).toString());
          }
        }

        //收到透传消息
        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {

        }
        //收到已读回执
        @Override
        public void onMessageRead(List<EMMessage> messages) {

        }
        //收到已送达回执
        @Override
        public void onMessageDelivered(List<EMMessage> messages) {

        }
        //消息状态变动
        @Override
        public void onMessageChanged(EMMessage message, Object change) {

        }
    };
}
