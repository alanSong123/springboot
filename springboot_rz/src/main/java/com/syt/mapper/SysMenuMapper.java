package com.syt.mapper;

import com.syt.entity.SysMenu;
import com.syt.entity.SysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int countByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int deleteByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int deleteByPrimaryKey(Long menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int insert(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int insertSelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    List<SysMenu> selectByExample(SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    SysMenu selectByPrimaryKey(Long menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int updateByPrimaryKeySelective(SysMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_menu
     *
     * @mbggenerated Tue Mar 26 12:11:02 CST 2019
     */
    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findMenuNotButton();

    List<String> findPermsByUserId(long userId);

    List<SysMenu> findDir(long UId);//查询目录

    List<SysMenu> findMenu(@Param("parentId") long parentId,@Param("uId") long uId);//查询目录下的菜单
    //查询目录
//    List<SysMenu> findDir(long uId);
//
//    //查询目录下菜单
//    List<SysMenu> findMenu(@Param("parentId") long parentid,
//                           @Param("uId") long uId);
}