package kr.co.myshop.vo;
//결제 클래스
public class Payment {
	private int salePayNo;	//(auto)결제번호
	private String payMethod;	//+결제수단
	private String payCom;		//+결제 대행사
	private String cardNum;		//+결제카드(계좌)번호
	private int payAmount;		//+결제금액
	public int getSalePayNo() {
		return salePayNo;
	}
	public void setSalePayNo(int salePayNo) {
		this.salePayNo = salePayNo;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getPayCom() {
		return payCom;
	}
	public void setPayCom(String payCom) {
		this.payCom = payCom;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
}