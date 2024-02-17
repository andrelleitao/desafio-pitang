package com.andrelleitao.app.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntityResourceImpl<PK, 
	T extends BaseEntityImpl<PK>> implements BaseEntityResource<PK> {
	
	private PK id;
	
	public BaseEntityResourceImpl(T entity) {
		if(entity != null) {
			this.id = entity.getId();
		}
	}
	
}
