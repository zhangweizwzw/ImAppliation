package imdemo.zw.com.imappliation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;
import java.util.regex.Pattern;

import imdemo.zw.com.imappliation.util.MyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG="MainActivity";
    private Button account_register_button,account_sign_in_button;
    private EditText account,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }

    private void InitView() {
        account_register_button= (Button) findViewById(R.id.account_register_button);
        account_sign_in_button= (Button) findViewById(R.id.account_sign_in_button);
        account_register_button.setOnClickListener(this);
        account_sign_in_button.setOnClickListener(this);

        account= (EditText) findViewById(R.id.account);
        password= (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.account_register_button:
                GoRegister();
                break;
            case R.id.account_sign_in_button:
                GoLogin();
                break;
        }
    }

    /**
     * 注册
     */
    private void GoRegister() {
        if(isOk()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(getUserName(),getPassWord());
                        System.out.println("注册成功");
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                      System.out.println("注册失败："+e.toString());
                    }
                }
            }).start();
            MyToast.show(this,"注册");
        }
    }

    /**
     * 登录
     */
    private void GoLogin() {
        if(isOk()){
            EMClient.getInstance().login(getUserName(),getPassWord(),new EMCallBack(){
                @Override
                public void onSuccess() {
                    startActivity(new Intent(MainActivity.this,LoginSuccessActivity.class));
                    finish();
                }

                @Override
                public void onError(int code, String error) {
                    System.out.println("登录失败："+error);
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        }
    }

    /**
     * 验证账号
     * @param name
     * @return
     */
    private boolean isAccountValid(String name) {
        Pattern pattern= Pattern.compile("^(13[0-9]|14[5|7]|15\\d|17[6|7]|18[\\d])\\d{8}$");
        return pattern.matcher(name).matches();
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        if(password.length()<=6 && password.length()!=0){
            return true;
        }
        return false;
    }

    public String getUserName(){
        return account.getText().toString().trim();
    }

    public String getPassWord(){
        return password.getText().toString().trim();
    }

    /**
     * 验证
     */
    public boolean isOk(){
        if(isAccountValid(getUserName())&&isPasswordValid(getPassWord())){
            return true;
        }else{
            MyToast.show(this,"请输入正确的账号和密码");
        }

        return false;
    }
}
