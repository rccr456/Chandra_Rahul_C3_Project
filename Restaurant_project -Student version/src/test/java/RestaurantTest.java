import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void create_restaurant_for_testing()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    public int get_menu_size()
    {
        if(this.restaurant != null) {
            return this.restaurant.getMenu().size();
        }
        else
            return 0;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //1.Arrange
        // Automatic call to create_restaurant_for_testing();
        LocalTime mockedCurrentTime =  LocalTime.parse("11:00:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(mockedCurrentTime);

        //2.Act
        boolean result = spiedRestaurant.isRestaurantOpen();

        //3.Assert
        assertTrue(result);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //1.Arrange
        // Automatic call to create_restaurant_for_testing();
        LocalTime mockedCurrentTime =  LocalTime.parse("23:00:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(mockedCurrentTime);

        //2.Act
        boolean result = spiedRestaurant.isRestaurantOpen();

        //3.Assert
        assertFalse(result);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        //1.Arrange
        // Automatic call to create_restaurant_for_testing();

        //2.Act
        int initialMenuSize = get_menu_size();
        restaurant.addToMenu("Sizzling brownie",319);

        //3.Assert
        assertEquals(initialMenuSize+1, get_menu_size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        //1.Arrange
        // Automatic call to create_restaurant_for_testing();

        //2.Act
        int initialMenuSize = get_menu_size();
        restaurant.removeFromMenu("Vegetable lasagne");

        //3.Assert
        assertEquals(initialMenuSize-1, get_menu_size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        //1.Arrange
        // Automatic call to create_restaurant_for_testing();

        //2.Act

        //3.Assert
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void getOrderValue_should_return_order_Total_Cost_for_a_list_of_menu_item_names()
    {
        //1.Arrange
        // Automatic call to create_restaurant_for_testing();
        List<String> selectedItemNames = Arrays.asList("Sweet corn soup", "Vegetable lasagne" ) ;


        //2.Act
        int totalCost = restaurant.getOrderValue(selectedItemNames) ;

        //3.Assert
        assertEquals(388, totalCost);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}