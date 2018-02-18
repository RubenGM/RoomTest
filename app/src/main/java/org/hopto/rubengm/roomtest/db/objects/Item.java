package org.hopto.rubengm.roomtest.db.objects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Creado por ruben el 18/02/2018.
 */
@Entity
public class Item {
	@PrimaryKey(autoGenerate = true)
	private int id;
	private String title;
	private String description;
	private boolean isNew;

	public Item() {
	}

	public Item(String title, String description) {
		this.title = title;
		this.description = description;
		this.isNew = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isNew() {
		return isNew;
	}

	public Item setNou(boolean aNew) {
		isNew = aNew;
		return this;
	}

	public void setNew(boolean aNew) {
		isNew = aNew;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		if (id != item.id) return false;
		if (isNew != item.isNew) return false;
		if (title != null ? !title.equals(item.title) : item.title != null) return false;
		return description != null ? description.equals(item.description) : item.description == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (isNew ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", isNew=" + isNew +
				'}';
	}
}