package com.shipyard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Docks {

    private String name;
    private int size;
    private Ship[] ships;
    private List<Container> containers;
    private Iterator containerIterator;

    public Docks(String name, int size, int heavyContainers, int refrigeratedContainers) {
        this.name = name;
        this.size = size; 
        this.ships = createShips(size);
        this.containers = generateContainers(heavyContainers,refrigeratedContainers);
    }

    public Iterator getContainerIterator() {
        return new Iterator<Container>() {
            int index;

            @Override
            public boolean hasNext() {
                while (index < containers.size()){
                    return true;
                }
                return false;
            }

            @Override
            public Container next() {
                if (this.hasNext()) {
                    return containers.get(index++);
                }
                return null;
            }

            @Override
            public void remove() {
                containers.remove(index);
            }
        };
    }

    public Ship[] createShips(int num) {
        Ship[] ships = new Ship[num];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship();
        }
        return ships; 
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }
    
    public Ship[] getShips() {
        return this.ships;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Container> generateContainers(int heavyContainers, int refrigeratedContainers) {
        List<Container> containers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < heavyContainers; i++) {
            Container container = new HeavyContainer(random.nextBoolean(), random.nextBoolean());
            containers.add(container);
        }
        for (int i = 0; i < refrigeratedContainers; i++) {
            Container container = new RefrigeratedContainer(random.nextBoolean(), random.nextBoolean());
            containers.add(container);
        }
        return containers;
    }

}