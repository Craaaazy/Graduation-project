package cn.ning.ysestate.service;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    HouseInfo save(HouseInfo houseInfo);
    List<HouseInfo> findAll();
    List<HouseInfo> findByUser(User user);
    Optional<HouseInfo> findById(String id);
    void deleteById(String id);

    List<HouseInfo> findByTitleLike(String title);

    Page<HouseInfo> findAll(Pageable pageable);
    Page<HouseInfo> findAll(Specification<HouseInfo> specification, Pageable pageable);
}
