package org.project.kakaotalk.repository;

import org.project.kakaotalk.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

}
