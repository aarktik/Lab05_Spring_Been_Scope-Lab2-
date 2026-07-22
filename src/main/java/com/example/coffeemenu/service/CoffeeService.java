package com.example.coffeemenu.service;

import com.example.coffeemenu.model.Coffee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    private final List<Coffee> coffeeList = new ArrayList<>();
    private Long nextId = 1L;

    public CoffeeService() {
        // Mock Data เริ่มต้น 2 รายการตามโจทย์
        addCoffee(new Coffee(null, "Espresso", 45.0));
        addCoffee(new Coffee(null, "Latte", 55.0));
    }

    public List<Coffee> getAllCoffees() {
        return coffeeList;
    }

    public Optional<Coffee> getCoffeeById(Long id) {
        return coffeeList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public Coffee addCoffee(Coffee coffee) {
        coffee.setId(nextId++);
        coffeeList.add(coffee);
        return coffee;
    }

    public Optional<Coffee> updateCoffee(Long id, Coffee updatedCoffee) {
        return getCoffeeById(id).map(existingCoffee -> {
            existingCoffee.setName(updatedCoffee.getName());
            existingCoffee.setPrice(updatedCoffee.getPrice());
            return existingCoffee;
        });
    }

    public boolean deleteCoffee(Long id) {
        return coffeeList.removeIf(c -> c.getId().equals(id));
    }
}