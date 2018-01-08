/**
 * @copyright 2014
 * @company CORSWORK
 * @version 1.0
 */

package com.wk.cd.license;

import com.wk.cd.license.CheckService;
import com.wk.lang.Inject;

/**
 * Class Description:
 * Created by lixl on 16-10-14.
 */
public class LicenseCheckService {
    @Inject private CheckService cksvc;
    public void checkLicense(){
        cksvc.checkLicense();
    }
}
