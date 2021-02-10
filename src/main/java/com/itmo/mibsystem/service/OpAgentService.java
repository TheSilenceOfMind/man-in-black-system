package com.itmo.mibsystem.service;

import com.itmo.mibsystem.dao.op_agent.ActDetentionRepository;
import com.itmo.mibsystem.dao.pasportist.AlienPassportRepository;
import com.itmo.mibsystem.model.op_agent.ActDetention;
import com.itmo.mibsystem.model.passporter.AlienPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpAgentService {
    @Autowired
    private ActDetentionRepository actDetentionRepository;

    public List<ActDetention> getActDetention() {
        List<ActDetention> actDetentions = new ArrayList<ActDetention>();
        actDetentionRepository.findAll().iterator().forEachRemaining(actDetentions::add);

        return actDetentions;
    }

    public List<ActDetention> getActDetentionByFilds(String scene, long idGuiltyAlien, long idAgent, String discription) {
        return actDetentionRepository.findActDetentionBySceneAndIdGuiltyAlienAndIdUserAgentAndDescription(scene , idGuiltyAlien, idAgent, discription);
    }

    public ActDetention insertActDetention(ActDetention actDetention) {
        return actDetentionRepository.save(actDetention);
    }

    public void deleteActDetention(ActDetention actDetention) {
        actDetentionRepository.deleteById(actDetention.getActDetentionId());
    }

    public ActDetention updateActDetention(ActDetention actDetention){
        return actDetentionRepository.save(actDetention);
    }
}
