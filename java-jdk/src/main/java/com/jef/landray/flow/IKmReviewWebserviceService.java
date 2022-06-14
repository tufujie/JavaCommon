package com.jef.landray.flow;

public interface IKmReviewWebserviceService extends java.rmi.Remote {
    public java.lang.String addReview(com.jef.landray.flow.KmReviewParamterForm arg0) throws java.rmi.RemoteException, com.jef.landray.flow.Exception;

    public java.lang.String approveProcess(com.jef.landray.flow.KmReviewParamterForm arg0) throws java.rmi.RemoteException, com.jef.landray.flow.Exception;
}