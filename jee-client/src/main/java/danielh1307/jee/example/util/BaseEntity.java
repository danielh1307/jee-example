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

	/**
	 * 
	 * @return the id (PK in the database)
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @return true if the entity is already persisted in the database
	 */
	public boolean isPersisted() {
		return id != null;
	}

	/**
	 * Tests if the given parameter is not null and not empty. If the test
	 * fails, a {@link RuntimeException} is thrown.
	 * 
	 * @param aParam
	 *            the parameter to test
	 * @param aParamName
	 *            the name of the parameter (only needed for the text in the
	 *            exception)
	 */
	protected void testParamNotNullNotEmpty(String aParam, String aParamName) {
		if (aParam == null || aParam.trim().isEmpty()) {
			throw new RuntimeException(
					"The param [" + aParamName + "] must not be null or empty, currently: [" + aParam + "]");
		}
	}

	/**
	 * Tests if the given parameter is not null. If the test fails, a
	 * {@link RuntimeException} is thrown.
	 * 
	 * @param aParam
	 *            the parameter to test
	 * @param aParamName
	 *            the name of the parameter (only needed for the text in the
	 *            exception)
	 */
	protected void testParamNotNull(Object aParam, String aParamName) {
		if (aParam == null) {
			throw new RuntimeException("The param [" + aParamName + "] must not be null");
		}
	}

	public LifeCycleType getLifeCycle() {
		return lifeCylce;
	}

	/**
	 * 
	 * @return the persisted image of the entity. If a transaction makes
	 *         changes, this method returns the object before the changes were
	 *         made. If the entity is not yet persisted, null is returned.
	 */
	protected BaseEntity getBeforeImage() {
		return beforeImage;
	}

	/**
	 * 
	 * @return creates the image of the entity which is returned by
	 *         {@link BaseEntity#getBeforeImage()}. This method is always called
	 *         after loading the object from the database ({@link PostLoad}) and
	 *         must be implemented by the subclass.
	 */
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
