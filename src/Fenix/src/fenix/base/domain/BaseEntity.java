package fenix.base.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2109540329117361945L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Version
	protected Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	protected Date created;

	@Column(nullable = false)
	protected String creator;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;

	protected String updater;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != obj.getClass()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		return this.id != null && this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		if (this.id == null) {
			return super.hashCode();
		} else {
			return this.id.hashCode();
		}
	}
}
