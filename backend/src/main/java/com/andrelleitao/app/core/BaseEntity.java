package com.andrelleitao.app.core;

public interface BaseEntity<PK> {
	
	PK getId();

	void setId(PK id);
	
}
