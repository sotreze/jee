package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemRole {
	

//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
	
	@Id
    private String name;

    public SystemRole() {
    }
    
//    public SystemRole(Integer id) {
//        this.id = id;
//    }
//
    public SystemRole(String name) {
        this.name = name;
    }
//    
//    public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		SystemRole other = (SystemRole) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		return true;
//	}
//    
//	@Override
//	public String toString() {
//		return "SystemRole [id=" + id + ", nome=" + name + "]";
//	}	
}