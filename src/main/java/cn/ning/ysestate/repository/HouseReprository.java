package cn.ning.ysestate.repository;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseReprository extends JpaRepository<HouseInfo, String> {
    HouseInfo save(HouseInfo houseInfo);
    void deleteById(String id);
    HouseInfo findByUser(User user);

    @Override
    List<HouseInfo> findAll();

}
