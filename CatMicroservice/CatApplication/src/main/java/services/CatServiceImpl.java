package services;

import cat.CatModel;
import dao.CatDao;
import entity.CatEntity;
import jakarta.transaction.Transactional;
import mapper.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "dao")
public class CatServiceImpl implements CatService {
    private final CatDao catDao;

    @Autowired
    public CatServiceImpl(CatDao catDao) {
        this.catDao = catDao;
    }

    @Transactional
    @Override
    public void add(CatModel catModel) {
        catDao.save(CatMapper.modelToEntity(catModel));
    }

    @Transactional
    @Override
    public void addFriend(Long catId, Long friendCatId) {
        catDao.addFriend(catId, friendCatId);
        catDao.addFriend(friendCatId, catId);
    }

    @Transactional
    @Override
    public Optional<CatModel> getById(Long id) {
        return catDao.findById(id).map(CatMapper::entityToModel);

    }

    @Transactional
    @Override
    public Optional<CatModel> getByIdAndOwnerId(Long id, Long ownerId) {
        return catDao.findById(id).map(CatMapper::entityToModel);

    }

    @Transactional
    @Override
    public Optional<List<CatModel>> getAll(String breed, String color, Long ownerId) {
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        CatEntity cat = new CatEntity();

        if (ownerId != null) {
            cat.setOwnerId(ownerId);
        }

        if (color != null && !color.isEmpty()) {
            cat.setColor(color);
        }
        if (breed != null && !breed.isEmpty()) {
            cat.setBreed(breed);
        }

        Example<CatEntity> example = Example.of(cat, matcher);

        return Optional.of(catDao.findAll(example).stream().map(CatMapper::entityToModel).toList());
    }

    @Transactional
    @Override
    public void update(CatModel catModel) {
        Optional<CatEntity> catEntity = catDao.findById(catModel.getId());
        if (catEntity.isEmpty()) {
            return;
        }
        CatEntity newCatEntity = CatMapper.modelToEntity(catModel);
        catDao.save(newCatEntity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (catDao.findById(id).isPresent()) {
            catDao.deleteById(id);
        }
    }
}
