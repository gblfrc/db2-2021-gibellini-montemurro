package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;

public class MvPackageId implements Serializable {
	private int id_package;
	private int months;
	
	public MvPackageId() {	}
	
	public MvPackageId(int id_package,int months) {
		this.id_package=id_package;
		this.months=months;
	}
	
	@Override
	public int hashCode() {
		 return Objects.hash(getId_package(), getMonths() );
	}
	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MvPackageId)) return false;
        MvPackageId mvPackageId = (MvPackageId) o;
        return Objects.equals(getId_package(), mvPackageId.getId_package()) &&
                Objects.equals(getMonths(), mvPackageId.getMonths());
    }
	
	public int getId_package() {
		return id_package;
	}
	
	public int getMonths() {
		return months;
	}
}
