package com.zero.system.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zero.system.entity.AdminEntity;
import com.zero.system.mapper.AdminEntityMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminEntityMapper adminEntityMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        QueryWrapper<AdminEntity> adminEntityQueryWrapper = new QueryWrapper<>();
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 取出表单用户名
        String username = upToken.getUsername();
        adminEntityQueryWrapper.eq("username", username);
        AdminEntity adminEntity = adminEntityMapper.selectOne(adminEntityQueryWrapper);
        // 查询是否有该用户
        if (adminEntity == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        String salt = "woshiyan";
        // 靠用户名从数据库查询该用户的全部信息
        // 传入:用户名,加密后的密码,盐值,该realm的名字，加密算法和加密次数在已经在配置文件中指定
//        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, adminEntity.getPassword(),ByteSource.Util.bytes(username), getName());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, adminEntity.getPassword(),ByteSource.Util.bytes(salt), getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       /* // 1. 从 PrincipalCollection 中来获取登录用户的信息
        Object principal = principals.getPrimaryPrincipal();
        // 2. 利用登录的用户的信息来..当前用户的角色或权限(可能需要查询数据库)
        Set<String> roles = new HashSet<String>();
        roles.add("user");
        if ("admin".equals(principal)) {
            roles.add("admin");
        }
        // 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        // 4. 返回 SimpleAuthorizationInfo 对象.*/
        return null;
    }

}
