# Tinkers' Tool Leveling

You know back in the days, when you played GTA and used the cheat code for a tank, and then went on and caused mayhem? This mod is like that, except for tinker tools and the cheatcode takes longer to enter.


Gives your Tools XP, allowing them to level up. On levelup an extra modifier is rewarded.
You get xp for doing whatever the tool was intended for, so breaking blocks with pickaxes, killing things with swords, etc.

Note that swords get xp relative to the damage dealt when the entity is killed, not when the damage is dealt. You only get xp if the mob is killed!

## STB Edition Changes

As of 1.2.0 (first forked version) there are the following additional features/changes:

° Config file: Minimum level multiplier is now 1 instead of 2. This means that the next level can require just as much XP as the previous one, instead of minimum twice the amount of XP

° Config file: Added baseXP. A basic amount of XP that will be awarded for any action (Currently only works for block breaking tools, like pickaxes and hammers)

° Config file: Added miningToolsXPOverrides: For mining tools, an override for specific blocks that will give a user-defined amount of XP. For example, the user can set to award more XP when the tool mines ores, or no XP when the user mines stone

° Config file: Added enableMiningToolsXPOverride. If false, it disable the above mentioned miningToolsXPOverride. Useful to disable the feature temporarily, without losing the custom configuration

° Config file: Added miningLevelXP. If true, the tool receives more XP the higher the difference between the tool's mining level and the block's mining level. This encourages players to use tools with the highest mining level, but also encourages them to mine blocks with lower mining levels, such as stone. Overrides takes precedence on the amount of XP

° Lang file: Changes to messages (only US_en). There are now more of them (up to level 25) and they focus on the rarity of the tools. Everyone can craft a mundane wood pickaxe, not everyone can level up the tool with thousands of mined blocks. IT_it is coming soon (TM), contributions for other languages are welcome

## Future Development

I don't plan to change much else from the mod. I was thinking about message configurability (custom level up messages and level names), but that should already be doable by using a mod like Resource Loader or similar. I was also thinking about awarding more XP to non-mining tools in some situations, but generally those tools already receive multiple XP points at a time. Small suggestions are welcome
