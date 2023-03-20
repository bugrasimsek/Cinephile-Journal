package MYSQLMethods;

import static MYSQLMethods.DatabaseManager.url;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MovieDatabaseManager extends DatabaseManager {

    public MovieDatabaseManager() throws SQLException {
        url = "jdbc:mysql://localhost:3306/moviedb";
    }

    public void addMovieRow(String movieName, String relaseDateString, int duration,
            int user_id, int rating, String watchDateString, boolean isWatched) throws SQLException {

        int isWatchedInt = 0;
        if (isWatched) {
            isWatchedInt = 1;
        }

        Date watch_date = null;

        try { //if format is not correct, it will throw an exception
            watch_date = java.sql.Date.valueOf(watchDateString);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getCause());
        }

        Date relase_date = java.sql.Date.valueOf(relaseDateString);

        String query = "INSERT INTO moviedb.movie"
                + "(movie_name, relase_date, duration, user_id, user_rating, isWatched, watch_date) "
                + "VALUES(?,?,?,?,?,?,?)";

        
        PreparedStatement addMovieStatement = getConnection().prepareStatement(query);
        addMovieStatement.setString(1, movieName);
        addMovieStatement.setDate(2, relase_date);
        addMovieStatement.setInt(3, duration);
        addMovieStatement.setInt(4, user_id);
        addMovieStatement.setInt(5, rating);
        addMovieStatement.setInt(6, isWatchedInt);
        addMovieStatement.setDate(7, watch_date);
        addMovieStatement.execute();
        addMovieStatement.close();

        int movieID = getMovieID(movieName);
        
    }

    public void removeMovie(String movieName) throws SQLException {
        String query = "DELETE FROM movie\n"
                + "WHERE movie_id = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setInt(1, getMovieID(movieName));
        searchIDStatement.execute();
        searchIDStatement.close();
    }

    public int getMovieID(String movieName) throws SQLException {

        String query = "SELECT movie_id\n"
                + "FROM moviedb.movie\n"
                + "WHERE movie_name = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, movieName);

        ResultSet idResult = searchIDStatement.executeQuery();

        if (idResult.next()) {
            int id = idResult.getInt(1);
            searchIDStatement.close();
            return id;
        } else {
            searchIDStatement.close();
            return -1;
        }
    }

    public ArrayList<Object> getMovieDetails(String dbTitle) throws SQLException {
        ArrayList<Object> movieInfoList = new ArrayList<>();

        String query = "SELECT watch_date, duration, relase_date, user_rating\n"
                + "FROM moviedb.movie\n"
                + "WHERE movie_name = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, dbTitle);

        ResultSet movieResultSet = searchIDStatement.executeQuery();

        if (movieResultSet.next()) {
            Date watchDate = movieResultSet.getDate(1);
            int duration = movieResultSet.getInt(2);
            Date relaseDate = movieResultSet.getDate(3);
            int userRating = movieResultSet.getInt(4);

            String watchDateString = "Not Specified";
            String relaseDateString = "(?)";

            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            try {
                watchDateString = dateFormat.format(watchDate);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                relaseDateString = relaseDate.toString().substring(0, 4);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            movieInfoList.add(watchDateString);
            movieInfoList.add(duration);
            movieInfoList.add(relaseDateString);
            movieInfoList.add(userRating);
            searchIDStatement.close();
        }

        return movieInfoList;
    }

    public ArrayList<String> getMovieGenres(String movieName) throws SQLException {

        ArrayList<String> genres = new ArrayList<>();

        String query = "SELECT genre_name \n"
                + "FROM moviedb.movie_genre\n"
                + "JOIN moviedb.movie on movie.movie_id = movie_genre.movie_id\n"
                + "JOIN moviedb.genre on genre.genre_id = movie_genre.genre_id\n"
                + "WHERE movie_name = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, movieName);
        ResultSet movieResultSet = searchIDStatement.executeQuery();

        while (movieResultSet.next()) {
            genres.add(movieResultSet.getString(1));
        }

        return genres;
    }

    public ArrayList<String> getMovieCast(String movieName) throws SQLException {
        ArrayList<String> cast = new ArrayList<>();

        String query = "SELECT person_name, character_name\n"
                + "FROM moviedb.movie_cast\n"
                + "JOIN moviedb.person on person.person_id = movie_cast.person_id\n"
                + "WHERE movie_id = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setInt(1, getMovieID(movieName));
        ResultSet movieResultSet = searchIDStatement.executeQuery();

        while (movieResultSet.next()) {
            cast.add(movieResultSet.getString(1) + " (" + movieResultSet.getString(2) + ")");
        }

        return cast;
    }

    public ArrayList<String> getMovieCrew(String movieName) throws SQLException {
        ArrayList<String> crew = new ArrayList<>();

        String query = "SELECT person_name, job_name\n"
                + "FROM moviedb.movie_crew\n"
                + "JOIN moviedb.person on person.person_id = movie_crew.person_id\n"
                + "JOIN moviedb.job on job.job_id = movie_crew.job_id\n"
                + "WHERE movie_id = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setInt(1, getMovieID(movieName));
        ResultSet movieResultSet = searchIDStatement.executeQuery();

        while (movieResultSet.next()) {
            crew.add(movieResultSet.getString(1) + " (" + movieResultSet.getString(2) + ")");
        }

        return crew;
    }

    //Returns the data of a single movie
    public ArrayList<String> getMovieData(String movieName) throws SQLException {
        ArrayList<String> infoList = new ArrayList<>();

        String query = "SELECT movie_name, watch_date\n"
                + "FROM moviedb.movie\n"
                + "WHERE movie_name LIKE (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, "%" + movieName + "%");

        ResultSet movieResultSet = searchIDStatement.executeQuery();

        while (movieResultSet.next()) {
            String name = movieResultSet.getString(1);
            Date watchDate = movieResultSet.getDate(2);
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String watchDateString = " -Not Specified- ";

            try {
                watchDateString = dateFormat.format(watchDate);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            String currentString = name + " (Watched on: " + watchDateString + ")";
            infoList.add(currentString);

        }

        return infoList;

    }

    //Returns list of all movies that are logged by user
    public ArrayList<Object[]> getWatchedMoviesData() throws SQLException {

        int isWatched = 1;

        ArrayList<Object[]> movieList = new ArrayList<>();

        
        String query = "SELECT movie_name, watch_date, user_rating\n"
                + "FROM moviedb.movie\n"
                + "WHERE isWatched = (?)";

        
        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setInt(1, isWatched);

        ResultSet movieResultSet = searchIDStatement.executeQuery();

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

        while (movieResultSet.next()) {
            String movieName = movieResultSet.getString(1);
            Date watchDate = movieResultSet.getDate(2);
            int userRate = movieResultSet.getInt(3);
            String userRateString = "-Movie Not Rated-";
            String watchDateString = "-Date Not Specified-";

            try {
                watchDateString = dateFormat.format(watchDate);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (userRate != -1) {
                userRateString = String.valueOf(userRate);
            }

            Object[] objectArr = {movieName, watchDateString, userRateString};

            movieList.add(objectArr);
        }
        return movieList;
    }

    public int getJobID(String jobName) {
        int jobID;

        switch (jobName) {
            case "Director":
                jobID = 2;
                break;

            case "Screenwriter":
                jobID = 3;
                break;

            case "Screenplay":
                jobID = 3;
                break;

            case "Writer":
                jobID = 3;
                break;

            case "Novel":
                jobID = 4;
                break;

            default:
                jobID = -1;
        }

        return jobID;
    }

    public int getKeywordID(String keywordName) throws SQLException {

        String query = "SELECT keyword_id\n"
                + "FROM moviedb.keyword\n"
                + "WHERE keyword_name = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, keywordName);

        ResultSet idResult = searchIDStatement.executeQuery();

        if (idResult.next()) {
            int id = idResult.getInt(1);
            searchIDStatement.close();
            return id;
        } else {
            searchIDStatement.close();
            return -1;
        }
    }

    public int getGenreID(String genreName) throws SQLException {
        String query = "SELECT genre_id\n"
                + "FROM moviedb.genre\n"
                + "WHERE genre_name = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, genreName);

        ResultSet idResult = searchIDStatement.executeQuery();

        if (idResult.next()) {
            int id = idResult.getInt(1);
            searchIDStatement.close();
            return id;
        } else {
            searchIDStatement.close();
            return -1;
        }
    }

    public int getPersonID(String personName) throws SQLException {
        String query = "SELECT person_id\n"
                + "FROM moviedb.person\n"
                + "WHERE person_name = (?)";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setString(1, personName);

        ResultSet idResult = searchIDStatement.executeQuery();

        if (idResult.next()) {
            int id = idResult.getInt(1);
            searchIDStatement.close();
            return id;
        } else {
            searchIDStatement.close();
            return -1;
        }
    }

    public void addKeywordRow(int movieID, String keyword) throws SQLException {
        int keywordID = getKeywordID(keyword);
        PreparedStatement addKeywordStatement = null;
        String query = null;

        //if keyword is not in the table (id = -1) add it to the table
        if (keywordID == -1) {
            query = "INSERT INTO moviedb.keyword"
                    + "(keyword_name) VALUES(?)";

            addKeywordStatement = getConnection().prepareStatement(query);
            addKeywordStatement.setString(1, keyword);
            addKeywordStatement.execute();
            addKeywordStatement.close();
            keywordID = getKeywordID(keyword);
        }

        //movie_keyword table
        query = "INSERT INTO moviedb.movie_keyword"
                + "(movie_id, keyword_id) VALUES(?, ?)";

        addKeywordStatement = getConnection().prepareStatement(query);
        addKeywordStatement.setInt(1, movieID);
        addKeywordStatement.setInt(2, keywordID);

        addKeywordStatement.execute();
        addKeywordStatement.close();
    }

    public void addGenreRow(int movieID, String genre) throws SQLException {
        int genreID = getGenreID(genre);
        PreparedStatement addGenreStatement = null;
        String query = null;

        if (genreID == -1) { 
            query = "INSERT INTO moviedb.genre"
                    + "(genre_name) VALUES(?)";
            addGenreStatement = getConnection().prepareStatement(query);
            addGenreStatement.setString(1, genre);
            addGenreStatement.execute();
            addGenreStatement.close();
            genreID = getGenreID(genre);
        }

        query = "INSERT INTO moviedb.movie_genre"
                + "(movie_id, genre_id) VALUES(?, ?)";

        addGenreStatement = getConnection().prepareStatement(query);
        addGenreStatement.setInt(1, movieID);
        addGenreStatement.setInt(2, genreID);

        addGenreStatement.execute();
        addGenreStatement.close();
    }

    public void addNotesRow(int movieID) throws SQLException {
        String note = "sed vulputate mi sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada proin libero nunc consequat interdum varius sit amet mattis vulputate enim nulla aliquet porttitor lacus luctus accumsan tortor posuere ac ut consequat semper viverra nam libero justo laoreet sit amet cursus sit amet dictum";
        PreparedStatement addNoteStatement = null;
        String query = null;

        query = "INSERT INTO moviedb.notes"
                + "(movie_id, note_content) VALUES(?, ?)";

        addNoteStatement = getConnection().prepareStatement(query);
        addNoteStatement.setInt(1, movieID);
        addNoteStatement.setString(2, note);

        addNoteStatement.execute();
        addNoteStatement.close();
    }

    public void addPersonRow(String person) throws SQLException {
        PreparedStatement addGenreStatement = null;
        String query = null;

        query = "INSERT INTO moviedb.person"
                + "(person_name) VALUES(?)";

        addGenreStatement = getConnection().prepareStatement(query);
        addGenreStatement.setString(1, person);
        addGenreStatement.execute();
        addGenreStatement.close();

    }

    public void addCrewRow(int movieID, String person, String jobName) throws SQLException {
        int personID = getPersonID(person);
        int jobID = getJobID(jobName);

        if (personID == -1) {
            addPersonRow(person);
            personID = getPersonID(person);
        }

        String query = "INSERT INTO moviedb.movie_crew"
                + "(movie_id, person_id, job_id) VALUES(?, ?, ?)";

        PreparedStatement addCrewStatement = getConnection().prepareStatement(query);
        addCrewStatement.setInt(1, movieID);
        addCrewStatement.setInt(2, personID);
        addCrewStatement.setInt(3, jobID);

        addCrewStatement.execute();
        addCrewStatement.close();
    }

    public void addCastRow(int movieID, String person, String charName) throws SQLException {
        int personID = getPersonID(person);

        if (personID == -1) {
            addPersonRow(person);
            personID = getPersonID(person);
        }

        String query = "INSERT INTO moviedb.movie_cast"
                + "(movie_id, person_id, character_name) VALUES(?, ?, ?)";

        PreparedStatement addCrewStatement = getConnection().prepareStatement(query);
        addCrewStatement.setInt(1, movieID);
        addCrewStatement.setInt(2, personID);
        addCrewStatement.setString(3, charName);

        addCrewStatement.execute();
        addCrewStatement.close();
    }

    public void addPosterRow(int movieID, String localPath) throws SQLException {
        PreparedStatement addNoteStatement = null;
        String query = null;

        query = "INSERT INTO moviedb.movie_posters"
                + "(movie_id, poster_path) VALUES(?, ?)";

        addNoteStatement = getConnection().prepareStatement(query);
        addNoteStatement.setInt(1, movieID);
        addNoteStatement.setString(2, localPath);

        addNoteStatement.execute();
        addNoteStatement.close();

    }


    public ArrayList<String> getLatestPosters(int limit) throws SQLException {
        ArrayList<String> posterList = new ArrayList<>();

        String query = "SELECT poster_path\n"
                + "FROM moviedb.movie_posters\n"
                + "WHERE poster_id % 2 = 0\n"
                + "ORDER BY poster_id DESC\n"
                + "LIMIT ?";

        PreparedStatement searchIDStatement = getConnection().prepareStatement(query);
        searchIDStatement.setInt(1, limit);
        ResultSet movieResultSet = searchIDStatement.executeQuery();

        while (movieResultSet.next()) {
            String path = movieResultSet.getString(1);
            posterList.add(path);
        }
        return posterList;

    }

}
