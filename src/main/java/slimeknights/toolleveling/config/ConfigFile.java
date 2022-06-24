package slimeknights.toolleveling.config;

import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import slimeknights.mantle.config.AbstractConfigFile;
import slimeknights.mantle.configurate.objectmapping.Setting;
import slimeknights.mantle.configurate.objectmapping.serialize.ConfigSerializable;
import slimeknights.tconstruct.library.TinkerRegistry;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static slimeknights.tconstruct.tools.harvest.TinkerHarvestTools.*;

@ConfigSerializable
public class ConfigFile extends AbstractConfigFile {

  private final static int CONFIG_VERSION = 2;

  @Setting
  General general = new General();
  @Setting
  ToolXP toolxp = new ToolXP();

  public ConfigFile() {
  }

  public ConfigFile(File file) {
    super(file);
  }

  @Override
  public int getConfigVersion() {
    return CONFIG_VERSION;
  }

  @Override
  public void insertDefaults() {
    clearNeedsSaving();
    // fill in defaults for missing entries
    TinkerRegistry.getTools().stream()
                  .filter(tool -> !toolxp.baseXpForTool.containsKey(tool))
                  .forEach(tool -> {
                    toolxp.baseXpForTool.put(tool, getDefaultXp(tool));
                    setNeedsSaving();
                  });
  }

  private int getDefaultXp(Item item) {
    Set<Item> aoeTools = Sets.newHashSet(hammer, excavator, lumberAxe);
    if(scythe != null) {
      aoeTools.add(scythe);
    }

    if(aoeTools.contains(item)) {
      return 9 * toolxp.defaultBaseXP;
    }
    return toolxp.defaultBaseXP;
  }



  @ConfigSerializable
  static class General {

    @Setting(comment = "Reduces the amount of modifiers a newly build tool gets if the value is lower than the regular amount of modifiers the tool would have")
    public int newToolMinModifiers = 3;

    @Setting(comment = "Maximum achievable levels. If set to 0 or lower there is no upper limit")
    public int maximumLevels = -1;
  }

  @ConfigSerializable
  static class ToolXP {
    @Setting(comment = "Base XP used when no more specific entry is present for the tool")
    public int defaultBaseXP = 500;

    @Setting(comment = "Base XP for each of the listed tools")
    public Map<Item, Integer> baseXpForTool = new HashMap<>();

    @Setting(comment = "How much the XP cost will multiply per level. Minimum 1 (STB Edition change)")
    public float levelMultiplier = 2f;

    @Setting(comment = "How much XP each action will add to the tool. Useful for giving XP only when doing " +
            "specific actions (e.g.: mining ores gives XP, while stone does not) or to give fractional XP (stone = 2, ores = 3) (STB Edition feature)")
    public int baseToolXP = 1;

    @Setting(comment = "How much XP each mined block gives. Format is: <modid:registry_name@metadata>=<xp>. For example: \"minecraft:coal_ore@0\"=2 (STB Edition feature)")
    public Map<String, Integer> miningToolsXPOverride = new HashMap<String, Integer>() {{
      put("minecraft:coal_ore@0", 2);
      put("minecraft:iron_ore@0", 2);
      put("minecraft:gold_ore@0", 3);
      put("minecraft:lapis_ore@0", 3);
      put("minecraft:redstone_ore@0", 3);
      put("minecraft:diamond_ore@0", 4);
      put("minecraft:emerald_ore@0", 4);
      put("minecraft:quartz_ore@0", 3);
    }};

    @Setting(comment = "Set this to false to disable the miningToolsXPOverrides. Useful if you want to temporarily disable the feature (STB Edition feature)")
    public boolean enableMiningToolsXPOverride = true;

    @Setting(comment = "If true, blocks not in miningToolsXPOverride config will give as much XP as the difference " +
            "between the tool mining level and the block mining level. Formula is Abs(ToolMiningLevel - BlockMiningLevel + 1) (STB Edition feature)")
    public boolean miningLevelXP = false;
  }
}
