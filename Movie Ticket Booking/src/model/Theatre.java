package model;

import java.util.*;

public class Theatre {

    private Map<Date, Map<Screen, List<Show>>> showsByDateAndScreen;

    public Theatre() {
        showsByDateAndScreen = new HashMap<>();
    }

    public void addShow(Date date, Screen screen, Show show) {
        showsByDateAndScreen
                .computeIfAbsent(date, k -> new HashMap<>())
                .computeIfAbsent(screen, k -> new ArrayList<>())
                .add(show);
    }

    public List<Show> getShowForScreenAndDate(Date date, Screen screen) {
        return showsByDateAndScreen
                .getOrDefault(date, new HashMap<>())
                .getOrDefault(screen, new ArrayList<>());
    }


}
