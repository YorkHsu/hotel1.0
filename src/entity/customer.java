package entity;

public class customer {
    private String id;
    private String phoneNumber;
    private String name;
    private String gender;
    private String[]rooms=new String [10];;
    private int account;
    private int times;//消费个数；
    private String vip;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public customer() {
        for (int i=0;i<10;i++)rooms[i]="无";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getRooms() {
        return rooms;
    }

    public void addroom(String room) {
        for (int i=0;i<10;i++){
            if(rooms[i]=="无"){
                rooms[i]=room;
                break;
            }
        }
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}
