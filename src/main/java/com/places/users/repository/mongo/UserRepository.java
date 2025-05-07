package com.places.users.repository.mongo;

import com.places.users.model.UserEntity;
import com.places.users.utils.Constants;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

    private final MongoTemplate mongoTemplateUsers;

    public UserRepository(MongoTemplate mongoTemplateUsers) {
        this.mongoTemplateUsers = mongoTemplateUsers;
    }

    public UserEntity save(UserEntity user) {
        return mongoTemplateUsers.save(user, "user");
    }

    public void update(Query query, Update update) {
        mongoTemplateUsers.updateFirst(query, update, UserEntity.class);
    }

    public UserEntity findById(String id) {
        return mongoTemplateUsers.findById(id, UserEntity.class);
    }

    public UserEntity findByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.USER.USERNAME).is(userName));
        return mongoTemplateUsers.findOne(query, UserEntity.class);
    }

    public UserEntity findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.USER.EMAIL).is(email));
        return mongoTemplateUsers.findOne(query, UserEntity.class);
    }

    public UserEntity findByDNI(String dni) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.USER.DNI).is(dni));
        return mongoTemplateUsers.findOne(query, UserEntity.class);
    }
}
