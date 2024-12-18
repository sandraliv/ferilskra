package io.github.sandraliv.ferilskra.services;

import io.github.sandraliv.ferilskra.klasar.Study;
import java.util.List;
import java.util.Optional;

public interface studyServiceInterface {
    List<Study> findAll();

    Study save(Study study);

    Optional<Study> findBySlug(String slug);

    Optional<Study> findByStudyName(String studyName);

    void delete(Study study);

    Optional<Study> findById(Integer id);
}
