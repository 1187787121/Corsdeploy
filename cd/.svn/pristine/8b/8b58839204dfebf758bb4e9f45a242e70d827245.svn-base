package com.wk.cd.remote.sh.service;

import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.ScriptExecErrorException;

import java.io.IOException;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * Class Description:
 * @author HT
 */
public class WebLogicTelnetRCallService extends TelnetRCallService {
	
	@Override
	public RConnection connect(RBean bean) {
		try {
			TelnetClient client = new TelnetClient("cvtelnet");
			client.connect(bean.getSoc_ip(), bean.getSoc_port());
			return new WebLogicTelnetConn(client, bean);
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
		}
	}

	private static class WebLogicTelnetConn extends
			TelnetRCallService.TelnetConn {
		String weblogic_params;
		private static final String WEBLOGIC_STR = "weblogic.Deployer";

		private WebLogicTelnetConn(TelnetClient conn, RBean bean)
				throws IOException {
			super(conn, bean);
			this.weblogic_params = bean.getWas_params();
		}

		public void send(String cmd) throws IOException {
			int length = cmd.indexOf(WEBLOGIC_STR);
			String weblogic_cmd;
			if (length > 0) {
				StringBuffer sb = new StringBuffer(cmd);
				sb.insert(length + "weblogic.Deployer".length(), " "
						+ this.weblogic_params);
				weblogic_cmd = sb.toString();
			} else {
				weblogic_cmd = cmd;
			}
			logger.debug("send weblogic cmd:[{}]", weblogic_cmd);
			super.send(weblogic_cmd);
		}
	}
}