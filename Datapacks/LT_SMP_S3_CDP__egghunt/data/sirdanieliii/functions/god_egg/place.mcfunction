#Placing god_egg

execute at @s align xyz run summon armor_stand ~0.5 ~ ~0.5 {Marker: 1b, Invisible: 1b, Invulnerable: 1, Pose: {Head: [0f, 180f, 0f]}, Tags: ["god_egg"], ArmorItems: [{}, {}, {}, {id: "minecraft:item_frame", Count: 1b, tag: {CustomModelData: 1}}]}

execute at @s run setblock ~ ~ ~ minecraft:glass
execute at @s align xyz run playsound minecraft:block.amethyst_block.chime block @a[distance=..16]
kill @s
