package com.andrelleitao.app.core.audited;

import com.andrelleitao.app.core.BaseRepository;
import com.andrelleitao.app.core.BaseServiceImpl;
import com.andrelleitao.app.core.BaseSpecification;

public class BaseAuditedService<PK, T extends BaseAudited<PK>, 
	SP extends BaseSpecification<PK, T>, R extends BaseRepository<PK, T>>
		extends BaseServiceImpl<PK, T, SP, R> {	
		
}
