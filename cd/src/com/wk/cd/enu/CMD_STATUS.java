package com.wk.cd.enu;

import com.wk.cd.enu.CMD_STATUS;
import com.wk.db.EnumValue;

public class CMD_STATUS extends EnumValue<CMD_STATUS> {
	private static final long serialVersionUID = -7404174511524061509L;
	
	public static final CMD_STATUS NOBEGIN = new CMD_STATUS(1, "未开始");

	public static final CMD_STATUS RUNNING = new CMD_STATUS(2, "正在执行");

	public static final CMD_STATUS ERROR = new CMD_STATUS(3, "执行报错");

	public static final CMD_STATUS CHKFAILED = new CMD_STATUS(4, "验证失败");

	public static final CMD_STATUS SUCCEED = new CMD_STATUS(5, "执行成功");

	public static final CMD_STATUS SKIP = new CMD_STATUS(6, "执行跳过");
	
	public static final CMD_STATUS STOP = new CMD_STATUS(7, "执行停止");
	
	private CMD_STATUS(int value, String name) {
		super(value, name);
	}
}