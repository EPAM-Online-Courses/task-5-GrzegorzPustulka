package efs.task.collections.game;

import efs.task.collections.data.DataProvider;
import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;

public class GameLobby {

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby() {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList() {
        return playableTownsWithHeroesList;
    }

    //TODO Dodać miasta i odpowiadających im bohaterów z DLC gry do mapy dostępnych
    // miast - playableTownsWithHeroesList, tylko jeżeli jeszcze się na niej nie znajdują.
    public void enableDLC() {
        List<Town> townsWithDLC = new ArrayList<>();
        townsWithDLC.addAll(this.dataProvider.getTownsList());
        townsWithDLC.addAll(this.dataProvider.getDLCTownsList());

        Set<Hero> heroesWithDLC = new HashSet<>();
        heroesWithDLC.addAll(this.dataProvider.getHeroesSet());
        heroesWithDLC.addAll(this.dataProvider.getDLCHeroesSet());

        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(townsWithDLC, heroesWithDLC);
    }

    //TODO Usunąć miasta i odpowiadających im bohaterów z DLC gry z mapy dostępnych
    // miast - playableTownsWithHeroesList.
    public void disableDLC() {
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(this.dataProvider.getTownsList(), this.dataProvider.getHeroesSet());
    }

    // TODO Sprawdza czy mapa playableCharactersByTown zawiera dane miasto.
    //  Jeśli tak zwróć listę bohaterów z tego miasta.
    //  Jeśli nie rzuć wyjątek NoSuchElementException z wiadomością NO_SUCH_TOWN + town.getName()
    public List<Hero> getHeroesFromTown(Town town) {
        List<Hero> heroesList = playableTownsWithHeroesList.get(town);
        if (heroesList == null) {
            throw new NoSuchElementException(NO_SUCH_TOWN + town.getTownName());
        }
        return heroesList;
    }

    // TODO Metoda powinna zwracać mapę miast w kolejności alfabetycznej z odpowiadającymi im bohaterami.
    //  Każde z miast charakteryzuje się dwoma klasami bohaterów dostępnymi dla tego miasta - Town.startingHeroClass.
    //  Mapa ma zawierać pare klucz-wartość gdzie klucz: miasto, wartość: lista bohaterów;
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> availableTowns, Set<Hero> availableHeroes) {
        Map<Town, List<Hero>> townsMap = new TreeMap<>();
        for (Town town : availableTowns) {
            List<Hero> heroesList = new ArrayList<>();
            for (Hero hero : availableHeroes) {
                if (town.getStartingHeroClasses().contains(hero.getHeroClass())) {
                    heroesList.add(hero);
                }
            }
            townsMap.put(town, heroesList);
        }
        return townsMap;
    }

    //TODO metoda zwraca wybranego bohatera na podstawie miasta z którego pochodzi i imienia.
    // Jeżeli istnieje usuwa go z listy dostępnych bohaterów w danym mieście i zwraca bohatera.
    // Jeżeli nie ma go na liście dostępnych bohaterów rzuca NoSuchElementException z wiadomością HERO_NOT_FOUND + name
    public Hero selectHeroByName(Town heroTown, String name) {
        List<Hero> heroesList = playableTownsWithHeroesList.get(heroTown);
        if (heroesList == null) {
            throw new NoSuchElementException(NO_SUCH_TOWN + heroTown.getTownName());
        } else {
            for (Hero hero : heroesList) {
                if (hero.getName().equals(name)) {
                    heroesList.remove(hero);
                    return hero;
                }
            }
            throw new NoSuchElementException(HERO_NOT_FOUND + name);
        }
    }
}