INSERT INTO us_role_rs_priv (DPRL_CODE, RS_CODE, BACKUP_FLD) select '11000101',rs_code, '' from RS_RES WHERE PUBLIC_YN_FLAG = 2 AND RCD_STATE = 1;
INSERT INTO dp_dept_rs_priv (DEPT_ID, RS_CODE, BACKUP_FLD) select '110001',rs_code, '' from RS_RES WHERE PUBLIC_YN_FLAG = 2 AND RCD_STATE = 1;