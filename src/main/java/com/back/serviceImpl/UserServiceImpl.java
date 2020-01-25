package com.back.serviceImpl;

import com.back.dao.ResourseMapper;
import com.back.dao.UserMapper;
import com.back.entity.Resourse;
import com.back.entity.ResourseExample;
import com.back.entity.User;
import com.back.entity.UserExample;
import com.back.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private ResourseMapper resourseMapper;

    @Override
    public List<User> getAllUser() {
        UserExample userExample = new UserExample();
        return userMapper.selectByExample(userExample);
    }

    @Override
    public User getUserByName(String account) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountEqualTo(account);
        List<User> user = userMapper.selectByExample(userExample);
        if(null!=user&&user.size()>0){
            return user.get(0);
        }
        else{
            return null;
        }

    }

    @Override
    public int registUser(User user) {
        //密码加密
        String md5Password = new Md5Hash(user.getPassword(),user.getAccount(),10).toString();
        user.setSalt(user.getAccount());
        user.setPassword(md5Password);
        user.setName(user.getAccount());
        int result = userMapper.insertSelective(user);
        return result;
    }

    @Override
    public List<Resourse> getResourseByUserName(String userName) {
        String resourseId = userMapper.getResourseIdByUserName(userName);
        List<Resourse> root = new ArrayList<Resourse>();
        if(resourseId.contains(",")){
            String[] resoursesName = resourseId.split(",");
            for(int i = 0; i<resoursesName.length;i++){
                ResourseExample resourseExample = new ResourseExample();
                resourseExample.createCriteria().andIdEqualTo(resoursesName[i]);
                Resourse resourse = resourseMapper.selectByPrimaryKey(resoursesName[i]);
                //判断是否一级，先处理一级
                if(resourse.getLever().equals("1")){
                    List<Resourse> currentChild = new ArrayList<Resourse>();
                    String url = resourse.getUrl();
                    String menuTag = url.substring(url.lastIndexOf("/")+1,url.length());
                    resourse.setMenuTag(menuTag);
                    //寻找其二级
                    for(int j=0;j<resoursesName.length;j++){
                        ResourseExample checkChild = new ResourseExample();
                        resourseExample.createCriteria().andIdEqualTo(resoursesName[j]);
                        Resourse child = resourseMapper.selectByPrimaryKey(resoursesName[j]);
                        if(child.getParentId()!=null && child.getParentId().equals(resourse.getId())){
                            String childUrl = resourse.getUrl();
                            String childeMenuTag = childUrl.substring(childUrl.lastIndexOf("/")+1,childUrl.length());
                            child.setMenuTag(childeMenuTag);
                            currentChild.add(child);
                        }
                    }
                    if(currentChild.size()>0){
                        resourse.setChildren(currentChild);
                }
                root.add(resourse);
            }
        }
    }
        return root;
    }
}

