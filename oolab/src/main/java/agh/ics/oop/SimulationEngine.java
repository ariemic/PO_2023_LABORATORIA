package agh.ics.oop;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable {
    //bedzie wywooływac wiele symulacji asynchronicznie
    private final ArrayList<Simulation> simulations;
    private final List<Thread> threads;
    ExecutorService executorService;

    public SimulationEngine(ArrayList<Simulation> simulations, int n){
        this.executorService = Executors.newFixedThreadPool(n);
        this.simulations = simulations;
        this.threads = new ArrayList<>();
        for(Simulation simulation: simulations){
            threads.add(new Thread((Runnable) simulation));
        }

    }
    public void runSync() throws PositionAlreadyOccupiedException{
        for(Simulation simulation: simulations){
            simulation.run();
        }
    }
    public void runAsync(){
        for(Thread thread : threads){
           thread.start();
        }
    }
    public void awaitSimulationEnds() throws InterruptedException{
        for (Thread thread : threads){
            thread.join();
        }
        if(executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdown();
        }
    }

    public void runAsyncInThreadPool() throws InterruptedException {
        for (Simulation simulation : simulations){
            executorService.submit((Runnable) simulation);
        }
    }

    @Override
    public void run() {
        System.out.println("Thread started.");
    }
}
