package imdemo.zw.com.imappliation;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import org.w3c.dom.Text;

import imdemo.zw.com.imappliation.R;
import imdemo.zw.com.imappliation.util.SnackbarUtils;

public class AddFridendActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG="AddFridendActivity";
    private TextView title_center;
    private ImageView title_left;
    private EditText fri_name,fri_reason;
    private Button goadd;
    private CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fridend);

        initView();
    }

    private void initView() {
        title_left= (ImageView) findViewById(R.id.title_left);
        title_center= (TextView) findViewById(R.id.title_center);
        title_center.setText("加好友");
        title_left.setVisibility(View.VISIBLE);
        title_left.setImageResource(R.mipmap.back);
        title_left.setOnClickListener(this);
        fri_name= (EditText) findViewById(R.id.fri_name);
        fri_reason= (EditText) findViewById(R.id.fri_reason);
        goadd= (Button) findViewById(R.id.goadd);
        goadd.setOnClickListener(this);
        container= (CoordinatorLayout) findViewById(R.id.container);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.title_left:
                finish();
                break;
            case R.id.goadd:
                String friname=fri_name.getText().toString().trim();
                String resson=fri_reason.getText().toString().trim();
                if(friname.isEmpty()){
                    SnackbarUtils.longSnackbar(container,"好友名不能为空", getResources().getColor(R.color.white),getResources().getColor(R.color.title_bg)).show();
                }else{
                    goAddFridend(friname,resson);
                }
                break;
        }
    }

    public void goAddFridend(final String friname, final String resson){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().contactManager().addContact(friname, resson);
                    SnackbarUtils.longSnackbar(container,"请求已发送", getResources().getColor(R.color.white),getResources().getColor(R.color.title_bg)).show();
                    finish();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.i(TAG,"添加好友失败："+e.getMessage().toString());
                }
            }
        }).start();

    }

}
