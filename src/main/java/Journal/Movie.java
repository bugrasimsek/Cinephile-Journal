package Journal;

import MYSQLMethods.MovieDatabaseManager;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonCrew;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Movie extends JournalItem {

    MovieDatabaseManager movieDatabase = null;

    public Movie(String name, boolean isWatched, String watchDate,
            int runtime, double movieScore, String relaseDate, int userRating, String posterType,
            List<Keyword> keywords, List<Genre> genres,
            List<PersonCast> cast, List<PersonCrew> crew) throws SQLException {
        this.name = name;
        this.isWatched = isWatched;
        this.watchDate = watchDate;
        this.runtime = runtime;
        this.movieScore = movieScore;
        this.relaseDate = relaseDate;
        this.userRating = userRating;
        this.posterType = posterType;

        //şunlar düzenleme istiyor
        this.keywords = keywords;
        this.genres = genres;
        this.cast = cast;
        this.crew = crew;

        for (int i = 0; i < genres.size(); i++) {
            genreList.add(genres.get(i).getName());
        }

        for (int i = 0; i < cast.size(); i++) {
            castList.add(cast.get(i).getName() + " - " + cast.get(i).getCharacter());

        }

        for (int i = 0; i < crew.size(); i++) {
            crewList.add(crew.get(i).getName() + " - " + crew.get(i).getJob());
        }

        for (int i = 0; i < keywords.size(); i++) { 
            keywordList.add(keywords.get(i).getName());
        }

        movieDatabase = new MovieDatabaseManager();

        addMovie();
        this.databaseID = movieDatabase.getMovieID(name);

        addGenres();
        addKeywords(); 
        addCast();
        addCrew();
        addPoster("w100"); //100x150 poster path
        addPoster("w250"); //250x375 poster path

        movieDatabase.disconnect();

    }

    public Movie(String properTitle, boolean isWatched, String watchDate,
            int runtime, String relaseDate, int userRating, int posterWidth,
            ArrayList<String> genres, ArrayList<String> cast, ArrayList<String> crew) throws SQLException {
        this.name = properTitle;
        this.isWatched = isWatched;
        this.watchDate = watchDate;
        this.runtime = runtime;
        this.relaseDate = relaseDate;
        this.userRating = userRating;
        this.movieScore = 0;

        movieDatabase = new MovieDatabaseManager();
        this.databaseID = movieDatabase.getMovieID(properTitle);

        this.genreList = genres;
        this.castList = cast;
        this.crewList = crew;
        this.keywordList = null;

      
        this.genres = null;
        this.cast = null;
        this.crew = null;
        this.keywords = null;

        this.posterType = "jpg";
        this.posterPath = "src/main/resources/posters/" + name + posterWidth + "." + posterType;

    }

    public void addMovie() throws SQLException {
        int tempUserID = 2;

        movieDatabase.addMovieRow(this.name, this.relaseDate, this.runtime,
                tempUserID, userRating, watchDate, isWatched);
    }

    public void addGenres() throws SQLException {
        for (int i = 0; i < genres.size(); i++) {
            movieDatabase.addGenreRow(databaseID, genres.get(i).getName());
        }
    }

    public void addKeywords() throws SQLException {
        for (int i = 0; i < keywords.size(); i++) {
            movieDatabase.addKeywordRow(databaseID, keywords.get(i).getName());
        }
    }

    public void addCast() throws SQLException {
        int size;

        if (cast.size() < 3) {
            size = cast.size();
        } else //only 3 cast members
        {
            size = 3;
        }

        for (int i = 0; i < size; i++) {
            movieDatabase.addCastRow(databaseID, cast.get(i).getName(), cast.get(i).getCharacter());
        }
    }

    public void addCrew() throws SQLException {
        int size = 30;
        String job;
        PersonCrew currentCrewPerson;

        //only 30 crew members
        if (crew.size() < 30) {
            size = crew.size();
        }

        for (int i = 0; i < size; i++) {
            currentCrewPerson = crew.get(i);
            job = currentCrewPerson.getJob(); //only director, writer, screenplay, novel, screenwriter

            if (job.equals("Director") || job.equals("Writer")
                    || job.equals("Screenplay") || job.equals("Novel") || job.equals("Screenwriter")) {
                movieDatabase.addCrewRow(databaseID, currentCrewPerson.getName(), job);
            }

        }
    }

    public void addPoster(String width) throws SQLException {
        posterPath = "src/main/resources/posters/" + name + width + "." + posterType;
        movieDatabase.addPosterRow(databaseID, posterPath);
    }

    @Override
    public String toString() {
        return this.name + this.databaseID;
    }

}
