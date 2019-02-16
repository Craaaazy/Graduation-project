package cn.ning.ysestate.service;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;

import java.util.List;

public interface HouseService {
    HouseInfo save(HouseInfo houseInfo);
    void deleteByTitle(String title);
    List<HouseInfo> findAll();
    List<HouseInfo> findByUser(User user);
    HouseInfo findByTitle(String title);

}
