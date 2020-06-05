package com.itmo.mibsystem.service;

import com.itmo.mibsystem.daos.AccessRulesRepository;
import com.itmo.mibsystem.daos.DocumentRepository;
import com.itmo.mibsystem.daos.UserRepository;
import com.itmo.mibsystem.model.AccessRules;
import com.itmo.mibsystem.model.Document;
import com.itmo.mibsystem.model.User;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Service
public class RepositoryManager implements RulesBasedRepositoryManager {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    AccessRulesRepository accessRulesRepository;

    @PostConstruct
    public void fillRepos() {
        accessRulesRepository.save(new AccessRules(0L, "USER"));
        accessRulesRepository.save(new AccessRules(1L, "DOCUMENT"));
        documentRepository.save(new Document(0L, 0L, 0L, "f1", "f2", "f3", "desp"));
        userRepository.save(new User(0L, "new username", "new pass"));
    }

    public CrudRepository<?, Long> getRepository(Long roleId) throws Exception {
        Optional<AccessRules> accessRules = accessRulesRepository.findById(roleId);
        if (!accessRules.isPresent()) {
            throw new Exception("Для данного пользователя нет записи!");
        }

        switch (accessRules.get().getTableName()) {
            case "USER":
                return userRepository;
            case "DOCUMENT":
                return documentRepository;
            default:
                throw new Exception("Данная таблица не зарегистрирована!");
        }
    }
}
