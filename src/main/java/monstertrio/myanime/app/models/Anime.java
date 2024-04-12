package monstertrio.myanime.app.models;

public class Anime {
    private int id;
    private String title;
    private String desc;
    private int rating;
    private String status;
    private String genre;
    private String imageUrl;
    private int userId;
    //private String imageUrl;
    public Anime(){
        this.id = 0; // Default ID, could be set to a negative number or a special value to indicate it's not set
        this.title = "";
        this.desc = "";
        this.rating = 0;
        this.status = "";
        this.genre = "";
        this.userId=0;
        //this.imageUrl = "";
    }
    public Anime(String title, String description, int rating, String status, String genre, int userId) {
        this.title = title;
        this.desc = description;
        this.rating = rating;
        this.status = status;
        this.genre = genre;
        this.userId = userId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }
    //rating
    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating=rating;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

    // Genre
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImageUrl() {return imageUrl;}

    public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}

    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){this.userId=userId;}

}
