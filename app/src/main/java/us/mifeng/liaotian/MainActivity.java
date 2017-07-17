package us.mifeng.liaotian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.rong.imkit.RongIM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance()!=null){
                    RongIM.getInstance().startPrivateChat(MainActivity.this,"20000","呵呵哒");
                }
            }
        });
    }
//    public void liaotian(View view){
//        if (RongIM.getInstance()!=null){
//            RongIM.getInstance().startPrivateChat(MainActivity.this, "10000", "title");
//        }
//    }

}
