package model.DataObject;

import java.io.Serializable;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 0d0eddc24703da245581c1c5c43c99c109e4e8ee
=======

import java.util.ArrayList;
import java.util.List;
>>>>>>> 68d7724192480b27cd38fe1e96888a08d8eb095f

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.IDataObject.ICapitulo;

@Entity
@Table(name="Capitulo")
public class Capitulo implements ICapitulo, Serializable{

	private static long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	@Column(name="name")
	protected String name;	
	@Column(name="text")
	protected String text;
	@OneToMany(mappedBy = "capitulo",cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Notas_Cap> parts;
	
	


	public Capitulo() {
		this(-1L,"","",new ArrayList<Notas_Cap>());
	}
	public Capitulo(String name, String text) {
		this(-1L,name,text,new ArrayList<Notas_Cap>());
	}
	public Capitulo(String name, String text, Notas_Cap Note_cap) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.parts = new ArrayList<Notas_Cap>();
		this.parts.add(Note_cap);
	}
	public Capitulo(String name, String text, List<Notas_Cap> part) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.parts = part;
	}
	public Capitulo(Long id, String name, String text) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.parts = new ArrayList<Notas_Cap>();
	}
	public Capitulo(Long id, String name, String text, Notas_Cap Note_cap) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.parts = new ArrayList<Notas_Cap>();
		this.parts.add(Note_cap);
	}
	public Capitulo(Long id, String name, String text, List<Notas_Cap> part) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.parts = part;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Capitulo other = (Capitulo) obj;
		return Objects.equals(id, other.id) || Objects.equals(name, other.name);
	}
<<<<<<< HEAD
<<<<<<< HEAD
	
	
=======
>>>>>>> 0d0eddc24703da245581c1c5c43c99c109e4e8ee
=======
>>>>>>> 68d7724192480b27cd38fe1e96888a08d8eb095f
	@Override
	public String toString() {
		return "Capitulo [id=" + id + ", name=" + name + ", text=" + text + ", parts=" + parts + "]";
	}
	
}