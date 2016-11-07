package com.seven.demobox.designpatterns.patterns.composite;

import com.seven.demobox.designpatterns.common.PatternsCommonFragment;

import android.os.Bundle;
import android.webkit.WebView;

public class CompositeDesFragment extends PatternsCommonFragment {

    @Override
    public void loadData(Bundle bundle, WebView webView) {
        if (webView == null) return;
        // Restore a webview if we are meant to restore
        if (bundle != null) {
            webView.restoreState(bundle);
        } else {
            webView.loadUrl("file:///android_asset/CompositePattern_Des.html");
        }
    }
}
