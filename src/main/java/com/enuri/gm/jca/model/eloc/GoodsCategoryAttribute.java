package com.enuri.gm.jca.model.eloc;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class GoodsCategoryAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GoodsCategoryAttributeId id;
	private int displayOrder;
	private int tagOrder;

	@ManyToOne
	@JoinColumn(name = "attribute_id", insertable = false, updatable = false)
	private GoodsAttribute att;
}
