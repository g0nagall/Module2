package shoppingCart;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void mainTest() {
        ShoppingCart.main(new String[]{"args"});
    }

    @Test
    void appendFormattedTest() {
        String testValue = "appendFormatted testing";

        StringBuilder sb = new StringBuilder();

        ShoppingCart.appendFormatted(sb, testValue, 0, 23);
        assertEquals(sb.toString(), "appendFormatted testing ");

        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, testValue, -1, 15);
        assertEquals(sb.toString(), "appendFormatted ");

        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, testValue, 1, 6);
        assertEquals(sb.toString(), "append ");
    }

    @Test
    void calculateDiscountTest() {
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 100));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.NEW, 25));
        assertEquals(60, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 100));
        assertEquals(55, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 50));
        assertEquals(50, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 2));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 1));
        assertEquals(3, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 35));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 900));
        assertEquals(0, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.REGULAR, 1));
        assertEquals(80, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 400));
        assertEquals(75, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 50));
        assertEquals(72, ShoppingCart.calculateDiscount(ShoppingCart.ItemType.SALE, 20));
    }

    @Test
    void formatTicketTest() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 0.99, 5, ShoppingCart.ItemType.NEW);
        cart.addItem("Banana", 20.00, 4, ShoppingCart.ItemType.SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, ShoppingCart.ItemType.SALE);
        cart.addItem("Nails", 2.00, 500, ShoppingCart.ItemType.REGULAR);
        String expectedValue = "# Item                          Price Quan. Discount   Total \n"
                + "------------------------------------------------------------\n"
                + "1 Apple                          $.99     5        -   $4.95 \n"
                + "2 Banana                       $20.00     4      50%  $40.00 \n"
                + "3 A long piece of toilet paper $17.20     1      70%   $5.16 \n"
                + "4 Nails                         $2.00   500      50% $500.00 \n"
                + "------------------------------------------------------------\n"
                + "4                                                    $550.11 ";

        assertEquals(expectedValue, cart.formatTicket());
    }
}