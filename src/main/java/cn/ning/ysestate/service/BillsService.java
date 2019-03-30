package cn.ning.ysestate.service;


import cn.ning.ysestate.model.Billings;
import cn.ning.ysestate.model.User;

import java.util.List;
import java.util.Optional;

public interface BillsService {

    Billings save (Billings billings);
    Optional<Billings> findById(String id);
    List<Billings> findAll();

}
