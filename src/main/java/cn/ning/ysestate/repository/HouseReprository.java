package cn.ning.ysestate.repository;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.service.HouseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseReprository extends JpaRepository<HouseInfo, String> {
    HouseInfo save(HouseInfo houseInfo);
    void deleteByTitle(String title);
    List<HouseInfo> findByUser(User user);
    HouseInfo findByTitle(String title);

}
