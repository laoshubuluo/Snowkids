package com.rat.nm.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;

import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2015/8/17
 * introduce : 发送成功提示框
 */
public class SendSuccessDialog extends Dialog {

    private Dialog dialog;

    public SendSuccessDialog(Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.view_dialog_send_success);
        dialog = this;
    }

    @Override
    public void show() {
        // 开启定时器，定时退出
        TimeCount timeCount = new TimeCount(2000, 500);
        timeCount.start();
        super.show();
    }

    /**
     * 重新发送倒计时
     */
    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            reset();
            if (null != dialog)
                dialog.dismiss();
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
        }

        public void reset() {
            this.cancel();
        }
    }
}
