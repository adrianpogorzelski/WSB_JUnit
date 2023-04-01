package wsb.wsb_junit.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import wsb.wsb_junit.models.Restaurant;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantNameServicesTest {

    @InjectMocks
    RestaurantNameServices restaurantNameServices;

    Restaurant restaurant;

    @BeforeEach
    void setup() {
        restaurant = new Restaurant(1, "Restauracja", "Ulica", "Miasto");
    }

    @Test
    void getReversedName() {

        // Arrange

        // Act
        String result = restaurantNameServices.getReversedName(restaurant);

        // Assert
        assertEquals("ajcaruatseR", result);
    }

    @Test
    void getFormattedAddress() {
        String result = restaurantNameServices.getFormattedAddress(restaurant);
        assertEquals("Restauracja - Ulica, Miasto", result);
    }

    @ParameterizedTest
    @MethodSource("provider")
    void getFormattedAddress_parametrized(Restaurant restaurantUnderTest, String expectedAddress) {
        // Arrange

        // Act
        String result = restaurantNameServices.getFormattedAddress(restaurantUnderTest);

        // Assert
        assertEquals(expectedAddress, result);
    }

    public static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(new Restaurant(1, "A", "B", "C"), "A - B, C"),
                Arguments.of(new Restaurant(1, "1", "2", "3"), "1 - 2, 3"),
                Arguments.of(new Restaurant(1, "X", "Y", "Z"), "X - Y, Z")
        );
    }

    @Disabled
    @DisplayName("IS ODD TEST")
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5} )
    void isOdd(int number) {
        assertEquals(1, number % 2);
    }

}