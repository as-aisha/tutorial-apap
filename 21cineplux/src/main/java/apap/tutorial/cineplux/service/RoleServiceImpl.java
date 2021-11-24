package apap.tutorial.cineplux.service;

import apap.tutorial.cineplux.model.RoleModel;
import apap.tutorial.cineplux.repository.RoleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDB roleDB;

    @Override
    public List<RoleModel> getRoleList() {  return roleDB.findAll();  }
}
