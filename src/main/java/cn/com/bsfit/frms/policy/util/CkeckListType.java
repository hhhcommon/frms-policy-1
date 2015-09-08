package cn.com.bsfit.frms.policy.util;

/**
 * 核查单类型
 * 
 * @author admin
 *
 */
public enum CkeckListType {
	
	COUNTER(1, "柜台业务"),
	BANKCARD(2, "银行卡业务"),
	BUSINNESSCRAD(3, "公务卡业务");
	
	private Integer index;
	private String text;
	
	private CkeckListType(Integer index, String text) {
		this.index = index;
		this.text = text;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
