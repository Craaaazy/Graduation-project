package cn.ning.ysestate.service;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    HouseInfo save(HouseInfo houseInfo);
    List<HouseInfo> findAll();
    List<HouseInfo> findByUser(User user);
    Optional<HouseInfo> findById(String id);
    void deleteById(String id);
}
