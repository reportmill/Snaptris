package snapdemos.tetris;
import snap.geom.*;
import snap.gfx.*;
import snap.view.*;

/**
 * A class to represent a tetris block.
 */
public class Block extends View {
    
    // The pattern
    protected Pattern _pattern;
    
    // Constants
    static int TILE_SIZE = Pattern.TILE_SIZE;
    static Effect BLOCK_EFFECT = new ShadowEffect(8, Color.BLACK,0,0);

    /**
     * Constructor for given pattern.
     */
    public Block(Pattern aPattern)
    {
        super();
        setEffect(BLOCK_EFFECT);

        // Set pattern
        _pattern = aPattern;
        setSizeFromPattern();
    }

    /**
     * Returns the number of tiles.
     */
    public int getTileCount()  { return _pattern.tileCount; }

    /**
     * Returns the tile rect at given index in parent view coords.
     */
    public Rect getTileRectInParent(int anIndex)
    {
        double tileX = _pattern.fill[anIndex * 2] * TILE_SIZE;
        double tileY = _pattern.fill[anIndex * 2 + 1] * TILE_SIZE;
        return new Rect(getX() + tileX, getY() + tileY, TILE_SIZE, TILE_SIZE);
    }

    /**
     * Rotate right.
     */
    public void rotateRight()
    {
        _pattern = _pattern.getRotateRight();
        setSizeFromPattern();
    }

    /**
     * Sets the size from the pattern.
     */
    private void setSizeFromPattern()
    {
        double blockW = _pattern.colCount * TILE_SIZE;
        double blockH = _pattern.rowCount * TILE_SIZE;
        setSize(blockW, blockH);
        setPrefSize(blockW, blockH);
    }

    /**
     * Paint block pattern.
     */
    protected void paintFront(Painter aPntr)
    {
        _pattern.paint(aPntr);
    }

    /**
     * Returns a copy of this block.
     */
    public Block getCopy()  { return new Block(_pattern); }

    /**
     * Returns a block with random pattern.
     */
    public static Block getRandomBlock()
    {
        int randomIndex = (int) Math.floor(Math.random() * Pattern.ALL_PATTERNS.length);
        Pattern randomPattern = Pattern.ALL_PATTERNS[randomIndex];
        return new Block(randomPattern);
    }
}