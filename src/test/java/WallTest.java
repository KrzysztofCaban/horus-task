import kc.CompositeBlockImpl;
import kc.SimpleBlockImpl;
import kc.Wall;
import kc.interfaces.Block;
import kc.interfaces.CompositeBlock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private Block block1;
    private Block block2;
    private CompositeBlock compositeBlock;
    private Wall wall;

    @BeforeEach
    void setUp() {
        block1 = new SimpleBlockImpl("red", "brick");
        block2 = new SimpleBlockImpl("blue", "concrete");
        compositeBlock = new CompositeBlockImpl(List.of(block1, block2));
        wall = new Wall(List.of(block1, block2, compositeBlock));
    }

    @Test
    void findBlockByColor_returnsBlock_whenColorExists() {
        Optional<Block> result = wall.findBlockByColor("red");

        assertTrue(result.isPresent());
        assertEquals(block1, result.get());
    }

    @Test
    void findBlockByColor_returnsEmpty_whenColorDoesNotExist() {
        Optional<Block> result = wall.findBlockByColor("green");

        assertTrue(result.isEmpty());
    }

    @Test
    void findBlocksByMaterial_returnsBlocks_whenMaterialExists() {
        List<Block> result = wall.findBlocksByMaterial("brick");

        assertEquals(2, result.size());
        assertTrue(result.contains(block1));
        assertFalse(result.contains(block2));
    }

    @Test
    void findBlocksByMaterial_returnsEmptyList_whenMaterialDoesNotExist() {
        List<Block> result = wall.findBlocksByMaterial("wood");

        assertTrue(result.isEmpty());
    }

    @Test
    void count_returnsCorrectNumberOfBlocks() {
        int result = wall.count();

        assertEquals(4, result);
    }

    @Test
    void count_returnsCorrectNumberOfBlocks_includingCompositeBlocks() {
        Wall wallWithComposite = new Wall(List.of(compositeBlock));

        int result = wallWithComposite.count();

        assertEquals(2, result);
    }

    @Test
    void count_returnsCorrectNumberOfBlocks_includingCompositeBlocksWithinCompositeBlock() {
        compositeBlock = new CompositeBlockImpl(List.of(compositeBlock, block1, block2));
        Wall wallWithComposite = new Wall(List.of(compositeBlock));

        int result = wallWithComposite.count();

        assertEquals(4, result);
    }

    @Test
    void findBlockByColor_returnsEmpty_whenWallIsEmpty() {
        Wall emptyWall = new Wall(List.of());

        Optional<Block> result = emptyWall.findBlockByColor("red");

        assertTrue(result.isEmpty());
    }

    @Test
    void findBlocksByMaterial_returnsEmptyList_whenWallIsEmpty() {
        Wall emptyWall = new Wall(List.of());

        List<Block> result = emptyWall.findBlocksByMaterial("brick");

        assertTrue(result.isEmpty());
    }

    @Test
    void count_returnsZero_whenWallIsEmpty() {
        Wall emptyWall = new Wall(List.of());

        int result = emptyWall.count();

        assertEquals(0, result);
    }

    @Test
    void findBlockByColor_returnsBlock_whenColorExistsInCompositeBlock() {
        Optional<Block> result = wall.findBlockByColor("blue");

        assertTrue(result.isPresent());
        assertEquals(block2, result.get());
    }

    @Test
    void findBlocksByMaterial_returnsBlocks_whenMaterialExistsInCompositeBlock() {
        List<Block> result = wall.findBlocksByMaterial("concrete");

        assertEquals(2, result.size());
        assertTrue(result.contains(block2));
    }

    @Test
    void constructor_throwsException_whenBlocksListIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Wall(null));
        assertEquals("Blocks list cannot be null", exception.getMessage());
    }

    @Test
    void findBlockByColor_throwsException_whenColorIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> wall.findBlockByColor(null));
        assertEquals("Color cannot be null", exception.getMessage());
    }

    @Test
    void findBlocksByMaterial_throwsException_whenMaterialIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> wall.findBlocksByMaterial(null));
        assertEquals("Material cannot be null", exception.getMessage());
    }

}