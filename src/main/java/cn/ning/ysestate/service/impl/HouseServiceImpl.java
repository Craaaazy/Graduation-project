package cn.ning.ysestate.service.impl;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.repository.HouseReprository;
import cn.ning.ysestate.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseReprository houseReprository;

    @Override
    public HouseInfo save(HouseInfo houseInfo) {
        return houseReprository.save(houseInfo);
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
    public Optional<HouseInfo> findById(String id) {
        return houseReprository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        houseReprository.deleteById(id);
    }

    @Override
    public Page<HouseInfo> findAll(Pageable pageable) {
        return houseReprository.findAll(pageable);
    }

}
