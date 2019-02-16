package cn.ning.ysestate.service.impl;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.repository.HouseReprository;
import cn.ning.ysestate.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseReprository houseReprository;

    @Override
    public HouseInfo save(HouseInfo houseInfo) {
        return houseReprository.save(houseInfo);
    }

    @Override
    public void deleteByTitle(String title) {
        houseReprository.deleteByTitle(title);
    }

    @Override
    public List<HouseInfo> findAll() {
        return houseReprository.findAll();
    }

    @Override
    public List<HouseInfo> findByUser(User user) {
        return houseReprository.findByUser(user);
    }

    @Override
    public HouseInfo findByTitle(String title) {
        return houseReprository.findByTitle(title);
    }
}
