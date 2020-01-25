package com.back.shiro;

import com.back.entity.User;
import com.back.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        String name = principalCollection.toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        //权限赋值
//        String permissions = userService.getPermissionByUserName(name);
//        String[] permissionArr = permissions.split(",");
//        for(int i=0;i<permissionArr.length;i++) {
//            authorizationInfo.addStringPermission(permissionArr[i]);
//        }
//        //角色赋值
//        String roleName = userService.getRoleNameByUserName(name);
//        authorizationInfo.addRole(roleName);
        return authorizationInfo;
    }

    //验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.getUserByName(userName);
        if(null == user) {
            return null;
        }
        else {
            SimpleAuthenticationInfo authenticationInfo =
                    new SimpleAuthenticationInfo(user.getAccount(),user.getPassword(),ByteSource.Util.bytes(user.getSalt()),getName());
            return authenticationInfo;
        }

    }

}