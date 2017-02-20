package imdemo.zw.com.imappliation.model;

import java.util.List;

/**
 * Created by admin on 2017/2/17.
 */

public interface FriendsModel {
    void LoadFriends(FriendsLoadListener listener);

    interface FriendsLoadListener{
        void onComplete(List<String> friends);

        void onError(String s);
    }
}
