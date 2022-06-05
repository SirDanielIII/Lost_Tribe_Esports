# Main Loop


# God's Egg - Placing & Destroying
execute as @e[type=minecraft:item_frame,tag=god_egg] run function sirdanieliii:god_egg/place
execute as @e[type=minecraft:armor_stand,tag=god_egg] at @s unless block ~ ~ ~ minecraft:glass run function sirdanieliii:god_egg/destroy

# Give player buff
execute as @a[nbt={Inventory: [{id: "minecraft:item_frame", tag: {EntityTag: {Tags: ["god_egg"], Invisible: 1b}, CustomModelData: 1, display: {Name: '{"text":"God\'s Egg","italic":false,"bold":true,"color":"gold"}', Lore: ['{"text":"You ever wonder if","color":"light_purple","italic":true}', '{"text":"God is actually a bird?","color":"light_purple","italic":true}']}}}]}] run function sirdanieliii:god_egg/buff

# Prevent egg from being destroyed
execute as @e[type=minecraft:item,nbt={Item: {tag: {EntityTag: {Tags: ["god_egg"]}}}}] run data merge entity @s {Invulnerable: 1b, Age: -32768s}

# Make egg float on top of water or lava
execute as @e[type=minecraft:item,nbt={Item: {tag: {EntityTag: {Tags: ["god_egg"]}}}}] at @s if block ~ ~ ~ minecraft:water run tp @s ~ ~0.1 ~
execute as @e[type=minecraft:item,nbt={Item: {tag: {EntityTag: {Tags: ["god_egg"]}}}}] at @s if block ~ ~ ~ minecraft:lava run tp @s ~ ~0.1 ~