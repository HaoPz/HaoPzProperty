package com.haopz.haopzalipaytest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import kr.co.namee.permissiongen.PermissionGen;

public class MainActivity extends AppCompatActivity {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLo+4bRxywbW7XWlLlkC9/fTSrzw9o1E5PeC+bk3BujRGhTMT7d0OOv/kM6TCZi6pCZPFTsJX/e6v/zeoEKT9Jx+6pR+t+eRwEIfgdES80qGQiRvhA3q2WOHH/TPFb28ObVHNcSwd/h2xosGnaPkFBLAmf8uRh23Sv+jHZj0zSY8nbR2NfzxL4YBPnbKGtxCw3Sq7BL3ylxysJDrQqehH35WIGl46GjKCEMGwDbUelOSCOH/wN1WPOsz6EPD0xJb9outsuDH/5HCWI3YqY5vToV52oXoAS1MjSTZJjC/htJIB+pU6D9ZMsf5jVtZ6vsca4NftxgC0CDoIp25pfRQIlAgMBAAECggEAFf7NW6iY4UVrhypoElbi1G8RY9qqr7V5XFUvEo9nz1ITK9ge0wlXL94XSaxVhmoUeH7T8H3JMsP1NMbnjJ4+pM2ET0/PzU0gV5pOLa3uRJodo7SQGA+7QgVEF5W0EucL6aSkwy3iUXkwX1Eia0kRGEXqgjJNPQjHWYzMW1uSGzV2Mk81P2SttCIIBwbOonkadPZOlVCV2d8NwMkINp2rrstNNs6ZUZJaf4Um+12ae29Xy4/YD8wNc6gQdw5STyO1TgwtgXhbbPlRxzXsxbBaCQhaoZfpZx0GC8hsZuLVXjWnXVli6kfDYGR81S1j7q4f3q7520bzwOhw+iiiKupO4QKBgQDhcbUVKYIE51DOvGmx80oQBjjPipvlGib+apZ5g1sJ8vDgkDmPsuKOaUiPAjRsq2f6DskR0FW5RAVnu4dSJMEmymvIs0ptjUSnL8VfyaOclwymI4r2PYIXiJbxjPvws5RKN11XD0IycgheVoDPHRMvgn5G7g7JOFJ0ekDQwP7xeQKBgQCekRD3B1u7ycs+GU8gmaym9xh6z7seEZFBLm7hQtycujYZTcz3wT8vcgiC2PvN1NiVmWmGCVGbuBkXdPDl2p72Q/RE9EbMetn6xrabwPnMSvv3MqEF4JI5PaZBoAtpSHRfkZXHgLx2eSYH8Kf5gY5u0SlYdAwo2CNRvosU60/3DQKBgDCMTQOWobIuof8zIcu5aYyT2m4PGk022YjWKTtXpyY4U9j4Ff0094bVc1OglOuF6ek2f+Q/KgaweD2Da7urwJB67MUWnwOCdXNPXUCoanxRJt8sLkOisT4iIuc3hhWqhleJHqed2JKGbfjqxehUCYqknk7l+vcoEtGUdhq+ROh5AoGBAIYJrKSU9MdmCkchD9FH9jlhcLEH/m2pffbycEo8wc4+XlK/gH8CPQ43P9iT4S8aOEclWp0yLWkLkzJP6P7z8iHMSaUsNNjPFIo3Bc/drYsy65EHnDQipJh8c5jP2DWUSMMshscRHL6tJCfcF7IDAUrNxjx+RHUWV6zW/CyfyYXVAoGAdKwsJS0CKfOZqgWXloL86yHYACqdrENzI4Q74vXIaWKGbGkCb20QXwuszNQnB5mVQp95HdjOT9Tj8mIac/wElVASdBCGzdIZDbzZGxwMXS6KDOAioYQW1fGZzytBnM4s8eIuSFsafg2zJoqK+k6muG/NFrGl2FLOuuDrz8n7oUk=";
    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018040302495111";
    private static final int REQUEST_PERMISSION_ARRAY_FLAG = 100;
    private static final String TAG = "context instanceOf Activity ->";

    public String CALL_PERMISSION = Manifest.permission.CALL_PHONE;
    public String[] permissionArray = {Manifest.permission.CALL_PHONE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    Log.d(MainActivity.this.getClass().getSimpleName(), msg.toString());
                    @SuppressWarnings("unchecked")
                    PayResult payResult = (PayResult) msg.obj;
//                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(MainActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(MainActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.startPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPay();
            }
        });
        findViewById(R.id.callSomeOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSomeOne();
            }
        });

//        PermissionGen.needPermission();

    }
    /**
     * 打電話
     * */
    @TargetApi(23)
    public void callSomeOne(){
        // 拍照权限

        if (ContextCompat.checkSelfPermission(MainActivity.this, permissionArray[0]) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            Log.d(getClass().getSimpleName(), "    打电话权限未申请，正在申请");
//            ActivityCompat.requestPermissions(MainActivity.this, permissionArray, REQUEST_PERMISSION_ARRAY_FLAG);
            MainActivity.this.requestPermissions(permissionArray, REQUEST_PERMISSION_ARRAY_FLAG);
            // requestPermissions(permissionArray, REQUEST_PERMISSION_ARRAY_FLAG); // 23 up
        } else {
            // 执行动作
            Log.d(getClass().getSimpleName(), "    打电话权限已经存在。执行动作");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_ARRAY_FLAG:// 打电话,
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(getClass().getSimpleName(), "grantResults length -> " + grantResults.length + "    打电话权限申请成功");
                    Log.d(getClass().getSimpleName(), "    执行打电话动作");
                } else {
                    Log.d(getClass().getSimpleName(), "grantResults length -> " + grantResults.length + "     打电话权限申请失败");
                }
                break;
        }
    }

    private void startPay() {

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

//        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String privateKey = RSA2_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> stringStringMap = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = stringStringMap.toString();
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
