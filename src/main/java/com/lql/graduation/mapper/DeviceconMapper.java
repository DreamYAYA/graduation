package com.lql.graduation.mapper;

import com.lql.graduation.pojo.Devicecon;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceconMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DEVICECON
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String deviceconId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DEVICECON
     *
     * @mbg.generated
     */
    int insert(Devicecon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DEVICECON
     *
     * @mbg.generated
     */
    int insertSelective(Devicecon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DEVICECON
     *
     * @mbg.generated
     */
    Devicecon selectByPrimaryKey(String deviceconId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DEVICECON
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Devicecon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table DEVICECON
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Devicecon record);
}