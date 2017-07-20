package com.shinyleo.basic.dao;

import com.shinyleo.base.dao.IBaseDao;
import com.shinyleo.basic.entity.BasicChildEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public interface IBasicChildDao  extends IBaseDao {

    /**
     * 根据基础表id删除数据
     * @param basicId 基础表id
     */
    void delete(int basicId);

    /**
     * 根据基础表id查询基础表关联数据集合
     * @param basicId  基础表id
     * @return  基础表关联数据集合
     */
    List<BasicChildEntity> queryByBasicId(int basicId);

    /**
     * 根据basicId集合实现批量的删除
     * @param basicIds basicId集合
     */
    void deleteBatch(@Param("basicIds")int[] basicIds);

    /**
     * 根据基础id和子id集合删除
     * @param basicId 基础id
     * @param basicChildIds 子集合id
     */
    void deleteByChildIds(@Param("basicId")int basicId,@Param("basicChildIds")int[] basicChildIds);

    /**
     * 根据基础id和子id删除
     * @param basicId  基础id
     * @param basicChildId 子id
     */
    void deleteByBasicIdAndChildId(@Param("basicId")int basicId,@Param("basicChildId")int basicChildId);
}
