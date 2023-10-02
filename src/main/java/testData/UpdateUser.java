package testData;

public class UpdateUser extends AddUser{

    private final String userid;
    private final String id;


    public UpdateUser(String accountno, String deptNum, String salary, String pinCode, String userid, String id) {
        super(accountno, deptNum, salary, pinCode);
        this.userid = userid;
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public String getId() {
        return id;
    }
}
