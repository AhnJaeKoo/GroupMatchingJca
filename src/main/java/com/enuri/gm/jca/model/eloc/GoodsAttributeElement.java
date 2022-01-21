package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "attributeId")
public class GoodsAttributeElement implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GoodsAttributeElementId id;
	private String attributeElement;
	private String delYn;

	@ManyToOne
	@JoinColumn(name = "attribute_id", insertable = false, updatable = false)
	private GoodsAttribute att;

}
