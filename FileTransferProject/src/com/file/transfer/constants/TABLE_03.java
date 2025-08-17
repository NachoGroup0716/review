package com.file.transfer.constants;

public enum TABLE_03 implements FileConstants {
	LK_IF_ID("LK_IF_ID"),
	LK_IF_ID_NM("LK_IF_ID_NM"),
	LK_S_INST_CD("LK_S_INST_CD"),
	LK_S_INST_NM("LK_S_INST_NM"),
	LK_S_SYS_CD("LK_S_SYS_CD"),
	LK_S_SYS_NM("LK_S_SYS_NM"),
	LK_R_INST_CD("LK_R_INST_CD"),
	LK_R_INST_NM("LK_R_INST_NM"),
	LK_R_SYS_CD("LK_R_SYS_CD"),
	LK_R_SYS_NM("LK_R_SYS_NM"),
	LK_PRCS_TYPE("LK_PRCS_TYPE"),
	LK_TYPE("LK_TYPE"),
	LK_MTHD_CD("LK_MTHD_CD"),
	LK_S_RCPTN_CD("LK_S_RCPTN_CD"),
	LK_TRSM_CYL("LK_TRSM_CYL"),
	LK_SER_DOC_CD("LK_SER_DOC_CD"),
	TSM_FLE_CSR_NM("TSM_FLE_CSR_NM"),
	TSM_FLE_NM("TSM_FLE_NM"),
	TSM_EDI_FLE_NM("TSM_EDI_FLE_NM"),
	RMRK("RMRK"),
	LK_GRP_IF_ID("LK_GRP_IF_ID"),
	EDI_ADDR("EDI_ADDR"),
	LK_EDI_BUSN_CD("LK_EDI_BUSN_CD"),
	TAR_YN("TAR_YN"),
	LINK_MTHD_CD("LINK_MTHD_CD"),
	LK_API_DATA_TYPE("LK_API_DATA_TYPE"),
	LK_API_TARGET_URL("LK_API_TARGET_URL"),
	LK_YN("LK_YN"),
	EDI_INST_GROUP_CD("EDI_INST_GROUP_CD"),
	LK_ENCRYPT_USE("LK_ENCRYPT_USE"),
	LK_NAMESPACE_XML("LK_NAMESPACE_XML"),
	LK_XMLNS_USE("LK_XMLNS_USE"),
	LK_PREFIX_TAG_USE("LK_PREFIX_TAG_USE"),
	LK_REQ_BODY_XML("LK_REQ_BODY_XML"),
	LK_RES_BODY_XML("LK_RES_BODY_XML"),
	BIZ_SRVC_ID("BIZ_SRVC_ID"),
	BIZ_SRVC_METHOD("BIZ_SRVC_METHOD"),
	BATCH_JOB_ID("BATCH_JOB_ID"),
	OPEN_YN("OPEN_YN");
	
	private String name;
	private String fullname;
	
	private TABLE_03(String name) {
		this.name = name;
		this.fullname = "TABLE_03." + name;
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
