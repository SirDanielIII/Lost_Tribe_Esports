#Destroy god_egg

execute if entity @p[gamemode=survival] run summon item ~ ~0.5 ~ {Item: {id: "minecraft:item_frame", Count: 1b, tag: {EntityTag: {Tags: ["god_egg"], Invisible: 1b, Invulnerable: 1}, CustomModelData: 1, display: {Name: '{"text":"God\'s Egg","italic":false,"bold":true,"color":"gold"}', Lore: ['{"text":"You ever wonder if","color":"light_purple","italic":true}', '{"text":"God is actually a bird?","color":"light_purple","italic":true}']}, RepairCost: -99999}}, Motion: [0.0d, 0.2d, 0.0d], PickupDelay: 10}

execute if entity @p[gamemode=survival] run kill @e[type=item,distance=..1,limit=1,sort=nearest,nbt={Item: {id: "minecraft:glass"}}]
kill @s