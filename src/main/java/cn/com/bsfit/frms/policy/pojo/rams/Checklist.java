package cn.com.bsfit.frms.policy.pojo.rams;

import java.util.Date;

public class Checklist {
	private Long id;

	private String orderId;

	private Date createTime;

	private Date updateTime;

	private Long transVol;

	private Long riskLevel;

	private Integer status;

	private Long operUserId;

	private String verifiStrategy;

	private String notifyStrategy;

	private Integer score;

	private String payUserId;

	private String recUserId;

	private String payBankCardNo;

	private String recTerminalId;

	private String redundantFields1;

	private String redundantFields2;

	private String redundantFields3;

	private String control;

	private String bizCode;

	private String frmsDataType;

	private Date frmsTransTime;

	private Long frmsTransVol;

	private String frmsOperStatus;

	private String frmsOrderChnl;

	private String frmsOrderType;

	private String frmsTradeMode;

	private String frmsTradeType;

	private String frmsUserId;

	private String frmsUserIdCard;

	private String frmsUserType;

	private String frmsBankCardNo;

	private String frmsColName;

	private String frmsColCardNo;

	private String frmsOperNo;

	private String frmsUserName;

	private String frmsPayDetail;

	private String frmsTradeFrom;

	private String frmsTradeDepart;

	private String frmsColCustAcct;

	private String frmsFreId;

	private String frmsSerialsNo;

	private String frmsBankCardType;

	private String frmsCryType;

	private String frmsBillFlay;

	private String frmsTradeSrc;

	private String frmsMerchSeq;

	private String frmsMerchStat;

	private String frmsCdhpBh;

	private String frmsDueDate;

	private String frmsTransStatus;

	private String frmsCdhpStatus;

	private String frmsCdhpAmt;

	private String frmsCompanyName;

	private String frmsOperName;

	private String frmsSignDateNet;

	private String frmsSignDateMob;

	private String frmsSignMobNum;

	private Integer checklistType;

	private String frmsMerchName;
	
	private String rulesCode;
	
	private String frmsCustName;
	
	private String frmsUserPhone;
	
	public Checklist(Long id, String orderId, Date createTime, Date updateTime, Long transVol, Long riskLevel,
			Integer status, Long operUserId, String verifiStrategy, String notifyStrategy, Integer score,
			String payUserId, String recUserId, String payBankCardNo, String recTerminalId, String redundantFields1,
			String redundantFields2, String redundantFields3, String control, String bizCode, String frmsDataType,
			Date frmsTransTime, Long frmsTransVol, String frmsOperStatus, String frmsOrderChnl, String frmsOrderType,
			String frmsTradeMode, String frmsTradeType, String frmsUserId, String frmsUserIdCard, String frmsUserType,
			String frmsBankCardNo, String frmsColName, String frmsColCardNo, String frmsOperNo, String frmsUserName,
			String frmsPayDetail, String frmsTradeFrom, String frmsTradeDepart, String frmsColCustAcct,
			String frmsFreId, String frmsSerialsNo, String frmsBankCardType, String frmsCryType, String frmsBillFlay,
			String frmsTradeSrc, String frmsMerchSeq, String frmsMerchStat, String frmsCdhpBh, String frmsDueDate,
			String frmsTransStatus, String frmsCdhpStatus, String frmsCdhpAmt, String frmsCompanyName,
			String frmsOperName, String frmsSignDateNet, String frmsSignDateMob, String frmsSignMobNum,
			Integer checklistType, String frmsMerchName, String rulesCode, String frmsCustName, String frmsUserPhone) {
		this.id = id;
		this.orderId = orderId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.transVol = transVol;
		this.riskLevel = riskLevel;
		this.status = status;
		this.operUserId = operUserId;
		this.verifiStrategy = verifiStrategy;
		this.notifyStrategy = notifyStrategy;
		this.score = score;
		this.payUserId = payUserId;
		this.recUserId = recUserId;
		this.payBankCardNo = payBankCardNo;
		this.recTerminalId = recTerminalId;
		this.redundantFields1 = redundantFields1;
		this.redundantFields2 = redundantFields2;
		this.redundantFields3 = redundantFields3;
		this.control = control;
		this.bizCode = bizCode;
		this.frmsDataType = frmsDataType;
		this.frmsTransTime = frmsTransTime;
		this.frmsTransVol = frmsTransVol;
		this.frmsOperStatus = frmsOperStatus;
		this.frmsOrderChnl = frmsOrderChnl;
		this.frmsOrderType = frmsOrderType;
		this.frmsTradeMode = frmsTradeMode;
		this.frmsTradeType = frmsTradeType;
		this.frmsUserId = frmsUserId;
		this.frmsUserIdCard = frmsUserIdCard;
		this.frmsUserType = frmsUserType;
		this.frmsBankCardNo = frmsBankCardNo;
		this.frmsColName = frmsColName;
		this.frmsColCardNo = frmsColCardNo;
		this.frmsOperNo = frmsOperNo;
		this.frmsUserName = frmsUserName;
		this.frmsPayDetail = frmsPayDetail;
		this.frmsTradeFrom = frmsTradeFrom;
		this.frmsTradeDepart = frmsTradeDepart;
		this.frmsColCustAcct = frmsColCustAcct;
		this.frmsFreId = frmsFreId;
		this.frmsSerialsNo = frmsSerialsNo;
		this.frmsBankCardType = frmsBankCardType;
		this.frmsCryType = frmsCryType;
		this.frmsBillFlay = frmsBillFlay;
		this.frmsTradeSrc = frmsTradeSrc;
		this.frmsMerchSeq = frmsMerchSeq;
		this.frmsMerchStat = frmsMerchStat;
		this.frmsCdhpBh = frmsCdhpBh;
		this.frmsDueDate = frmsDueDate;
		this.frmsTransStatus = frmsTransStatus;
		this.frmsCdhpStatus = frmsCdhpStatus;
		this.frmsCdhpAmt = frmsCdhpAmt;
		this.frmsCompanyName = frmsCompanyName;
		this.frmsOperName = frmsOperName;
		this.frmsSignDateNet = frmsSignDateNet;
		this.frmsSignDateMob = frmsSignDateMob;
		this.frmsSignMobNum = frmsSignMobNum;
		this.checklistType = checklistType;
		this.frmsMerchName = frmsMerchName;
		this.frmsCustName = frmsCustName;
		this.frmsUserPhone = frmsUserPhone;
	}

	public Checklist() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getTransVol() {
		return transVol;
	}

	public void setTransVol(Long transVol) {
		this.transVol = transVol;
	}

	public Long getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Long riskLevel) {
		this.riskLevel = riskLevel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(Long operUserId) {
		this.operUserId = operUserId;
	}

	public String getVerifiStrategy() {
		return verifiStrategy;
	}

	public void setVerifiStrategy(String verifiStrategy) {
		this.verifiStrategy = verifiStrategy == null ? null : verifiStrategy.trim();
	}

	public String getNotifyStrategy() {
		return notifyStrategy;
	}

	public void setNotifyStrategy(String notifyStrategy) {
		this.notifyStrategy = notifyStrategy == null ? null : notifyStrategy.trim();
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPayUserId() {
		return payUserId;
	}

	public void setPayUserId(String payUserId) {
		this.payUserId = payUserId == null ? null : payUserId.trim();
	}

	public String getRecUserId() {
		return recUserId;
	}

	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId == null ? null : recUserId.trim();
	}

	public String getPayBankCardNo() {
		return payBankCardNo;
	}

	public void setPayBankCardNo(String payBankCardNo) {
		this.payBankCardNo = payBankCardNo == null ? null : payBankCardNo.trim();
	}

	public String getRecTerminalId() {
		return recTerminalId;
	}

	public void setRecTerminalId(String recTerminalId) {
		this.recTerminalId = recTerminalId == null ? null : recTerminalId.trim();
	}

	public String getRedundantFields1() {
		return redundantFields1;
	}

	public void setRedundantFields1(String redundantFields1) {
		this.redundantFields1 = redundantFields1 == null ? null : redundantFields1.trim();
	}

	public String getRedundantFields2() {
		return redundantFields2;
	}

	public void setRedundantFields2(String redundantFields2) {
		this.redundantFields2 = redundantFields2 == null ? null : redundantFields2.trim();
	}

	public String getRedundantFields3() {
		return redundantFields3;
	}

	public void setRedundantFields3(String redundantFields3) {
		this.redundantFields3 = redundantFields3 == null ? null : redundantFields3.trim();
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control == null ? null : control.trim();
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode == null ? null : bizCode.trim();
	}

	public String getFrmsDataType() {
		return frmsDataType;
	}

	public void setFrmsDataType(String frmsDataType) {
		this.frmsDataType = frmsDataType == null ? null : frmsDataType.trim();
	}

	public Date getFrmsTransTime() {
		return frmsTransTime;
	}

	public void setFrmsTransTime(Date frmsTransTime) {
		this.frmsTransTime = frmsTransTime;
	}

	public Long getFrmsTransVol() {
		return frmsTransVol;
	}

	public void setFrmsTransVol(Long frmsTransVol) {
		this.frmsTransVol = frmsTransVol;
	}

	public String getFrmsOperStatus() {
		return frmsOperStatus;
	}

	public void setFrmsOperStatus(String frmsOperStatus) {
		this.frmsOperStatus = frmsOperStatus == null ? null : frmsOperStatus.trim();
	}

	public String getFrmsOrderChnl() {
		return frmsOrderChnl;
	}

	public void setFrmsOrderChnl(String frmsOrderChnl) {
		this.frmsOrderChnl = frmsOrderChnl == null ? null : frmsOrderChnl.trim();
	}

	public String getFrmsOrderType() {
		return frmsOrderType;
	}

	public void setFrmsOrderType(String frmsOrderType) {
		this.frmsOrderType = frmsOrderType == null ? null : frmsOrderType.trim();
	}

	public String getFrmsTradeMode() {
		return frmsTradeMode;
	}

	public void setFrmsTradeMode(String frmsTradeMode) {
		this.frmsTradeMode = frmsTradeMode == null ? null : frmsTradeMode.trim();
	}

	public String getFrmsTradeType() {
		return frmsTradeType;
	}

	public void setFrmsTradeType(String frmsTradeType) {
		this.frmsTradeType = frmsTradeType == null ? null : frmsTradeType.trim();
	}

	public String getFrmsUserId() {
		return frmsUserId;
	}

	public void setFrmsUserId(String frmsUserId) {
		this.frmsUserId = frmsUserId == null ? null : frmsUserId.trim();
	}

	public String getFrmsUserIdCard() {
		return frmsUserIdCard;
	}

	public void setFrmsUserIdCard(String frmsUserIdCard) {
		this.frmsUserIdCard = frmsUserIdCard == null ? null : frmsUserIdCard.trim();
	}

	public String getFrmsUserType() {
		return frmsUserType;
	}

	public void setFrmsUserType(String frmsUserType) {
		this.frmsUserType = frmsUserType == null ? null : frmsUserType.trim();
	}

	public String getFrmsBankCardNo() {
		return frmsBankCardNo;
	}

	public void setFrmsBankCardNo(String frmsBankCardNo) {
		this.frmsBankCardNo = frmsBankCardNo == null ? null : frmsBankCardNo.trim();
	}

	public String getFrmsColName() {
		return frmsColName;
	}

	public void setFrmsColName(String frmsColName) {
		this.frmsColName = frmsColName == null ? null : frmsColName.trim();
	}

	public String getFrmsColCardNo() {
		return frmsColCardNo;
	}

	public void setFrmsColCardNo(String frmsColCardNo) {
		this.frmsColCardNo = frmsColCardNo == null ? null : frmsColCardNo.trim();
	}

	public String getFrmsOperNo() {
		return frmsOperNo;
	}

	public void setFrmsOperNo(String frmsOperNo) {
		this.frmsOperNo = frmsOperNo == null ? null : frmsOperNo.trim();
	}

	public String getFrmsUserName() {
		return frmsUserName;
	}

	public void setFrmsUserName(String frmsUserName) {
		this.frmsUserName = frmsUserName == null ? null : frmsUserName.trim();
	}

	public String getFrmsPayDetail() {
		return frmsPayDetail;
	}

	public void setFrmsPayDetail(String frmsPayDetail) {
		this.frmsPayDetail = frmsPayDetail == null ? null : frmsPayDetail.trim();
	}

	public String getFrmsTradeFrom() {
		return frmsTradeFrom;
	}

	public void setFrmsTradeFrom(String frmsTradeFrom) {
		this.frmsTradeFrom = frmsTradeFrom == null ? null : frmsTradeFrom.trim();
	}

	public String getFrmsTradeDepart() {
		return frmsTradeDepart;
	}

	public void setFrmsTradeDepart(String frmsTradeDepart) {
		this.frmsTradeDepart = frmsTradeDepart == null ? null : frmsTradeDepart.trim();
	}

	public String getFrmsColCustAcct() {
		return frmsColCustAcct;
	}

	public void setFrmsColCustAcct(String frmsColCustAcct) {
		this.frmsColCustAcct = frmsColCustAcct == null ? null : frmsColCustAcct.trim();
	}

	public String getFrmsFreId() {
		return frmsFreId;
	}

	public void setFrmsFreId(String frmsFreId) {
		this.frmsFreId = frmsFreId == null ? null : frmsFreId.trim();
	}

	public String getFrmsSerialsNo() {
		return frmsSerialsNo;
	}

	public void setFrmsSerialsNo(String frmsSerialsNo) {
		this.frmsSerialsNo = frmsSerialsNo == null ? null : frmsSerialsNo.trim();
	}

	public String getFrmsBankCardType() {
		return frmsBankCardType;
	}

	public void setFrmsBankCardType(String frmsBankCardType) {
		this.frmsBankCardType = frmsBankCardType == null ? null : frmsBankCardType.trim();
	}

	public String getFrmsCryType() {
		return frmsCryType;
	}

	public void setFrmsCryType(String frmsCryType) {
		this.frmsCryType = frmsCryType == null ? null : frmsCryType.trim();
	}

	public String getFrmsBillFlay() {
		return frmsBillFlay;
	}

	public void setFrmsBillFlay(String frmsBillFlay) {
		this.frmsBillFlay = frmsBillFlay == null ? null : frmsBillFlay.trim();
	}

	public String getFrmsTradeSrc() {
		return frmsTradeSrc;
	}

	public void setFrmsTradeSrc(String frmsTradeSrc) {
		this.frmsTradeSrc = frmsTradeSrc == null ? null : frmsTradeSrc.trim();
	}

	public String getFrmsMerchSeq() {
		return frmsMerchSeq;
	}

	public void setFrmsMerchSeq(String frmsMerchSeq) {
		this.frmsMerchSeq = frmsMerchSeq == null ? null : frmsMerchSeq.trim();
	}

	public String getFrmsMerchStat() {
		return frmsMerchStat;
	}

	public void setFrmsMerchStat(String frmsMerchStat) {
		this.frmsMerchStat = frmsMerchStat == null ? null : frmsMerchStat.trim();
	}

	public String getFrmsCdhpBh() {
		return frmsCdhpBh;
	}

	public void setFrmsCdhpBh(String frmsCdhpBh) {
		this.frmsCdhpBh = frmsCdhpBh == null ? null : frmsCdhpBh.trim();
	}

	public String getFrmsDueDate() {
		return frmsDueDate;
	}

	public void setFrmsDueDate(String frmsDueDate) {
		this.frmsDueDate = frmsDueDate == null ? null : frmsDueDate.trim();
	}

	public String getFrmsTransStatus() {
		return frmsTransStatus;
	}

	public void setFrmsTransStatus(String frmsTransStatus) {
		this.frmsTransStatus = frmsTransStatus == null ? null : frmsTransStatus.trim();
	}

	public String getFrmsCdhpStatus() {
		return frmsCdhpStatus;
	}

	public void setFrmsCdhpStatus(String frmsCdhpStatus) {
		this.frmsCdhpStatus = frmsCdhpStatus == null ? null : frmsCdhpStatus.trim();
	}

	public String getFrmsCdhpAmt() {
		return frmsCdhpAmt;
	}

	public void setFrmsCdhpAmt(String frmsCdhpAmt) {
		this.frmsCdhpAmt = frmsCdhpAmt == null ? null : frmsCdhpAmt.trim();
	}

	public String getFrmsCompanyName() {
		return frmsCompanyName;
	}

	public void setFrmsCompanyName(String frmsCompanyName) {
		this.frmsCompanyName = frmsCompanyName == null ? null : frmsCompanyName.trim();
	}

	public String getFrmsOperName() {
		return frmsOperName;
	}

	public void setFrmsOperName(String frmsOperName) {
		this.frmsOperName = frmsOperName == null ? null : frmsOperName.trim();
	}

	public String getFrmsSignDateNet() {
		return frmsSignDateNet;
	}

	public void setFrmsSignDateNet(String frmsSignDateNet) {
		this.frmsSignDateNet = frmsSignDateNet == null ? null : frmsSignDateNet.trim();
	}

	public String getFrmsSignDateMob() {
		return frmsSignDateMob;
	}

	public void setFrmsSignDateMob(String frmsSignDateMob) {
		this.frmsSignDateMob = frmsSignDateMob == null ? null : frmsSignDateMob.trim();
	}

	public String getFrmsSignMobNum() {
		return frmsSignMobNum;
	}

	public void setFrmsSignMobNum(String frmsSignMobNum) {
		this.frmsSignMobNum = frmsSignMobNum == null ? null : frmsSignMobNum.trim();
	}

	public Integer getChecklistType() {
		return checklistType;
	}

	public void setChecklistType(Integer checklistType) {
		this.checklistType = checklistType;
	}

	public String getFrmsMerchName() {
		return frmsMerchName;
	}

	public void setFrmsMerchName(String frmsMerchName) {
		this.frmsMerchName = frmsMerchName == null ? null : frmsMerchName.trim();
	}

	public String getRulesCode() {
		return rulesCode;
	}

	public void setRulesCode(String rulesCode) {
		this.rulesCode = rulesCode;
	}

	public String getFrmsCustName() {
		return frmsCustName;
	}

	public void setFrmsCustName(String frmsCustName) {
		this.frmsCustName = frmsCustName;
	}

	public String getFrmsUserPhone() {
		return frmsUserPhone;
	}

	public void setFrmsUserPhone(String frmsUserPhone) {
		this.frmsUserPhone = frmsUserPhone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bizCode == null) ? 0 : bizCode.hashCode());
		result = prime * result + ((checklistType == null) ? 0 : checklistType.hashCode());
		result = prime * result + ((control == null) ? 0 : control.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((frmsBankCardNo == null) ? 0 : frmsBankCardNo.hashCode());
		result = prime * result + ((frmsBankCardType == null) ? 0 : frmsBankCardType.hashCode());
		result = prime * result + ((frmsBillFlay == null) ? 0 : frmsBillFlay.hashCode());
		result = prime * result + ((frmsCdhpAmt == null) ? 0 : frmsCdhpAmt.hashCode());
		result = prime * result + ((frmsCdhpBh == null) ? 0 : frmsCdhpBh.hashCode());
		result = prime * result + ((frmsCdhpStatus == null) ? 0 : frmsCdhpStatus.hashCode());
		result = prime * result + ((frmsColCardNo == null) ? 0 : frmsColCardNo.hashCode());
		result = prime * result + ((frmsColCustAcct == null) ? 0 : frmsColCustAcct.hashCode());
		result = prime * result + ((frmsColName == null) ? 0 : frmsColName.hashCode());
		result = prime * result + ((frmsCompanyName == null) ? 0 : frmsCompanyName.hashCode());
		result = prime * result + ((frmsCryType == null) ? 0 : frmsCryType.hashCode());
		result = prime * result + ((frmsCustName == null) ? 0 : frmsCustName.hashCode());
		result = prime * result + ((frmsDataType == null) ? 0 : frmsDataType.hashCode());
		result = prime * result + ((frmsDueDate == null) ? 0 : frmsDueDate.hashCode());
		result = prime * result + ((frmsFreId == null) ? 0 : frmsFreId.hashCode());
		result = prime * result + ((frmsMerchName == null) ? 0 : frmsMerchName.hashCode());
		result = prime * result + ((frmsMerchSeq == null) ? 0 : frmsMerchSeq.hashCode());
		result = prime * result + ((frmsMerchStat == null) ? 0 : frmsMerchStat.hashCode());
		result = prime * result + ((frmsOperName == null) ? 0 : frmsOperName.hashCode());
		result = prime * result + ((frmsOperNo == null) ? 0 : frmsOperNo.hashCode());
		result = prime * result + ((frmsOperStatus == null) ? 0 : frmsOperStatus.hashCode());
		result = prime * result + ((frmsOrderChnl == null) ? 0 : frmsOrderChnl.hashCode());
		result = prime * result + ((frmsOrderType == null) ? 0 : frmsOrderType.hashCode());
		result = prime * result + ((frmsPayDetail == null) ? 0 : frmsPayDetail.hashCode());
		result = prime * result + ((frmsSerialsNo == null) ? 0 : frmsSerialsNo.hashCode());
		result = prime * result + ((frmsSignDateMob == null) ? 0 : frmsSignDateMob.hashCode());
		result = prime * result + ((frmsSignDateNet == null) ? 0 : frmsSignDateNet.hashCode());
		result = prime * result + ((frmsSignMobNum == null) ? 0 : frmsSignMobNum.hashCode());
		result = prime * result + ((frmsTradeDepart == null) ? 0 : frmsTradeDepart.hashCode());
		result = prime * result + ((frmsTradeFrom == null) ? 0 : frmsTradeFrom.hashCode());
		result = prime * result + ((frmsTradeMode == null) ? 0 : frmsTradeMode.hashCode());
		result = prime * result + ((frmsTradeSrc == null) ? 0 : frmsTradeSrc.hashCode());
		result = prime * result + ((frmsTradeType == null) ? 0 : frmsTradeType.hashCode());
		result = prime * result + ((frmsTransStatus == null) ? 0 : frmsTransStatus.hashCode());
		result = prime * result + ((frmsTransTime == null) ? 0 : frmsTransTime.hashCode());
		result = prime * result + ((frmsTransVol == null) ? 0 : frmsTransVol.hashCode());
		result = prime * result + ((frmsUserId == null) ? 0 : frmsUserId.hashCode());
		result = prime * result + ((frmsUserIdCard == null) ? 0 : frmsUserIdCard.hashCode());
		result = prime * result + ((frmsUserName == null) ? 0 : frmsUserName.hashCode());
		result = prime * result + ((frmsUserPhone == null) ? 0 : frmsUserPhone.hashCode());
		result = prime * result + ((frmsUserType == null) ? 0 : frmsUserType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((notifyStrategy == null) ? 0 : notifyStrategy.hashCode());
		result = prime * result + ((operUserId == null) ? 0 : operUserId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((payBankCardNo == null) ? 0 : payBankCardNo.hashCode());
		result = prime * result + ((payUserId == null) ? 0 : payUserId.hashCode());
		result = prime * result + ((recTerminalId == null) ? 0 : recTerminalId.hashCode());
		result = prime * result + ((recUserId == null) ? 0 : recUserId.hashCode());
		result = prime * result + ((redundantFields1 == null) ? 0 : redundantFields1.hashCode());
		result = prime * result + ((redundantFields2 == null) ? 0 : redundantFields2.hashCode());
		result = prime * result + ((redundantFields3 == null) ? 0 : redundantFields3.hashCode());
		result = prime * result + ((riskLevel == null) ? 0 : riskLevel.hashCode());
		result = prime * result + ((rulesCode == null) ? 0 : rulesCode.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transVol == null) ? 0 : transVol.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((verifiStrategy == null) ? 0 : verifiStrategy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checklist other = (Checklist) obj;
		if (bizCode == null) {
			if (other.bizCode != null)
				return false;
		} else if (!bizCode.equals(other.bizCode))
			return false;
		if (checklistType == null) {
			if (other.checklistType != null)
				return false;
		} else if (!checklistType.equals(other.checklistType))
			return false;
		if (control == null) {
			if (other.control != null)
				return false;
		} else if (!control.equals(other.control))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (frmsBankCardNo == null) {
			if (other.frmsBankCardNo != null)
				return false;
		} else if (!frmsBankCardNo.equals(other.frmsBankCardNo))
			return false;
		if (frmsBankCardType == null) {
			if (other.frmsBankCardType != null)
				return false;
		} else if (!frmsBankCardType.equals(other.frmsBankCardType))
			return false;
		if (frmsBillFlay == null) {
			if (other.frmsBillFlay != null)
				return false;
		} else if (!frmsBillFlay.equals(other.frmsBillFlay))
			return false;
		if (frmsCdhpAmt == null) {
			if (other.frmsCdhpAmt != null)
				return false;
		} else if (!frmsCdhpAmt.equals(other.frmsCdhpAmt))
			return false;
		if (frmsCdhpBh == null) {
			if (other.frmsCdhpBh != null)
				return false;
		} else if (!frmsCdhpBh.equals(other.frmsCdhpBh))
			return false;
		if (frmsCdhpStatus == null) {
			if (other.frmsCdhpStatus != null)
				return false;
		} else if (!frmsCdhpStatus.equals(other.frmsCdhpStatus))
			return false;
		if (frmsColCardNo == null) {
			if (other.frmsColCardNo != null)
				return false;
		} else if (!frmsColCardNo.equals(other.frmsColCardNo))
			return false;
		if (frmsColCustAcct == null) {
			if (other.frmsColCustAcct != null)
				return false;
		} else if (!frmsColCustAcct.equals(other.frmsColCustAcct))
			return false;
		if (frmsColName == null) {
			if (other.frmsColName != null)
				return false;
		} else if (!frmsColName.equals(other.frmsColName))
			return false;
		if (frmsCompanyName == null) {
			if (other.frmsCompanyName != null)
				return false;
		} else if (!frmsCompanyName.equals(other.frmsCompanyName))
			return false;
		if (frmsCryType == null) {
			if (other.frmsCryType != null)
				return false;
		} else if (!frmsCryType.equals(other.frmsCryType))
			return false;
		if (frmsCustName == null) {
			if (other.frmsCustName != null)
				return false;
		} else if (!frmsCustName.equals(other.frmsCustName))
			return false;
		if (frmsDataType == null) {
			if (other.frmsDataType != null)
				return false;
		} else if (!frmsDataType.equals(other.frmsDataType))
			return false;
		if (frmsDueDate == null) {
			if (other.frmsDueDate != null)
				return false;
		} else if (!frmsDueDate.equals(other.frmsDueDate))
			return false;
		if (frmsFreId == null) {
			if (other.frmsFreId != null)
				return false;
		} else if (!frmsFreId.equals(other.frmsFreId))
			return false;
		if (frmsMerchName == null) {
			if (other.frmsMerchName != null)
				return false;
		} else if (!frmsMerchName.equals(other.frmsMerchName))
			return false;
		if (frmsMerchSeq == null) {
			if (other.frmsMerchSeq != null)
				return false;
		} else if (!frmsMerchSeq.equals(other.frmsMerchSeq))
			return false;
		if (frmsMerchStat == null) {
			if (other.frmsMerchStat != null)
				return false;
		} else if (!frmsMerchStat.equals(other.frmsMerchStat))
			return false;
		if (frmsOperName == null) {
			if (other.frmsOperName != null)
				return false;
		} else if (!frmsOperName.equals(other.frmsOperName))
			return false;
		if (frmsOperNo == null) {
			if (other.frmsOperNo != null)
				return false;
		} else if (!frmsOperNo.equals(other.frmsOperNo))
			return false;
		if (frmsOperStatus == null) {
			if (other.frmsOperStatus != null)
				return false;
		} else if (!frmsOperStatus.equals(other.frmsOperStatus))
			return false;
		if (frmsOrderChnl == null) {
			if (other.frmsOrderChnl != null)
				return false;
		} else if (!frmsOrderChnl.equals(other.frmsOrderChnl))
			return false;
		if (frmsOrderType == null) {
			if (other.frmsOrderType != null)
				return false;
		} else if (!frmsOrderType.equals(other.frmsOrderType))
			return false;
		if (frmsPayDetail == null) {
			if (other.frmsPayDetail != null)
				return false;
		} else if (!frmsPayDetail.equals(other.frmsPayDetail))
			return false;
		if (frmsSerialsNo == null) {
			if (other.frmsSerialsNo != null)
				return false;
		} else if (!frmsSerialsNo.equals(other.frmsSerialsNo))
			return false;
		if (frmsSignDateMob == null) {
			if (other.frmsSignDateMob != null)
				return false;
		} else if (!frmsSignDateMob.equals(other.frmsSignDateMob))
			return false;
		if (frmsSignDateNet == null) {
			if (other.frmsSignDateNet != null)
				return false;
		} else if (!frmsSignDateNet.equals(other.frmsSignDateNet))
			return false;
		if (frmsSignMobNum == null) {
			if (other.frmsSignMobNum != null)
				return false;
		} else if (!frmsSignMobNum.equals(other.frmsSignMobNum))
			return false;
		if (frmsTradeDepart == null) {
			if (other.frmsTradeDepart != null)
				return false;
		} else if (!frmsTradeDepart.equals(other.frmsTradeDepart))
			return false;
		if (frmsTradeFrom == null) {
			if (other.frmsTradeFrom != null)
				return false;
		} else if (!frmsTradeFrom.equals(other.frmsTradeFrom))
			return false;
		if (frmsTradeMode == null) {
			if (other.frmsTradeMode != null)
				return false;
		} else if (!frmsTradeMode.equals(other.frmsTradeMode))
			return false;
		if (frmsTradeSrc == null) {
			if (other.frmsTradeSrc != null)
				return false;
		} else if (!frmsTradeSrc.equals(other.frmsTradeSrc))
			return false;
		if (frmsTradeType == null) {
			if (other.frmsTradeType != null)
				return false;
		} else if (!frmsTradeType.equals(other.frmsTradeType))
			return false;
		if (frmsTransStatus == null) {
			if (other.frmsTransStatus != null)
				return false;
		} else if (!frmsTransStatus.equals(other.frmsTransStatus))
			return false;
		if (frmsTransTime == null) {
			if (other.frmsTransTime != null)
				return false;
		} else if (!frmsTransTime.equals(other.frmsTransTime))
			return false;
		if (frmsTransVol == null) {
			if (other.frmsTransVol != null)
				return false;
		} else if (!frmsTransVol.equals(other.frmsTransVol))
			return false;
		if (frmsUserId == null) {
			if (other.frmsUserId != null)
				return false;
		} else if (!frmsUserId.equals(other.frmsUserId))
			return false;
		if (frmsUserIdCard == null) {
			if (other.frmsUserIdCard != null)
				return false;
		} else if (!frmsUserIdCard.equals(other.frmsUserIdCard))
			return false;
		if (frmsUserName == null) {
			if (other.frmsUserName != null)
				return false;
		} else if (!frmsUserName.equals(other.frmsUserName))
			return false;
		if (frmsUserPhone == null) {
			if (other.frmsUserPhone != null)
				return false;
		} else if (!frmsUserPhone.equals(other.frmsUserPhone))
			return false;
		if (frmsUserType == null) {
			if (other.frmsUserType != null)
				return false;
		} else if (!frmsUserType.equals(other.frmsUserType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notifyStrategy == null) {
			if (other.notifyStrategy != null)
				return false;
		} else if (!notifyStrategy.equals(other.notifyStrategy))
			return false;
		if (operUserId == null) {
			if (other.operUserId != null)
				return false;
		} else if (!operUserId.equals(other.operUserId))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (payBankCardNo == null) {
			if (other.payBankCardNo != null)
				return false;
		} else if (!payBankCardNo.equals(other.payBankCardNo))
			return false;
		if (payUserId == null) {
			if (other.payUserId != null)
				return false;
		} else if (!payUserId.equals(other.payUserId))
			return false;
		if (recTerminalId == null) {
			if (other.recTerminalId != null)
				return false;
		} else if (!recTerminalId.equals(other.recTerminalId))
			return false;
		if (recUserId == null) {
			if (other.recUserId != null)
				return false;
		} else if (!recUserId.equals(other.recUserId))
			return false;
		if (redundantFields1 == null) {
			if (other.redundantFields1 != null)
				return false;
		} else if (!redundantFields1.equals(other.redundantFields1))
			return false;
		if (redundantFields2 == null) {
			if (other.redundantFields2 != null)
				return false;
		} else if (!redundantFields2.equals(other.redundantFields2))
			return false;
		if (redundantFields3 == null) {
			if (other.redundantFields3 != null)
				return false;
		} else if (!redundantFields3.equals(other.redundantFields3))
			return false;
		if (riskLevel == null) {
			if (other.riskLevel != null)
				return false;
		} else if (!riskLevel.equals(other.riskLevel))
			return false;
		if (rulesCode == null) {
			if (other.rulesCode != null)
				return false;
		} else if (!rulesCode.equals(other.rulesCode))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transVol == null) {
			if (other.transVol != null)
				return false;
		} else if (!transVol.equals(other.transVol))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (verifiStrategy == null) {
			if (other.verifiStrategy != null)
				return false;
		} else if (!verifiStrategy.equals(other.verifiStrategy))
			return false;
		return true;
	}
	
}