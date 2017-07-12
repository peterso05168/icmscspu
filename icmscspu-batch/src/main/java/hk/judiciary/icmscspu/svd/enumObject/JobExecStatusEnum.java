package hk.judiciary.icmscspu.svd.enumObject;

public enum JobExecStatusEnum {
	SUCCESS("S"),
	PARTIAL_SUCCESS("P"),
	FAIL("F");
	private String code;
	private JobExecStatusEnum(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
