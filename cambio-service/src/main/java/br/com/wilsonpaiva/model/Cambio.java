package br.com.wilsonpaiva.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity(name = "cambio")
public class Cambio  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "from_currency",nullable = false,length = 3)
	private String from;
	@Column(name = "to_currency",nullable = false,length = 3)
	private String to;
	@Column(nullable = false)
	private BigDecimal conversionFactor;
	@Transient
	private BigDecimal convertedValeu;
	@Transient
	private String enviroment;
	
	public Cambio(Long id, String from, String to, BigDecimal conversionFactor, BigDecimal convertedValeu,
			String enviroment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = conversionFactor;
		this.convertedValeu = convertedValeu;
		this.enviroment = enviroment;
	}

	public Cambio() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public BigDecimal getConvertedValeu() {
		return convertedValeu;
	}

	public void setConvertedValeu(BigDecimal convertedValeu) {
		this.convertedValeu = convertedValeu;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversionFactor == null) ? 0 : conversionFactor.hashCode());
		result = prime * result + ((convertedValeu == null) ? 0 : convertedValeu.hashCode());
		result = prime * result + ((enviroment == null) ? 0 : enviroment.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cambio other = (Cambio) obj;
		if (conversionFactor == null) {
			if (other.conversionFactor != null)
				return false;
		} else if (!conversionFactor.equals(other.conversionFactor))
			return false;
		if (convertedValeu == null) {
			if (other.convertedValeu != null)
				return false;
		} else if (!convertedValeu.equals(other.convertedValeu))
			return false;
		if (enviroment == null) {
			if (other.enviroment != null)
				return false;
		} else if (!enviroment.equals(other.enviroment))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}
}
