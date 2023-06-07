package efs.task.collections.data;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;


public class DataProvider {

    public static final String DATA_SEPARATOR = ",";

    // TODO Utwórz listę miast na podstawie tablicy Data.baseTownsArray.
    //  Tablica zawiera String zawierający nazwę miasta oraz dwie klasy bohatera związane z tym miastem oddzielone przecinkami.
    //  Korzystając z funkcji split() oraz stałej DATA_SEPARATOR utwórz listę obiektów klasy efs.task.collections.entities.Town.
    //  Funkcja zwraca listę obiektów typu Town ze wszystkimi dziewięcioma podstawowymi miastami.
    public List<Town> getTownsList() {
        List<Town> towns = new ArrayList<>();

        for (String townData : Data.baseTownsArray) {
            String[] townDataArray = townData.split(DATA_SEPARATOR);
            String townName = townDataArray[0];
            List<String> startingHeroClasses = Arrays.asList(townDataArray[1].trim(), townDataArray[2].trim());
            Town town = new Town(townName, startingHeroClasses);
            towns.add(town);
        }

        return towns;
    }

    //TODO Analogicznie do getTownsList utwórz listę miast na podstawie tablicy Data.DLCTownsArray
    public List<Town> getDLCTownsList() {
        List<Town> dlcTowns = new ArrayList<>();

        for (String dlcTownData : Data.dlcTownsArray) {
            String[] dlcTownDataArray = dlcTownData.split(DATA_SEPARATOR);
            String dlcTownName = dlcTownDataArray[0];
            List<String> startingHeroClasses = Arrays.asList(dlcTownDataArray[1].trim(), dlcTownDataArray[2].trim());
            Town dlcTown = new Town(dlcTownName, startingHeroClasses);
            dlcTowns.add(dlcTown);
        }

        return dlcTowns;
    }

    //TODO Na podstawie tablicy Data.baseCharactersArray utworzyć listę bohaterów dostępnych w grze.
    // Tablica Data.baseCharactersArray zawiera oddzielone przecinkiem imie bohatera, klasę bohatera.
    // Korzystając z funkcji split() oraz DATA_SEPARATOR utwórz listę unikalnych obiektów efs.task.collections.entities.Hero.
    // UWAGA w Data.baseCharactersArray niektórzy bohaterowie powtarzają się, do porównania bohaterów używamy zarówno imie jak i jego klasę;
    public Set<Hero> getHeroesSet() {
        Set<Hero> heroes = new HashSet<>();
        for (String character : Data.baseCharactersArray) {
            String[] heroData = character.split(DATA_SEPARATOR);
            Hero hero = new Hero(heroData[0].trim(), heroData[1].trim());
            heroes.add(hero);
        }
        return heroes;
    }

    //TODO Analogicznie do getHeroesSet utwórz listę bohaterów na podstawie tablicy Data.DLCCharactersArray
    public Set<Hero> getDLCHeroesSet() {
        Set<Hero> dlcHeroes = new HashSet<>();
        for (String heroData : Data.dlcCharactersArray) {
            String[] heroDataSplit = heroData.split(DATA_SEPARATOR);
            Hero dlcHero = new Hero(heroDataSplit[0].trim(), heroDataSplit[1].trim());
            dlcHeroes.add(dlcHero);
        }
        return dlcHeroes;
    }
}
