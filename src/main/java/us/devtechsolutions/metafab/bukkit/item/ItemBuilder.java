package us.devtechsolutions.metafab.bukkit.item;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.devtechsolutions.metafab.util.C;

import java.util.*;

/**
 * @author LBuke (Teddeh)
 */
public final class ItemBuilder {

    private static final int LORE_LENGTH = 80;

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    private HashMap<Enchantment, Integer> enchantments = null;

    private HashMap<LoreType, LinkedList<String>> lores = null;
    private LoreType[] loreOrder = new LoreType[]{ LoreType.ENCHANTMENT, LoreType.GENERAL };

    private ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    private ItemBuilder(ItemBuilder builder) {
        this.itemStack = builder.itemStack.clone();
        this.itemMeta = builder.itemMeta.clone();
        this.lores = builder.lores;
        this.enchantments = builder.enchantments;
        this.loreOrder = builder.loreOrder;
    }

    public static ItemBuilder of(ItemStack itemStack) {
        return new ItemBuilder(itemStack);
    }

    public static ItemBuilder of(ItemBuilder builder) {
        return new ItemBuilder(builder);
    }

    public static ItemBuilder of(Material material) {
        return new ItemBuilder(new ItemStack(material));
    }

    public static ItemBuilder of(Material material, short durability) {
        ItemStack stack = new ItemStack(material);
        stack.setDurability(durability);
        return new ItemBuilder(stack);
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public Material type() {
        return itemStack.getType();
    }

    public ItemBuilder type(Material type) {
        itemStack.setType(type);
        return this;
    }

    public ItemBuilder durability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder flags(ItemFlag... flags) {
        if (itemMeta != null)
            itemMeta.addItemFlags(flags);
        return this;
    }

    public ItemBuilder name(String name) {
        if (itemMeta != null)
            itemMeta.setDisplayName(name != null ? C.translate(name) : "???");
        return this;
    }

    public String name() {
        return itemMeta.getDisplayName();
    }
    
    public void setItemStack(ItemStack itemStack) {
    	this.itemStack = itemStack;
	}

    public ItemBuilder lore(List<String> strings) {
        return lore(LoreType.GENERAL, strings);
    }

    public ItemBuilder lore(String... strings) {
        return lore(LoreType.GENERAL, strings);
    }

    public ItemBuilder lore(LoreType type, String... strings) {
        if (lores == null)
            lores = Maps.newHashMap();

        LinkedList<String> list = lores.getOrDefault(type, Lists.newLinkedList());
        list.addAll(Arrays.asList(strings));
        lores.put(type, list);
        return this;
    }

    public ItemBuilder lore(LoreType type, List<String> strings) {
        if (lores == null)
            lores = Maps.newHashMap();

        LinkedList<String> list = lores.getOrDefault(type, Lists.newLinkedList());
        list.addAll(strings);
        lores.put(type, list);
        return this;
    }

    public ItemBuilder cutLore(LoreType type, int byHowMuch) {
        if (lores == null) return this;
        LinkedList<String> list = lores.getOrDefault(type, Lists.newLinkedList());
        for (int i = 0; i < byHowMuch; i++)
            list.removeLast();
        lores.put(type, list);
        return this;
    }

    public ItemBuilder cutLore(int byHowMuch) {
        return cutLore(LoreType.GENERAL, byHowMuch);
    }

    public ItemBuilder sortLore(LoreType... types) {
        loreOrder = types;
        return this;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder bookEnchantment(Enchantment enchantment, int level) {
        if (itemMeta instanceof EnchantmentStorageMeta) {
            ((EnchantmentStorageMeta) itemMeta).addStoredEnchant(enchantment, level, true);
        }
        return this;
    }

    public ItemBuilder skullOwner(Player player) {
        return skullOwner(player.getUniqueId());
    }

    public ItemBuilder skullOwner(OfflinePlayer player) {
        return skullOwner(player.getUniqueId());
    }

    public ItemBuilder skullOwner(UUID uuid) {
        if (itemMeta instanceof SkullMeta) {
            ((SkullMeta) itemMeta).setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        }

        return this;
    }

    public ItemBuilder removePatterns() {
        if (itemMeta instanceof BannerMeta) {
            for (int i = 0; i < ((BannerMeta) itemMeta).numberOfPatterns(); i++) {
                ((BannerMeta) itemMeta).getPattern(i);
            }
        }

        return this;
    }

    public ItemBuilder clearNBT() {
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        for (NamespacedKey key : container.getKeys()) {
            container.remove(key);
        }
        return this;
    }

    public ItemBuilder skullOwner(String string) {
        if (itemMeta instanceof SkullMeta) {
            ((SkullMeta) itemMeta).setOwner(string);
        }

        return this;
    }

    public ItemBuilder potion(PotionEffectType type, int duration, int amp) {
        if (itemMeta instanceof PotionMeta) {
            ((PotionMeta) itemMeta).clearCustomEffects();
            ((PotionMeta) itemMeta).addCustomEffect(new PotionEffect(type, duration, amp), true);
        }

        return this;
    }

    public ItemBuilder potionColor(Color color) {
        if (itemMeta instanceof PotionMeta) {
            ((PotionMeta) itemMeta).setColor(color);
        }

        return this;
    }

    public ItemStack build() {
        if (lores != null) {
            LinkedList<String> lore = new LinkedList<>();
            if (itemMeta != null && itemMeta.getLore() != null) {
                lore.addAll(itemMeta.getLore());
            }

            for (LoreType loreType : loreOrder) {
                if (lores.containsKey(loreType)) {
                    LinkedList<String> strings = lores.get(loreType);
                    for (String line : strings) {
                        lore.add(C.translate(line));
                    }
                }
            }

            if (itemMeta != null) {
                itemMeta.setLore(lore);
            }
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemBuilder setCustomModelData(int value) {
        itemMeta.setCustomModelData(value);
        return this;
    }

    public ItemBuilder model(int value) {
        itemMeta.setCustomModelData(value);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder attackSpeed(double attackSpeed) {
        AttributeModifier a = new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, a);
        return this;
    }

    public ItemBuilder attackDamage(double attackDamage) {
        AttributeModifier a = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", attackDamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, a);
        return this;
    }

    public ItemBuilder armor(double armor) {
        AttributeModifier feet = new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier legs = new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        AttributeModifier chest = new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        AttributeModifier head = new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        if (itemStack.getType().name().contains("BOOTS"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, feet);

        if (itemStack.getType().name().contains("LEGGINGS"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, legs);

        if (itemStack.getType().name().contains("CHESTPLATE"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, chest);

        if (itemStack.getType().name().contains("HELMET"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, head);
        return this;
    }

    public ItemBuilder armorToughness(double armor) {
        AttributeModifier feet = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier legs = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        AttributeModifier chest = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        AttributeModifier head = new AttributeModifier(UUID.randomUUID(), "generic.armor_toughness", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        if (itemStack.getType().name().contains("BOOTS"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, feet);

        if (itemStack.getType().name().contains("LEGGINGS"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, legs);

        if (itemStack.getType().name().contains("CHESTPLATE"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, chest);

        if (itemStack.getType().name().contains("HELMET"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, head);
        return this;
    }

    public ItemBuilder armorKnockback(double armor) {
        AttributeModifier feet = new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier legs = new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        AttributeModifier chest = new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        AttributeModifier head = new AttributeModifier(UUID.randomUUID(), "generic.knockback_resistance", armor, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        if (itemStack.getType().name().contains("BOOTS"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, feet);

        if (itemStack.getType().name().contains("LEGGINGS"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, legs);

        if (itemStack.getType().name().contains("CHESTPLATE"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, chest);

        if (itemStack.getType().name().contains("HELMET"))
            itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, head);
        return this;
    }

    public ItemBuilder color(int r, int g, int b) {
        if (itemMeta instanceof LeatherArmorMeta) {
            LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemMeta;
            armorMeta.setColor(Color.fromRGB(r, g, b));
        }
        return this;
    }

    public ItemBuilder color(Color color) {
        if (itemMeta instanceof LeatherArmorMeta) {
            LeatherArmorMeta armorMeta = (LeatherArmorMeta) itemMeta;
            armorMeta.setColor(color);
        }
        return this;
    }
}
