package us.mifeng.liaotian;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;


/**
 * Created by shido on 2017/7/15.
 */

public class ConversationActivity extends FragmentActivity {
    private String token1 = "RwGEWDvq+Q3G9NlykB2bwOCjDzwCQ7LKLVF25snrMWsoXwkZcCsyRuXbjCT1wLBjouP7kO94b54bf/52MSSKNw==";
    private TextView mTitle;
    private RelativeLayout mBack;
    private String mTargetId;
    private Conversation.ConversationType mConversationType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        Intent intent = getIntent();
        setActionBar();
        getIntentDate(intent);
        isReconnect(intent);
    }

    private void isReconnect(Intent intent) {
        //程序切到后台，收到消息后点击进入,会执行这里
        if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {
            reconnect(token1);
        } else {
            enterFragment(mConversationType, mTargetId);
        }
    }

    private void reconnect(String token1) {

        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token1, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {

                    enterFragment(mConversationType, mTargetId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }



    private void getIntentDate(Intent intent) {
        mTargetId = intent.getData().getQueryParameter("targetId");
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

        enterFragment(mConversationType, mTargetId);
        setActionBarTitle(mTargetId);
    }
    /**
     * 设置 actionbar title
     */
    private void setActionBarTitle(String targetid) {
        mTitle.setText(targetid);
    }


    private void setActionBar() {
        mTitle = (TextView) findViewById(R.id.txt1);
        mBack = (RelativeLayout) findViewById(R.id.back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
