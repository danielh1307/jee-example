package danielh1307.jee.example.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.persistence.Version;

import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.enums.LifeCycleTypeConverter;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * From JPA specification: If an entity instance is to be passed by value as
	 * a detached object (e.g., through a remote interface), the entity class
	 * must implement the Serializable interface.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	protected Integer id;
	
	@Version
	protected Integer version;
	
	@Column(nullable = false)
	@Convert(converter = LifeCycleTypeConverter.class)
	private LifeCycleType lifeCylce;
	
	@Transient
	private BaseEntity beforeImage;
	
	public BaseEntity() {
		lifeCylce = LifeCycleType.AKTIV;
	}
	
	public Integer getId() {
		return id;
	}
	
	public boolean isPersisted() {
		return id != null;
	}
	
	protected void testParamNotNullNotEmpty(String aParam, String aParamName) {
		if (aParam == null || aParam.trim().isEmpty()) {
			throw new RuntimeException("The param [" + aParamName + "] must not be null or empty, currently: [" + aParam + "]");
		}
	}
	
	protected void testParamNotNull(Object aParam, String aParamName) {
		if (aParam == null) {
			throw new RuntimeException("The param [" + aParamName + "] must not be null");
		}
	}
	
	public LifeCycleType getLifeCycle() {
		return lifeCylce;
	}
	
	protected BaseEntity getBeforeImage() {
		return beforeImage;
	}
	
	protected abstract BaseEntity buildBeforeImage();
	
	/**
	 * Protected so clients cannot call this method.
	 */
	protected void setLifeCycle(LifeCycleType lifeCycle) {
		this.lifeCylce = lifeCycle;
	}
	
	@PostLoad
	private void postLoad() {
		beforeImage = buildBeforeImage();
	}
}
