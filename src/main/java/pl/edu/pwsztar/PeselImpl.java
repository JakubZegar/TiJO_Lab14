package pl.edu.pwsztar;

public class PeselImpl implements Pesel {

    private String userId;

    PeselImpl(String userId) {
        this.userId = userId;
    }

    @Override
    public int getDigit(int digitIndex) {
        return Integer.parseInt(String.valueOf(userId.charAt(digitIndex)));
    }

    @Override
    public int getLength() {
        return userId.length();
    }

    @Override
    public String getDay() {

        String res = "";
        res += userId.charAt(4);
        res += userId.charAt(5);
        return res;
    }

    @Override
    public String getMonth() {
        String res = "";
        res += userId.charAt(2);
        res += userId.charAt(3);
        return res;
    }

    @Override
    public String getYear() {
        String res = "";
        res += userId.charAt(0);
        res += userId.charAt(1);
        return res;
    }


}
