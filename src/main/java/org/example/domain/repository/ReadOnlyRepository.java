package org.example.domain.repository;

import org.example.domain.ReadOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadOnlyRepository extends JpaRepository<ReadOnly, Long> {
}
