/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wk.cd.remote.sc.service;

import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.sc.SCRSession;
import com.wk.cd.remote.sc.bean.SCBean;
import com.wk.cd.remote.sh.service.JSchRCallService;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.TelnetRCallService;
import com.wk.lang.Inject;
import com.wk.logging.Log;

public class SVNCallService implements SCCallService {

	@Inject
	private Log logger;

	@Inject
	private JSchRCallService ssh_svc;

	@Inject
	private TelnetRCallService telnet_svc;

	public SCRSession connect(SCBean bean) {
		PROTOCOL_TYPE proto_type = bean.getProtocol_type();
		RSession remote_sess;
		if (PROTOCOL_TYPE.SSH == proto_type) {
			RConnection conn = this.ssh_svc.getConnection(bean);
			remote_sess = conn.openShellSession();
		} else {
			if (PROTOCOL_TYPE.TELNET == proto_type) {
				RConnection conn = this.telnet_svc.getConnection(bean);
				remote_sess = conn.openShellSession();
			} else {
				throw new RuntimeException("ProtoType[" + proto_type
						+ "] not svn protocol type");
			}
		}
		return new SVNSession(remote_sess, bean);
	}
	
	public SCRSession connect(SCBean bean, int step_count) {
		  PROTOCOL_TYPE proto_type = bean.getProtocol_type();
	        RSession remote_sess;
	        if (PROTOCOL_TYPE.SSH == proto_type) {
	            RConnection conn = ssh_svc.getConnection(bean);
	            remote_sess = conn.openShellSession();
	        } else if (PROTOCOL_TYPE.TELNET == proto_type) {
	            RConnection conn = telnet_svc.getConnection(bean);
	            remote_sess = conn.openShellSession();
	        } else if(proto_type == PROTOCOL_TYPE.AGENT){
	        	remote_sess = new AgentRSession(bean.getSoc_ip(), bean.getSoc_port(),IMPL_TYPE.SHELL,step_count, AgentRSession.SYNCHRO_TYPE, true, "common/");
			} else {
	            throw new RuntimeException("ProtoType[" + proto_type + "] not svn protocol type");
	        }
	        return new SVNSession(remote_sess, bean);
	}
}