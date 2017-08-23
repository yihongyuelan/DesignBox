package com.seven.designbox.designpatterns.patterns.compound;

import com.seven.designbox.designpatterns.common.PatternsCommonFragment;

import android.os.Bundle;
import android.webkit.WebView;

public class CompoundDesFragment extends PatternsCommonFragment {

    @Override
    public void loadData(Bundle bundle, WebView webView) {
        if (webView == null) return;
        // Restore a webview if we are meant to restore
        if (bundle != null) {
            webView.restoreState(bundle);
        } else {
            webView.loadUrl("file:///android_asset/CompoundPatternDes.html");
        }
    }
}
