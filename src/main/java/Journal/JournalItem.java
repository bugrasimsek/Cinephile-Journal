package Journal;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public abstract class JournalItem {
    public String name;
    public Boolean isWatched = false;
    public String watchDate; 
    public int userRating;
    public int databaseID;

    int tmdbID;
    public int runtime;
    double movieScore;
    String posterPath;
    public String relaseDate;
    List<Keyword> keywords;
    List<Genre> genres;
    List<PersonCast> cast;
    List<PersonCrew> crew;
    String posterType;
    
    public ArrayList<String> castList = new ArrayList<>(); 
    public ArrayList<String> crewList = new ArrayList<>();  
    public ArrayList<String> genreList = new ArrayList<>(); 
    public ArrayList<String> keywordList = new ArrayList<>();
    
    
}
