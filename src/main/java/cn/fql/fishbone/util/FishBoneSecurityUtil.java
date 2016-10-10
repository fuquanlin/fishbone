package cn.fql.fishbone.util;

import cn.fql.fishbone.FishBoneStore2;
import cn.fql.fishbone.model.domain.Permission;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuquanlin on 2016/9/29.
 */
public class FishBoneSecurityUtil {

    public static List<Permission> getPermisionsFromSubject(Subject currentUser) {
        List<Permission> myPermission = new ArrayList<>();
        for(Permission permission: FishBoneStore2.ALL_PERMSSIONS){
            if(currentUser.isPermitted(permission.getPermissioncode())){
                myPermission.add(permission);
            }
        }
//        boolean[] currentUserPermitted = currentUser.isPermitted(ALL_PERMISSION_CODES);
//        List<String> permissions = new ArrayList<>();
//        for (int i = 0; i < currentUserPermitted.length; i++) {
//            if (currentUserPermitted[i]) {
//                permissions.add(ALL_PERMISSION_CODES[i]);
//            }
//        }
        return myPermission;
    }
}
