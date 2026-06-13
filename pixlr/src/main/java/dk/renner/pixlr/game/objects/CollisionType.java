package dk.renner.pixlr.game.objects;

enum CollisionType {
    NONE,
    HORIZONTAL,
    TOP,
    BOTTOM;

    boolean blocksHorizontalMovement() {
        return this == HORIZONTAL;
    }

    boolean landedOnGround() {
        return this == BOTTOM;
    }
}
