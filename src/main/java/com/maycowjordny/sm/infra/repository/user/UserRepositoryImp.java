package com.maycowjordny.sm.infra.repository.user;

import com.maycowjordny.sm.domain.model.User;
import com.maycowjordny.sm.infra.entity.UserEntity;
import com.maycowjordny.sm.infra.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(UserMapper.toEntity(user));
        entityManager.flush();
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT u FROM UserEntity u WHERE u.email = :email";

        UserEntity userEntity = entityManager.createQuery(sql, UserEntity.class)
                .setParameter("email", email)
                .getSingleResult();

        return UserMapper.toDomain(userEntity);
    }
}