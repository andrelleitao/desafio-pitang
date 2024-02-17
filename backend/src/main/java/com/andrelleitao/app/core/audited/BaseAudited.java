package com.andrelleitao.app.core.audited;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.andrelleitao.app.core.BaseEntityImpl;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Estrutura base para criação de classes com auditoria. * 
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseAudited<PK> extends BaseEntityImpl<PK> {
	
	private LocalDateTime createdAt;

	@CreatedDate
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	private LocalDateTime updatedAt;

	@LastModifiedDate
	@Column(name = "UPDATED_AT", nullable = false)
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
