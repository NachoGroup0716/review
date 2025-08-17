package com.file.transfer.constants;

public enum TABLE_01 implements FileConstants {
	LK_SN("LK_SN"),
	LK_TRAN_ID("LK_TRAN_ID"),
	LK_IF_ID("LK_IF_ID"),
	LK_PRCS_CD("LK_PRCS_CD"),
	LK_S_INST_CD("LK_S_INST_CD"),
	LK_S_SYS_CD("LK_S_SYS_CD"),
	SND_DPCD("SND_DPCD"),
	LK_R_INST_CD("LK_R_INST_CD"),
	LK_R_SYS_CD("LK_R_SYS_CD"),
	RCV_DPCD("RCV_DPCD"),
	DAT_CRT_DT("DAT_CRT_DT"),
	LK_STAT_PRCS_DT("LK_STAT_PRCS_DT"),
	LK_TRN_PRCS_CD("LK_TRN_PRCS_CD"),
	LK_ERR_CN("LK_ERR_CN"),
	ORG_FILE_PATH_NM("ORG_FILE_PATH_NM"),
	ORG_FILE_NM("ORG_FILE_NM"),
	ORG_FILE_SIZE("ORG_FILE_SIZE"),
	EDI_BUSN_CD("EDI_BUSN_CD"),
	SND_FILE_NM("SND_FILE_NM"),
	LINK_FAIL_CNT("LINK_FAIL_CNT"),
	DOC_CD("DOC_CD"),
	CERT_ID("CERT_ID"),
	MSG_ID("MSG_ID");
	
	private String name;
	private String fullname;
	
	private TABLE_01(String name) {
		this.name = name;
		this.fullname = "TABLE_01." + name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getFullname() {
		return this.fullname;
	}
}
