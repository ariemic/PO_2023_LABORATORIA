## Streamy, Comparatory


Poniższy kod nie działa, mimo zaimplementowania w klasie Aniamal jak i Vector2d interfejsu 
Comparable. Powodem jest bezpośredni brak funkcjie getX, getY w klasie Aniamal.


#### Wersja błędna
     return animals.values().stream()
                .sorted(Comparator
                        .comparing(Animal::getPosition)
                        .thenComparing(Vector2d::getX)
                        .thenComparing(Vector2d::getY)
                .collect(Collectors.toList());
    
#### Wersja poprawiona
    return animals.values().stream()
                .sorted(Comparator
                        .comparing(Animal::getPosition)
                        .thenComparing(animal -> animal.getPosition().getX())
                        .thenComparing(animal -> animal.getPosition().getY()))
                .collect(Collectors.toList());

    //lub tak nie trzeba się bawić z implementacją interfejsu Comparable

    List<Animal> orderedAnimals = new ArrayList<>(animals.values());
        Collections.sort(orderedAnimals, Comparator
                .comparing((Animal animal) -> animal.getPosition().getX()) //musimy operować na jednym obiekcie klasy animal nie na całej klasie
                .thenComparing((Animal animal) -> animal.getPosition().getY()));

## Mockowanie
Testując jakąś metode nie możemy jej mockować.
Nie ma wtedy sensu testowanie takiej metody bo ty tak naprawde piszesz w 
mocku co ona ma zwrócić! Możesz mockować metody których działanie już wcześniej sprawdziłeś ale nie takie dla których
teraz pieszesz testy!

Ba nawet tej klasy która posiada tą metodę nie możesz zmokować!
Musisz stworzyć normalny obiekt. Klasy pośrednie potrzebne ale nie z metodą którą testujesz mogą być zmokowane