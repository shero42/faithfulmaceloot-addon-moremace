package com.aric3435.faithfulmaceloot;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.loot.LootTables;

public class FaithfulMaceLoot implements ModInitializer {

    @Override
    public void onInitialize() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

            if (id.equals(LootTables.ANCIENT_CITY_CHEST)) {

                LootPool.Builder pool = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1, 2))

                    .with(ItemEntry.builder(Registries.ITEM.get(new Identifier("faithfulmace", "mace")))
                        .weight(3))

                    .with(ItemEntry.builder(Registries.ITEM.get(new Identifier("faithfulmace", "mace")))
                        .weight(1)
                        .apply(EnchantWithLevelsLootFunction.builder(
                            UniformLootNumberProvider.create(20, 40)
                        )))

                    .with(ItemEntry.builder(Registries.ITEM.get(new Identifier("faithfulmace", "wind_charge")))
                        .weight(20)
                        .apply(SetCountLootFunction.builder(
                            UniformLootNumberProvider.create(2, 8)
                        )));

                tableBuilder.pool(pool.build());
            }
        });
    }
}