package com.seven.designbox.designpatterns.patterns.singleton;

import com.seven.designbox.designpatterns.common.PatternsCommonFragment;

import android.os.Bundle;
import android.webkit.WebView;

public class SingletonFragment extends PatternsCommonFragment {

    @Override
    public void loadData(Bundle bundle, WebView webView) {
        if (webView == null) return;
        // Restore a webview if we are meant to restore
        if (bundle != null) {
            webView.restoreState(bundle);
        } else {
            //Home Screen, Simple explanation
            webView.loadUrl("file:///android_asset/SingletonPattern.html");
        }
    }
}