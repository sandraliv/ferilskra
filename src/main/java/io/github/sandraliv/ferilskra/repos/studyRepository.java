package io.github.sandraliv.ferilskra.repos;

import io.github.sandraliv.ferilskra.klasar.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface studyRepository extends JpaRepository<Study, Integer> {
    Optional<Study> findByStudyName(String studyName);
    Optional<Study> findBySlug(String slug);
    void delete(Study study);
}
