package apap.tugas.situ.service;

import apap.tugas.situ.model.RoleModel;
import apap.tugas.situ.repository.RoleDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

    @Override
    public RoleModel getRoleById(Long id) {
        return roleDb.findById(id).get();
    }
}
