package com.lin.service.Impl;

import com.lin.entity.Menu;
import com.lin.mapper.MenuMapper;
import com.lin.service.MenuService;
import com.lin.utils.TreeUtil;
import com.lin.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :lzm
 * @date :2022/4/9 14:08
 * @description :
 * @modyified By:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Override
    public Map<String, Object> initmenu(Integer userid) {

        Map<String, Object> map = new HashMap<>(16);
        Map<String,Object> homeInfo = new HashMap<>(16);
        Map<String,Object> logoInfo = new HashMap<>(16);
        List<MenuVo> menuInfo = new ArrayList<>();
        List<Menu> menuList = menuMapper.findallmenu(userid);
        for (Menu m: menuList
             ) {
            MenuVo menuVo=new MenuVo();
            menuVo.setId(m.getId());
            menuVo.setPid(m.getPid());
            menuVo.setHref(m.getHref());
            menuVo.setTitle(m.getTitle());
            menuVo.setIcon(m.getIcon());
            menuVo.setTarget(m.getTarget());
            menuInfo.add(menuVo);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo,0));
        homeInfo.put("title","首页");
        homeInfo.put("href","/towelcome");//控制器路由,自行定义
        logoInfo.put("title","CRM系统");
        logoInfo.put("image","/static/images/logo.png");//静态资源文件路径,可使用默认的logo.png
        map.put("homeInfo",homeInfo);
        map.put("logoInfo",logoInfo);
        return map;
    }


}
