package funkycarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.ParsedRule;
import carpet.settings.SettingsManager;
import net.fabricmc.api.ModInitializer;

public class FunkyCarpet implements CarpetExtension, ModInitializer {
    public static SettingsManager settingsManager;

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new FunkyCarpet());
    }

    @Override
    public void onGameStarted() {
        // create custom settingsManager
        settingsManager = new SettingsManager(null, FunkyCarpetRuleCategory.MODNAME, "FunkyCarpet");
        settingsManager.parseSettingsClass(FunkyCarpetSettings.class);

        // load FunkyCarpet settings to carpet
        CarpetServer.settingsManager.parseSettingsClass(FunkyCarpetSettings.class);

        // workaround for rule being overwritten: https://github.com/gnembon/fabric-carpet/issues/802
        CarpetServer.settingsManager.addRuleObserver((source, rule, value) -> {
            ParsedRule<?> FunkyCarpetRule = settingsManager.getRule(rule.name);
            ParsedRule<?> carpetRule = CarpetServer.settingsManager.getRule(rule.name);

            // check if the rule being changed exists in FunkyCarpet, but isn't the same rule as the one in carpet's settingsManager
            // if so, update the rule (if types are the same)
            if(FunkyCarpetRule != null && carpetRule != null && FunkyCarpetRule != carpetRule && FunkyCarpetRule.type == carpetRule.type) {
                FunkyCarpetRule.set(source, value);
            }
        });
    }
}