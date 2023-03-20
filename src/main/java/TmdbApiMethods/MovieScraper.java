package TmdbApiMethods;

import System.WorkingWithImages;
import Journal.Movie;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MovieScraper {

    String apiKey = "7cf84b70eea30051628967800efc4f26";
    TmdbApi apiObj = new TmdbApi(apiKey);
    TmdbMovies moviesObj = new TmdbApi(apiKey).getMovies();

    MovieDb chosenMovie = null;
    MovieDb chosenMovieByID = null;

    //DATA TO SCRAPED
    String title;
    int id;
    int runtime;
    double movieScore;
    String posterType;
    String posterUrlPath;
    String relaseDate;
    List<Keyword> keywords;
    List<Genre> genres;
    List<PersonCast> cast;
    List<PersonCrew> crew;
    private List<MovieDb> searchResults;
    Movie movieObj = null; 

    public void createMovieObject(Boolean isWatched, String watchDate, int userRating) throws SQLException {
        scrapeID();
        getMovieById(id);

        scrapeTitle();
        scrapeDuration();
        scrapeRelaseDate();

        scrapeCast();
        scrapeCrew();
        scrapeGenres();
        scrapeKeywords();

        setPosterType("jpg"); //can be changed to "png" or "jpg"
        scrapePoster();

        movieObj = new Movie(title, isWatched, watchDate,
                runtime, movieScore, relaseDate, userRating, posterType,
                keywords, genres, cast, crew);

    }

    public void scrapeMovieList(String movieToSearch) {

        MovieResultsPage movieResults = apiObj.getSearch().searchMovie(movieToSearch, //movieTitle
                null, //searchYear
                null, //language
                true, //includeAdultMovies
                1); //page

        searchResults = movieResults.getResults();
    }

    public void decideMovieIndex(int index) {
        chosenMovie = searchResults.get(index);
    }

    public void scrapeID() {
        id = chosenMovie.getId();

    }

    public void getMovieById(int id) {
        chosenMovieByID = moviesObj.getMovie(id, null, null);
    }

    public void scrapeTitle() {
        title = chosenMovieByID.getTitle();
    }

    public void scrapeRelaseDate() {
        relaseDate = chosenMovie.getReleaseDate();

    }

    public void scrapeKeywords() {  
        keywords = moviesObj.
                getMovie(id, null, TmdbMovies.MovieMethod.keywords).getKeywords();
    }

    public void scrapeGenres() {
        genres = chosenMovieByID.getGenres();
    }

    public void scrapeDuration() {
        runtime = chosenMovieByID.getRuntime();

    }

    public void scrapeCast() {
        cast = moviesObj.
                getMovie(id, null, TmdbMovies.MovieMethod.credits).getCredits().getCast();

    }

    public void scrapeCrew() {
        crew = moviesObj.
                getMovie(id, null, TmdbMovies.MovieMethod.credits).getCredits().getCrew();
    }

    public void scrapePoster() {
        posterUrlPath = chosenMovie.getPosterPath();

        try {
            WorkingWithImages.imageSaver(posterUrlPath, title, posterType);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //if more than one poster is needed, use this method
        //moviesObj.getMovie(id, null, TmdbMovies.MovieMethod.images).getPosterPath();
    }

    public List<MovieDb> getSearchResults() {
        return searchResults;
    }

    public void setPosterType(String posterType) {
        this.posterType = posterType;
    }

}
