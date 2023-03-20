package Kolokvium_2.Movies;

import java.util.*;
import java.util.stream.Collectors;

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

// vashiot kod ovde
class Movie{
    private String title;
    private List<Integer> ratings = new ArrayList<>();

    public Movie(String title, List<Integer> ratings) {
        this.title = title;
        this.ratings = ratings;
    }

    double avgRating() {
        return ratings.stream().mapToInt(i->i).summaryStatistics().getAverage();
    }


    public String getTitle() {
        return title;
    }
    public List<Integer> getRatings() {
        return ratings;
    }
    @Override
    public String toString() {
        //Love on the Run (1979) (7.33) of 6 ratings
        return String.format("%s (%.2f) of %d ratings", title, avgRating(), ratings.size());
    }
}
class MoviesList{
    List<Movie> moviesList;

    public MoviesList() {
        this.moviesList = new LinkedList<>();
    }
    public void addMovie(String title, int[] ratings){
        moviesList.add(new Movie(title, Arrays.stream(ratings).boxed().collect(Collectors.toList())));
    }
    public List<Movie> top10ByAvgRating(){
        return moviesList.stream().sorted(Comparator.comparing(Movie::avgRating).reversed().thenComparing(Movie::getTitle))
                .limit(10).collect(Collectors.toList());
    }
    public int maxRatingMovie(){
        return moviesList.stream().map(movie->movie.getRatings().size()).mapToInt(i->i)
                .summaryStatistics().getMax();
    }
    public List<Movie> top10ByRatingCoef(){
        //(се пресметува како просечен ретјтинг на филмот x вкупно број на рејтинзи на филмот
        // / максимален број на рејтинзи (од сите филмови во листата)
//        List<Movie> list1 = moviesList.stream()
//                .sorted(Comparator.comparing(movie -> movie.avgRating() * movie.getRatings().size() / maxRatingMovie()))
//                .collect(Collectors.toList());
//        List<Movie> resultat = new LinkedList<>();
//        for(int i=list1.size()-1;i>=list1.size()-10;i--){
//            resultat.add(list1.get(i));
//        }
//        return resultat;
        return moviesList.stream().sorted(new CoefComparator(maxRatingMovie()))
                .limit(10).collect(Collectors.toList());
    }
}
class CoefComparator implements Comparator<Movie>{
    int maxRating;

    public CoefComparator(int maxRating) {
        this.maxRating = maxRating;
    }

    @Override
    public int compare(Movie o1, Movie o2) {
        int ar = Double.compare(o1.avgRating()*o1.getRatings().size() / maxRating,
                o2.avgRating()*o2.getRatings().size()/maxRating);
        if (ar==0){
            return o1.getTitle().compareTo(o2.getTitle());
        }return  -ar;
    }
}
