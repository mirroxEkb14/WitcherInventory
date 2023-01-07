package inventory;

import model.characters.heroes.Geralt;
import model.items.weapons.SilverWeapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipmentTest {

    @Test
    void increaseVitality() {
        var equipment = Equipment.getInstance();
        equipment.increaseVitality(-20);
        assertEquals(equipment.getVitality(), Geralt.getMIN_HP());
    }

    @Test
    void setWeaponSlot() {
        var equipment = Equipment.getInstance();
        var weaponItem = SilverWeapon.AERONDIGHT;
        var slotIndex = equipment.getWeaponSlots().size(); // by 1 more than there are indexes

        assertDoesNotThrow(() -> equipment.setWeaponSlot(slotIndex, weaponItem));
    }
}