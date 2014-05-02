package org.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "log")
@XmlRootElement(name = "log")
public class Log {

	@Id
	@Column(name = "id_log")
	private int id;

	@Column(name = "info")
	private String info;

	@Column(name = "date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Log() {
		super();
	}

	public Log(String info, DateTime date) {
		this.info = info;
		this.date = date;
	}
}