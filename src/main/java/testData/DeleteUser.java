package testData;

public class DeleteUser {
    private final String userid;
    private final String id;


    public DeleteUser(String userid, String id) {
        this.id = id;
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getId() {
        return id;
    }
}
