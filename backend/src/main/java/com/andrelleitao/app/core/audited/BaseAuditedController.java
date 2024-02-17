package com.andrelleitao.app.core.audited;

import java.io.Serializable;

import com.andrelleitao.app.core.BaseControllerImpl;
import com.andrelleitao.app.core.BaseRepository;
import com.andrelleitao.app.core.BaseSpecificationImpl;

public abstract class BaseAuditedController<PK extends Serializable, T extends BaseAudited<PK>, 
	S extends BaseAuditedService<PK, T, SP, R>, SP extends BaseSpecificationImpl<PK, T>, R extends BaseRepository<PK, T>, DTO extends BaseAuditedResource<PK, T>>
		extends BaseControllerImpl<PK, T, S, SP, R, DTO> {
		
}