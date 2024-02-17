package com.andrelleitao.app.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<PK, T extends BaseEntity<PK>>
		extends JpaRepository<T, PK>, JpaSpecificationExecutor<T> {

}
