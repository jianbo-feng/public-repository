package com.citydo.sofaboot_server.service.impl;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.invoke.SofaResponseCallback;
import com.alipay.sofa.rpc.core.request.RequestBase;

public class CallbackImpl implements SofaResponseCallback {


    public void onAppResponse(Object appResponse, String methodName, RequestBase request) {
        System.out.println("callback client process:" + appResponse);
    }


    public void onAppException(Throwable throwable, String methodName, RequestBase request) {
    }

    public void onSofaException(SofaRpcException sofaException, String methodName, RequestBase request) {
    }

}
