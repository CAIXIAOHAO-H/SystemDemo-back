package com.back.serviceImpl;


import com.back.dao.ResourseMapper;
import com.back.dao.RoleMapper;
import com.back.entity.Resourse;
import com.back.entity.ResourseExample;
import com.back.entity.Role;
import com.back.entity.RoleExample;
import com.back.service.SystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private ResourseMapper resourseMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Resourse> getAllResourse() {
        ResourseExample resourseExample = new ResourseExample();
        resourseExample.createCriteria().andLeverEqualTo("1");
        List<Resourse>  resourses = resourseMapper.selectByExample(resourseExample);
        List<Resourse> root = new ArrayList<Resourse>();
        //遍历所有一级菜单
        for (Resourse item : resourses) {
            item.setTitle(item.getName());
            ResourseExample checkChildren = new ResourseExample();
            checkChildren.createCriteria().andParentIdEqualTo(item.getId());
            List<Resourse> childrens = resourseMapper.selectByExample(checkChildren);
            if(null!=childrens&&childrens.size()>0){
                for (Resourse child:childrens) {
                    child.setTitle(child.getName());
                }
                item.setChildren(childrens);
            }
            root.add(item);

        }
        return root;
    }

    @Override
    public List<Role> getAllRoles() {
        RoleExample roleExample = new RoleExample();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return roles;
    }
}
