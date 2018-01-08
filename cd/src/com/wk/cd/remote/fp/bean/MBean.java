package com.wk.cd.remote.fp.bean;

import com.wk.cd.exc.CannotCloneException;
import java.util.List;

public class MBean implements Cloneable {
	private String work_seq;
	private String file_name;
	private long file_size;
	private long trans_size;
	private long trans_speed;
	private int all_num;
	private int trans_num;
	private List<String> all_file;

	public String getWork_seq() {
		return this.work_seq;
	}

	public void setWork_seq(String work_seq) {
		this.work_seq = work_seq;
	}

	public String getFile_name() {
		return this.file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public long getFile_size() {
		return this.file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public long getTrans_size() {
		return this.trans_size;
	}

	public void setTrans_size(long trans_size) {
		this.trans_size = trans_size;
	}

	public long getTrans_speed() {
		return this.trans_speed;
	}

	public void setTrans_speed(long trans_speed) {
		this.trans_speed = trans_speed;
	}

	public int getAll_num() {
		return this.all_num;
	}

	public void setAll_num(int all_num) {
		this.all_num = all_num;
	}

	public int getTrans_num() {
		return this.trans_num;
	}

	public void setTrans_num(int trans_num) {
		this.trans_num = trans_num;
	}

	public List<String> getAll_file() {
		return this.all_file;
	}

	public void setAll_file(List<String> all_file) {
		this.all_file = all_file;
	}

	public MBean clone() {
		MBean m = null;
		try {
			m = (MBean) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new CannotCloneException().addScene("CLASS", "MBean");
		}

		return m;
	}
}