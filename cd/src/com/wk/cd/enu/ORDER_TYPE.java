/**
* Title: ORDER_TYPE.java
* File Description: ��������
* @copyright 2015
* @company CORSWORK
* @author AutoGen
* @version 1.0
* @date 2015-10-12
*/
package com.wk.cd.enu;
import com.wk.db.EnumValue;
/**
* Class Description: ��������
* @author AutoGen
*/
public class ORDER_TYPE
        extends EnumValue<ORDER_TYPE> {

    /** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4571389261701367148L;

	/**
    * ���캯��
    */

    private ORDER_TYPE(int value, String name) {
        super(value, name);
    }

    public static final String ENUMCN = "��������";

    /**
    * 1 ����
    */
    public static final ORDER_TYPE ASC = new ORDER_TYPE(1, "����");

    /**
    * 2 ����
    */
    public static final ORDER_TYPE DESC = new ORDER_TYPE(2, "����");
}
