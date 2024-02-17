package com.andrelleitao.app.core;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseEntitySerializer<PK, T extends BaseEntityImpl<PK>> extends BaseEntityImpl<PK> {	

	public BaseEntitySerializer(T entity) {
		if(entity != null) {
			setId(entity.getId());
		}
	}
	
	public abstract T toEntity();
	
}
