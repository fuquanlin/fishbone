package cn.fql.fishbone.util;

import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuquanlin on 2016/9/29.
 */
public class FishBoneSecurityUtil {
    public static final String[] ALL_PERMISSIONS = {"welcome","user","role","log"};

    public static String[] getPermisionsFromSubject(Subject currentUser){
        boolean[] currentUserPermitted = currentUser.isPermitted(ALL_PERMISSIONS);
        List<String> permissions = new ArrayList<>();
        for (int i =0;i<currentUserPermitted.length;i++) {
            if (currentUserPermitted[i]){
                permissions.add(ALL_PERMISSIONS[i]);
            }
        }
        return permissions.toArray(new String[permissions.size()]);
    }
}
