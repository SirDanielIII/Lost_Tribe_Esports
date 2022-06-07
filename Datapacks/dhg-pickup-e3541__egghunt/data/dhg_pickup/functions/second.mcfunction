execute as @e[type=#dhg_pickup:no_pickup, predicate=dhg_pickup:unmodified] run data merge entity @s {CanPickUpLoot:0b}

schedule function dhg_pickup:second 1s
