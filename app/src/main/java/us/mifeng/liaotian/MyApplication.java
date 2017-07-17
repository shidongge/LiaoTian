package us.mifeng.liaotian;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by shido on 2017/7/15.
 */

public class MyApplication extends Application {
    //{"code":200,"userId":"10000","token":"RwGEWDvq+Q3G9NlykB2bwOCjDzwCQ7LKLVF25snrMWsoXwkZcCsyRuXbjCT1wLBjouP7kO94b54bf/52MSSKNw=="}
    //{"code":200,"userId":"10086","token":"Y9Au5NK6VaKk6Z4BV6aaucqTUertap58pXdG8Xbrv6GLONjohvtFl/ogMXqJWwop5jLppIOFdHGRMzmVAvYUCQ=="}

    //如果这里的token值选第一个 ，那么在mainActivity里面就要写第二个userID 反之选第二个一样
    private String token1 = "RwGEWDvq+Q3G9NlykB2bwOCjDzwCQ7LKLVF25snrMWsoXwkZcCsyRuXbjCT1wLBjouP7kO94b54bf/52MSSKNw==";
    private String token2 = "Y9Au5NK6VaKk6Z4BV6aaucqTUertap58pXdG8Xbrv6GLONjohvtFl/ogMXqJWwop5jLppIOFdHGRMzmVAvYUCQ==";
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);

        connect(token1);

    }


    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                            2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.e(TAG, "onSuccess: "+"成功");
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.e(TAG, "onError: "+"失败" );
                }
            });
        }
    }
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
