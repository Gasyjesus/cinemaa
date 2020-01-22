package cinema.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Movie {           //creation de la classe movie
	
	// attributes
	private String titre;
	private int annee;
	private int duration;
	private PersonMoi director;
	private List<PersonMoi> actors;

	//constructors
	public Movie(String title, int year) {                  // m�thode permettant de cr�er un objet de la classe cr��e (constructor)
		this(title, year, 0);
	}

	public Movie(String titre, int annee, int duration) {
		this(titre, annee, duration, null);
	}

	
	public Movie(String titre, int annee, PersonMoi director) {
		this(titre, annee, 0, director);
	}
	
	
	public Movie(String titre, int annee, int duration, PersonMoi director) {
		super();
		this.titre = Objects.requireNonNull(titre);
		this.annee = annee;
		this.duration = duration;
		this.director = director;
		this.actors = new ArrayList<>();
	}


	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
		

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}


	public PersonMoi getDirector() {
		return director;
	}

	public void setDirector(PersonMoi director) {
		this.director = director;
	}
	
	
	public void addActor(PersonMoi actor) 
	{
		this.actors.add(actor);
	}
	
	public void addAllActors(Collection<? extends PersonMoi> listeActors)
	{
		this.actors.addAll(listeActors);
	}
	
	public void addAllActors(PersonMoi... actors)
	{
		this.addAllActors(Arrays.asList(actors));
	}
	
	
	public Iterator<PersonMoi> iteratorActors()
	{
		return this.actors.iterator();
	}
	
	
	public Stream<PersonMoi> streamActors()
	{
		return this.actors.stream();
	}
//	
//	public Iterable<PersonMoi> itera
	
	//Methods
//	@Override
//	public String toString() {
//		return "Movie [titre=" + titre + ", annee=" + annee + ", duration=" + duration + "]";
//	}
	
	@Override
	public String toString() {
		return "\n" + titre + " (" + annee 	+ (duration==0?"" : ", "+duration+"mn" ) + ")";
	}


	@Override
	public int hashCode() {
		return Objects.hash(titre, annee);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return this.titre.equals(other.titre)
				&& this.annee == other.annee;
	}
	
	
	
}
