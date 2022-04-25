package com.lin.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface MenuService {

    public Map<String,Object> initmenu(Integer userid);
}
