package com.wtz.community.dao;

import com.wtz.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：
 * Date:2021/11/12 20:30
 **/
@Mapper
public interface DiscussPostMapper {    //discuss_post表
    /*社区首页+个人主页: 贴子分页展示*/
    /*用户ID（用于个人主页的展示：sql-<if>设置为0-所有，不为0-该用户发的）, 每页起始行行号 ，每页贴子限制数*/
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    /*查询贴子行数*/
    /*@Param  1.用于为参数起别名 2.如果只有一个参数且在<if>标签使用，则必须添加别名否则报错*/
    int selectDiscussPostRows(@Param("userId") int userId);
}
