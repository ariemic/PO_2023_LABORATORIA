package agh.ics.oop;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    //bedzie wywooływac wiele symulacji asynchronicznie
    private final ArrayList<Simulation> simulations;
    private final List<Thread> threads;

    public SimulationEngine(ArrayList<Simulation> simulations){
        this.simulations = simulations;
        this.threads = new ArrayList<>();
        for(Simulation simulation: simulations){
            threads.add(new Thread(simulation));
        }

    }
    public void runSync() throws PositionAlreadyOccupiedException{
        for(Simulation simulation: simulations){
            simulation.run();
        }
    }


}
