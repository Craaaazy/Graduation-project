package cn.ning.ysestate.service;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;

import java.util.List;

public interface HouseService {
    HouseInfo save(HouseInfo houseInfo);
    void deleteById(String id);
    List<HouseInfo> findAll();
    HouseInfo findByUser(User user);
}
