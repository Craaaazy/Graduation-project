package cn.ning.ysestate.service.impl;

import cn.ning.ysestate.model.Billings;
import cn.ning.ysestate.model.User;
import cn.ning.ysestate.repository.BillsReprository;
import cn.ning.ysestate.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillsServiceImpl implements BillsService {
    @Autowired
    BillsReprository billsReprository;

    @Override
    public Billings save(Billings billings) {
        return billsReprository.save(billings);
    }

    @Override
    public Optional<Billings> findById(String id) {
        return billsReprository.findById(id);
    }

    @Override
    public List<Billings> findAll() {
        return billsReprository.findAll();
    }

}
