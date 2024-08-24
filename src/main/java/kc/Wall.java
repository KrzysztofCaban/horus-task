package kc;

import kc.interfaces.Block;
import kc.interfaces.CompositeBlock;
import kc.interfaces.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        if (blocks == null) {
            throw new IllegalArgumentException("Blocks list cannot be null");
        }
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        return flatMapBlocks(blocks.stream())
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
        return flatMapBlocks(blocks.stream())
                .filter(block -> block.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int) flatMapBlocks(blocks.stream()).count();
    }

    private Stream<Block> flatMapBlocks(Stream<Block> blockStream) {
        return blockStream.flatMap(block -> {
            if (block instanceof CompositeBlock) {
                return flatMapBlocks(((CompositeBlock) block).getBlocks().stream());
            } else {
                return Stream.of(block);
            }
        });
    }
}