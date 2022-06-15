package funkycarpet;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.RuleCategory;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class FunkyCarpetSettings {
    // carefulbreak
    @Rule(
        desc = "Places the mined block in the player inventory when sneaking",
        category = {RuleCategory.SURVIVAL, RuleCategory.FEATURE, FunkyCarpetRuleCategory.MODNAME}
    )
    public static boolean carefulBreak = true;
}