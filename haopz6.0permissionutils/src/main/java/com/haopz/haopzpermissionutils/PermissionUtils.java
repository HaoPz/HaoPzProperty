package com.haopz.haopzpermissionutils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HaoPz on 2018/5/2.
 */

public class PermissionUtils {
    private static int REQUEST_PERMISSION_CODE = -1;
    private static String[] mRequestPermission;
    private static PermissionUtils permissionUtils;

    private PermissionUtils() {
    }

    public static PermissionUtils getInstance() {

        if (permissionUtils == null) {
            permissionUtils = new PermissionUtils();
        }

        return permissionUtils;
    }

    /**
     * 请求权限码
     */
    public PermissionUtils setRequestPermissionCode(int requestPermissionCode) {
        REQUEST_PERMISSION_CODE = requestPermissionCode;
        return permissionUtils;
    }

    /**
     * 请求权限
     */
    public void setRequestPermission(String[] requestPermission, Object object) {
        mRequestPermission = requestPermission;

        requestPermission(requestPermission, object);
    }

    /**
     * 请求权限
     */
    @TargetApi(23)
    public static void requestPermission(String[] requestPermission, Object object) {
        if (requestPermission.length <= 0) {
            return;
        }
        if (object instanceof Activity) {
            ((Activity) object).requestPermissions(requestPermission, REQUEST_PERMISSION_CODE);
        } else if(object instanceof Fragment){
            ((Fragment) object).requestPermissions(requestPermission, REQUEST_PERMISSION_CODE);
        } else {
            throw new IllegalArgumentException(object.getClass().getName() + "is no suooorted");
        }
    }

    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                  @NonNull int[] grantResults,
                                OnRequestPermissionResult onRequestPermissionResult) {
        if(requestCode == REQUEST_PERMISSION_CODE){
            int isHaveDeniedPermission = -1 ; // flag : 非-1 都是存在拒绝权限
            for(int i=0 ; i<grantResults.length ; i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    // 存在拒絕權限
                    isHaveDeniedPermission = 1;
                }
            }

            if(isHaveDeniedPermission == -1){
                onRequestPermissionResult.onRequestPerSuccess();
            }else{
                onRequestPermissionResult.onRequestPerFail();
            }
        }
    }

//    public static void getRequestPermissionResult(OnRequestPermissionResult onRequestPermissionResult){
//        mOnRequestPermissionResult = onRequestPermissionResult;
//    }

//    public static OnRequestPermissionResult mOnRequestPermissionResult;

    public interface OnRequestPermissionResult {
        void onRequestPerSuccess();

        void onRequestPerFail();
    }

    @Nullable
    private Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }
}
