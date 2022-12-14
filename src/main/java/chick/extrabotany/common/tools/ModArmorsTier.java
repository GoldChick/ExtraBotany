package chick.extrabotany.common.tools;

import chick.extrabotany.common.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorsTier implements ArmorMaterial
{
    ARMOR_OBSIDIAN("obsidian",
            40,
            new int[]{5, 8, 10, 5},
            20,
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            2.0F,
            0.0F,
            () -> Ingredient.of(Items.OBSIDIAN)
    ),
    ARMOR_SHADOWIUM("shadowium", 24, new int[]{2, 5, 6, 2}, 26,
            SoundEvents.ARMOR_EQUIP_IRON, 1F, 0.0F, () -> Ingredient.of(ModItems.SHADOW_INGOT.get())
    ),
    ARMOR_PHOTONIUM("photonium", 27, new int[]{3, 5, 7, 2}, 30,
            SoundEvents.ARMOR_EQUIP_IRON, 1F, 0.0F, () -> Ingredient.of(ModItems.PHOTON_INGOT.get())
    ),
    MIKU("miku", 5, new int[]{2, 4, 5, 1}, 22,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0F, 0.0F, () -> Ingredient.of(ModItems.MANA_DRINK.get())
    ),
    MAID("maid", 40, new int[]{4, 7, 9, 4}, 32,
            SoundEvents.ARMOR_EQUIP_DIAMOND, 3F, 0.0F, () -> Ingredient.of(ModItems.PHOTON_INGOT.get())
    ),//ing: GOLD_CLOTH
    SHOOTING_GUARDIAN("shooting_guardian", 34, new int[]{3, 7, 8, 4}, 34,
            SoundEvents.ARMOR_EQUIP_IRON, 2F, 0.0F, () -> Ingredient.of(ModItems.ORICHALCOS.get())
    ),
    SILENT_SAGES("silent_sages", 50, new int[]{4, 8, 9, 5}, 40,
            SoundEvents.ARMOR_EQUIP_IRON, 3F, 0.0F, () -> Ingredient.of(ModItems.ORICHALCOS.get())
    );
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};

    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorsTier(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial)
    {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot p_40410_)
    {
        return MAX_DAMAGE_ARRAY[p_40410_.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot p_40411_)
    {
        return this.damageReductionAmountArray[p_40411_.getIndex()];
    }

    @Override
    public int getEnchantmentValue()
    {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound()
    {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairIngredient()
    {
        return this.repairMaterial.get();
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public float getToughness()
    {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}
