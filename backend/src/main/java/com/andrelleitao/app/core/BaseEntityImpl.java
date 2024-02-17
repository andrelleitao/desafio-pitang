package com.andrelleitao.app.core;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

/**
 * Estrutura base para criação de novas classes JPA.
 */
@MappedSuperclass
public abstract class BaseEntityImpl<PK> implements BaseEntity<PK> {

	private PK id;
	
	/**
	 * Foi colocada como transient para que não haja criação da coluna ID.
	 * A responsabilidade de criar tal coluna, com o respectivo nome, ficará com as classes filhas.
	 */
	@Override
	@Transient
	public PK getId() {
		return this.id;
	}

	@Override
	public void setId(PK id) {
		this.id = id;		
	}
	
}
