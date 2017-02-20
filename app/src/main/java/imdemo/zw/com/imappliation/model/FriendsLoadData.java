package imdemo.zw.com.imappliation.model;

import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;


/**
 * Created by admin on 2017/2/17.
 */

public class FriendsLoadData implements FriendsModel {
    @Override
    public void LoadFriends(final FriendsLoadListener listener) {
        System.out.println("000000000000000000");

//        RxAndroid(listener);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    System.out.println("好友数为："+usernames.size());
                    listener.onComplete(usernames);
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    System.out.print("错误为"+e.toString());
                    listener.onError(e.toString());
                }
            }
        }).start();
    }

//    private void RxAndroid(final FriendsLoadListener listener) {
//        Observable<String> observable = Observable.just("");
//        observable.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                try {
//                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
//                    System.out.println("好友数为：" + usernames.size());
//                    listener.onComplete(usernames);
//                } catch (HyphenateException e) {
//                    e.printStackTrace();
//                    System.out.print("错误为" + e.toString());
//                    listener.onError(e.toString());
//                }
//            }
//        });
//    }

}
