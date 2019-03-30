package cn.ning.ysestate.repository;

import cn.ning.ysestate.model.Billings;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillsReprository extends JpaRepository<Billings, String>{
}
