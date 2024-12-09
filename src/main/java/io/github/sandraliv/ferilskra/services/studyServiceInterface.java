package io.github.sandraliv.ferilskra.services;

import io.github.sandraliv.ferilskra.klasar.Study;
import java.util.List;

public interface studyServiceInterface {
    List<Study> findAll();

    Study save(Study study);
}
