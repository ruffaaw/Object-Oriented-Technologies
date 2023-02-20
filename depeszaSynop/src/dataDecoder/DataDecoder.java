package dataDecoder;


public class DataDecoder implements IDataDecoder{
    public String informationAboutTypeOfDepesza(String input) {
        return switch (input) {
            case "AAXX" -> "Depesza ze stacji ladowej\n";
            case "BBXX" -> "Depesza ze stacji morskiej\n";
            case "" -> "Brak informacji na temat depeszy\n";
            default -> "Nieprawidłowy kod depeszy\n";
        };
    }

    public String informationAboutDateAndTime(String input) {
        if (input.isEmpty())
            return "";

        String string = "";
        char wind = input.charAt(4);
        switch (wind) {
            case '0' -> string = "prędkość w m/s (mierzona wiatromierzem Wilda lub szacowana)";
            case '1' -> string = "prędkość w m/s (mierzona anemometrem)";
            case '2' -> string = "prędkość w węzłach (mierzona wiatromierzem Wilda lub szacowana)";
            case '3' -> string = "prędkość w węzłach (mierzona anemometrem)";
        }
        return "Pomiar wykonany dnia " + input.charAt(0) + input.charAt(1) +
                " o godzinie " + input.charAt(2) + input.charAt(3) + " a " + string;
    }

    public String informationAboutID(String input) {
        if (input.isEmpty())
            return "";

        return "Identyfikator stacji: " + input + "\n";
    }

    public String informationAboutStructure(String input) {
        if (input.isEmpty())
            return "";

        String ir, ix, h, VV;
        ir = switch (input.charAt(0)) {
            case '0' -> "grupa opadowa w rozdziale 1 i 3";
            case '1' -> "grupa opadowa tylko w rozdziale 1";
            case '2' -> "grupa opadowa tylko w rozdziale 3";
            case '3' -> "grupa opadowa pominięta (opady nie wystąpiły)";
            case '4' -> "grupa opadowa pominięta (nie wykonywano pomiarów opadu)";
            default -> "Nieprawidlowe dane";
        };

        ix = switch (input.charAt(1)) {
            case '1' -> "stacja nieautomatyczna, grupa 7wwWW włączona";
            case '2' -> "stacja nieautomatyczna, grupa 7wwWW wyłączona (brak zjawisk)";
            case '3' -> "stacja nieautomatyczna, grupa 7wwWW wyłączona (brak danych)";
            case '4' -> "stacja automatyczna, grupa 7wwWW włączona";
            case '5' -> "stacja automatyczna, grupa 7wwWW wyłączona (brak zjawisk)";
            case '6' -> "stacja automatyczna, grupa 7wwWW wyłączona (brak danych)";
            default -> "Nieprawidlowe dane";
        };

        h = switch (input.charAt(2)) {
            case '0' -> "0 do 50 m";
            case '1' -> "50 do 100 m";
            case '2' -> "100 do 200 m";
            case '3' -> "200 do 300 m";
            case '4' -> "300 do 600 m";
            case '5' -> "600 do 1000 m";
            case '6' -> "1000 do 1500 m";
            case '7' -> "1500 do 2000 m";
            case '8' -> "2000 do 2500 m";
            case '9' -> "powyżej 2500 m";
            case '/' -> "nieznana";
            default -> "Nieprawidlowe dane";
        };

        String string = "";
        string = String.valueOf(input.charAt(3)) + input.charAt(4);
        if (string.equals("//"))
            VV = "brak danych";
        else if (string.equals("00"))
            VV = "ponizej 0,1 km";
        else if (Integer.parseInt(string) >= 51 && Integer.parseInt(string) <= 55)
            VV = "nie stosuje sie";
        else if (Integer.parseInt(string) >= 01 && Integer.parseInt(string) <= 50)
            VV = (Double.parseDouble(string) / 10) + " km";
        else if (Integer.parseInt(string) >= 56 && Integer.parseInt(string) <= 80)
            VV = (Integer.parseInt(string) - 50) + " km";
        else if (Integer.parseInt(string) == 81)
            VV = "35 km";
        else if (Integer.parseInt(string) == 82)
            VV = "40 km";
        else if (Integer.parseInt(string) == 83)
            VV = "45 km";
        else if (Integer.parseInt(string) == 84)
            VV = "50 km";
        else if (Integer.parseInt(string) == 85)
            VV = "55 km";
        else if (Integer.parseInt(string) == 86)
            VV = "60 km";
        else if (Integer.parseInt(string) == 87)
            VV = "65 km";
        else if (Integer.parseInt(string) == 88)
            VV = "70 km";
        else if (Integer.parseInt(string) == 89)
            VV = "powyzej 70 km";
        else if (Integer.parseInt(string) == 90)
            VV = "ponizej 0,05 km";
        else if (Integer.parseInt(string) == 91)
            VV = "0,05 - 0,2 km";
        else if (Integer.parseInt(string) == 92)
            VV = "0,2 - 0,5 km";
        else if (Integer.parseInt(string) == 93)
            VV = "0,5 - 1,0 km";
        else if (Integer.parseInt(string) == 94)
            VV = "1,0 - 2,0 km";
        else if (Integer.parseInt(string) == 95)
            VV = "2,0 - 4,0 km";
        else if (Integer.parseInt(string) == 96)
            VV = "4,0 - 10,0 km";
        else if (Integer.parseInt(string) == 97)
            VV = "10,0 - 20,0 km";
        else if (Integer.parseInt(string) == 98)
            VV = "20,0 - 50,0 km";
        else if (Integer.parseInt(string) == 99)
            VV = "powyzej 50 km";
        else
            VV = "Nieprawidlowe dane";

        return ir + ", typ stacji: " + ix + ", podstawa chmur: " + h + ", widzialnosc pozioma: " + VV + "\n";
    }

    public String informationAboutCloudly(String input) {
        if (input.isEmpty())
            return "";

        String N, dd, ff;
        N = switch (input.charAt(0)) {
            case '0' -> "0/8 (niebo bez chmur)";
            case '1' -> "1/8";
            case '2' -> "2/8";
            case '3' -> "3/8";
            case '4' -> "4/8";
            case '5' -> "5/8";
            case '6' -> "6/8";
            case '7' -> "7/8";
            case '8' -> "8/8";
            case '9' -> "niebo niewidoczne";
            case '/' -> "brak danych";
            default -> "Nieprawidlowe dane";
        };

        String string = String.valueOf(input.charAt(1)) + input.charAt(2);
        if (string.equals("//"))
            dd = "brak danych";
        else if(Integer.parseInt(string) == 00)
            dd = "cisza";
        else if (Integer.parseInt(string) > 00 && Integer.parseInt(string) < 36)
            dd = "od " + (Integer.parseInt(string) * 10 - 5) + "do " + (Integer.parseInt(string) * 10 + 5) + " stopni";
        else if (Integer.parseInt(string) == 36)
            dd = "od 355 do 4 stopni";
        else if (Integer.parseInt(string) == 99)
            dd = "zmienny lub z wielu kierunków";
        else
            dd = "Nieprawidlowe dane";

        ff = String.valueOf(input.charAt(3)) + input.charAt(4);

        return "wielkosc zachmurzenia ogolnego: " + N
                + ", kierunek wiatru: " + dd
                + ", predkosc wiatru: " + Integer.parseInt(ff) + "\n";
    }

    public String informationAboutTemperature(String input) {
        if (input.isEmpty())
            return "";

        String s, TTT;
        if (input.charAt(1) == '0') {
            s = "dodatnia";
            TTT = "+ " + Double.parseDouble(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) / 10;
        } else if (input.charAt(1) == '1') {
            s = "ujemna";
            TTT = "- " + Double.parseDouble(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) / 10;
        } else
            s = TTT = "Nieprawidlowe dane";
        return "temperatura " + s + ", temperatura powietrza: " + TTT + " stopni Celciusza\n";
    }

    public String informationAboutHumidity(String input) {
        if (input.isEmpty())
            return "";

        String s, TTT;
        if (input.charAt(1) == '0') {
            s = "temperatura dodatnia, ";
            TTT = "temperatura punktu rosy: + " + Double.parseDouble(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) / 10 + " stopni Celciusza\n";
        } else if (input.charAt(1) == '1') {
            s = "temperatura ujemna, ";
            TTT = "temperatura punktu rosy: - " + Double.parseDouble(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) / 10 + " stopni Celciusza\n";
        } else if (input.charAt(1) == '9') {
            s = "";
            if (Integer.parseInt(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) <= 100)
                TTT = "wilgotnosc wzgledna powietrza: " + Integer.parseInt(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) + " %";
            else
                TTT = "Nieprawidlowa wartosc wilgotnosci wzglednej powietrza";
        } else
            s = TTT = "Nieprawidlowe dane";
        return s + TTT;

    }

    public String informationAboutPressure(String input) {
        if (input.isEmpty())
            return "";

        String PPPP = "";
        PPPP = String.valueOf(Double.parseDouble(String.valueOf(input.charAt(1)) + input.charAt(2) + input.charAt(3) + input.charAt(4)) / 10);
        return "cisnienie atmosferyczne na poziomie stacji wynosi: " + PPPP + " hPA\n";
    }

    public String informationAboutPressureReducedToSeaLevel(String input) {
        String PPPP = "";
        if(input.charAt(1) == '0')
            PPPP = String.valueOf("1" + "0" + Double.parseDouble(String.valueOf(input.charAt(1)) + input.charAt(2) + input.charAt(3) + input.charAt(4)) / 10);
        else
            PPPP = String.valueOf(Double.parseDouble(String.valueOf(input.charAt(1)) + input.charAt(2) + input.charAt(3) + input.charAt(4)) / 10);

        return "ciśnienie zredukowane do poziomu morza: " + PPPP + " hPA\n";
    }

    public String informationAboutPressureTendency(String input) {
        if (input.isEmpty())
            return "";

        String a, ppp;
        a = switch (input.charAt(1)) {
            case '0' -> "wzrost, potem spadek; ciśnienie jest wyższe lub takie samo jak przed trzema godzinami";
            case '1' ->
                    "wzrost potem stan stały; lub wzrost, potem wzrost wolniejszy; ciśnienie jest wyższe niż przed trzema godzinami";
            case '2' -> "wzrost równomierny lub nierównomierny; ciśnienie jest wyższe niż przed trzema godzinami";
            case '3' ->
                    "spadek, potem wzrost; lub stan stały, potem wzrost; lub wzrost, potem wzrost słabszy; ciśnienie jest wyższe niż przed trzema godzinami";
            case '4' -> "stan stały; ciśnienie jest takie samo jak przed trzema godzinami";
            case '5' -> "spadek, potem wzrost; ciśnienie jest takie samo lub niższe niż przed trzema godzinami";
            case '6' ->
                    "spadek, potem stan stały; lub spadek, potem spadek wolniejszy; ciśnienie jest niższe niż przed trzema godzinami";
            case '7' -> "spadek równomierny lub nierównomierny; ciśnienie jest niższe niż przed trzema godzinami";
            case '8' ->
                    "wzrost, potem spadek; lub stan stały, potem spadek; lub spadek, potem spadek szybszy; ciśnienie jest niższe niż przed trzema godzinami " +
                            "liczby klucza 2, 4 i 7 - gdy w typie stacji ( ix ) podano stację automatyczną";
            default -> "Nieprawidlowe dane";
        };

        ppp = String.valueOf(Double.parseDouble(String.valueOf(input.charAt(2)) + input.charAt(3) + input.charAt(4)) / 10);

        return "jezeli chodzi o tendencje ciśnienia na poziomie stacji w ciągu 3 ostatnich godzin to : " + a
                + ", zmiana cisnienia: " + ppp + "hPa\n";
    }

    public String informationAboutRainfall(String input) {
        if (input.isEmpty())
            return "";

        int RRR = 0;
        String tr = "", strRRR = "";
        try {
            RRR = Integer.parseInt(String.valueOf(input.charAt(1)) + input.charAt(2) + input.charAt(3));
        } catch (StringIndexOutOfBoundsException e) {
            return "Brak informacji na temat opadow\n";
        }
        if (RRR >= 0 && RRR <= 988)
            strRRR = RRR + " mmm";
        else if (RRR == 989)
            strRRR = " 989 i więcej mm";
        else if (RRR == 990)
            strRRR = " slad";
        else if (RRR == 991)
            strRRR = "0.1 mm";
        else if (RRR == 992)
            strRRR = "0.2 mm";
        else if (RRR == 993)
            strRRR = "0.3 mm";
        else if (RRR == 994)
            strRRR = "0.4 mm";
        else if (RRR == 995)
            strRRR = "0.5 mm";
        else if (RRR == 996)
            strRRR = "0.6 mm";
        else if (RRR == 997)
            strRRR = "0.7 mm";
        else if (RRR == 998)
            strRRR = "0.8 mm";
        else if (RRR == 999)
            strRRR = "0.9 mm";
        else
            strRRR = "Nieprawidlowe dane";

        tr = switch (input.charAt(4)) {
            case '1' -> "6 h";
            case '2' -> "12 h";
            case '3' -> "18 h";
            case '4' -> "24 h";
            case '5' -> "1 h";
            case '6' -> "2 h";
            case '7' -> "3 h";
            case '8' -> "9 h";
            case '9' -> "15 h";
            default -> "Nieprawidlowe dane";
        };

        return "suma opadow: " + strRRR
                + ", czas trwania okresu kończącego się w terminie obserwacji: " + tr + "\n";
    }

    public String informationAboutCurrentAndPreviousWeatherCondition(String input) {
        String ww, WW;
        if (input.isEmpty())
            return "";
        ww = switch (String.valueOf(input.charAt(1)) + String.valueOf(input.charAt(2))) {
            case "00" -> "niebo bezchmurne";
            case "01" -> "chmury zanikają";
            case "02" -> "stan nieba nie zmienia się";
            case "03" -> "chmury rozwijają się";
            case "04" -> "widzialność zmniejszona przez dym";
            case "05" -> "zmętnienie";
            case "06" -> "pył w powietrzu (nie wzniesiony przez wiatr w czasie obserwacji)";
            case "07" -> "pył w powietrzu wznoszony przez wiatr";
            case "08" -> "wiry pyłowe lub piaskowe";
            case "09" -> "wiry pyłowe lub piaskowe w zasięgu widoczności, ale nie na stacji";
            case "10" -> "zamglenie";
            case "11" -> "mgła w płatach";
            case "12" -> "cienka warstwa mgły";
            case "13" -> "widoczna błyskawica, nie słychać grzmotu";
            case "14" -> "opady nie sięgające gruntu w zasięgu widoczności";
            case "15" -> "opady w zasięgu widoczności, lecz nie na stacji";
            case "16" -> "opady w pobliżu, lecz nie na stacji";
            case "17" -> "burza w czasie obserwacji, ale bez opadu na stacji";
            case "18" -> "nawałnica w zasięgu widoczności";
            case "19" -> "trąba powietrzna";
            case "20" -> "mżawka (nie marznąca)";
            case "21" -> "deszcz (nie marznący)";
            case "22" -> "śnieg";
            case "23" -> "deszcz ze śniegiem lub deszcz lodowy";
            case "24" -> "mżawka marznąca lub deszcz marznący";
            case "25" -> "przelotny deszcz";
            case "26" -> "przelotny śnieg";
            case "27" -> "przelotny grad";
            case "28" -> "mgła lub mgła lodowa";
            case "29" -> "burza";
            case "30" -> "słaba lub umiarkowana wichura pyłowa lub piaskowa o zmniejszającej się intensywności";
            case "31" -> "słaba lub umiarkowana wichura pyłowa lub piaskowa o niezmieniającej się intensywności";
            case "32" -> "słaba lub umiarkowana wichura pyłowa lub piaskowa o zwiększającej się intensywności";
            case "33" -> "silna wichura pyłowa lub piaskowa o zmniejszającej się intensywności";
            case "34" -> "silna wichura pyłowa lub piaskowa o niezmieniającej się intensywności";
            case "35" -> "silna wichura pyłowa lub piaskowa o zwiększającej się intensywności";
            case "36" -> "słaba lub umiarkowana zamieć śnieżna niska";
            case "37" -> "silna zamieć śnieżna niska";
            case "38" -> "słaba lub umiarkowana zamieć śnieżna wysoka";
            case "39" -> "silna zamieć śnieżna wysoka";
            case "40" -> "mgła (mgła lodowa) w pewnej odległości od stacji";
            case "41" -> "mgła (mgła lodowa) w płatach";
            case "42" -> "mgła (mgła lodowa), niebo widoczne, staje się rzadsza";
            case "43" -> "mgła (mgła lodowa), niebo niewidoczne, staje się rzadsza";
            case "44" -> "mgła (mgła lodowa), niebo widoczne, bez zmian w ciągu ostatniej godziny";
            case "45" -> "mgła (mgła lodowa), niebo niewidoczne, bez zmian w ciągu ostatniej godziny";
            case "46" -> "mgła (mgła lodowa), niebo widoczne, gęstnieje";
            case "47" -> "mgła (mgła lodowa), niebo niewidoczne, gęstnieje";
            case "48" -> "mgła osadzająca szadź, niebo widoczne";
            case "49" -> "mgła osadzająca szadź, niebo niewidoczne mżawka w czasie obserwacji";
            case "50" -> "słaba, niemarznąca mżawka z przerwami";
            case "51" -> "słaba, niemarznąca, ciągła mżawka";
            case "52" -> "umiarkowana, niemarznąca mżawka z przerwami";
            case "53" -> "umiarkowana, niemarznąca, ciągła mżawka";
            case "54" -> "intensywna, niemarznąca mżawka z przerwami";
            case "55" -> "intensywna, niemarznąca, ciągła mżawka";
            case "56" -> "słaba marznąca mżawka";
            case "57" -> "umiarkowana lub silna marznąca mżawka";
            case "58" -> "słaba mżawka z deszczem";
            case "59" -> "umiarkowana lub silna mżawka z deszczem, deszcz w czasie obserwacji";
            case "60" -> "niemarznący, słaby deszcz z przerwami";
            case "61" -> "niemarznący, słaby, ciągły";
            case "62" -> "niemarznący, umiarkowany deszcz z przerwami";
            case "63" -> "niemarznący, umiarkowany, ciągły deszcz";
            case "64" -> "niemarznący, silny deszcz z przerwami";
            case "65" -> "niemarznący, silny, ciągły deszcz";
            case "66" -> "słaby marznący deszcz";
            case "67" -> "umiarkowany lub silny marznący deszcz";
            case "68" -> "słaby deszcz ze śniegiem";
            case "69" -> "umiarkowany lub silny deszcz ze śniegiem opady stałe w czasie obserwacji";
            case "70" -> "słaby śnieg z przerwami";
            case "71" -> "słaby, ciągły śnieg";
            case "72" -> "umiarkowany śnieg z przerwami";
            case "73" -> "umiarkowany, ciągły śnieg";
            case "74" -> "silny śnieg z przerwami";
            case "75" -> "silny, ciągły śnieg";
            case "76" -> "słupki lodowe";
            case "77" -> "śnieg ziarnisty";
            case "78" -> "oddzielne gwiazdki śniegu";
            case "79" -> "ziarna lodowe lub deszcz lodowy";
            case "80" -> "słaby przelotny deszcz";
            case "81" -> "umiarkowany lub silny przelotny deszcz";
            case "82" -> "gwałtowny przelotny deszcz";
            case "83" -> "słaby przelotny deszcz ze śniegiem";
            case "84" -> "umiarkowany lub silny przelotny deszcz ze śniegiem";
            case "85" -> "słaby przelotny śnieg";
            case "86" -> "umiarkowany lub silny przelotny śnieg";
            case "87" -> "słabe przelotne krupy lodowe lub śnieżne";
            case "88" -> "umiarkowane lub silne, przelotne krupy lodowe lub śnieżne";
            case "89" -> "słaby przelotny grad";
            case "90" -> "umiarkowany lub silny przelotny grad";
            case "91" -> "burza w ciągu ostatniej godziny, w czasie obserwacji lekki deszcz";
            case "92" -> "burza w ciągu ostatniej godziny, w czasie obserwacji umiarkowany lub silny deszcz";
            case "93" -> "burza w ciągu ostatniej godziny, w czasie obserwacji lekki śnieg lub deszcz ze śniegiem";
            case "94" ->
                    "burza w ciągu ostatniej godziny, w czasie obserwacji umiarkowany lub silny śnieg lub deszcz ze śniegiem";
            case "95" -> "lekka lub umiarkowana burza w czasie obserwacji";
            case "96" -> "lekka lub umiarkowana burza z gradem w czasie obserwacji";
            case "97" -> "silna burza";
            case "98" -> "silna burza z wichurą pyłową lub piaskową";
            case "99" -> "silna burza z gradem";
            case "//" -> "Nie oznaczono";
            default -> "Nieprawidlowe dane";
        };

        WW = switch (String.valueOf(input.charAt(3)) + String.valueOf(input.charAt(4))) {
            case "00" -> "chmury pokrywają połowę lub mniej nieba";
            case "01" ->
                    "chmury pokrywały ponad połowę nieba przez część okresu i mniej niż połowę przez pozostałą część okresu";
            case "02" -> "chmury pokrywały ponad połowę nieba";
            case "03" -> "wichura pyłowa, wichura piaskowa lub zamieć śnieżna";
            case "04" -> "mgła, mgła lodowa lub zmętnienie";
            case "05" -> "mżawka";
            case "06" -> "deszcz ciągły";
            case "07" -> "śnieg lub deszcz ze śniegiem";
            case "08" -> "deszcz przelotny";
            case "09" -> "burza";
            case "//" -> "Nie oznaczono";
            default -> "Nieprawidlowe dane";
        };

        return "aktualny stan pogody: " + ww
                + ", przeszly stan pogody: " + WW
                + "\n";
    }

    public String informationAboutSkyCondition(String input) {
        if (input.isEmpty())
            return "";
        String nl, cl, cm, ch;
        if (input.charAt(1) != '/')
            nl = "ilość chmur piętra niskiego: " + String.valueOf(input.charAt(1));
        else
            nl = "zachurzenie chmurami srednimi";

        cl = switch (input.charAt(2)) {
            case '0' -> "brak";
            case '1' -> "Cumulus humilis lub Cumulus fractus (ale nie złej pogody) lub obie chmury razem";
            case '2' ->
                    "Cumulus mediocris lub congestus występujący sam lub z Cu hum lub Cu fra bądź też ze Stratocumulus wszystkie chmury na tym samym poziomie";
            case '3' -> "Cumulonimbus calvus (sam lub z Cu, Sc lub St)";
            case '4' -> "Stratocumulus cumulogenitus";
            case '5' -> "Stratocumulus (lecz nie cumulogenitus)";
            case '6' -> "Stratus nebulosus lub Stratus fractus (lecz nie złej pogody) lub obie chmury razem";
            case '7' ->
                    "Stratus fractus lub Cumulus fractus (złej pogody) lub obie chmury razem (pannus), zwykle pod Altostratus lub Nimbostratus";
            case '8' ->
                    "Cumulus i Stratocumulus (lecz nie Stratocumulus cumulogenitus) o podstawach na różnych poziomach";
            case '9' ->
                    "Cumulonimbus capillatus (często z kowadłem) występujący sam lub z Cumulonimbus calvus, Cumulus, Stratocumulus, Stratus lub pannus";
            case '/' -> "chmury nie były widoczne (ciemność, mgła)";
            default -> "Nieprawidlowe dane";
        };

        cm = switch (input.charAt(3)) {
            case '0' -> "brak";
            case '1' -> "Altostratus translucidus";
            case '2' -> "Altostratus opacus lub Nimbostratus";
            case '3' -> "Altocumulus translucidus na jednym poziomie";
            case '4' ->
                    "ławice Altocumulus translucidus, często soczewkowate, ciągle zmieniające się i występujące na jednym lub kilku poziomach";
            case '5' ->
                    "Altocumulus translucidus w pasmach, albo jedna lub więcej warstw Altocumulus translucidus lub opacus, stopniowo     zaciągająca niebo; te chmury Ac na ogół w całości grubieją";
            case '6' -> "Altocumulus cumulogenitus (lub cumulonimbogenitus)";
            case '7' ->
                    "Altocumulus translucidus lub opacus w dwóch lub więcej warstwach, lub Altocumulus opacus w pojedynczej warstwie, nie zaciągający nieba lub Altocumulus z Altostratus lub Nimbostratus";
            case '8' -> "Altocumulus castellanus lub floccus";
            case '9' -> "Altocumulus na niebie o wyglądzie chaotycznym, na ogół na kilku poziomach";
            case '/' -> "chmury nie były widoczne (ciemność, mgła, ciągła warstwa chmur niskich)";
            default -> "Nieprawidlowe dane";
        };

        ch = switch (input.charAt(4)) {
            case '0' -> "brak";
            case '1' -> "Cirrus fibratus lub Cirrus uncinus (nie zaciągający nieba)";
            case '2' ->
                    "Cirrus spissatus w ławicach lub w postaci poplątanych wiązek, albo Cirrus castellanus lub floccus";
            case '3' -> "Cirrus spissatus cumulonimbogenitus";
            case '4' -> "Cirrus uncinus lub Cirrus fibratus lub obie te chmury razem (stopniowo zaciągający niebo)";
            case '5' ->
                    "Cirrus (w pasmach) i Cirrostratus lub sam Cirrostratus stopniowo zaciągający niebo, ale nie wyżej niż 45° nad horyzontem";
            case '6' ->
                    "Cirrus (w pasmach) i Cirrostratus lub sam Cirrostratus stopniowo zaciągający niebo, powyżej niż 45° nad horyzontem, lecz nie pokrywa całkowicie nieba";
            case '7' -> "Cirrostratus pokrywający całkowicie niebo";
            case '8' -> "Cirrostratus nie zaciągający i nie pokrywający nieba";
            case '9' -> "Cirrocumulus sam lub przeważający wśród chmur wysokich";
            case '/' -> "chmury nie były widoczne (ciemność, mgła, ciągła warstwa chmur niższych)";
            default -> "Nieprawidlowe dane";
        };

        return nl + ", rodzaj chmur piętra niskiego: " + cl
                + ", rodzaj chmur piętra średniego: " + cm
                + ", rodzaj chmur piętra wysokiego: " + ch
                + "\n";
    }

    public String informationAboutExactTimeOfObservation(String input) {
        if (input.isEmpty())
            return "";

        return "dokladny czas obserwacji: " + input.charAt(1) + input.charAt(2)
                + ":" + input.charAt(3) + input.charAt(4) + "\n";
    }

}