package kr.co.myshop.vo;
//배송 클래스
public class Parsel {
	private int parselNo;	//(auto)배송코드
	private String parselAddr;	//+배송지
	private String cusTel;		//+고객연락처
	private String parselCompany; //(admin)배송회사
	private String parselTel;	//(admin)배송기사연락처
	private int parselState;	//(0:배송전,1:배송중,2:도착,3:구매결정)-배송상태
	private String baleCode;	//화물코드
	public int getParselNo() {
		return parselNo;
	}
	public void setParselNo(int parselNo) {
		this.parselNo = parselNo;
	}
	public String getParselAddr() {
		return parselAddr;
	}
	public void setParselAddr(String parselAddr) {
		this.parselAddr = parselAddr;
	}
	public String getCusTel() {
		return cusTel;
	}
	public void setCusTel(String cusTel) {
		this.cusTel = cusTel;
	}
	public String getParselCompany() {
		return parselCompany;
	}
	public void setParselCompany(String parselCompany) {
		this.parselCompany = parselCompany;
	}
	public String getParselTel() {
		return parselTel;
	}
	public void setParselTel(String parselTel) {
		this.parselTel = parselTel;
	}
	public int getParselState() {
		return parselState;
	}
	public void setParselState(int parselState) {
		this.parselState = parselState;
	}
	public String getBaleCode() {
		return baleCode;
	}
	public void setBaleCode(String baleCode) {
		this.baleCode = baleCode;
	}
	public String getParselUrl(String parselCompany) {
		String parselUrl = "";
		if(parselCompany.equals("CJ대한통운")) {
			parselUrl = "https://www.doortodoor.co.kr/parcel/doortodoor.do?fsp_action=PARC_ACT_002&fsp_cmd=retrieveInvNoACT&invc_no=";
		} else if(parselCompany.equals("롯데택배")){
			parselUrl = "https://www.lotteglogis.com/mobile/reservation/tracking/linkView?InvNo=";
		} else if(parselCompany.equals("우체국택배")){
			parselUrl = "https://service.epost.go.kr/trace.RetrieveDomRigiTraceList.comm?displayHeader=N&sid1=";
		} else if(parselCompany.equals("로젠택배")){
			parselUrl = "https://www.ilogen.com/m/personal/trace/";
		} else if(parselCompany.equals("한진택배")){
			parselUrl = "https://www.hanjin.co.kr/kor/CMS/DeliveryMgr/WaybillResult.do?mCode=MN038&schLang=KR&wblnumText2=";
		} else if(parselCompany.equals("CU 편의점택배")){
			parselUrl = "https://www.cupost.co.kr/postbox/delivery/localResult.cupost?invoice_no=";
		} else if(parselCompany.equals("EMS 택배")){
			parselUrl = "http://service.epost.go.kr/trace.RetrieveEmsTrace.postal?ems_gubun=E&POST_CODE=";
		} else if(parselCompany.equals("경동택배")){
			parselUrl = "http://kdexp.com/basicNewDelivery.kd?barcode=";
		} else {
			parselUrl = "http://www.naver.com";
		}
		return parselUrl;
	}	
}