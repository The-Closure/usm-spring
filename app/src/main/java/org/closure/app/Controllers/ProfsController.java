package org.closure.app.Controllers;

import java.util.Date;

import org.closure.app.Models.ProfsModel;
import org.closure.app.Repositories.ProfsRepo;
import org.closure.app.entities.ProfsEntity;
import org.closure.app.services.FormService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profs")
public class ProfsController {

    @Autowired
    ProfsRepo profsRepo;
    @Autowired
    FormService service;

    @GetMapping(path = "/koko")
    public ProfsEntity gettoto() {
        return new ProfsEntity();
    }

    @PostMapping(path = "/addprofs")
    public ProfsModel addProfs(@RequestBody ProfsEntity p) {
        ProfsModel response = new ProfsModel();
        if (profsRepo.findByEmail(p.getEmail()).isPresent()) {
            response.setName("100 - prof is exist");
            return response;
        } else {
            p.setCreated_at(new Date());
            return response = service.convertToProfResponseForm(profsRepo.save(p));
        }
    }

}