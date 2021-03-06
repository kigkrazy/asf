package com.reizx.asf.view.fragment;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.blankj.utilcode.util.ShellUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.asf.R;
import com.reizx.asf.contract.SettingContract;
import com.reizx.asf.presenter.SettingPresenter;
import com.reizx.asf.util.AsfLog;
import com.reizx.asf.util.RxUtil;
import com.reizx.asf.view.common.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_setting_page_show_ip_des)
    TextView tvIpStatus;
    Disposable ds;

    @SuppressLint("CheckResult")
    @OnClick(R.id.btn_setting_page_test)
    public void clickTest(){
        if (ds != null && !ds.isDisposed()){
            AsfLog.d("dispose the subscribe...");
            ds.dispose();
            ds = null;
            return;
        }

        AsfLog.d("click setting page test");
        ds = Flowable.interval(1,  TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) {
                        AsfLog.d("the inter ... " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        AsfLog.d("flow err : " + throwable);
                    }
                });
    }

    @OnClick(R.id.btn_setting_page_xlog)
    public void printXlog(){
        AsfLog.d("start exec ...");
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        initTopBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar() {
        mTopBar.setTitle("设置");
    }

    @Override
    public void showIpStatus(String msg) {
        tvIpStatus.setText(msg);
    }
}
