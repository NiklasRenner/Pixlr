package dk.renner.pixlr.game.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameObjectInteractionTest {

    @Test
    void canInteractWithRejectsSelfAndInactiveObjects() {
        GameObject first = block();
        GameObject second = block();

        assertFalse(first.canInteractWith(first));
        assertTrue(first.canInteractWith(second));

        second.setActive(false);

        assertFalse(first.canInteractWith(second));
        assertFalse(second.canInteractWith(first));
    }

    @Test
    void canInteractWithRejectsNonInteractableObjects() {
        GameObject first = block();
        GameObject second = block();

        second.setInteractable(false);

        assertFalse(first.canInteractWith(second));
        assertFalse(second.canInteractWith(first));
    }

    private static Block block() {
        return new Block(GameObjectType.BLOCK, 64, 64, 0, 0);
    }
}
