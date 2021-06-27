package br.com.slippers.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Feedback {
		
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;

	@Column(nullable=false)
	private String texto;

	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
}
