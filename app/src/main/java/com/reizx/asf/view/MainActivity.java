package com.reizx.asf.view;

import android.Manifest;
import android.annotation.SuppressLint;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.reizx.asf.R;
import com.reizx.asf.contract.MainActivityContract;
import com.reizx.asf.presenter.MainActivityPresenter;
import com.reizx.asf.util.AsfMgrLog;
import com.reizx.asf.view.common.BaseActivity;
import com.reizx.asf.view.fragment.MainFragment;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityContract.View {
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        QMUIStatusBarHelper.setStatusBarDarkMode(this);//沉浸式状态栏
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_app_main, mainFragment)
                .commit();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void onCreateFinish() {
        //super.onCreateFinish();
        requestPermission();
    }

    /**
     * 请求权限
     */
    @SuppressLint("CheckResult")
    public void requestPermission() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INSTALL_PACKAGES,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.INTERNET).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    // 用户已经同意该权限
                    AsfMgrLog.d(permission.name + " is granted.");
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    AsfMgrLog.d(permission.name + " is denied. More info should be provided.");
                } else {
                    // 用户拒绝了该权限，并且选中『不再询问』
                    AsfMgrLog.d(permission.name + " is denied.");
                }
            }
        });
    }
}
