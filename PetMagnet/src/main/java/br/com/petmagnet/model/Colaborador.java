package br.com.petmagnet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tb_colaborador")
public class Colaborador extends LogRegistro {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_colaborador")
	@Column(name = "id_colaborador")
	private Long id;	

	@Column(name = "nm_colaborador", length = 60)
	@NotEmpty
	@Length(min = 5, max = 60, message = "O nome deve ter entre {min} a {max} caracteres")
	private String nome;

	@Column(name = "ds_senha", length = 20)
	@NotEmpty
	@Length(min = 5, max = 20, message = "A senha deve ter entre {min} a {max} caracteres")
	private String senha;

	@ManyToOne
	@JoinColumn(name = "id_estabelecimento")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Estabelecimento estabelecimento;	
}
