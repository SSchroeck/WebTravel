package com.webtravel.WebViewJava;

import static android.service.controls.ControlsProviderService.TAG;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.webtravel.Structures.SearchKt;

public class MyWebViewClient extends WebViewClient {

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
        int statusCode = errorResponse.getStatusCode();
        Log.w(TAG, "onReceivedHttpError: "+statusCode );

    }
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        String errorCase = "net::ERR_NAME_NOT_RESOLVED";
        if(errorCode ==-2 && description.equals(errorCase)){
            SearchKt.fixUrl();
        }
        else{
            Log.w(TAG, "onReceivedError: "+errorCode );
            Log.w(TAG, "string description: "+description );
            Log.w(TAG, "Url that failed: "+failingUrl );
        }

    }


    }
