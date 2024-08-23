package kc;

import kc.interfaces.Block;

public class SimpleBlockImpl implements Block {
    private String color;
    private String material;

    public SimpleBlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }
}