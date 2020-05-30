package pl.edu.pwsztar;

import java.io.Serializable;

public interface Pesel extends Serializable {

    int getDigit(int digitIndex);

    int getLength();

    String getDay();

    String getMonth();

    String getYear();
}
