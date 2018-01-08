package com.wk.cd.remote.sh.service;

import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.ScriptExecErrorException;

import java.io.IOException;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class WasTelnetRCallService extends TelnetRCallService {
	
	@Override
	public RConnection connect(RBean bean) {
		try {
			TelnetClient client = new TelnetClient("cvtelnet");
			client.connect(bean.getSoc_ip(), bean.getSoc_port());
			return new WasTelnetConn(client, bean);
		} catch (IOException e) {
			logger.error(e.toString(), e);
			throw new ScriptExecErrorException().addScene("SCRIPT", "connect");
		}
	}

	private static class WasTelnetConn extends TelnetRCallService.TelnetConn {
		String was_params;

		private WasTelnetConn(TelnetClient conn, RBean bean) throws IOException {
			super(conn, bean);
			this.was_params = bean.getWas_params();
		}

		public void send(String value) throws IOException {
			String was_cmd;
			if (checkWasCmd(value))
				was_cmd = "wsadmin.sh -lang jython -c \"" + value + "\" "
						+ this.was_params;
			else {
				was_cmd = value;
			}

			super.send(was_cmd);
		}

		/**
		 * Description: 判断是否要拼接 要则为true 
		 * @return
		 */
		private boolean checkWasCmd(String cmd) {
			String[] list = { "AdminApp.", "AdminConfig.",
					"AdminNodeManagement.", "AdminControl.", "AdminTask." };

			for (String str : list) {
				if (cmd.startsWith(str)) {
					return true;
				}
			}
			return false;
		}
	}
}