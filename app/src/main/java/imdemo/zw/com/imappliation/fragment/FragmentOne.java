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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);

        return view;
    }

}
