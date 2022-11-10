package org.example.readonly.domain.repository;

import org.example.readonly.domain.NonReadOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonReadOnlyRepository extends JpaRepository<NonReadOnly, Long> {
}
