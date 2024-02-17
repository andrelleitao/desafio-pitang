package com.andrelleitao.app.core.audited;

import java.time.LocalDateTime;

import com.andrelleitao.app.core.BaseEntityResourceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseAuditedResource<PK, T extends BaseAudited<PK>>
		extends BaseEntityResourceImpl<PK, T> {
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	public BaseAuditedResource() {
		super();
	}

	public BaseAuditedResource(T entity) {
		super(entity);

		if (entity != null) {
			createdAt = entity.getCreatedAt();
			updatedAt = entity.getUpdatedAt();
		}
	}
	
}