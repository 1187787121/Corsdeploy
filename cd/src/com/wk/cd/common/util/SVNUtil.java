/**
 * Title: SVNUtil.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年11月6日
 */
package com.wk.cd.common.util;

import java.io.File;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.svn.ISVNConnector;
import org.tmatesoft.svn.core.internal.util.SVNHashMap;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNMerger;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.internal.wc.SVNDiffConflictChoiceStyle;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.wc.ISVNConflictHandler;
import org.tmatesoft.svn.core.wc.ISVNMerger;
import org.tmatesoft.svn.core.wc.ISVNMergerFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;

import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author yangl
 */
public class SVNUtil {

	private static final String DEFAULT_IGNORES = "*.o *.lo *.la *.al .libs *.so *.so.[0-9]* *.a *.pyc *.pyo *.rej *~ #*# .#* .*.swp .DS_Store";
	private static final String DEFAULT_LOCALE = Locale.getDefault().toString();
	private static final String DEFAULT_TIMEZONE = TimeZone.getDefault().getID();

	private static final Log logger = LogFactory.getLog();
	private static final SVNOptions OPTIONS = new SVNOptions();

	public static void export(DtSourceInfo version_soc_info, String remote_path, String local_dir)
		throws SVNException {
		StringBuilder url = new StringBuilder();
		url.append(version_soc_info.getBackup_fld()).append("://").append(version_soc_info.getSoc_ip()).append(":").append(version_soc_info.getSoc_port())
				.append(version_soc_info.getUser_root_path()).append(remote_path);
		logger.debug("svn export url: [{}]", url);
		String passed_key = DESUtil.docryptAllowReverse(false, null, version_soc_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, version_soc_info.getRemote_passwd());
		export(url.toString(), new File(local_dir), version_soc_info.getRemote_uname(), remote_passwd);
	}

	public static void export(String url, File dstDir, String username, String password)
		throws SVNException {
		SVNClientManager client_manager = SVNClientManager.newInstance(OPTIONS, username, password);
		SVNUpdateClient client = client_manager.getUpdateClient();
		if (!dstDir.exists()) {
			dstDir.mkdirs();
		}
		logger.info("SVN Export URL:[" + url + "] TO:[" + dstDir.getAbsolutePath() + "]");
		SVNURL svnurl = SVNURL.parseURIEncoded(url);
		client.doExport(svnurl, dstDir, SVNRevision.HEAD, SVNRevision.HEAD, null, true, SVNDepth.FILES);
	}

	/**
	 * Description: 检出文件
	 * @param version_soc_info
	 * @param url
	 * @param destPath
	 * @return
	 * @throws SVNException
	 */
	public static long checkout(DtSourceInfo version_soc_info, String remote_path, String destPath)
		throws SVNException {
		StringBuilder url = new StringBuilder();
		url.append(version_soc_info.getBackup_fld()).append("://").append(version_soc_info.getSoc_ip()).append(":").append(version_soc_info.getSoc_port())
				.append(version_soc_info.getUser_root_path()).append(remote_path);
		String passed_key = DESUtil.docryptAllowReverse(false, null, version_soc_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, version_soc_info.getRemote_passwd());
		SVNClientManager clientManager = SVNClientManager.newInstance(OPTIONS, version_soc_info.getRemote_uname(), remote_passwd);
		SVNUpdateClient updateClient = clientManager.getUpdateClient();
		File local_path = new File(destPath);
		if (!local_path.exists()) {
			local_path.mkdirs();
		}
		updateClient.setIgnoreExternals(false);
		SVNURL svnurl = SVNURL.parseURIEncoded(url.toString());
		return updateClient.doCheckout(svnurl, local_path, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.FILES, false);
	}

	/**
	 * Description: 加入版本控制
	 * @param clientManager
	 * @param wcPath
	 * @throws SVNException
	 */
	public static void addEntry(DtSourceInfo version_soc_info, String wcPath)
		throws SVNException {
		String passed_key = DESUtil.docryptAllowReverse(false, null, version_soc_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, version_soc_info.getRemote_passwd());
		SVNClientManager clientManager = SVNClientManager.newInstance(OPTIONS, version_soc_info.getRemote_uname(), remote_passwd);
		clientManager.getWCClient().doAdd(new File[] { new File(wcPath) }, true, false, false, SVNDepth.INFINITY, false, false, true);
	}

	/**
	 * Description: 提交变更文件到版本
	 * @param clientManager
	 * @param wcPath
	 * @param keepLocks
	 * @param commitMessage
	 * @return
	 * @throws SVNException
	 */
	public static SVNCommitInfo commit(DtSourceInfo version_soc_info, String wcPath, boolean keepLocks, String commitMessage)
		throws SVNException {
		String passed_key = DESUtil.docryptAllowReverse(false, null, version_soc_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, version_soc_info.getRemote_passwd());
		SVNClientManager clientManager = SVNClientManager.newInstance(OPTIONS, version_soc_info.getRemote_uname(), remote_passwd);
		return clientManager.getCommitClient().doCommit(new File[] { new File(wcPath) }, keepLocks, commitMessage, null, null, false, false, SVNDepth.INFINITY);
	}

	/**
	 * 获取当前目录下的子目录和文件
	 * @return
	 * @throws SVNException
	 */
	public static List<FileListBean> lsRemotePath(DtSourceInfo version_soc_info, String remote_path) {
		StringBuilder url = new StringBuilder();
		url.append(version_soc_info.getBackup_fld()).append("://").append(version_soc_info.getSoc_ip()).append(":").append(version_soc_info.getSoc_port())
				.append(remote_path.startsWith("/") ? remote_path : "/" + remote_path);
		String passed_key = DESUtil.docryptAllowReverse(false, null, version_soc_info.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, version_soc_info.getRemote_passwd());

		List<SVNDirEntry> svnDirList = listFolder(url.toString(), version_soc_info.getRemote_uname(), remote_passwd);

		List<FileListBean> file_bean_list = new ArrayList<FileListBean>();
		for (SVNDirEntry svnBean : svnDirList) {
			FileListBean fileBean = new FileListBean();
			if (svnBean.getKind().getID() == 0) {
				fileBean.setDir(true);
			} else {
				fileBean.setDir(false);
			}

			fileBean.setFile(svnBean.getName());
			file_bean_list.add(fileBean);
		}

		return file_bean_list;
	}

	/**
	 * 列出指定SVN 地址目录下的子目录
	 * @param url
	 * @return
	 * @throws SVNException
	 */
	@SuppressWarnings("unchecked")
	public static List<SVNDirEntry> listFolder(String url, String remote_name, String temote_password) {
		SVNClientManager client_manager = SVNClientManager.newInstance(OPTIONS, remote_name, remote_name);
		if (checkPath(client_manager, url) == 1) {
			SVNRepository repository = createRepository(client_manager, url);
			try {
				Collection<SVNDirEntry> list = repository.getDir("", -1, null, (List<SVNDirEntry>) null);
				List<SVNDirEntry> dirs = new ArrayList<SVNDirEntry>(list.size());
				dirs.addAll(list);
				return dirs;
			} catch (SVNException e) {
				logger.error("listFolder error", e);
			}

		}
		return null;
	}

	private static SVNRepository createRepository(SVNClientManager manager, String url) {

		try {
			return manager.createRepository(SVNURL.parseURIEncoded(url), true);
		} catch (SVNException e) {
			logger.error("createRepository error", e);
		}
		return null;
	}

	/**
	 * 检查路径是否存在
	 * @param url
	 * @return 1：存在 0：不存在 -1：出错
	 */
	public static int checkPath(SVNClientManager client_manager, String url) {
		SVNRepository repository = createRepository(client_manager, url);
		SVNNodeKind nodeKind;
		try {
			nodeKind = repository.checkPath("", -1);
			boolean result = nodeKind == SVNNodeKind.NONE ? false : true;
			if (result)
				return 1;
		} catch (SVNException e) {
			logger.error("checkPath error", e);
			return -1;
		}
		return 0;
	}

	/**
	 * Class Description: <b>这个类与Util的使用无关，不要修改它，无视就行。</b><br/>
	 * SVNKits内置的DefaultSVNOptions类会在环境中生成并使用.subversion目录，同时保存SVN的认证信息。<br/>
	 * 这个类覆盖了父类中ISVNOptions接口的相关方法，作用是避免生成及使用SVN配置文件，同时不再保存认证信息。<br/>
	 * @author yangl
	 */
	@SuppressWarnings("rawtypes")
	static class SVNOptions
			extends DefaultSVNOptions {

		private String myKeywordLocale = DEFAULT_LOCALE;
		private String myKeywordTimezone = DEFAULT_TIMEZONE;
		private SimpleDateFormat myKeywordDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss' 'ZZZZ' ('E', 'dd' 'MMM' 'yyyy')'");

		/**
		 * Description:
		 * @param location
		 * @return
		 */
		@Override
		public ISVNConnector createTunnelConnector(SVNURL url) {
			return null;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public boolean isUseCommitTimes() {
			return false;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public String[] getIgnorePatterns() {
			ArrayList<String> tokensList = new ArrayList<String>();
			for (StringTokenizer tokens = new StringTokenizer(DEFAULT_IGNORES, " \t"); tokens.hasMoreTokens();) {
				String token = tokens.nextToken();
				if ("".equals(token)) {
					continue;
				}
				tokensList.add(token);
			}
			return tokensList.toArray(new String[tokensList.size()]);
		}

		/**
		 * Description:
		 * @param file
		 * @param target
		 * @return
		 */
		@Override
		public Map applyAutoProperties(File file, Map target) {
			target = target == null ? new SVNHashMap() : target;
			return target;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public ISVNMergerFactory getMergerFactory() {
			return this;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public DateFormat getKeywordDateFormat() {
			String localeID = DEFAULT_LOCALE;
			String tzID = DEFAULT_TIMEZONE;
			if (!myKeywordTimezone.equals(tzID)) {
				TimeZone tz = TimeZone.getTimeZone(tzID);
				myKeywordTimezone = tzID;
				synchronized (myKeywordDateFormat) {
					myKeywordDateFormat.setTimeZone(tz);
				}
			}
			if (!myKeywordLocale.equals(localeID)) {
				Locale newLocale = toLocale(localeID);
				if (newLocale == null) {
					newLocale = Locale.getDefault();
				}
				myKeywordLocale = localeID;
				synchronized (myKeywordDateFormat) {
					myKeywordDateFormat.setCalendar(Calendar.getInstance(myKeywordDateFormat.getTimeZone(), newLocale));
					myKeywordDateFormat.setDateFormatSymbols(new DateFormatSymbols(newLocale));
				}
			}
			return myKeywordDateFormat;
		}

		private Locale toLocale(String str) {
			if (str == null) {
				return null;
			}
			int len = str.length();
			if (len != 2 && len != 5 && len < 7) {
				return null;
			}
			char ch0 = str.charAt(0);
			char ch1 = str.charAt(1);
			if (ch0 < 'a' || ch0 > 'z' || ch1 < 'a' || ch1 > 'z') {
				return null;
			}
			if (len == 2) {
				return new Locale(str, "");
			}
			if (str.charAt(2) != '_') {
				return null;
			}
			char ch3 = str.charAt(3);
			char ch4 = str.charAt(4);
			if (ch3 < 'A' || ch3 > 'Z' || ch4 < 'A' || ch4 > 'Z') {
				return null;
			}
			if (len == 5) {
				return new Locale(str.substring(0, 2), str.substring(3, 5));
			}
			if (str.charAt(5) != '_') {
				return null;
			}
			return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public String[] getPreservedConflictFileExtensions() {
			ArrayList<String> tokensList = new ArrayList<String>();
			for (StringTokenizer tokens = new StringTokenizer("", " \n\r\t"); tokens.hasMoreTokens();) {
				String token = tokens.nextToken();
				if ("".equals(token)) {
					continue;
				}
				tokensList.add(token);
			}
			return tokensList.toArray(new String[tokensList.size()]);
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public boolean isAllowAllForwardMergesFromSelf() {
			return false;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public byte[] getNativeEOL() {
			return System.getProperty("line.separator").getBytes();
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public String getNativeCharset() {
			return System.getProperty("file.encoding");
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public Map getFileExtensionsToMimeTypes() {
			return null;
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public ISVNConflictHandler getConflictResolver() {
			return null;
		}

		/**
		 * Description:
		 * @param conflictStart
		 * @param conflictSeparator
		 * @param conflictEnd
		 * @return
		 */
		@Override
		public ISVNMerger createMerger(byte[] conflictStart, byte[] conflictSeparator, byte[] conflictEnd) {
			return new DefaultSVNMerger(conflictStart, conflictSeparator, conflictEnd, null, SVNDiffConflictChoiceStyle.CHOOSE_MODIFIED_LATEST);
		}

		/**
		 * Description:
		 * @return
		 */
		@Override
		public boolean isAuthStorageEnabled() {
			return false;
		}

	}
}
