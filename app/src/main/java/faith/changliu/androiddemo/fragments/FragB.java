package faith.changliu.androiddemo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import faith.changliu.androiddemo.R;
import faith.changliu.androiddemo.helpers.CommonUtils;
import faith.changliu.androiddemo.helpers.DownloadUtils;

public class FragB extends Fragment implements View.OnClickListener {

    private WebView mWebView;
    private ProgressBar mProgressBar;
    private Button mBtnLoadUrl;
    private TextInputLayout mEtUrlInputLayout;
    private TextInputEditText mEtUrlInput;


    public static FragB newInstance() {
        return new FragB();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnLoadUrl = view.findViewById(R.id.mBtnLoadUrl);
        mEtUrlInputLayout = view.findViewById(R.id.mEtUrlInputLayout);
        mEtUrlInput = view.findViewById(R.id.mEtUrlInput);
        mProgressBar = view.findViewById(R.id.progressBar);
        mWebView = view.findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 0 && newProgress < 100) {
                    mProgressBar.setProgress(newProgress);
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient());

        mBtnLoadUrl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mBtnLoadUrl) {
            mEtUrlInputLayout.clearFocus();
            String url = mEtUrlInput.getText().toString();
            // load website if connected
            if (DownloadUtils.isConnected()) {
                mWebView.loadUrl(url);
            } else {
                CommonUtils.snackbar(v, getContext(), R.string.no_internet);
            }
        }
    }
}
