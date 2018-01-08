package com.wk.cd.remote.sc;

import com.wk.db.EnumValue;

/**
 * Created by ½ªÖ¾¸Õ on 2016/11/12.
 */
public class SCType extends EnumValue<SCType> {


	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3382564860965889499L;

	private SCType(int value, String name) {
        super(value, name);
    }

    public static final SCType SVN = new SCType(1, "SVN");

}
