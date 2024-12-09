package io.github.sandraliv.ferilskra.repos;

import io.github.sandraliv.ferilskra.klasar.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studyRepository extends JpaRepository<Study, Integer> {
}
