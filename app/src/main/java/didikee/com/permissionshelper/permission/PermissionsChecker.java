package didikee.com.permissionshelper.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by didik on 2016/8/5.
 */
public class PermissionsChecker {

    private Activity mActivity;

    public PermissionsChecker(Activity activity) {
        this.mActivity=activity;
    }

    /**
     * 是否需要检测权限
     * @return
     */
    public boolean shouldCheckPermission(){
        if (Build.VERSION.SDK_INT>=23){
            return true;
        }
        return false;
    }

    /**
     * 判断是否用于某项权限
     *
     * @param permission
     * @return true
     *         false
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkSelfPermission(String permission){
       return ContextCompat.checkSelfPermission(mActivity,permission)== PackageManager.PERMISSION_GRANTED;
    }

    public void onDestroy(){
        mActivity=null;
    }

    /**
     *
     * @param permissions
     * @return true 拥有所有权限,否则返回 false
     */
    public boolean checkSelfPermissions(String... permissions) {
        if (permissions.length==0){
            return false;
        }
        for (String permission : permissions) {
            if (!checkSelfPermission(permission)) {
                return false;
            }
        }
        return true;
    }


}
