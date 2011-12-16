package mr.common.model;

import javax.persistence.MappedSuperclass;

import mr.common.model.OptionData;


/**
 * Tabla base para dicccionarios de datos.
 * @author Mariano Ruiz
 */
@MappedSuperclass
public abstract class AuditableDictionary extends AuditableEntity
                     implements OptionData, Comparable<AuditableDictionary> {

	private static final long serialVersionUID = 1L;

	private String code;
	private String description;
	private String largeDescription;


	/**
	 * Usa el <code>compareToIgnoreCase()</code> de {@link #getCode()} para
	 * la comparación.
	 */
	public int compareTo(AuditableDictionary a) {
		return getCode().compareToIgnoreCase(a.getCode());
	}

	public String toString() {
		return description;
	}

	/**
	 * Código de la entidad, si es nulo
	 * el código será el id.
	 */
	public String getCode() {
		if(code!=null) {
			return code; 
		} else if(getId()!=null) {
			return getId().toString();
		}
		return null;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLargeDescription() {
		return largeDescription;
	}
	public void setLargeDescription(String largeDescription) {
		this.largeDescription = largeDescription;
	}
}
