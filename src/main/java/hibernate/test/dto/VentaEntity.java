package hibernate.test.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

	@Entity
	@Table(name = "VENTA", uniqueConstraints = {
			@UniqueConstraint(columnNames = "ID")})

	public class VentaEntity implements Serializable {
		
		private static final long serialVersionUID = -1798070786993154676L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID", unique = true, nullable = false)
		private Integer ventaID;
		
		@Column(name = "FECHA", unique = false, nullable = false, length = 100)
		private String fecha;
		
		@Column(name = "IMPORTE", unique = false, nullable = false, length = 100)
		private float importe;
		
		@ManyToOne
		@JoinColumn(name = "ID_PERSONA", unique = true, nullable = false)
		private PersonaEntity idPersona;

		public Integer getVentaID() {
			return ventaID;
		}

		public void setVentaID(Integer ventaID) {
			this.ventaID = ventaID;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public float getImporte() {
			return importe;
		}

		public void setImporte(float importe) {
			this.importe = importe;
		}

	
		public PersonaEntity getIdPersona() {
			return idPersona;
		}

		public void setIdPersona(PersonaEntity idPersona) {
			this.idPersona = idPersona;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}
