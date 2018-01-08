package com.wk.cd.module1.info;


import com.wk.cd.module1.info.ParamInfo;
import com.wk.util.StringUtil;

/**
 * Created by ½ªÖ¾¸Õ on 2016/11/4.
 */
public class Param {

    private ParamInfo info;

    private String singleValue;

    private String[] listValue;

    public Param(ParamInfo info) {
        this.info = info;
    }

    public Param(ParamInfo info, String singleValue) {
        this.info = info;
        this.singleValue = singleValue;
    }

    public Param(ParamInfo info, String[] listValue) {
        this.info = info;
        this.listValue = listValue;
    }

    public boolean hasGroup() {
        return !StringUtil.isEmpty(info.getParam_group());
    }

    public String getName() {
        return info.getParam_name();
    }

    public boolean isList() {
        return listValue != null;
    }

    public int size() {
        return isList() ? listValue.length : 1;
    }

    public ParamInfo getInfo() {
        return info;
    }

    public void setInfo(ParamInfo info) {
        this.info = info;
    }

    public String getSingleValue() {
        return singleValue;
    }

    public void setSingleValue(String singleValue) {
        this.singleValue = singleValue;
    }

    public String[] getListValue() {
        return isList() ? listValue : new String[]{singleValue};
    }

    public void setListValue(String[] listValue) {
        this.listValue = listValue;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (isList()) {
            sb.append("{").append(getName()).append(" : [");
            for (String s : getListValue()) {
                sb.append("\"").append(s).append("\"").append(",");
            }
            if (size() > 0) {
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append("]}");
        } else {
            sb.append("{").append(getName()).append(" : \"").append(singleValue).append("\"}");
        }
        return sb.toString();
    }
}
