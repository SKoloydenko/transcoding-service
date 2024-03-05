package com.sdk.transcoding.repository;

import com.sdk.transcoding.entity.Job;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, UUID> {
  List<Job> findByExternalId(UUID externalId);
}
