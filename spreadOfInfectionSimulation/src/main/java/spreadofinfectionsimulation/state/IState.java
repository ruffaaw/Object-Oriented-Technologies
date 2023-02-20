package spreadofinfectionsimulation.state;

import spreadofinfectionsimulation.person.Person;

public interface IState {
    void handle(Person person1, Person person2, double distance);
}
