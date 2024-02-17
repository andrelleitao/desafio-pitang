package com.andrelleitao.app.core;

public interface BaseEntityResource<PK> {
	PK getId();

	void setId(PK id);
}
