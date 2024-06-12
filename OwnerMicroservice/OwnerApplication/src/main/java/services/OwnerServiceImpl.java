package services;

import dao.OwnerDao;
import mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import owner.OwnerModel;
import entity.OwnerEntity;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "dao")
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;

    @Autowired
    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public void add(OwnerModel ownerModel) {
        ownerDao.save(OwnerMapper.modelToEntity(ownerModel));
    }

    @Override
    public Optional<OwnerModel> get(Long id) {
        return ownerDao.findById(id).map(OwnerMapper::entityToModel);
    }

    @Override
    public Optional<List<OwnerModel>> getAll() {
        return Optional.of(ownerDao.findAll().stream().map(OwnerMapper::entityToModel).toList());
    }

    @Override
    public void update(OwnerModel ownerModel) {
        Optional<OwnerEntity> ownerEntity = ownerDao.findById(ownerModel.getId());
        if (ownerEntity.isEmpty()) {
            return;
        }
        OwnerEntity newOwnerEntity = OwnerMapper.modelToEntity(ownerModel);
        ownerDao.save(newOwnerEntity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        ownerDao.deleteById(id);
    }
}
