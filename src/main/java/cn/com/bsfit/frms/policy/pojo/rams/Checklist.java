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

	private String rulesCode;

	public Checklist() {
		super();
	}

	public Checklist(Long id, String orderId, Date createTime, Date updateTime, Long transVol, Long riskLevel,
			Integer status, Long operUserId, String verifiStrategy, String notifyStrategy, Integer score,
			String payUserId, String recUserId, String payBankCardNo, String recTerminalId, String redundantFields1,
			String redundantFields2, String redundantFields3, String control, String bizCode, String rulesCode) {
		super();
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
		this.rulesCode = rulesCode;
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
		this.orderId = orderId;
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
		this.verifiStrategy = verifiStrategy;
	}

	public String getNotifyStrategy() {
		return notifyStrategy;
	}

	public void setNotifyStrategy(String notifyStrategy) {
		this.notifyStrategy = notifyStrategy;
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
		this.payUserId = payUserId;
	}

	public String getRecUserId() {
		return recUserId;
	}

	public void setRecUserId(String recUserId) {
		this.recUserId = recUserId;
	}

	public String getPayBankCardNo() {
		return payBankCardNo;
	}

	public void setPayBankCardNo(String payBankCardNo) {
		this.payBankCardNo = payBankCardNo;
	}

	public String getRecTerminalId() {
		return recTerminalId;
	}

	public void setRecTerminalId(String recTerminalId) {
		this.recTerminalId = recTerminalId;
	}

	public String getRedundantFields1() {
		return redundantFields1;
	}

	public void setRedundantFields1(String redundantFields1) {
		this.redundantFields1 = redundantFields1;
	}

	public String getRedundantFields2() {
		return redundantFields2;
	}

	public void setRedundantFields2(String redundantFields2) {
		this.redundantFields2 = redundantFields2;
	}

	public String getRedundantFields3() {
		return redundantFields3;
	}

	public void setRedundantFields3(String redundantFields3) {
		this.redundantFields3 = redundantFields3;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getRulesCode() {
		return rulesCode;
	}

	public void setRulesCode(String rulesCode) {
		this.rulesCode = rulesCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bizCode == null) ? 0 : bizCode.hashCode());
		result = prime * result + ((control == null) ? 0 : control.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
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

	@Override
	public String toString() {
		return "Checklist [id=" + id + ", orderId=" + orderId + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", transVol=" + transVol + ", riskLevel=" + riskLevel + ", status=" + status
				+ ", operUserId=" + operUserId + ", verifiStrategy=" + verifiStrategy + ", notifyStrategy="
				+ notifyStrategy + ", score=" + score + ", payUserId=" + payUserId + ", recUserId=" + recUserId
				+ ", payBankCardNo=" + payBankCardNo + ", recTerminalId=" + recTerminalId + ", redundantFields1="
				+ redundantFields1 + ", redundantFields2=" + redundantFields2 + ", redundantFields3=" + redundantFields3
				+ ", control=" + control + ", bizCode=" + bizCode + ", rulesCode=" + rulesCode + "]";
	}
}