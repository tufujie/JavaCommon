package com.jef.landray.flow;

/**
 * @author Administrator
 * @date 2022/6/14
 */
public class IKmReviewWebserviceServiceProxy implements IKmReviewWebserviceService {
    private String _endpoint = null;
    private IKmReviewWebserviceService iKmReviewWebserviceService = null;

    public IKmReviewWebserviceServiceProxy() {
        _initIKmReviewWebserviceServiceProxy();
    }

    public IKmReviewWebserviceServiceProxy(String endpoint) {
        _endpoint = endpoint;
        _initIKmReviewWebserviceServiceProxy();
    }

    private void _initIKmReviewWebserviceServiceProxy() {
        try {
            iKmReviewWebserviceService = (new IKmReviewWebserviceServiceServiceLocator()).getIKmReviewWebserviceServicePort();
            if (iKmReviewWebserviceService != null) {
                if (_endpoint != null)
                    ((javax.xml.rpc.Stub) iKmReviewWebserviceService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
                else
                    _endpoint = (String) ((javax.xml.rpc.Stub) iKmReviewWebserviceService)._getProperty("javax.xml.rpc.service.endpoint.address");
            }

        } catch (javax.xml.rpc.ServiceException serviceException) {
        }
    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (iKmReviewWebserviceService != null)
            ((javax.xml.rpc.Stub) iKmReviewWebserviceService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

    }

    public IKmReviewWebserviceService getIKmReviewWebserviceService() {
        if (iKmReviewWebserviceService == null)
            _initIKmReviewWebserviceServiceProxy();
        return iKmReviewWebserviceService;
    }

    public java.lang.String addReview(KmReviewParamterForm arg0) throws java.rmi.RemoteException, Exception {
        if (iKmReviewWebserviceService == null)
            _initIKmReviewWebserviceServiceProxy();
        return iKmReviewWebserviceService.addReview(arg0);
    }

    public java.lang.String approveProcess(KmReviewParamterForm arg0) throws java.rmi.RemoteException, Exception {
        if (iKmReviewWebserviceService == null)
            _initIKmReviewWebserviceServiceProxy();
        return iKmReviewWebserviceService.approveProcess(arg0);
    }


}
