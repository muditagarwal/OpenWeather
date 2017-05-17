package com.assignment.openweather.view.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.assignment.openweather.R;
import com.assignment.openweather.utils.PermissionUtils;
import com.assignment.openweather.utils.Utils;

import java.util.List;

public class PermissionActivity extends AppCompatActivity implements PermissionUtils.PermissionGrantInterface {

    private String[] requiredPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
    private List<String> requestedPermissionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestedPermissionList = PermissionUtils.checkPermissions(this, requiredPermissions);
        if (!Utils.isNotEmptyList(requestedPermissionList)) {
            //we already have the all the permissions.. whoopieee.. move to next screen
            showNextScreen();
        } else {
            PermissionUtils.checkAndRequestPermissions(this, requiredPermissions);
        }
    }

    @Override
    public void doSomethingOnPermissionGrant() {
        //move to next screen
        showNextScreen();
    }

    @Override
    public void doSomethingOnPermissionNotGranted(final boolean hasFullyDeniedPermission) {
        if (hasFullyDeniedPermission) {
            //now we cant do anything, open settings and let them figure it out
            PermissionUtils.showDialogToOpenSettings(this);
        } else {
            //show a message that without the permission, they cannot progress ahead
            PermissionUtils.showRationaleDialog(this, requiredPermissions);
        }
    }

    private void showNextScreen() {
        Intent i = new Intent(this, WeatherActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(this, requestedPermissionList, requiredPermissions, requestCode, grantResults);
    }
}
