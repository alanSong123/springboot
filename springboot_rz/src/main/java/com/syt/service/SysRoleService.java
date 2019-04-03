package com.syt.service;

import java.util.List;

public interface SysRoleService {

    public List<String> findRolesByUserId(long userId);

}
