import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void load_restaurant_for_testing()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    public int get_restaurant_size()
    {
        if(service.getRestaurants() != null) {
            return service.getRestaurants().size();
        }
        else
            return 0;
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
         // 1.Arrange
         // Automatic call to load_restaurant_for_testing();

        //2. Act
        Restaurant searchedRestaurant = service.findRestaurantByName("Amelie's cafe");

        //3.Assert
        assertEquals(searchedRestaurant.getName(),restaurant.getName());
    }


    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        // 1.Arrange
        //Automatic call to load_restaurant_for_testing();

        //2. Act

        //3.Assert
        assertThrows(restaurantNotFoundException.class,()->{
        service.findRestaurantByName("My Cafe");
        });
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        // 1.Arrange
        //Automatic call to load_restaurant_for_testing();

        //2. Act
        int initialNumberOfRestaurants = get_restaurant_size();
        service.removeRestaurant("Amelie's cafe");

        //3.Assert
        assertEquals(initialNumberOfRestaurants-1, get_restaurant_size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        // 1.Arrange
        //Automatic call to load_restaurant_for_testing();

        //2.Act

        //3.Assert
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        // 1.Arrange
        //Automatic call to load_restaurant_for_testing();

        //2.Act
        int initialNumberOfRestaurants = get_restaurant_size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));

        //3.Assert
        assertEquals(initialNumberOfRestaurants + 1,get_restaurant_size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}