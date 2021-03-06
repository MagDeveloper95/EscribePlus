package model.DataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.IDataObject.ICapitulo;

@Entity
@Table(name="Capitulo")
@NamedQueries({
	@NamedQuery(name="getAll", query = "SELECT c FROM Capitulo c"),
	@NamedQuery(name="getCapituloFromParte", query = "SELECT c FROM Capitulo c WHERE c.parte.id=:idpartes")
})
public class Capitulo implements ICapitulo, Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	@Column(name="name")
	protected String name;	
	@Column(name="text")
	protected String text;
	@OneToMany(mappedBy = "capitulo",cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Notas_Cap> notas;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_parte")
	protected Partes parte;
	
	public Capitulo() {
		this(-1L,"","",new ArrayList<Notas_Cap>(),new Partes());
	}
	public Capitulo(String name) {
		this(-1L,name,"",new ArrayList<Notas_Cap>(),new Partes());
	}
	public Capitulo(String name, String text) {
		this(-1L,name,text,new ArrayList<Notas_Cap>(),new Partes());
	}
	public Capitulo(String name, String text, Notas_Cap Note_cap) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.notas = new ArrayList<Notas_Cap>();
		this.notas.add(Note_cap);
		this.parte = new Partes();

	}
	public Capitulo(String name, String text, List<Notas_Cap> part) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.notas = part;
		this.parte = new Partes();

	}
	public Capitulo(String name, String text, Partes part) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.notas = new ArrayList<Notas_Cap>();
		this.parte = part;

	}
	public Capitulo(Long id, String name, String text) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.notas = new ArrayList<Notas_Cap>();
		this.parte = new Partes();
	}
	public Capitulo(Long id, String name, String text, Notas_Cap Note_cap) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.notas = new ArrayList<Notas_Cap>();
		this.notas.add(Note_cap);
		this.parte = new Partes();
	}
	public Capitulo(Long id, String name, String text, List<Notas_Cap> part) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.notas = part;
		this.parte = new Partes();
	}	

	public Capitulo(Long id, String name, String text, List<Notas_Cap> notas, Partes parte) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
		this.notas = notas;
		this.parte = parte;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Notas_Cap> getNotas() {
		return notas;
	}
	public void setNotas(List<Notas_Cap> notas) {
		this.notas = notas;
	}
	public Partes getParte() {
		return parte;
	}
	public void setParte(Partes parte) {
		this.parte = parte;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  name ;
	}

	
}