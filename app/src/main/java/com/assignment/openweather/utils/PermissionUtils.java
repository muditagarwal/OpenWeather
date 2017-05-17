package com.assignment.openweather.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.assignment.openweather.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muditagarwal on 03/01/17.
 */

public class PermissionUtils {

    private static final int REQUEST_SYSTEM_PERMISSION_CODE = 111;

    public static List<String> checkPermissions(Activity activity, String[] requiredPermissions) {
        List<String> requestedPermissionList = new ArrayList<String>();
        if (Utils.isNotEmptyArray(requiredPermissions)) {
            for (String permission : requiredPermissions) {
                if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    requestedPermissionList.add(permission);
                }
            }
        }
        return requestedPermissionList;
    }

    public static List<String> checkAndRequestPermissions(Activity activity, String[] requiredPermissions) {
        List<String> requestedPermissionList = new ArrayList<String>();
        if (Utils.isNotEmptyArray(requiredPermissions)) {
            requestedPermissionList = checkPermissions(activity, requiredPermissions);

            if (Utils.isNotEmptyList(requestedPermissionList)) {
                requiredPermissions = requestedPermissionList.toArray(new String[requestedPermissionList.size()]);
                ActivityCompat.requestPermissions(activity, requiredPermissions, REQUEST_SYSTEM_PERMISSION_CODE);
            }
        }
        return requestedPermissionList;
    }

    public static void onRequestPermissionsResult(Activity activity, List<String> requestedPermissionList, String[] requiredPermissions, int requestCode, int[] grantResults) {
        if (requestCode == REQUEST_SYSTEM_PERMISSION_CODE) {
            boolean needToOpenSettings = false;
            boolean hasGrantedAllPermissions = true;
            for (int i = 0; i < grantResults.length; i++) {
                int result = grantResults[i];
                if (result != PackageManager.PERMISSION_GRANTED) {
                    hasGrantedAllPermissions = false;
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, requestedPermissionList.get(i))) {
                        needToOpenSettings = true;
                    }
                }
            }

            if (!hasGrantedAllPermissions) {
                if (activity instanceof PermissionGrantInterface) {
                    ((PermissionGrantInterface) activity).doSomethingOnPermissionNotGranted(needToOpenSettings);
                }
            } else {
                if (activity instanceof PermissionGrantInterface)
                    ((PermissionGrantInterface) activity).doSomethingOnPermissionGrant();
            }
        }
    }

    public static void showRationaleDialog(final Activity activity, final String[] requiredPermissions) {
        new AlertDialog.Builder(activity).setMessage(R.string.permission_required_message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkAndRequestPermissions(activity, requiredPermissions);
            }
        }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        }).setCancelable(false).create().show();
    }

    public static void showDialogToOpenSettings(final Activity activity) {
        new AlertDialog.Builder(activity).setMessage(R.string.permission_required_message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        }).setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        }).setCancelable(false).create().show();
    }

    public interface PermissionGrantInterface {

        void doSomethingOnPermissionGrant();

        void doSomethingOnPermissionNotGranted(boolean hasFullyDeniedPermission);
    }
}
