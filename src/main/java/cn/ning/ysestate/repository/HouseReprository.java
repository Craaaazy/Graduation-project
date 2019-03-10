package cn.ning.ysestate.repository;

import cn.ning.ysestate.model.HouseInfo;
import cn.ning.ysestate.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseReprository extends JpaRepository<HouseInfo, String> {
    HouseInfo save(HouseInfo houseInfo);
    void deleteById(String id);
    List<HouseInfo> findByUser(User user);
    Optional<HouseInfo> findById(String id);

    Page<HouseInfo> findAll(Pageable pageable);
    Page<HouseInfo> findAll(Specification<HouseInfo> specification,Pageable pageable);

}
