package com.rat.snowkids.util;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * @author L.jinzhu
 * @Description: View的开关Controll
 * @date 2015-09-01 下午18:07:16
 */
public class ViewUtils {
    /**
     * 开关view
     * @param view
     */
    public static void toggleView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
            return;
        }
        view.setVisibility(View.VISIBLE);
    }

    public static void toggleView(View view, View parent) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
            LayoutParams params = parent.getLayoutParams();
            params.height = DimensionUtils.convertDipToPixels(
                    parent.getResources(), 50);
            parent.setLayoutParams(params);
            parent.postInvalidate();
            return;
        }
        view.setVisibility(View.VISIBLE);
        LayoutParams params = parent.getLayoutParams();
        params.height = DimensionUtils.convertDipToPixels(
                parent.getResources(), 220);
        parent.setLayoutParams(params);
        parent.postInvalidate();
    }

    /**
     * 关闭 view
     * @param view
     */
    public static void shutView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
            return;
        }
    }

    public static void shutView(View view, View parent) {
        if (view.getVisibility() == View.VISIBLE) {
            LayoutParams params = parent.getLayoutParams();
            params.height = DimensionUtils.convertDipToPixels(
                    parent.getResources(), 50);
            parent.setLayoutParams(params);
            view.setVisibility(View.GONE);
            return;
        }
        LayoutParams params = parent.getLayoutParams();
        params.height = DimensionUtils.convertDipToPixels(
                parent.getResources(), 50);
        parent.setLayoutParams(params);
    }
}
