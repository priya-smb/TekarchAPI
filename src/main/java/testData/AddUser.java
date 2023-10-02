package testData;



public class AddUser {

    private final String accountno;
    private final String departmentno;
    private final String salary;
    private final String pinCode;

    public AddUser(String accountno, String deptNum, String salary, String pinCode) {
        this.accountno = accountno;
        this.departmentno = deptNum;
        this.salary = salary;
        this.pinCode = pinCode;
    }

    public String getAccountno() {
        return accountno;
    }

    public String getDepartmentno() {
        return departmentno;
    }

    public String getSalary() {
        return salary;
    }

    public String getPinCode() {
        return pinCode;
    }
}
