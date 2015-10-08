package com.kidskart.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.kidskart.MainActivity;
import com.kidskart.R;
import com.kidskart.callbacks.SwitchFragmentsCallback;

import java.util.regex.Pattern;

/**
 * Created by Nilesh on 19/09/15.
 */
public class HomePageFragment extends Fragment {

    private static final String TAG = "HomePageFragment";
    WebView webViewDashboard;
    SwitchFragmentsCallback switchFragmentsCallback;
    String url;
   // Handler getProductURLTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_fragment, container, false);
        webViewDashboard = (WebView) view.findViewById(R.id.webViewDashboard);
        //initWebview("https://www.dropbox.com");
        Bundle bundle = getArguments();
        url = bundle.getString("url");
        initWebview(url);
        return view;
    }



    private void initWebview(String urlToLoad){
        WebSettings settings = webViewDashboard.getSettings();
        settings.setJavaScriptEnabled(true);
        //webViewDashboard.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webViewDashboard.setScrollbarFadingEnabled(true);


        webViewDashboard.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click..." + url);
                //return super.shouldOverrideUrlLoading(view, url);
                //startActivity(new Intent(getActivity(), SubCategoryListActivity.class));
                if(url.contains("category") || url.contains("products")){

                }
                CategoryListFragment fragment = new CategoryListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key",getCategoryOrProduct(url));
                bundle.putString("value",getCategoryID(url));
                fragment.setArguments(bundle);
                switchFragmentsCallback.switchFragmentAddToBackStack(fragment, MainActivity.FRAG_SUBCATEGORY_LIST, null, true);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Page Load Complete..." + url);
                //startActivity(new Intent(getActivity(), SubCategoryListActivity.class));
                super.onPageFinished(view, url);
            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e(TAG, "Error: " + error.toString());
                Toast.makeText(getActivity(), getString(R.string.webview_error), Toast.LENGTH_SHORT).show();
                super.onReceivedError(view, request, error);
            }
        });

        webViewDashboard.loadUrl(urlToLoad);
    }

    private String getCategoryID(String url){
        String result = null;
        String [] split;
            split = url.split(Pattern.quote("/"));
            if(split.length > 0){
                StringBuilder key = new StringBuilder();
                /*key.append(split[split.length - 2]);
                key.append(":");*/
                key.append(split[split.length - 1]);
                result = key.toString();
                Log.d(TAG,"ID = "+result);
            }




        return result;
    }

    private String getCategoryOrProduct(String url){
        String result = null;
        String [] split;
            split = url.split(Pattern.quote("/"));
            if(split.length > 0){
                StringBuilder key = new StringBuilder();
                /*key.append(split[split.length - 2]);
                key.append(":");*/
                key.append(split[split.length - 2]);
                result = key.toString();
                Log.d(TAG,"Value = "+result);
            }




        return result;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        switchFragmentsCallback = (SwitchFragmentsCallback) activity;
    }
}
