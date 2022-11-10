package org.example.readonly.domain.repository;

import org.example.readonly.domain.ReadOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadOnlyRepository extends JpaRepository<ReadOnly, Long> {
}
