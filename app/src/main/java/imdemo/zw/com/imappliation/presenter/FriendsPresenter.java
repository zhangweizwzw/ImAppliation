package imdemo.zw.com.imappliation.presenter;

import android.support.v4.app.FragmentActivity;

import java.util.List;

import imdemo.zw.com.imappliation.model.FriendsLoadData;
import imdemo.zw.com.imappliation.model.FriendsModel;
import imdemo.zw.com.imappliation.view.FriendsView;

/**
 * Created by admin on 2017/2/17.
 */

public class FriendsPresenter {
    //view
    FriendsView friendsView;
    //model
    FriendsModel friendsModel=new FriendsLoadData();

    public FriendsPresenter(FriendsView friendsView) {
        super();
        this.friendsView = friendsView;
    }

    /**
     * 绑定view和model
     */
    public void fetch(){
        if(friendsModel!=null){
            friendsModel.LoadFriends(new FriendsModel.FriendsLoadListener() {
                @Override
                public void onComplete(List<String> friends) {
                    friendsView.showFriends(friends);
                }

                @Override
                public void onError(String s) {

                }
            });
        }

    }
}
