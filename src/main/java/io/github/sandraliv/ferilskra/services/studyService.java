package io.github.sandraliv.ferilskra.services;

import io.github.sandraliv.ferilskra.klasar.Study;
import io.github.sandraliv.ferilskra.repos.studyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studyService implements studyServiceInterface {
    private final studyRepository studyRepository;

    @Autowired
    public studyService(studyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }
    @Override
    public Study save(Study study) {
        return studyRepository.save(study);
    }
}
