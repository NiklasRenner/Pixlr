package dk.renner.pixlr.game;

import dk.renner.pixlr.game.objects.GameObject;
import dk.renner.pixlr.game.objects.Player;

import java.util.List;

public record LevelData(Player player, List<GameObject> objects) {
}
