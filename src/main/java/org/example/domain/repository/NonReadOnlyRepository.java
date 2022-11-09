package org.example.domain.repository;

import org.example.domain.NonReadOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonReadOnlyRepository extends JpaRepository<NonReadOnly, Long> {
}
