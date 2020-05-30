package pl.edu.pwsztar;

import java.util.Optional;

final class UserId implements UserIdChecker {

    private final String id;
    private Pesel pesel;
    public UserId(final String id ) {
        this.id = id;
        this.pesel = new PeselImpl(id);
    }

    @Override
    public boolean isCorrectSize() {
        return pesel.getLength() == 11;
    }

    @Override
    public Optional<Sex> getSex() {
        if( pesel.getDigit(9) % 2 == 0){
            return Optional.of(Sex.WOMAN);
        } else return Optional.of(Sex.MAN);
    }

    @Override
    public boolean isCorrect() {
        int day = Integer.parseInt(pesel.getDay());
        int month = Integer.parseInt(pesel.getMonth());

        if(!((month > 0 && month <= 12) || (month > 20 && month <= 32 ) || (month > 40 && month <= 52) || (month > 60 && month <= 72) || (month > 80 && month <= 92))){
            return false;
        }

        if( day < 0 || day > 31 ||  pesel.getLength() != 11 ){
            return false;
        }

        int sum = 0;
        int[] values = {1,3,7,9,1,3,7,9,1,3,1};
        for (int i = 0; i<11 ; ++i){
            sum += pesel.getDigit(i) * values[i];
        }
        return sum % 10 == 0;
    }

    @Override
    public Optional<String> getDate() {

        if( !isCorrect() ){
            return Optional.empty();
        }

        String date="";
        int month = Integer.parseInt(pesel.getMonth());

        date += pesel.getDay();
        date += "-";

        if(month > 0 && month <= 12){
            date += pesel.getMonth();
            date += "-";
            date += "19";
            date += pesel.getYear();
        } else if (month > 20 && month <= 32){
            if(month - 20 < 10 ){
                date += "0";
            }
            date += month - 20;
            date += "-";
            date += "20";
            date += pesel.getYear();
        } else if (month > 40 && month <= 52){
            if(month - 40 < 10 ){
                date += "0";
            }
            date += month - 40;
            date += "-";
            date += "21";
            date += pesel.getYear();
        } else if (month > 60 && month <= 72){
            if(month - 60 < 10 ){
                date += "0";
            }
            date += month - 60;
            date += "-";
            date += "22";
            date += pesel.getYear();
        } else if (month > 80 && month <= 92){
            if(month - 80 < 10 ){
                date += "0";
            }
            date += month - 80;
            date += "-";
            date += "18";
            date += pesel.getYear();
        }
        return Optional.of(date);
    }
}
