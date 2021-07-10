package com.jef.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 主要用来web界面的订单列表用
 * 
 * @author Administrator
 *
 */
public class OrderInfoDto implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5171492844623321351L;

	private Long id;

	private String extraOrderId;

	private BigDecimal extraOrderAmount;
	private BigDecimal extraOrderAmountCNY;

	private Integer scmOrderStatus;

	private Integer issueStatus;

	private Integer frozenStatus;

	private String gmtCreate;

	private Date gmtPaySuccess;

	private Integer hasRequestLoan;

	private Integer fundStatus;

	private String gmtPayTime;
	private String gmtSendTime;

	private Date finalSendTime;

	private Long shopId;

	private Long companyId;

	private BigDecimal weight;
	private BigDecimal realWeigth;

	private Integer productKind;
	private Integer totalProductCount;
	private Integer hasNewLeaveMessage;
	private Integer hasNewConversations;
	private Integer hasMemo;
	private String memo;
	private Boolean hasFullname;
	private Integer hasBindlogistics;
	private String freightAgencyEnumTagShow;

	private String buyerName;
	private String receiveCountryName;
	private String logisticsType;
	private String scmOrderStatusDesc;
	private String extraOrderStatusDesc;
	private String FromTodayDesc;
	private String checkbox;
	private String action;
	private boolean hasPrint;

	private String shopName;

	private String displayCountDownTime;

	private String country;
	private String countryCNName;
	private String countryENName;

	private String contactPerson;

	private boolean outOfStock;

	private String sellerName;

	// 放款时间
	private Date releasedDatetime;

	private String toBeConfirmedNote;

	// 子订单(用于订单列表中显示)
	private String childOrdersHTML;
	// 原始订单ID(补发)
	private String originalOrderIdHTML;
	// 拆分订单
	private boolean splitOrder;
	// 线上发货订单
	private Integer orderType;
	// 订单所绑定的运单号 下划线分隔
	private String waybillNos;
	// 客服备注
	private String note;
	private Integer bizType;

	// 货运跟踪信息
	private String trackingInfo;
    private String trackingNo;

	private Integer sendTypeFlag;

    private String site;
    private String recordNumber;

	//用户对接的物流方式 + 用户对接时的物流编号
	private String freightExpressLogistics;

    //父订单（用于订单列表显示）
    private String parentOrdersHTML;

    private Boolean hasWarningForSku;//是否有警告提示：系统自动给仓库添加SKU

    private Long storageId;

    private String storageName;//仓库名称

    private String province;

    private String city;

    private String phoneNumber;

    private String mobileNo;

    private String editAddress;

    private String zip;

    public void setParentOrdersHTML(String parentOrdersHTML) {
        this.parentOrdersHTML = parentOrdersHTML;
    }

    public String getParentOrdersHTML() {
        return parentOrdersHTML;
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getLogisticsType() {
		return logisticsType;
	}

	public void setLogisticsType(String logisticsType) {
		this.logisticsType = logisticsType;
	}

	public String getScmOrderStatusDesc() {
		return scmOrderStatusDesc;
	}

	public void setScmOrderStatusDesc(String scmOrderStatusDesc) {
		this.scmOrderStatusDesc = scmOrderStatusDesc;
	}

	public String getExtraOrderStatusDesc() {
		return extraOrderStatusDesc;
	}

	public void setExtraOrderStatusDesc(String extraOrderStatusDesc) {
		this.extraOrderStatusDesc = extraOrderStatusDesc;
	}

	public String getFromTodayDesc() {
		return FromTodayDesc;
	}

	public void setFromTodayDesc(String fromTodayDesc) {
		FromTodayDesc = fromTodayDesc;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExtraOrderId() {
		return extraOrderId;
	}

	public void setExtraOrderId(String extraOrderId) {
		this.extraOrderId = extraOrderId;
	}

	public BigDecimal getExtraOrderAmount() {
		return extraOrderAmount;
	}

	public void setExtraOrderAmount(BigDecimal extraOrderAmount) {
		this.extraOrderAmount = extraOrderAmount;
	}

	public Integer getScmOrderStatus() {
		return scmOrderStatus;
	}

	public void setScmOrderStatus(Integer scmOrderStatus) {
		this.scmOrderStatus = scmOrderStatus;
	}

	public Integer getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(Integer issueStatus) {
		this.issueStatus = issueStatus;
	}

	public Integer getFrozenStatus() {
		return frozenStatus;
	}

	public void setFrozenStatus(Integer frozenStatus) {
		this.frozenStatus = frozenStatus;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtPaySuccess() {
		return gmtPaySuccess;
	}

	public void setGmtPaySuccess(Date gmtPaySuccess) {
		this.gmtPaySuccess = gmtPaySuccess;
	}

	public Integer getHasRequestLoan() {
		return hasRequestLoan;
	}

	public void setHasRequestLoan(Integer hasRequestLoan) {
		this.hasRequestLoan = hasRequestLoan;
	}

	public Integer getFundStatus() {
		return fundStatus;
	}

	public void setFundStatus(Integer fundStatus) {
		this.fundStatus = fundStatus;
	}

	public String getGmtPayTime() {
		return gmtPayTime;
	}

	public void setGmtPayTime(String gmtPayTime) {
		this.gmtPayTime = gmtPayTime;
	}

	public Date getFinalSendTime() {
		return finalSendTime;
	}

	public void setFinalSendTime(Date finalSendTime) {
		this.finalSendTime = finalSendTime;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getProductKind() {
		return productKind;
	}

	public void setProductKind(Integer productKind) {
		this.productKind = productKind;
	}

	public Integer getTotalProductCount() {
		return totalProductCount;
	}

	public void setTotalProductCount(Integer totalProductCount) {
		this.totalProductCount = totalProductCount;
	}

	public Integer getHasNewLeaveMessage() {
		return hasNewLeaveMessage;
	}

	public void setHasNewLeaveMessage(Integer hasNewLeaveMessage) {
		this.hasNewLeaveMessage = hasNewLeaveMessage;
	}

	public Integer getHasMemo() {
		return hasMemo;
	}

	public void setHasMemo(Integer hasMemo) {
		this.hasMemo = hasMemo;
	}

	public String getGmtSendTime() {
		return gmtSendTime;
	}

	public void setGmtSendTime(String gmtSendTime) {
		this.gmtSendTime = gmtSendTime;
	}

	public boolean isHasPrint() {
		return hasPrint;
	}

	public void setHasPrint(boolean hasPrint) {
		this.hasPrint = hasPrint;
	}

	public String getReceiveCountryName() {
		return receiveCountryName;
	}

	public void setReceiveCountryName(String receiveCountryName) {
		this.receiveCountryName = receiveCountryName;
	}

	public String getDisplayCountDownTime() {
		return displayCountDownTime;
	}

	public void setDisplayCountDownTime(String displayCountDownTime) {
		this.displayCountDownTime = displayCountDownTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCNName() {
		return countryCNName;
	}

	public void setCountryCNName(String countryCNName) {
		this.countryCNName = countryCNName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isOutOfStock() {
		return outOfStock;
	}

	public void setOutOfStock(boolean outOfStock) {
		this.outOfStock = outOfStock;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Date getReleasedDatetime() {
		return releasedDatetime;
	}

	public void setReleasedDatetime(Date releasedDatetime) {
		this.releasedDatetime = releasedDatetime;
	}

	public BigDecimal getExtraOrderAmountCNY() {
		return extraOrderAmountCNY;
	}

	public void setExtraOrderAmountCNY(BigDecimal extraOrderAmountCNY) {
		this.extraOrderAmountCNY = extraOrderAmountCNY;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getRealWeigth() {
		return realWeigth;
	}

	public void setRealWeigth(BigDecimal realWeigth) {
		this.realWeigth = realWeigth;
	}

	public String getToBeConfirmedNote() {
		return toBeConfirmedNote;
	}

	public void setToBeConfirmedNote(String toBeConfirmedNote) {
		this.toBeConfirmedNote = toBeConfirmedNote;
	}

	public Boolean getHasFullname() {
		return hasFullname;
	}

	public void setHasFullname(Boolean hasFullname) {
		this.hasFullname = hasFullname;
	}

	public String getChildOrdersHTML() {
		return childOrdersHTML;
	}

	public void setChildOrdersHTML(String childOrdersHTML) {
		this.childOrdersHTML = childOrdersHTML;
	}

	public String getOriginalOrderIdHTML() {
		return originalOrderIdHTML;
	}

	public void setOriginalOrderIdHTML(String originalOrderIdHTML) {
		this.originalOrderIdHTML = originalOrderIdHTML;
	}

	public boolean isSplitOrder() {
		return splitOrder;
	}

	public void setSplitOrder(boolean splitOrder) {
		this.splitOrder = splitOrder;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getWaybillNos() {
		return waybillNos;
	}

	public void setWaybillNos(String waybillNos) {
		this.waybillNos = waybillNos;
	}

	public String getCountryENName() {
		return countryENName;
	}

	public void setCountryENName(String countryENName) {
		this.countryENName = countryENName;
	}

	public Integer getHasBindlogistics() {
		return hasBindlogistics;
	}

	public void setHasBindlogistics(Integer hasBindlogistics) {
		this.hasBindlogistics = hasBindlogistics;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getHasNewConversations() {
		return hasNewConversations;
	}

	public void setHasNewConversations(Integer hasNewConversations) {
		this.hasNewConversations = hasNewConversations;
	}

	public String getTrackingInfo() {
		return trackingInfo;
	}

	public void setTrackingInfo(String trackingInfo) {
		this.trackingInfo = trackingInfo;

	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public Integer getSendTypeFlag() {
		return sendTypeFlag;
	}

	public void setSendTypeFlag(Integer sendTypeFlag) {
		this.sendTypeFlag = sendTypeFlag;
	}

	public String getFreightAgencyEnumTagShow() {
		return freightAgencyEnumTagShow;
	}

	public void setFreightAgencyEnumTagShow(String freightAgencyEnumTagShow) {
		this.freightAgencyEnumTagShow = freightAgencyEnumTagShow;
	}

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

	public String getFreightExpressLogistics() {
		return freightExpressLogistics;
	}

	public void setFreightExpressLogistics(String freightExpressLogistics) {
		this.freightExpressLogistics = freightExpressLogistics;
	}

    public Boolean getHasWarningForSku() {
        return hasWarningForSku;
    }

    public void setHasWarningForSku(Boolean hasWarningForSku) {
        this.hasWarningForSku = hasWarningForSku;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEditAddress() {
        return editAddress;
    }

    public void setEditAddress(String editAddress) {
        this.editAddress = editAddress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}