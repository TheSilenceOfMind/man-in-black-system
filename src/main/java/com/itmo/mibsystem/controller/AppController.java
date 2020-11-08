package com.itmo.mibsystem.controller;

import static com.itmo.mibsystem.util.Util.getDecodedCategoryName;

import com.itmo.mibsystem.dao.DocumentRepository;
import com.itmo.mibsystem.dao.UserRepository;
import com.itmo.mibsystem.model.Document;
import com.itmo.mibsystem.model.User;
import com.itmo.mibsystem.service.RepositoryManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BaladKV
 * @since 04.06.2020
 */
@Controller
public class AppController {

    @Autowired
    RepositoryManager repositoryManager;

    @PostMapping("/login")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        Model model
    ) throws Exception {
        UserRepository repository = repositoryManager.getUserRepository();
        List<User> userList = repository.findByUsernameStartsWithIgnoreCase(username);
        if (userList.isEmpty()) {
            model.addAttribute("error", "user not found");
            return "login";
        }
        if (!password.equals(userList.get(0).getPassword())) {
            model.addAttribute("error", "password is incorrect");
            return "login";
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping({"/", "/index"})
    public String listAllDocs(
        @RequestParam(name = "crit_1", required = false) String cat1,
        @RequestParam(name = "crit_2", required = false) String cat2,
        @RequestParam(name = "crit_3", required = false) String cat3,
        @RequestParam(name = "keywords", required = false) String keywords,
        Model model) {
        DocumentRepository repository = repositoryManager.getDocumentRepository();
        List<Document> docs = new ArrayList<>();
        repository.findAll().forEach(
            d -> {
                if (cat1 != null) {
                    if (isValid(d, cat1, cat2, cat3, keywords)) {
                        docs.add(d);
                    }
                } else {
                    docs.add(d);
                }
            }
        );
        model.addAttribute("docs", docs);
        return "index";
    }

    @PostMapping("/index")
    public String index(
        @RequestParam(name = "hidden_id") Long id,
        @RequestParam(name = "field_1") String field1,
        @RequestParam(name = "field_2") String field2,
        @RequestParam(name = "field_3") String field3,
        @RequestParam(name = "crit_1") String cat1,
        @RequestParam(name = "crit_2") String cat2,
        @RequestParam(name = "crit_3") String cat3,
        @RequestParam(name = "description") String desc,
        @RequestParam(name = "remove_flag", required = false) String isRemoving
    ) {
        DocumentRepository repository = repositoryManager.getDocumentRepository();
        Document doc;
        if (id == null) {
            doc = new Document();
        } else {
            Optional<Document> curDocOpt = repository.findById(id);
            doc = curDocOpt.orElse(new Document());
        }
        if (isRemoving.equals("true")) {
            repository.delete(doc);
            return "redirect:/index";
        }
        doc.setCategory1(getDecodedCategoryName(1, cat1));
        doc.setCategory2(getDecodedCategoryName(2, cat2));
        doc.setCategory3(getDecodedCategoryName(3, cat3));
        doc.setField1(field1);
        doc.setField2(field2);
        doc.setField3(field3);
        doc.setDescription(desc);
        if (doc.getId() == null) {
            // todo remove dummy id setting, let DB set needed ID
            Long maxId = -1L;
            for (Document d : repository.findAll()) {
                maxId = Math.max(d.getId(), maxId);
            }
            doc.setId(maxId + 1);
        }
        repository.save(doc);
        return "redirect:/index";
    }

    private boolean isValid(Document doc, String cat1, String cat2, String cat3, String kw) {
        boolean ret = true;
        String decCat1 = getDecodedCategoryName(1, cat1);
        String decCat2 = getDecodedCategoryName(2, cat2);
        String decCat3 = getDecodedCategoryName(3, cat3);
        if (!decCat1.isEmpty())
            ret &= decCat1.equals(doc.getCategory1());
        if (!decCat2.isEmpty())
            ret &= decCat2.equals(doc.getCategory2());
        if (!decCat3.isEmpty())
            ret &= decCat3.equals(doc.getCategory3());
        if (kw != null && !kw.isEmpty()) {
            ret &= doc.getDescription().toLowerCase().contains(kw.toLowerCase());
        }
        return ret;
    }
}
