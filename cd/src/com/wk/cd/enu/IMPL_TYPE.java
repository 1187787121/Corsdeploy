package com.wk.cd.enu;

import com.wk.db.EnumValue;

/**
 * Module实现类型 Created by 姜志刚 on 2016/11/3.
 */
public class IMPL_TYPE
		extends EnumValue<IMPL_TYPE> {

	/**
	 * @Fields serialVersionUID : -6845168958810421586L
	 */
	private static final long serialVersionUID = -6845168958810421586L;

	private IMPL_TYPE(int value, String name) {
		super(value, name);
	}


	/**
	 * FTP
	 */
	public static final IMPL_TYPE FTP = new IMPL_TYPE(1, "FTP");

	/**
	 * SHELL
	 */
	public static final IMPL_TYPE SHELL = new IMPL_TYPE(2, "SHELL");

	/**
	 * WAS
	 */
	public static final IMPL_TYPE WAS = new IMPL_TYPE(3, "WAS");

	/**
	 * SVN
	 */
	public static final IMPL_TYPE SVN = new IMPL_TYPE(4, "SVN");

	/**
	 * WEBLOGIC
	 */
	public static final IMPL_TYPE WEBLOGIC = new IMPL_TYPE(5, "WEBLOGIC");
	
	/**
	 * SQL
	 */
	public static final IMPL_TYPE SQL = new IMPL_TYPE(6, "SQL");
	
	/**
	 * PYTHON2
	 */
	public static final IMPL_TYPE PYTHON2 = new IMPL_TYPE(7, "PYTHON2");
	
	/**
	 * PYTHON3
	 */
	public static final IMPL_TYPE PYTHON3 = new IMPL_TYPE(8, "PYTHON3");
	
	/**
	 * CONFIG
	 */
	public static final IMPL_TYPE CONFIG = new IMPL_TYPE(9, "CONFIG");
	
	/**
	 * MANUAL
	 */
	public static final IMPL_TYPE MANUAL = new IMPL_TYPE(10, "MANUAL");

	/**
	 * BAT
	 */
	public static final IMPL_TYPE BAT = new IMPL_TYPE(11, "BAT");
	
	/**
	 * Firefly
	 */
	public static final IMPL_TYPE FIREFLY = new IMPL_TYPE(12, "FIREFLY");
	
	/**
	 * ClearCase
	 */
	public static final IMPL_TYPE CLEARCASE = new IMPL_TYPE(13, "CLEARCASE");
}
