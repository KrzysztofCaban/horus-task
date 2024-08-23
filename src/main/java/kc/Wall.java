package kc;

import kc.interfaces.Block;
import kc.interfaces.Structure;

import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    @Override
    public Optional<Block> findBlockByColor(String color) {
        //TODO: implement
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        //TODO: implement
        return List.of();
    }

    @Override
    public int count() {
        //TODO: implement
        return 0;
    }
}