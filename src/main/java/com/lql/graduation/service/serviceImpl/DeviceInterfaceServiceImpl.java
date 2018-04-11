package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.DeviceInterfaceDataMapper;
import com.lql.graduation.mapper.DeviceInterfaceMapper;
import com.lql.graduation.pojo.DeviceInterface;
import com.lql.graduation.pojo.DeviceInterfaceData;
import com.lql.graduation.pojo.Vo.CharData;
import com.lql.graduation.service.DeviceInterfaceService;
import com.lql.graduation.util.Constant;
import com.lql.graduation.util.ServerResponse;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jnlp.IntegrationService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class DeviceInterfaceServiceImpl implements DeviceInterfaceService{

    @Autowired
    private DeviceInterfaceMapper deviceInterfaceMapper;
    @Autowired
    private DeviceInterfaceDataMapper deviceInterfaceDataMapper;


    public Integer insertMathInterface(){


       return null;
    }


    /**
     *
     * 数据接口的列表
     * @param status
     * @return
     */
    @Override
    public List list(Integer status) {
       List<DeviceInterface> deviceInterfaceList = deviceInterfaceMapper.selectByStatus(status);
        return deviceInterfaceList;
    }

    /**
     *
     * 创建数据接口
     * @param deviceInterface
     */
    @Override
    public void createDeviceInterface(DeviceInterface deviceInterface) {


        deviceInterface.setDeviceInterfaceStatus(Constant.Status.OK_STATUS+"");
        deviceInterface.setCreateTime(new Date());
        deviceInterface.setUpdateTimer(new Date());
        deviceInterfaceMapper.insert(deviceInterface);
    }

    @Override
    public ServerResponse getDeviceInterfaceById(Integer id) {

        try {
            DeviceInterface deviceInterface = deviceInterfaceMapper.selectByPrimaryKey(id);
            return ServerResponse.createBySuccessMessage(deviceInterface);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage("查询失败!");
        }

    }

    @Override
    public ServerResponse updateInterface(DeviceInterface deviceInterface) {

        try{

        deviceInterfaceMapper.updateByPrimaryKeySelective(deviceInterface);
        return ServerResponse.createBySuccessMessage("修改成功!");
        }catch (Exception e){

            return ServerResponse.createByErrorMessage("修改失败!");
        }

    }

    @Override
    public ServerResponse getInterfaceValue(DeviceInterface deviceInterface) {

        DeviceInterface deviceInterface1 = deviceInterfaceMapper.selectByPrimaryKey(Integer.parseInt(deviceInterface.getDeviceInterfaceId()));
        List<DeviceInterfaceData > deviceInterfaceDatas = deviceInterfaceDataMapper.selectInterfaceData(deviceInterface1);
        List<String> cateory = new LinkedList<>();
        List<Integer> datas = new LinkedList<>();
        for (DeviceInterfaceData dia : deviceInterfaceDatas){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cateory.add(simpleDateFormat.format(dia.getDataTime()));
            datas.add(Integer.parseInt(dia.getDeviceInerfaceData()));

            }
        CharData charData = new CharData(cateory,datas);


        return ServerResponse.createBySuccessMessage(charData);
    }


}
